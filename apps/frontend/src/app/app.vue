<script setup lang="ts">
const route = useRoute();
const SITE_URL = "https://solitar.link";
// Self-referencing canonical (H1) — hardcoded origin so it's always absolute
// and correct regardless of request host.
const canonicalUrl = computed(() => {
	const path = route.path === "/" ? "/" : route.path.replace(/\/+$/, "");
	return `${SITE_URL}${path}`;
});

// Canonical link (H1)
useHead({
	link: [{ rel: "canonical", href: canonicalUrl }],
});

// Global OG defaults (M2)
useSeoMeta({
	ogType: "website",
	ogSiteName: "Solitar",
	ogUrl: canonicalUrl,
});

// Global structured data (H2): Organization + WebSite
useSchemaOrg([
	defineOrganization({
		name: "Solitar",
		logo: "/icon-light.svg",
		sameAs: ["https://github.com/solitar-dev/code"],
	}),
	defineWebSite({
		name: "Solitar",
	}),
]);
</script>

<template>
	<Toaster
		position="top-right"
		:toast-options="{
			unstyled: true,
			classes: {
				error: 'bg-red-800 rounded-lg p-3',
				success: 'bg-green-800 rounded-lg p-3',
				icon: 'hidden',
			},
		}" />
	<Header />
	<NuxtPage />
	<Footer />
</template>
