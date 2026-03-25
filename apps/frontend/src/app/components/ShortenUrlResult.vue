<script setup lang="ts">
import { useQRCode } from "@vueuse/integrations/useQRCode";

type Props = {
	url: string;
	isOpen: boolean;
	openModal: () => void;
	closeModal: () => void;
};

const { url } = defineProps<Props>();

const qrCode = computed(() => useQRCode(url));
const { copy } = useClipboard();
const { $toast } = useNuxtApp();

function copyUrl() {
	copy(url);
	$toast.success("Copied");
}

function downloadQr() {
	const a = document.createElement("a");
	a.href = qrCode.value.value;
	a.download = "qr.png";
	a.click();
}
</script>

<template>
	<SModal :is-open :open-modal :close-modal class="w-11/12 max-w-xl font-mono">
		<template #title>
			<NuxtLink
				:to="url"
				class="text-fg text-lg font-mono font-bold underline-link"
				target="_blank"
				>{{ url }}<i class="i-tabler-external-link align-top"></i
			></NuxtLink>
		</template>

		<template #body>
			<div class="flex justify-center items-center">
				<div class="p-1 border border-border rounded-lg">
					<img :src="qrCode.value" />
				</div>
			</div>
		</template>

		<template #footer>
			<div class="flex items-center justify-between flex-col-reverse sm:flex-row gap-5">
				<SButton :label="$t('button.close')" variant="secondary" @click="closeModal" />
				<div class="flex items-center gap-2 flex-col-reverse sm:flex-row w-full sm:w-auto">
					<SButton
						:label="$t('button.download')"
						variant="default"
						@click="downloadQr"
						leading-icon="i-tabler-download" />
					<SButton
						:label="$t('button.copy_url')"
						variant="default"
						@click="copyUrl"
						leading-icon="i-tabler-copy" />
				</div>
			</div>
		</template>
	</SModal>
</template>
