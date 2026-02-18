<script setup lang="ts">
import { joinURL } from "ufo";
import { ShortenUrlDisplay } from "#components";

const { r$ } = useShortenForm();
const config = useRuntimeConfig();
const toast = useToast();
const overlay = useOverlay();

const modal = overlay.create(ShortenUrlDisplay);

const isPending = ref(false);

async function onSubmit() {
	isPending.value = true;

	const { longUrl, alias, neverExpire, expireTime, expireUnit } = r$.$value;

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

		const shortenedUrl = joinURL(config.public.siteUrl, res.shortCode);
		modal.open({ url: shortenedUrl });
	} catch (error: any) {
		if (!error.data) {
			toast.add({
				title: "Uh oh! Something went wrong.",
				color: "error",
			});
		} else {
			toast.add({
				title: error.data.error,
				description: error.data.message,
				color: "error",
			});
		}
	} finally {
		isPending.value = false;
	}
}
</script>

<template>
	<UPageHero
		description="The simplest way to create short links and QR codes. No signup required — just paste, shorten, and share. "
		orientation="horizontal">
		<template #title>
			<p>The only <span class="text-primary">tool</span> you need</p>
		</template>

		<template #footer>
			<Counter />
		</template>

		<ShortenUrlForm :r$="r$" @on-submit="onSubmit" />
	</UPageHero>

	<USeparator />

	<Features />
</template>
