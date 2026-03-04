<script setup lang="ts">
import type { NuxtError } from "#app";

const { error } = defineProps({
	error: Object as () => NuxtError,
});

const status = computed(() => error?.status || 500);
const statusText = computed(() => error?.statusText);
</script>

<template>
	<Header />
	<div class="h-screen grid place-items-center">
		<div class="font-mono text-center">
			<h1 class="text-9xl">{{ status }}</h1>
			<h2 class="text-2xl">{{ statusText }}</h2>
		</div>
	</div>
	<Footer />
</template>
