<script setup lang="ts">
defineOgImage("Basic.takumi", {
	subtitle: "./settings",
	description: "customize your solitar experience",
});
useSeoMeta({
	title: $t("site.settings.title"),
	ogTitle: $t("site.settings.og_title"),
	twitterTitle: $t("site.settings.og_title"),
	description: $t("site.settings.description"),
	ogDescription: $t("site.settings.description"),
	twitterDescription: $t("site.settings.description"),
});
</script>

<template>
	<div class="container-sm max-w-2xl h-screen font-mono">
		<div class="py-12 mt-12">
			<h1 class="text-3xl font-bold">settings</h1>
			<p class="text-fg-muted lowercase">{{ $t("site.settings.description") }}</p>
		</div>
		<div class="flex flex-col gap-12">
			<ThemeSetting />
		</div>
	</div>
</template>
