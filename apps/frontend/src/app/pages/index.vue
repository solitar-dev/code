<script setup lang="ts">
defineOgImage("Basic.takumi");
useSeoMeta({
	title: $t("site.url_shortener.title"),
	ogTitle: $t("site.url_shortener.og_title"),
	twitterTitle: $t("site.url_shortener.og_title"),
	description: $t("site.url_shortener.description"),
	ogDescription: $t("site.url_shortener.description"),
	twitterDescription: $t("site.url_shortener.description"),
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
