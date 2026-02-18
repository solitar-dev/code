<script setup lang="ts">
import type { InferRegleRoot } from "@regle/core";

const { r$ } = defineProps<{ r$: InferRegleRoot<typeof useQrCodeForm> }>();
</script>

<template>
	<div class="max-w-2xl mx-auto">
		<UPageCard title="QR Code Generator">
			<UForm
				:schema="r$"
				:state="r$.$value"
				@submit="$emit('onSubmit')"
				class="space-y-5"
				action="">
				<UFormField label="URL" name="url" required>
					<UInput
						type="url"
						placeholder="https://example.com/my/long/url"
						class="w-full"
						v-model="r$.$value.url" />
				</UFormField>

				<UButton class="justify-center w-full" size="lg" type="submit">Generate</UButton>
			</UForm>
		</UPageCard>
	</div>
</template>
