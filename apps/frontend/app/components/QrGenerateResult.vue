<script setup lang="ts">
import { useQRCode } from "@vueuse/integrations/useQRCode";

const { url } = defineProps<{
	url: string;
}>();

const qrcode = computed(() => {
	return useQRCode(() => url, { scale: 6 });
});
</script>

<template>
	<div class="w-full border border-gray-400 rounded-lg bg-bg-100">
		<div class="p-7 text-secondary flex flex-col gap-5">
			<p class="uppercase text-secondary text-lg font-semibold text-center">Your QR</p>
			<div class="flex items-center justify-center flex-col gap-5">
				<NuxtImg :src="qrcode.value" alt="QR" />
			</div>
		</div>
	</div>
</template>
