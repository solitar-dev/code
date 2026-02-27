export default defineNuxtConfig({
	modules: [
		"@unocss/nuxt",
		"@nuxt/fonts",
		"@nuxtjs/color-mode",
		"@vueuse/nuxt",
		"@regle/nuxt",
		"nuxt-og-image",
		"vue-sonner/nuxt",
	],
	css: ["~/assets/css/main.css"],
	srcDir: "src/app",
	dir: {
		public: "src/public",
	},
	components: [
		{
			path: "~/components",
			pathPrefix: false,
		},
	],
	fonts: {
		families: [
			{
				name: "Geist",
				weights: ["400", "500", "600", "700", "800"],
				global: true,
			},
			{
				name: "Geist Mono",
				weights: ["400", "500", "600", "700", "800"],
				global: true,
			},
			{
				name: "Orbitron",
				weights: ["400"],
				global: true,
			},
		],
	},
	// Runtime
	runtimeConfig: {
		public: {
			apiBaseUrl: "",
			site: {
				name: "",
				url: "",
				env: "",
			},
		},
	},
	// Build
	nitro: {
		prerender: {
			autoSubfolderIndex: false,
		},
		preset: "cloudflare-module",
		cloudflare: {
			nodeCompat: true,
		},
	},

	// Development
	devtools: { enabled: true },
	compatibilityDate: "2025-07-15",
});
