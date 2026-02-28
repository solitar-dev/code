<script setup lang="ts">
type Props = {
	subtitle?: string;
	description?: string;
};

defineProps<Props>();
</script>

<template>
	<div class="relative h-full w-full overflow-hidden">
		<img src="/og_template.png" alt="og" class="absolute z-1" width="1200px" height="630px" />

		<div class="relative flex flex-col justify-between my-12 mx-20 z-10">
			<h1 class="text-7xl text-[#EDEDED]" :style="{ fontFamily: 'Orbitron' }">Solitar</h1>

			<div class="flex flex-col" :style="{ fontFamily: 'Geist Mono' }">
				<h2 class="text-5xl text-[#EDEDED]" v-if="subtitle">{{ subtitle }}</h2>
				<h3 v-if="description" class="text-[#A0A0A0] text-3xl">
					{{ description }}
				</h3>
			</div>
		</div>
	</div>
</template>
