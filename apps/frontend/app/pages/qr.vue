<script setup lang="ts">
const { $toast } = useNuxtApp();
const { r$ } = useQrGenerateForm();

const url = ref("");

async function handleSubmit() {
	const { valid, data } = await r$.$validate();

	if (!valid) return;

	url.value = data.url;
	$toast.success("Your QR code has been generated");
}
</script>

<template>
	<div class="w-11/12 sm:max-w-4xl mx-auto space-y-3xl">
		<QrGenerateForm :r$="r$" @handle-submit="handleSubmit" />
		<QrGenerateResult :url="url" v-if="url" />
	</div>
</template>
