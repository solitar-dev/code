<script setup lang="ts">
import { QrCodeModal } from "#components";
import type { FormSubmitEvent } from "@nuxt/ui";
import * as v from "valibot";

const schema = v.object({
	url: v.pipe(v.string("URL is required"), v.url("Invalid URL")),
});

const state = ref({
	url: undefined,
});

const overlay = useOverlay();
const modal = overlay.create(QrCodeModal);

async function onSubmit(event: FormSubmitEvent<v.InferOutput<typeof schema>>) {
	event.preventDefault();

	modal.open({
		url: event.data.url,
	});
}
</script>

<template>
	<div class="max-w-2xl mx-auto">
		<UPageCard title="QR Code Generator">
			<UForm :schema="schema" :state="state" @submit="onSubmit" class="space-y-5">
				<UFormField label="URL" name="url" required>
					<UInput
						type="url"
						placeholder="https://example.com/my/long/url"
						class="w-full"
						size="xl"
						v-model="state.url" />
				</UFormField>

				<UButton class="justify-center w-full" size="lg" type="submit" label="Generate" />
			</UForm>
		</UPageCard>
	</div>
</template>
