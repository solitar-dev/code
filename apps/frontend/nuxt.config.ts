export default defineNuxtConfig({
	modules: [
		"@unocss/nuxt",
		"@nuxtjs/color-mode",
		"@regle/nuxt",
		"@vueuse/nuxt",
		"vue-sonner/nuxt",
		"@nuxt/image",
		"@nuxt/test-utils/module",
	],
	compatibilityDate: "2025-07-15",
	devtools: { enabled: true },
	css: ["~/assets/css/global.css"],
	runtimeConfig: {
		public: {
			siteUrl: "",
			apiBaseUrl: "",
		},
	},
	app: {
		head: {
			title: "Solitär",
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
	colorMode: {
		preference: "system",
		fallback: "light",
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
});
