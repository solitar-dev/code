<script setup lang="ts">
import type { NuxtError } from "#app";

const status = computed(() => props.error?.status || 500);

const props = defineProps({
	error: Object as () => NuxtError,
});
</script>

<template>
	<Header />
	<div class="h-screen grid place-items-center">
		<div class="font-mono text-center">
			<h1 class="text-9xl">{{ status }}</h1>
			<h2 class="text-2xl">{{ error?.statusText }}</h2>
		</div>
	</div>
	<Footer />
</template>
