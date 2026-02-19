export default defineNuxtConfig({
	modules: ["@nuxt/ui"],
	css: ["~/assets/css/main.css"],
	srcDir: "src/app",
	dir: {
		public: "src/public",
	},
	// Development
	compatibilityDate: "2025-07-15",
	devtools: { enabled: true },
});
