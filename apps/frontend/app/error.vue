<script setup lang="ts">
import type { NuxtError } from "#app";

const props = defineProps({
	error: Object as () => NuxtError,
});

const handleError = () => clearError({ redirect: "/" });
</script>

<template>
	<div class="">
		<Navbar />
		<div class="grid place-items-center w-full h-screen">
			<div class="flex justify-center items-center gap-8 flex-col">
				<p class="text-center text-7xl text-primary font-bold">{{ error?.status }}</p>
				<p class="text-center text-xl text-primary">{{ error?.message }}</p>
				<button
					@click="handleError"
					class="p-3 border border-rounded-lg border-gray-400 text-center text-secondary hover:(text-primary cursor-pointer border-gray-600) duration-300">
					Back to Home
				</button>
			</div>
		</div>
		<Footer />
	</div>
</template>
