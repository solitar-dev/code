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

	devtools: { enabled: true },

	compatibilityDate: "2025-07-15",
});
