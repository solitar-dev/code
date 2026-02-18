<script setup lang="ts">
import type { ToasterProps } from "@nuxt/ui";

const toaster: ToasterProps = { position: "top-right" };

const config = useRuntimeConfig();
</script>

<template>
	<UApp :toaster="toaster">
		<UBanner
			v-if="config.public.preview"
			icon="i-tabler:alert-triangle"
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
