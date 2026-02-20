<script setup lang="ts">
import * as v from "valibot";
import type { FormSubmitEvent, SelectItem } from "@nuxt/ui";

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
	expireUnit: v.pipe(
		v.string(),
		v.picklist(["second", "minute", "hour", "day", "week", "month", "year"]),
	),
});

type Schema = v.InferOutput<typeof schema>;

const state = ref({
	longUrl: "",
	alias: undefined,
	neverExpire: true,
	expireTime: 30,
	expireUnit: "second",
});

const toast = useToast();
async function onSubmit(event: FormSubmitEvent<Schema>) {
	toast.add({ title: "Success", description: "The form has been submitted.", color: "success" });
	console.log(event.data);
}
</script>

<template>
	<div class="w-full">
		<UCard class="mx-auto max-w-3xl">
			<UForm class="space-y-5" :schema="schema" :state="state" @submit="onSubmit" action="">
				<UFormField label="Long URL" name="longUrl" required>
					<UInput
						type="url"
						placeholder="https://example.com/my/long/url"
						class="w-full"
						v-model="state.longUrl" />
				</UFormField>

				<UFormField label="Alias" name="alias" hint="Optional">
					<UInput
						type="text"
						placeholder="my-awesome-link"
						class="w-full"
						v-model="state.alias" />
				</UFormField>

				<div class="">
					<UCheckbox label="No expiration" v-model="state.neverExpire" />
				</div>

				<UFormField label="Time" v-if="!state.neverExpire" name="expireTime">
					<div class="flex flex-row gap-3">
						<UInputNumber v-model="state.expireTime" />
						<USelect v-model="state.expireUnit" :items="selectItems" />
					</div>
				</UFormField>

				<UButton class="justify-center w-full" size="lg" type="submit">Shorten</UButton>
			</UForm>
		</UCard>
	</div>
</template>
