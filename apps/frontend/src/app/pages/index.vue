<script setup lang="ts">
defineOgImage("Basic.takumi");

useSeoMeta({
	title: "url shortener - solitar",
	ogTitle: "url shortener - solitar",
	twitterTitle: "url shortener - solitar",
	description: "Create short URLS for your website and social media pages in seconds",
	ogDescription: "Create short URLS for your website and social media pages in seconds",
	twitterDescription: "Create short URLS for your website and social media pages in seconds",
});
</script>

<template>
	<div class="container-sm flex flex-col gap-12">
		<div class="w-full min-h-5xl md:min-h-3xl flex items-center">
			<Hero />
		</div>
		<Features />
		<div class="min-h-12"></div>
	</div>
</template>
