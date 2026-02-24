export default defineNuxtConfig({
	modules: ["@unocss/nuxt", "@nuxt/fonts"],
	srcDir: "src/app",
	dir: {
		public: "src/public",
	},

	devtools: { enabled: true },

	compatibilityDate: "2025-07-15",
});
