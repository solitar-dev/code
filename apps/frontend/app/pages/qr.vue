<script setup lang="ts">
import { QrCodeDisplay } from "#components";

defineOgImageComponent("BasicTemplate", {
	title: "QR Code Generator",
	description: "Generate a QR code for your URL",
});

const { r$ } = useQrCodeForm();
const overlay = useOverlay();

const modal = overlay.create(QrCodeDisplay);

async function onSubmit() {
	modal.open({
		url: r$.$value.url,
	});
}
</script>

<template>
	<UContainer>
		<div class="w-full mt-24">
			<QrCodeForm :r$="r$" @on-submit="onSubmit" />
		</div>
	</UContainer>
</template>
