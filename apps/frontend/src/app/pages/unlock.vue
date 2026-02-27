<script setup lang="ts">
const route = useRoute();

const shortCode = route.query.c!.toString();

if (!shortCode) {
	await navigateTo("/");
}
</script>

<template>
	<UnlockForm :short-code />
</template>
