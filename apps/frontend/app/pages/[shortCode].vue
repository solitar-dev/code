<script setup lang="ts">
import { joinURL } from "ufo";

const config = useRuntimeConfig();
const route = useRoute();

const shortCode = route.params.shortCode!.toString();

try {
	const data = await $fetch<{ originalUrl: string }>(
		joinURL(config.public.apiBaseUrl, "forward", shortCode),
		{
			method: "get",
		},
	);
	await navigateTo(data.originalUrl, {
		external: true,
		redirectCode: 302,
	});
} catch (e) {
	await navigateTo("/", { redirectCode: 302, replace: true });
}
</script>

<template>
	<p>Redirecting...</p>
</template>
