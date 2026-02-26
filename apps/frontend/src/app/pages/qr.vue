<script setup lang="ts">
import { useQRCode } from "@vueuse/integrations/useQRCode";
import { type } from "arktype";

const permission = usePermission("clipboard-read");
const inputUrl = ref();
const isValidUrl = computed(() => {
	const out = schema({ url: inputUrl.value });

	return !(out instanceof type.errors);
});

const url = ref();
const qrCode = computed(() => useQRCode(url.value));

const schema = type({
	url: /^https?:\/\/(?:www\.)?[-a-zA-Z0-9@:%._+~#=]{1,256}\.[a-zA-Z0-9()]{1,6}\b(?:[-a-zA-Z0-9()@:%_+.~#?&/=]*)$/,
});

const modal = useTemplateRef("modal");

function generateQr() {
	url.value = inputUrl.value;
	modal.value?.showModal();
}

async function pasteUrl() {
	inputUrl.value = await navigator.clipboard.readText();

	if (isValidUrl.value) {
		generateQr();
	}
}

function clearUrl() {
	inputUrl.value = "";
}

function closeModal() {
	modal.value?.close();
}

function downloadQr() {
	const a = document.createElement("a");
	a.href = qrCode.value.value;
	a.download = "qr.png";
	a.click();
}
</script>

<template>
	<dialog
		ref="modal"
		class="w-11/12 max-w-xl m-auto backdrop:bg-bg/50 shadow-xl bg-bg border-border border rounded-lg">
		<div class="bg-bg p-5 rounded-t-lg">
			<h1 class="text-fg text-lg font-mono font-bold">Your QR Code</h1>
			<div class="w-full flex justify-center py-5">
				<img :src="qrCode.value" />
			</div>
		</div>
		<div
			class="border-t border-t-border bg-bg-muted p-5 flex items-center justify-between font-mono">
			<button
				class="text-fg border-border border rounded-lg p-2 hover:(cursor-pointer bg-gray-200)"
				@click="closeModal">
				Close
			</button>
			<button
				@click="downloadQr"
				class="flex justify-center items-center gap-1 text-gray-100 p-2 border border-border rounded-lg bg-gray-1000 hover:(bg-gray-1000/90 cursor-pointer)">
				<i class="i-tabler-download"></i>Download
			</button>
		</div>
	</dialog>
	<div class="container-sm h-screen flex justify-center items-center flex-col gap-2">
		<div
			class="relative flex items-center border-2 border-border rounded-lg w-full max-w-2xl group focus-within:(border-border-active)">
			<div class="absolute left-0 flex items-center pointer-events-none p-2">
				<i class="i-tabler-link text-fg-muted group-focus-within:text-fg"></i>
			</div>
			<input
				type="text"
				placeholder="paste the link here"
				class="font-mono outline-none w-full py-2 px-8"
				spellcheck="false"
				autocomplete="off"
				v-model="inputUrl" />
			<button
				class="absolute right-0 flex items-center cursor-pointer p-2"
				v-if="inputUrl"
				@click="clearUrl">
				<i class="i-tabler-x text-fg-muted group-focus-within:text-fg"></i>
			</button>
		</div>
		<div
			class="flex flex-col sm:flex-row justify-between items-center w-full max-w-2xl font-mono">
			<p class="text-sm self-start text-fg-muted hidden sm:block">
				Only HTTP/HTTPS URL get supported
			</p>
			<div class="flex gap-2 w-full sm:w-auto">
				<button
					v-if="inputUrl && isValidUrl"
					@click="generateQr"
					class="w-full sm:w-auto p-2 bg-bg-muted border border-border rounded-lg flex justify-center items-center gap-1 duration-200 hover:(bg-gray-200 cursor-pointer)">
					<i class="i-tabler-qrcode"></i>generate
				</button>
				<button
					v-if="permission != 'denied' && !inputUrl"
					@click="pasteUrl"
					class="w-full sm:w-auto p-2 bg-bg-muted border border-border rounded-lg flex justify-center items-center gap-1 duration-200 hover:(bg-gray-200 cursor-pointer)">
					<i class="i-tabler-clipboard"></i>paste & generate
				</button>
			</div>
		</div>
	</div>
</template>
