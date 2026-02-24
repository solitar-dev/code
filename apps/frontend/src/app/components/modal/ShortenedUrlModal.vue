<script setup lang="ts">
import { useQRCode } from "@vueuse/integrations/useQRCode";
import { useClipboard } from "@vueuse/core";

const { url } = defineProps<{ url: string }>();

const qrcode = computed(() => {
	return useQRCode(url, { scale: 6 });
});

const { copy } = useClipboard();
const toast = useToast();

function copyUrl() {
	copy(url);
	toast.add({ color: "success", title: "Copied" });
}
</script>

<template>
	<UModal>
		<template #title>
			<ULink :to="url" target="_blank" :active="true" :external="true">{{ url }}</ULink>
		</template>

		<template #body>
			<div class="flex flex-col items-center justify-center">
				<NuxtImg :src="qrcode.value" alt="QR" />
			</div>
		</template>

		<template #footer>
			<UButton label="Copy URL" @click="copyUrl" />
		</template>
	</UModal>
</template>
