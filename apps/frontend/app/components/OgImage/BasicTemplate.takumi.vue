<script setup lang="ts">
const props = withDefaults(
	defineProps<{
		title?: string;
		description?: string;
	}>(),
	{
		title: "Solitar",
		description: "A minimal URL shortener and QR generator",
	},
);
</script>

<template>
	<div class="bg-black w-full h-full flex items-center justify-center flex-col">
		<div class="w-full h-full p-16 flex items-start justify-center flex-col gap-28">
			<p class="text-white text-6xl">{{ title }}</p>
			<p class="text-[#a1a1a1] text-5xl">{{ description }}</p>
		</div>
	</div>
</template>
