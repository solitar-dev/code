<script setup lang="ts">
import type { ToasterProps } from "@nuxt/ui";

const toaster: ToasterProps = { position: "top-right" };

const runtimeConfig = useRuntimeConfig();
</script>

<template>
	<UApp :toaster="toaster">
		<UBanner
			v-if="runtimeConfig.public.preview"
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
