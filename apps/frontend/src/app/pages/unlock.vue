<script setup lang="ts">
// Utility page — keep out of the index (M1)
useSeoMeta({ robots: "noindex, follow" });

const route = useRoute();

const shortCode = route.query.c!.toString();

if (!shortCode) {
	await navigateTo("/");
}
</script>

<template>
	<UnlockForm :short-code />
</template>
