<script setup lang="ts">
import type { ToasterProps } from "@nuxt/ui";

const toaster: ToasterProps = { position: "top-right" };
</script>

<template>
	<UApp :toaster="toaster">
		<Header />

		<UMain>
			<NuxtPage />
		</UMain>

		<USeparator />
		<Footer />
	</UApp>
</template>
