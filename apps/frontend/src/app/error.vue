<script setup lang="ts">
import type { NuxtError } from "#app";

const props = defineProps<{
	error: NuxtError;
}>();
</script>

<template>
	<UApp>
		<Header />
		<UError :error="error" />
		<Footer />
	</UApp>
</template>
