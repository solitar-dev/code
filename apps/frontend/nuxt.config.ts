export default defineNuxtConfig({
	modules: [
		"@nuxt/ui",
		"@nuxt/fonts",
		"@regle/nuxt",
		"@vueuse/nuxt",
		"@nuxt/image",
		"@nuxt/test-utils/module",
		"nuxt-og-image",
		"@nuxtjs/robots",
		"@nuxtjs/sitemap",
		"nuxt-seo-utils",
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
			titleTemplate: "%s %separator %siteName",
			templateParams: {
				separator: "|",
				siteName: "Solitar",
			},
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
	fonts: {
		families: [
			{
				name: "Geist",
				provider: "google",
				global: true,
			},
		],
	},
	ui: {
		fonts: false,
	},
	site: {
		name: "Solitar",
	},
	robots: {
		blockAiBots: true,
		blockNonSeoBots: true,
	},
	ogImage: {
		buildCache: true,
		compatibility: {
			runtime: {
				takumi: "wasm",
			},
		},
	},
	seo: {
		fallbackTitle: false,
	},
});
