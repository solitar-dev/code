<script setup lang="ts">
import type { ToasterProps } from "@nuxt/ui";

const toaster: ToasterProps = { position: "top-right" };

const siteConfig = useSiteConfig();

useSeoMeta({
	title: "Solitar",
	description: "The only URL toolbox that you need",
	ogTitle: "Solitar",
	ogDescription: "The only URL toolbox that you need",
	ogImage: "/og.png",
	twitterCard: "summary_large_image",
});
</script>

<template>
	<UApp :toaster="toaster">
		<UBanner
			v-if="siteConfig.env === 'staging'"
			icon="i-carbon:warning-alt"
			title="Your are viewing Solitar's staging environment"
			color="warning" />
		<Header />

		<UMain>
			<NuxtPage />
		</UMain>

		<USeparator />
		<Footer />
	</UApp>
</template>
