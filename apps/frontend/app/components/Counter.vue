<script setup lang="ts">
import { joinURL } from "ufo";

const config = useRuntimeConfig();

const { data, pending } = await useLazyFetch<{
	totalClicks: number;
	totalLinks: number;
}>(joinURL(config.public.apiBaseUrl, "statistics"), {
	method: "get",
});

const totalLinks = computed(() => data.value?.totalLinks || 0);
const totalClicks = computed(() => data.value?.totalClicks || 0);
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
