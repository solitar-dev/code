<script setup lang="ts">
const { $api } = useNuxtApp();
const statisticRepo = repository($api);

const { pending, data } = await useAsyncData(() => statisticRepo.getStatisticData());

const totalLinks = data.value?.totalLinks ?? 0;
const totalClicks = data.value?.totalClicks ?? 0;
</script>

<template>
	<div class="flex flex-col sm:flex-row justify-center items-center sm:gap-5">
		<template v-if="pending"
			><USkeleton class="w-full h-7" /> <USkeleton class="w-full h-7"
		/></template>

		<template v-else>
			<p class="sm:text-xl font-semibold text-center sm:text-left">
				<span class="text-2xl text-primary">{{ totalLinks }}</span> links created that have
				been accessed <span class="text-2xl text-primary">{{ totalClicks }}</span> times
			</p>
		</template>
	</div>
</template>
