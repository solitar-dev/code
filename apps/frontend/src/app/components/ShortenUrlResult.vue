<script setup lang="ts">
import { useQRCode } from "@vueuse/integrations/useQRCode";

const modal = useTemplateRef("modal");
const url = ref("");
const qrCode = useQRCode(url);

function close() {
	modal.value?.close();
}

function downloadQr() {
	const a = document.createElement("a");
	a.href = qrCode.value;
	a.download = "qr.png";
	a.click();
}

defineExpose({
	open: (data: { url: string }) => {
		url.value = data.url;
		modal.value?.showModal();
	},
});
</script>

<template>
	<dialog
		ref="modal"
		class="w-11/12 max-w-xl m-auto backdrop:bg-bg/50 shadow-xl bg-bg border-border border rounded-lg">
		<div class="bg-bg p-5 rounded-t-lg">
			<NuxtLink :to="url" class="text-fg text-lg font-mono font-bold underline-link"
				>{{ url }}<i class="i-tabler-external-link align-top"></i
			></NuxtLink>
			<div class="w-full flex justify-center py-5">
				<img :src="qrCode" />
			</div>
		</div>
		<div
			class="border-t border-t-border bg-bg-muted p-5 flex items-center justify-between font-mono">
			<button
				class="text-fg border-border border rounded-lg p-2 hover:(cursor-pointer bg-gray-200)"
				@click="close">
				Close
			</button>
			<button
				@click="downloadQr"
				class="flex justify-center items-center gap-1 text-gray-100 p-2 border border-border rounded-lg bg-gray-1000 hover:(bg-gray-1000/90 cursor-pointer)">
				<i class="i-tabler-download"></i>Download
			</button>
		</div>
	</dialog>
</template>
