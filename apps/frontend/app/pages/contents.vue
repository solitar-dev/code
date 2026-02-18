<script setup lang="ts">
const route = useRoute();

const availableRoutes = new Map([
	["terms", "Terms & Conditions"],
	["privacy", "Privacy Policy"],
	["spam", "Spam Report"],
	["report", "Report Abuse"],
]);

const type = computed(() => {
	const currentQuery = Array.isArray(route.query.type) ? route.query.type[0] : route.query.type;

	if (!currentQuery || !availableRoutes.get(currentQuery)) {
		return "terms";
	}

	return currentQuery;
});

const items = computed(() => [
	[
		{
			icon: "i-tabler-file-description",
			label: availableRoutes.get("terms"),
			active: type.value === "terms",
			to: "?type=terms",
		},
		{
			icon: "i-tabler-lock",
			label: availableRoutes.get("privacy"),
			active: type.value === "privacy",
			to: "?type=privacy",
		},
		{
			icon: "i-tabler-flag-exclamation",
			label: availableRoutes.get("spam"),
			active: type.value === "spam",
			to: "?type=spam",
		},
		{
			icon: "i-tabler-message-report",
			label: availableRoutes.get("report"),
			active: type.value === "report",
			to: "?type=report",
		},
	],
]);
</script>

<template>
	<UContainer>
		<UPage>
			<template #left>
				<UPageAside><UNavigationMenu orientation="vertical" :items="items" /></UPageAside>
			</template>
			<UPageHeader :title="availableRoutes.get(type)" />
			<Terms v-if="type == 'terms'" />
			<Privacy v-if="type == 'privacy'" />
			<Spam v-if="type == 'spam'" />
			<Report v-if="type == 'report'" />
		</UPage>
	</UContainer>
</template>
