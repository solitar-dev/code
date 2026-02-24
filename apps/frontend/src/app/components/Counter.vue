<script setup lang="ts">
const { $api } = useNuxtApp();
const statisticRepo = repository($api);

const { pending, data } = await useAsyncData(() => statisticRepo.getStatisticData());

const totalLinks = data.value?.totalLinks ?? 0;
const totalClicks = data.value?.totalClicks ?? 0;
</script>

<template>
	<div class="flex flex-col items-center justify-center sm:flex-row sm:gap-5">
		<template v-if="pending"
			><USkeleton class="h-7 w-full" /> <USkeleton class="h-7 w-full"
		/></template>

		<template v-else>
			<p class="text-center font-semibold sm:text-left sm:text-xl">
				<span class="text-2xl text-primary">{{ totalLinks }}</span> links created that have
				been accessed <span class="text-2xl text-primary">{{ totalClicks }}</span> times
			</p>
		</template>
	</div>
</template>
