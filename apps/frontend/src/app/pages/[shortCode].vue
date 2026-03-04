<script setup lang="ts">
import { parseURL } from "ufo";

const route = useRoute();
const { $api } = useNuxtApp();
const urlRepository = repository($api);

const shortCode = route.params.shortCode!.toString();

const { data, error } = await useAsyncData(() => urlRepository.getLongUrl({ shortCode }));

if (error.value) {
	const { code } = error.value.data as { code: ErrorCode };

	switch (code) {
		case ErrorCode.URL_NOT_FOUND:
			throw createError({
				status: error.value.status,
				statusText: $t("error.url_not_found"),
			});
		case ErrorCode.URL_EXPIRED:
			throw createError({
				status: error.value.status,
				statusText: $t("error.url_expired"),
			});
		case ErrorCode.URL_DISABLED:
			throw createError({
				status: error.value.status,
				statusText: $t("error.url_disabled"),
			});
		case ErrorCode.PASSWORD_PROTECTED:
			await navigateTo({
				path: "/unlock",
				query: {
					c: shortCode,
				},
			});
		default:
			throw createError({ status: 500, statusText: $t("error.default") });
	}
}

const originalUrl = data.value as string;
const { protocol } = parseURL(originalUrl);
const isSecure = protocol === "https:";

if (isSecure) {
	await navigateTo(originalUrl, {
		external: true,
		redirectCode: 301,
	});
}
</script>

<template>
	<NotSecureWarning :url="originalUrl" v-if="!isSecure" />
</template>
