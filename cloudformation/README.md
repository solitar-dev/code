# Solitar Production CloudFormation

This folder contains a first production AWS deployment for Solitar without Route 53. DNS remains at the current DNS provider and is updated manually after the AWS stacks are ready.

## Architecture

- Frontend: Nuxt SSR on AWS Amplify Hosting.
- Backend: Spring Boot native image on ECS Fargate behind an HTTPS Application Load Balancer.
- Data: Aurora Serverless v2 PostgreSQL, encrypted, private subnets, writer and reader instances.
- Cache: ElastiCache Valkey replication group, TLS in transit, encryption at rest, auth token in Secrets Manager.
- Security: ACM for the API certificate, WAFv2 on the ALB, KMS, Secrets Manager, private ECS networking.
- CI/CD: GitHub Actions OIDC role. No long-lived AWS access keys are required in GitHub.

## Files

```text
cloudformation/
  README.md
  parameters/
    prod.example.json
  templates/
    00-foundation.yaml
    10-network.yaml
    20-security.yaml
    30-data.yaml
    40-backend.yaml
    50-frontend.yaml
    60-cicd.yaml
```

Deploy in this order:

1. `solitar-prod-foundation`
2. `solitar-prod-network`
3. `solitar-prod-security`
4. `solitar-prod-data`
5. `solitar-prod-backend`
6. `solitar-prod-frontend`
7. `solitar-prod-cicd`

## Important Bootstrap Notes

The security stack requests an ACM DNS-validated certificate for `api.solitar.link`. Because this phase does not use Route 53, CloudFormation will remain in `CREATE_IN_PROGRESS` until the ACM validation CNAME is added manually at the current DNS provider.

CloudFormation cannot output the ACM validation CNAME before the certificate resource completes. While the stack is waiting, get the record with:

```powershell
$certArn = aws acm list-certificates `
  --certificate-statuses PENDING_VALIDATION `
  --query "CertificateSummaryList[?DomainName==''api.solitar.link''].CertificateArn | [0]" `
  --output text `
  --region ap-southeast-1

aws acm describe-certificate `
  --certificate-arn $certArn `
  --query "Certificate.DomainValidationOptions[].ResourceRecord" `
  --output table `
  --region ap-southeast-1
```

The backend stack creates ECR and the ECS task definition. It defaults `DeployService=false` so the stack can complete before any image exists. After the first backend stack deploy, push the backend image to the new ECR repository, then update the backend stack with `DeployService=true`.

## Validate Templates

```powershell
$region = "ap-southeast-1"
Get-ChildItem .\cloudformation\templates\*.yaml | ForEach-Object {
  aws cloudformation validate-template `
    --template-body "file://$($_.FullName)" `
    --region $region
}
```

## Parameter Manifest

`parameters/prod.example.json` is a manifest for all stacks, not a direct AWS CLI parameter file. Copy it to `prod.json`, replace placeholders such as `GitHubAccessToken`, and adjust CIDRs/capacity before deployment.

PowerShell helper to write a per-stack parameter file:

```powershell
$config = Get-Content .\cloudformation\parameters\prod.json | ConvertFrom-Json
$region = $config.Region

function Export-StackParameters($key) {
  $path = Join-Path $env:TEMP "$key.parameters.json"
  $config.Stacks.$key.Parameters | ConvertTo-Json -Depth 10 | Set-Content $path -Encoding UTF8
  return $path
}
```

## Review Change Sets

Use `CREATE` for a new stack and `UPDATE` for an existing stack.

```powershell
function New-SolitarChangeSet($key, $type = "CREATE") {
  $stack = $config.Stacks.$key
  $params = Export-StackParameters $key
  $changeSetName = "preview-$(Get-Date -Format yyyyMMddHHmmss)"

  aws cloudformation create-change-set `
    --stack-name $stack.Name `
    --change-set-name $changeSetName `
    --change-set-type $type `
    --template-body "file://$((Resolve-Path $stack.Template).Path)" `
    --parameters "file://$params" `
    --capabilities CAPABILITY_NAMED_IAM `
    --region $region

  aws cloudformation wait change-set-create-complete `
    --stack-name $stack.Name `
    --change-set-name $changeSetName `
    --region $region

  aws cloudformation describe-change-set `
    --stack-name $stack.Name `
    --change-set-name $changeSetName `
    --region $region
}
```

Execute only after review:

```powershell
aws cloudformation execute-change-set `
  --stack-name solitar-prod-network `
  --change-set-name preview-YYYYMMDDHHMMSS `
  --region ap-southeast-1
```

## Backend Image Bootstrap

After `40-backend.yaml` is deployed with `DeployService=false`:

```powershell
$repoUri = aws cloudformation describe-stacks `
  --stack-name solitar-prod-backend `
  --query "Stacks[0].Outputs[?OutputKey==''BackendRepositoryUri''].OutputValue | [0]" `
  --output text `
  --region ap-southeast-1

$registry = ($repoUri -split "/")[0]
aws ecr get-login-password --region ap-southeast-1 | docker login --username AWS --password-stdin $registry

docker build -f .\apps\backend\Dockerfile -t "${repoUri}:latest" .
docker push "${repoUri}:latest"
```

Then set the backend stack parameter `DeployService=true` and deploy an update change set. The ECS service will start with `DesiredCount=2`, no public IP, and the ALB target group health check path from `HealthCheckPath`.

## Manual DNS

API:

- Validate the ACM certificate CNAME first.
- After the backend stack outputs `LoadBalancerDnsName`, create `api.solitar.link` as a CNAME to that ALB DNS name.
- If the DNS provider supports ALIAS/ANAME, use that only for apex-style records. For `api.solitar.link`, CNAME is sufficient.

Frontend:

- With `CreateCustomDomain=false`, use the `AmplifyDefaultUrl` output for smoke testing.
- To attach `solitar.link` and `www.solitar.link`, update the frontend stack with `CreateCustomDomain=true`, then add the Amplify certificate/app CNAME records shown by the stack output and Amplify console.

Before cutover, lower DNS TTL to 60-300 seconds. Raise TTL again after smoke tests pass.

## CI/CD Role

`60-cicd.yaml` creates or reuses the GitHub OIDC provider and creates an IAM role limited to:

- `solitar-dev/code`
- branch `prod`
- environment `prod`

The role can push to the backend ECR repository, register ECS task definitions, pass only the ECS task roles created by the backend stack, update only the production ECS service, read CloudFormation outputs, and start Amplify jobs.

## Pre-Go-Live App Work

These are still required before production traffic:

- Move Spring Boot from `4.1.0-RC1` to a stable release.
- Add readiness/liveness endpoints and change `HealthCheckPath` away from `/statistics`.
- Add a `test` script for `@solitar/backend`.
- Rate-limit `/forward` in the app in addition to WAF.
- Increment click count only after the password is correct.
- Ensure `.env.local` is ignored and not tracked.
- Run Flyway migrations as an ECS one-off task or pipeline step before rollout.
- Verify Nuxt produces Amplify Hosting SSR output under `apps/frontend/.amplify-hosting`. The template sets `NITRO_PRESET=aws_amplify`, but the current Nuxt config also contains a Cloudflare preset, so this should be tested before cutover.

## Acceptance Checks

Infrastructure:

- ECS tasks have no public IP.
- ALB listener is HTTPS and uses the issued ACM certificate.
- Target group has two healthy targets after `DeployService=true`.
- ECS can connect to Aurora with SSL.
- ECS can connect to Valkey over TLS.
- Secrets are not exposed in CloudFormation outputs.
- WAF managed rules and rate rule are attached to the ALB.

Application smoke tests:

```text
GET  https://api.solitar.link/statistics
POST https://api.solitar.link/create
GET  https://api.solitar.link/{shortCode}
```

Also confirm password-protected URLs do not increment clicks when the password is wrong, and the frontend calls `https://api.solitar.link`.



