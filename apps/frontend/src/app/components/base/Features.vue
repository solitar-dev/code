<script setup lang="ts">
import type { PageFeatureProps } from "@nuxt/ui";

const features: PageFeatureProps[] = [
	{
		title: "Blazingly Fast",
		description: "Instant redirects with zero latency. No middleman pages or ad-interstitials.",
		icon: "i-carbon:lightning",
		orientation: "vertical",
	},
	{
		title: "Privacy-Focused Analytics",
		description: "Track click counts and referrers without harvesting personal user data.",
		icon: "i-carbon:ibm-planning-analytics",
		orientation: "vertical",
	},
	{
		title: "Developer First",
		description: "Powerful REST API to automate link creation within your own applications.",
		icon: "i-carbon:code",
		orientation: "vertical",
	},
	{
		title: "Flexible Expiry",
		description:
			"Set links to live forever or schedule them to self-destruct after a specific time.",
		icon: "i-carbon:time",
		orientation: "vertical",
	},
	{
		title: "Security Verification",
		description:
			"Warns users when they access websites that do not comply with security standards.",
		icon: "i-carbon:security",
		orientation: "vertical",
	},
	{
		title: "Open Source",
		description: "Fully transparent redirection logic. Audit the code, host it yourself.",
		icon: "i-tabler:brand-open-source",
		orientation: "vertical",
	},
];
</script>

<template>
	<UPageSection title="Features" :features="features" class="bg-muted" />
</template>
