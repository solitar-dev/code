<script setup lang="ts">
import { useQRCode } from "@vueuse/integrations/useQRCode";
import { type } from "arktype";

defineOgImage("Basic.takumi", { subtitle: "./qr", description: "Generate QR code for url" });
useSeoMeta({
	title: $t("site.qr_generator.title"),
	ogTitle: $t("site.qr_generator.og_title"),
	twitterTitle: $t("site.qr_generator.og_title"),
	description: $t("site.qr_generator.description"),
	ogDescription: $t("site.qr_generator.description"),
	twitterDescription: $t("site.qr_generator.description"),
});

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

function generateQr() {
	url.value = inputUrl.value;
	openModal();
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

function downloadQr() {
	const a = document.createElement("a");
	a.href = qrCode.value.value;
	a.download = "qr.png";
	a.click();
}

const { isOpen, closeModal, openModal } = useModal();
</script>

<template>
	<SModal
		:is-open
		:close-modal
		:open-modal
		title="Your QR Code:"
		class="w-11/12 max-w-xl font-mono">
		<template #body>
			<div class="flex justify-center items-center">
				<div class="p-1 border border-border rounded-lg">
					<img :src="qrCode.value" />
				</div>
			</div>
		</template>
		<template #footer>
			<div class="flex items-center justify-between font-mono">
				<SButton label="Close" @click="closeModal" />
				<SButton label="Download" leading-icon="i-tabler-download" @click="downloadQr" />
			</div>
		</template>
	</SModal>

	<div class="container-sm max-w-2xl h-screen flex justify-center items-center flex-col gap-2">
		<div
			class="relative flex items-center border-2 border-border rounded-lg w-full group focus-within:(border-border-active)">
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
		<div class="flex flex-col sm:flex-row justify-between items-center w-full font-mono">
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
