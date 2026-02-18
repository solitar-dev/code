<script setup lang="ts">
import { joinURL, parseURL } from "ufo";

const config = useRuntimeConfig();
const route = useRoute();

const shortCode = route.params.shortCode!.toString();

const { data, error } = await useFetch<{ originalUrl: string }>(
	joinURL(config.public.apiBaseUrl, "forward", shortCode),
	{ method: "get", retry: 1 },
);

if (error.value) {
	throw createError({
		statusCode: error.value?.statusCode || 500,
		statusMessage: error.value?.data?.error || "Internal Server Error",
		message: error.value?.data?.message || "Failed to resolve short URL",
	});
}

const originalUrl = data.value?.originalUrl!;
const { protocol } = parseURL(originalUrl);
const isSecure = protocol === "https:";

if (isSecure) {
	await navigateTo(originalUrl, {
		external: true,
		redirectCode: 302,
	});
}

const forceHttps = () => {
	const secureUrl = originalUrl.replace(/^http:/, "https:");
	navigateTo(secureUrl, { external: true });
};

const acceptRisk = () => {
	navigateTo(originalUrl, { external: true });
};
</script>

<template>
	<div class="w-full h-screen grid place-items-center" v-if="!isSecure">
		<div class="flex flex-col justify-center items-center gap-5">
			<UIcon name="i-tabler-alert-triangle" class="size-12 text-warning" />
			<UPageCard class="max-w-lg" :reverse="false">
				<template #title>
					<p>Insecure Connection Warning</p>
				</template>

				<template #description>
					<div class="flex flex-col gap-3">
						<p>
							The link you're about to visit does
							<span class="font-bold">not use HTTPS</span>, which means your
							connection is <span class="font-bold">not encrypted</span>. Your data
							could be intercepted by third parties.
						</p>
						<UCard>
							<template #header>
								<p class="text-warning">{{ originalUrl }}</p>
							</template>
						</UCard>
						<ULink
							class="text-left"
							to="https://www.cloudflare.com/learning/ssl/why-is-http-not-secure/"
							:external="true"
							target="_blank"
							>What is HTTPS and why is it important?</ULink
						>
					</div>
				</template>

				<template #footer>
					<div class="flex flex-row gap-3">
						<UButton
							label="Enforce HTTPS"
							icon="i-tabler-lock"
							@click="forceHttps"
							class="hover:cursor-pointer" />
						<UButton
							label="Accept Risk & Continue"
							variant="ghost"
							color="error"
							@click="acceptRisk"
							class="hover:cursor-pointer" />
					</div>
				</template>
			</UPageCard>
		</div>
	</div>
</template>
