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
	schemaOrg: {
		enabled: false,
	},
});
