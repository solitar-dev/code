<script setup lang="ts">
type Feature = {
	title: string;
	description: string;
};

const features: Feature[] = [
	{
		title: "Blazingly Fast",
		description: "Instant redirects with zero latency. No middleman pages or ad-interstitials.",
	},
	{
		title: "Privacy-Focused Analytics",
		description: "Track click counts and referrers without harvesting personal user data.",
	},
	{
		title: "Developer First",
		description: "Powerful REST API to automate link creation within your own applications.",
	},
	{
		title: "Flexible Expiry",
		description:
			"Set links to live forever or schedule them to self-destruct after a specific time.",
	},
	{
		title: "Security Verification",
		description:
			"Warns users when they access websites that do not comply with security standards.",
	},
	{
		title: "Open Source",
		description: "Fully transparent redirection logic. Audit the code, host it yourself.",
	},
];
</script>

<template>
	<div class="flex flex-col gap-12">
		<div class="flex items-center md:items-end gap-2 justify-center flex-col md:flex-row">
			<h2 class="text-3xl font-semibold">What comes with Solitar?</h2>
			<p class="text-fg-muted text-lg">Everything you need when work with URLs</p>
		</div>
		<div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-5">
			<div
				class="border border-border rounded-lg p-5 flex flex-col gap-1 hover:(border-border-hover bg-gray-100/20) duration-200"
				v-for="feature in features">
				<div class="">
					<h3 class="font-extrabold text-lg">{{ feature.title }}</h3>
				</div>
				<div class="">
					<p class="text-fg-muted">{{ feature.description }}</p>
				</div>
			</div>
		</div>
	</div>
</template>
