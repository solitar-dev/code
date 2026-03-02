<script setup lang="ts">
type Feature = {
	title: string;
	description: string;
};

const features: Feature[] = [
	{
		title: "feature.item.speed.title",
		description: "feature.item.speed.description",
	},
	{
		title: "feature.item.analytics.title",
		description: "feature.item.analytics.description",
	},
	{
		title: "feature.item.api.title",
		description: "feature.item.api.description",
	},
	{
		title: "feature.item.expiration.title",
		description: "feature.item.expiration.description",
	},
	{
		title: "feature.item.security.title",
		description: "feature.item.security.description",
	},
	{
		title: "feature.item.open_source.title",
		description: "feature.item.open_source.description",
	},
];
</script>

<template>
	<div class="flex flex-col gap-12">
		<div class="flex items-center md:items-end gap-2 justify-center flex-col md:flex-row">
			<h2 class="text-3xl font-semibold">{{ $t("feature.title") }}</h2>
			<p class="text-fg-muted text-lg">{{ $t("feature.subtitle") }}</p>
		</div>
		<div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-5">
			<div
				class="border border-border rounded-lg p-5 flex flex-col gap-1 hover:(border-border-hover bg-gray-100/20) duration-200"
				v-for="feature in features">
				<div class="">
					<h3 class="font-extrabold text-lg">{{ $t(feature.title) }}</h3>
				</div>
				<div class="">
					<p class="text-fg-muted">{{ $t(feature.description) }}</p>
				</div>
			</div>
		</div>
	</div>
</template>
