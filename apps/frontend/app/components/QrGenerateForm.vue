<script setup lang="ts">
import type { InferRegleRoot } from "@regle/core";

defineProps<{ r$: InferRegleRoot<typeof useQrGenerateForm> }>();
</script>

<template>
	<div class="w-full border border-gray-400 rounded-lg mt-9xl">
		<div class="p-7 text-secondary flex flex-col gap-5">
			<p class="text-3xl text-center font-semibold text-primary">QR Code Generator</p>
			<div class="space-y-1">
				<label for="url" class="flex gap-1 text-primary"
					><i class="i-material-symbols-light:link text-xl" />URL</label
				>
				<input
					type="url"
					:class="[
						r$.url.$errors[0] != null
							? 'border-red-400 focus:border-red-600'
							: 'border-gray-400 focus:border-gray-600',
						'focus:(outline-none) w-full [&:not(:placeholder-shown)]:text-primary border bg-bg-100 p-3 rounded-lg',
					]"
					id="url"
					name="url"
					v-model="r$.$value.url"
					placeholder="https://example.com" />
				<p class="text-secondary text-red-900">{{ r$.url.$errors[0] }}</p>
			</div>
			<div class="">
				<button
					type="button"
					class="p-3 rounded-lg bg-teal-700 hover:(bg-teal-800 cursor-pointer) text-dark-gray-1000 font-semibold w-full duration-200"
					@click="$emit('handleSubmit')">
					Generate
				</button>
			</div>
		</div>
	</div>
</template>
