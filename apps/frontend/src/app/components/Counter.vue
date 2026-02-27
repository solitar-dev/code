<script setup lang="ts">
const { $api } = useNuxtApp();
const statisticRepo = repository($api);

const { pending, data } = await useAsyncData(() => statisticRepo.getStatisticData());

const totalLinks = computed(() => data.value?.totalLinks ?? 0);
const totalClicks = computed(() => data.value?.totalClicks ?? 0);
</script>

<template>
	<div class="">
		<div class="w-full bg-gray-200 h-7 rounded-lg" v-if="pending" />
		<p class="font-semibold text-lg" v-else>
			<span class="text-2xl text-primary">{{ totalLinks }}</span> links created that have been
			accessed <span class="text-2xl text-primary">{{ totalClicks }}</span> times
		</p>
	</div>
</template>
