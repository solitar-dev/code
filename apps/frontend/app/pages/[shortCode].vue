<script setup lang="ts">
import { joinURL } from "ufo";

const config = useRuntimeConfig();
const route = useRoute();

const shortCode = route.params.shortCode!.toString();

const { data, error } = await useFetch<{ originalUrl: string }>(
	joinURL(config.public.apiBaseUrl, "forward", shortCode),
	{ method: "get" },
);

if (data.value) {
	await navigateTo(data.value.originalUrl, {
		external: true,
		redirectCode: 302,
	});
}

if (error) {
	throw createError({ status: error.value?.status, message: error.value?.data.message });
}
</script>

<template>
	<p>Redirecting...</p>
</template>
