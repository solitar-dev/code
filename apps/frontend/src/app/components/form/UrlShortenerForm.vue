<script setup lang="ts">
import * as v from "valibot";
import type { FormSubmitEvent, SelectItem } from "@nuxt/ui";
import ShortenedUrlModal from "../modal/ShortenedUrlModal.vue";
import { joinURL } from "ufo";

const selectItems: SelectItem[] = [
	{
		label: "seconds",
		value: "second",
	},
	{
		label: "minutes",
		value: "minute",
	},
	{
		label: "hours",
		value: "hour",
	},
	{
		label: "days",
		value: "day",
	},
	{
		label: "weeks",
		value: "week",
	},
	{
		label: "months",
		value: "month",
	},
	{
		label: "years",
		value: "year",
	},
];

const schema = v.object({
	longUrl: v.pipe(
		v.string(),
		v.regex(
			/^https?:\/\/(?:www\.)?[-a-zA-Z0-9@:%._+~#=]{1,256}\.[a-zA-Z0-9()]{1,6}\b(?:[-a-zA-Z0-9()@:%_+.~#?&/=]*)$/,
			"Invalid URL",
		),
	),
	alias: v.optional(
		v.pipe(
			v.string(),
			v.regex(/^[a-zA-Z0-9_-]*$/, "Invalid alias"),
			v.minLength(7, "Alias must be at least 7 characters"),
			v.maxLength(255, "Alias is too long"),
		),
	),
	neverExpire: v.boolean(),
	expireTime: v.number(),
	expireUnit: v.pipe(v.string(), v.picklist(expireUnits)),
});

const state = ref({
	longUrl: "",
	alias: undefined,
	neverExpire: true,
	expireTime: 30,
	expireUnit: "second",
});

const { $api } = useNuxtApp();
const urlRepository = repository($api);
const overlay = useOverlay();
const toast = useToast();
const siteConfig = useSiteConfig();

const modal = overlay.create(ShortenedUrlModal);

async function onSubmit(event: FormSubmitEvent<v.InferOutput<typeof schema>>) {
	event.preventDefault();

	const { longUrl, alias, neverExpire } = event.data;

	const body: UrlShortenerBody = {
		url: longUrl,
		alias,
		...(!neverExpire && {
			expiresTime: generateExpireTime(event.data.expireTime, event.data.expireUnit),
		}),
	};

	try {
		const data = await urlRepository.shortenUrl(body);

		modal.open({ url: joinURL(siteConfig.url, data.shortCode) });
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
	}
}
</script>

<template>
	<div class="w-full">
		<UCard class="mx-auto max-w-3xl">
			<UForm class="space-y-5" :schema="schema" :state="state" @submit="onSubmit">
				<UFormField label="Long URL" name="longUrl" required>
					<UInput
						type="url"
						placeholder="https://example.com/my/long/url"
						class="w-full"
						size="xl"
						v-model="state.longUrl" />
				</UFormField>

				<UFormField label="Alias" name="alias" hint="Optional">
					<UInput
						type="text"
						placeholder="my-awesome-link"
						class="w-full"
						size="xl"
						v-model="state.alias" />
				</UFormField>

				<div class="">
					<UCheckbox label="No expiration" v-model="state.neverExpire" size="xl" />
				</div>

				<UFormField label="Time" v-if="!state.neverExpire" name="expireTime">
					<div class="flex flex-row gap-3">
						<UInputNumber v-model="state.expireTime" size="xl" />
						<USelect v-model="state.expireUnit" :items="selectItems" size="xl" />
					</div>
				</UFormField>

				<UButton class="justify-center w-full" size="lg" type="submit" label="Shorten" />
			</UForm>
		</UCard>
	</div>
</template>
