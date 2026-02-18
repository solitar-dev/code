<script setup lang="ts">
import { useQRCode } from "@vueuse/integrations/useQRCode";

const { url } = defineProps<{
	url: string;
}>();

const qrcode = computed(() => {
	return useQRCode(url, { scale: 6 });
});
</script>

<template>
	<UModal title="Your QR code:">
		<template #body>
			<div class="flex items-center justify-center flex-col">
				<NuxtImg :src="qrcode.value" alt="QR" />
			</div>
		</template>
	</UModal>
</template>
