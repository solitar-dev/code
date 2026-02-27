export default defineNuxtConfig({
	modules: ["@unocss/nuxt", "@nuxt/fonts", "@nuxtjs/color-mode", "@vueuse/nuxt", "@regle/nuxt"],
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
