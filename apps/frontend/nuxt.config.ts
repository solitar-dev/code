export default defineNuxtConfig({
	modules: [
		"@nuxt/ui",
		"@regle/nuxt",
		"@vueuse/nuxt",
		"@nuxt/image",
		"@nuxt/test-utils/module",
		"@nuxtjs/seo",
	],
	compatibilityDate: "2025-07-15",
	devtools: { enabled: true },
	css: ["~/assets/css/global.css"],
	runtimeConfig: {
		public: {
			siteUrl: "",
			apiBaseUrl: "",
			preview: true,
		},
	},
	app: {
		head: {
			title: "Solitar",
			meta: [{ name: "description", content: "A minimal URL shortener and QR generator" }],
			link: [
				{
					rel: "icon",
					type: "image/svg+xml",
					href: "/favicon-light.svg",
					media: "(prefers-color-scheme: light)",
				},
				{
					rel: "icon",
					type: "image/svg+xml",
					href: "/favicon-dark.svg",
					media: "(prefers-color-scheme: dark)",
				},
			],
		},
	},
	nitro: {
		prerender: {
			autoSubfolderIndex: false,
		},
		preset: "cloudflare-module",
		cloudflare: {
			nodeCompat: true,
		},
	},
	robots: {
		blockAiBots: true,
		blockNonSeoBots: true,
	},
});
