<script setup lang="ts">
import { useQRCode } from "@vueuse/integrations/useQRCode";

const modal = useTemplateRef("modal");
const url = ref("");
const qrCode = useQRCode(url);
const { copy } = useClipboard();
const { $toast } = useNuxtApp();

function close() {
	modal.value?.close();
}

function copyUrl() {
	copy(url.value);
	$toast.success("Copied");
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
			<NuxtLink
				:to="url"
				class="text-fg text-lg font-mono font-bold underline-link"
				target="_blank"
				>{{ url }}<i class="i-tabler-external-link align-top"></i
			></NuxtLink>
			<div class="w-full flex justify-center py-5">
				<div class="p-1 border border-border rounded-lg">
					<img :src="qrCode" />
				</div>
			</div>
		</div>
		<div
			class="border-t border-t-border bg-bg-muted p-5 flex items-center justify-between font-mono flex-col-reverse sm:flex-row gap-5">
			<button
				class="text-fg border-border border rounded-lg p-2 hover:(cursor-pointer bg-gray-200) w-full sm:w-auto bg-bg"
				@click="close">
				Close
			</button>
			<div class="flex items-center gap-2 flex-col-reverse sm:flex-row w-full sm:w-auto">
				<button
					@click="downloadQr"
					class="w-full flex justify-center items-center gap-1 text-gray-100 p-2 border border-border rounded-lg bg-gray-1000 hover:(bg-gray-1000/90 cursor-pointer)">
					<i class="i-tabler-download"></i>Download
				</button>
				<button
					@click="copyUrl"
					class="w-full flex justify-center items-center gap-1 text-gray-100 p-2 border border-border rounded-lg bg-gray-1000 hover:(bg-gray-1000/90 cursor-pointer)">
					<i class="i-tabler-copy"></i>Copy URL
				</button>
			</div>
		</div>
	</dialog>
</template>
