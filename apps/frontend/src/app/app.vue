<script setup lang="ts">
import type { ToasterProps } from "@nuxt/ui";

defineOgImageComponent("BasicOG");

const toaster: ToasterProps = { position: "top-right" };

const siteConfig = useSiteConfig();
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
