<script setup lang="ts">
import type { NavigationMenuItem } from "@nuxt/ui";

const route = useRoute();

const items = computed<NavigationMenuItem[]>(() => [
	{
		label: "Terms & Conditions",
		to: "/contents?type=terms",
		active: route.query.type == "terms",
	},
	{
		label: "Privacy Policy",
		to: "/contents?type=privacy",
		active: route.query.type == "privacy",
	},
	{
		label: "Spam Policy",
		to: "/contents?type=spam",
		active: route.query.type == "spam",
	},
	{
		label: "Report Abuse",
		to: "/contents?type=report",
		active: route.query.type == "report",
	},
]);
</script>

<template>
	<UFooter>
		<template #left>
			<p class="text-sm text-muted">
				<ULink
					class="text-default"
					to="https://github.com/solitar-dev/code/blob/main/LICENSE"
					target="_blank"
					>MIT</ULink
				>
				{{ new Date().getFullYear() }} © Toby Nguyen
			</p>
		</template>

		<UNavigationMenu
			:items="items"
			variant="link"
			orientation="horizontal"
			class="hidden sm:flex" />
		<UNavigationMenu
			:items="items"
			variant="link"
			orientation="vertical"
			class="flex sm:hidden"
			:ui="{
				linkLabel: 'mx-auto',
			}" />

		<template #right>
			<UButton
				icon="i-carbon:logo-github"
				color="neutral"
				variant="ghost"
				to="https://github.com/solitar-dev/code"
				target="_blank"
				aria-label="GitHub" />
		</template>
	</UFooter>
</template>
