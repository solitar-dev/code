<script setup lang="ts">
import { joinURL } from "ufo";

const { $toast } = useNuxtApp();
const config = useRuntimeConfig();
const { r$ } = useShortenForm();

const shortenedUrl = ref("");
const isPending = ref(false);

async function handleSubmit() {
	const { valid, data } = await r$.$validate();

	if (!valid) return;

	const { longUrl, alias, neverExpire, expireTime, expireUnit } = data;

	isPending.value = true;

	try {
		const res = await $fetch<{
			originalUrl: string;
			shortCode: string;
		}>(joinURL(config.public.apiBaseUrl, "create"), {
			method: "POST",
			body: {
				url: longUrl,
				...(alias != null && alias.length > 0 && { alias }),
				...(!neverExpire && { expiresTime: generateExpireTime(expireTime, expireUnit) }),
			},
		});

		shortenedUrl.value = joinURL(config.public.siteUrl, res.shortCode);
	} catch (error: any) {
		if (!error.data) {
			$toast.error("Something went wrong!");
		} else {
			$toast.error(error.data.error, {
				description: error.data.message,
			});
		}
	} finally {
		isPending.value = false;
	}
}
</script>

<template>
	<div class="w-11/12 sm:max-w-4xl mx-auto space-y-3xl">
		<Header />
		<ShortenForm :r$="r$" @handle-submit="handleSubmit" />
		<ShortenResult :url="shortenedUrl" :is-pending="isPending" v-if="shortenedUrl" />
		<Features />
	</div>
</template>
