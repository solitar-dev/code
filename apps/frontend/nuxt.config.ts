export default defineNuxtConfig({
	modules: ["@nuxt/ui", "@vueuse/nuxt", "@nuxt/image", "@nuxtjs/seo"],
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
		},
	},
	// Deploy
	nitro: {
		prerender: {
			autoSubfolderIndex: false,
		},
		preset: "cloudflare-module",
		cloudflare: {
			nodeCompat: true,
		},
	},
	// SEO
	robots: {
		blockAiBots: true,
		blockNonSeoBots: true,
	},
	// Development
	compatibilityDate: "2025-07-15",
	devtools: { enabled: true },
});
