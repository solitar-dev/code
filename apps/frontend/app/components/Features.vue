<script setup lang="ts">
type Feature = {
	icon: string;
	title: string;
	description: string;
};

const features: Feature[] = [
	{
		icon: "i-ph:lightning-light",
		title: "Blazingly Fast",
		description: "Instant redirects with zero latency. No middleman pages or ad-interstitials.",
	},
	{
		icon: "i-ph:chart-bar-light",
		title: "Privacy-Focused Analytics",
		description: "Track click counts and referrers without harvesting personal user data.",
	},
	{
		icon: "i-ph:code-light",
		title: "Developer First",
		description: "Powerful REST API to automate link creation within your own applications.",
	},
	{
		icon: "i-ph:clock-countdown-light",
		title: "Flexible Expiry",
		description:
			"Set links to live forever or schedule them to self-destruct after a specific time or click count.",
	},
	{
		icon: "i-material-symbols-light-shield-outline",
		title: "Secure",
		description: "HTTPS enforcement for every destination link.",
	},
	{
		icon: "i-tabler-brand-open-source",
		title: "Open Source",
		description: "Fully transparent redirection logic. Audit the code, host it yourself.",
	},
];
</script>

<template>
	<div class="grid gap-5 grid-cols-1 sm:grid-cols-2 md:grid-cols-3">
		<div class="border border-gray-400 rounded-lg bg-bg-100" v-for="it in features">
			<div class="p-5 space-y-sm">
				<div class="">
					<i :class="[it.icon, 'text-primary text-xl']" />
				</div>
				<div class="">
					<h1 class="text-primary font-semibold">{{ it.title }}</h1>
					<p class="text-secondary">{{ it.description }}</p>
				</div>
			</div>
		</div>
	</div>
</template>
