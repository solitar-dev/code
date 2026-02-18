<script setup lang="ts">
import type { SelectItem } from "@nuxt/ui";
import type { InferRegleRoot } from "@regle/core";

const { r$ } = defineProps<{ r$: InferRegleRoot<typeof useShortenForm> }>();

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
</script>

<template>
	<div class="w-full">
		<UCard class="mx-auto max-w-3xl">
			<UForm
				class="space-y-5"
				:schema="r$"
				:state="r$.$value"
				@submit="$emit('onSubmit')"
				action="">
				<UFormField label="Long URL" name="longUrl" required>
					<UInput
						type="url"
						placeholder="https://example.com/my/long/url"
						class="w-full"
						v-model="r$.$value.longUrl" />
				</UFormField>

				<UFormField label="Alias" name="alias" hint="Optional">
					<UInput
						type="text"
						placeholder="my-awesome-link"
						class="w-full"
						v-model="r$.$value.alias" />
				</UFormField>

				<div class="">
					<UCheckbox label="No expiration" v-model="r$.$value.neverExpire" />
				</div>

				<UFormField label="Time" v-if="!r$.$value.neverExpire" name="expireTime">
					<div class="flex flex-row gap-3">
						<UInputNumber v-model="r$.$value.expireTime" />
						<USelect v-model="r$.$value.expireUnit" :items="selectItems" />
					</div>
				</UFormField>

				<UButton class="justify-center w-full" size="lg" type="submit">Shorten</UButton>
			</UForm>
		</UCard>
	</div>
</template>
