<script setup lang="ts">
import type { NavigationMenuItem } from "@nuxt/ui";

const route = useRoute();

const items = computed<NavigationMenuItem[]>(() => [
	{
		label: "Shorten URL",
		to: "/",
		icon: "i-carbon:link",
		active: route.path === "/",
	},
	{
		label: "Generate QR",
		to: "/qr",
		icon: "i-carbon:qr-code",
		active: route.path === "/qr",
	},
	{
		label: "API",
		to: "https://docs.solitar.link",
		icon: "i-carbon:api",
		target: "_blank",
	},
]);
</script>

<template>
	<UHeader mode="slideover">
		<template #title>
			<p class="text 2xl font-brand font-normal">Solitär</p>
		</template>

		<UNavigationMenu :items="items" />

		<template #right>
			<UButton
				icon="i-carbon:logo-github"
				color="neutral"
				variant="ghost"
				to="https://github.com/solitar-dev/code"
				target="_blank"
				aria-label="GitHub" />

			<UColorModeButton />
		</template>

		<template #body>
			<UNavigationMenu :items="items" orientation="vertical" class="-mx-2.5" />
		</template>
	</UHeader>
</template>
