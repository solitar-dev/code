<script setup lang="ts">
import { useQRCode } from "@vueuse/integrations/useQRCode";
import { useClipboard } from "@vueuse/core";

const { copy } = useClipboard();
const { url } = defineProps<{
	url: string;
	isPending: boolean;
}>();

const showUrl = ref(false);
const qrcode = computed(() => {
	return useQRCode(() => url, { scale: 6 });
});

function copyUrl() {
	copy(url);
}

function toggleShowUrl() {
	showUrl.value = !showUrl.value;
}
</script>

<template>
	<div class="w-full border border-gray-400 rounded-lg bg-bg-100">
		<div class="flex justify-center items-center p-5" v-if="isPending">
			<p class="text-primary">Shortening...</p>
		</div>
		<div class="p-7 text-secondary flex flex-col gap-5" v-if="!isPending">
			<p class="uppercase text-secondary text-lg font-semibold text-center">
				Your shortened link
			</p>

			<div class="flex flex-col gap-5">
				<div class="flex flex-col sm:flex-row justify-between items-center gap-5">
					<p
						class="text-primary text-2xl sm:text-3xl font-semibold text-teal-700 hover:(underline cursor-pointer decoration-2)">
						<NuxtLink :href="url" target="_blank">{{ url }}</NuxtLink>
						<i class="i-carbon:launch text-lg align-top" />
					</p>
					<div class="flex flex-row gap-3">
						<button
							class="p-3 border border-gray-400 rounded-lg text-secondary hover:(text-primary border-gray-600 cursor-pointer) duration-200"
							@click="copyUrl">
							<i class="i-carbon:copy" />
							Copy</button
						><button
							class="p-3 border border-gray-400 rounded-lg text-secondary hover:(text-primary border-gray-600 cursor-pointer) duration-200"
							@click="toggleShowUrl">
							<i class="i-carbon:qr-code" />
							QR
						</button>
					</div>
				</div>
				<div class="flex items-center justify-center flex-col gap-5" v-if="showUrl">
					<NuxtImg :src="qrcode.value" alt="QR" />
				</div>
			</div>
		</div>
	</div>
</template>
