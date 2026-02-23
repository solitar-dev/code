<script setup lang="ts">
import { parseURL } from "ufo";

const route = useRoute();
const { $api } = useNuxtApp();
const urlRepository = repository($api);

const shortCode = route.params.shortCode!.toString();

const { data, error } = await useAsyncData(() => urlRepository.getLongUrl({ shortCode }));

if (error.value) {
	if (error.value.status == 401) {
		await navigateTo({
			path: "/unlock",
			query: {
				c: shortCode,
			},
		});
	} else {
		const e = error.value.data as { title: string; detail: string };

		throw createError({
			statusCode: error.value?.status || 500,
			statusMessage: e.title || "Internal Server Error",
			message: e.detail || "Failed to resolve short URL",
		});
	}
}

const originalUrl = data.value as string;
const { protocol } = parseURL(originalUrl);
const isSecure = protocol === "https:";

if (isSecure) {
	await navigateTo(originalUrl, {
		external: true,
		redirectCode: 302,
	});
}
</script>

<template>
	<div class="w-full h-screen grid place-items-center" v-if="!isSecure">
		<SecureWarning :url="originalUrl" />
	</div>
</template>
