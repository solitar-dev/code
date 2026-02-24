<script setup lang="ts">
import type { FormSubmitEvent, SelectItem } from "@nuxt/ui";
import { ShortenedUrlModal } from "#components";
import { joinURL } from "ufo";
import { type } from "arktype";

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

const schema = type({
	longUrl: type(
		/^https?:\/\/(?:www\.)?[-a-zA-Z0-9@:%._+~#=]{1,256}\.[a-zA-Z0-9()]{1,6}\b(?:[-a-zA-Z0-9()@:%_+.~#?&/=]*)$/,
	).describe("a valid URL"),
	"alias?": "'' | 7 <= string <= 255",
	"password?": "'' | 3 <= string <= 255",
	neverExpire: "boolean",
	expireTime: "number",
	expireUnit: "'second' | 'minute' | 'hour' | 'day' | 'week' | 'month' | 'year'",
});

type Schema = typeof schema.infer;

const state = ref<Schema>({
	longUrl: "",
	alias: "",
	password: "",
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

async function onSubmit(event: FormSubmitEvent<Schema>) {
	event.preventDefault();

	const { longUrl, alias, neverExpire, password } = event.data;

	const body: UrlShortenerBody = {
		url: longUrl,
		alias: alias === "" ? undefined : alias,
		password: password === "" ? undefined : password,
		...(!neverExpire && {
			expireTime: generateExpireTime(event.data.expireTime, event.data.expireUnit),
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
				title: error.data.title,
				description: error.data.detail,
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

				<UFormField label="Password" name="password" hint="Optional">
					<UInput type="password" class="w-full" size="xl" v-model="state.password" />
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
