export default defineNuxtConfig({
	modules: [
		"@unocss/nuxt",
		"@nuxt/fonts",
		"@nuxtjs/color-mode",
		"@vueuse/nuxt",
		"@regle/nuxt",
		"@nuxtjs/sitemap",
		"nuxt-schema-org",
		"nuxt-og-image",
		"vue-sonner/nuxt",
		"@nuxtjs/i18n",
		"@solitar/ui/nuxt",
	],
	css: ["@solitar/assets/solitar.css", "~/assets/css/main.css"],
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
		defaults: {
			formats: ["ttf"],
		},
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
	// Internationalization
	i18n: {
		restructureDir: "./src/i18n",
		defaultLocale: "en",
		// no_prefix: locale lives in a cookie, no /vi/ path — keeps the
		// root /[shortCode] redirect route untouched.
		strategy: "no_prefix",
		locales: [
			{ code: "en", name: "English", file: "en.json" },
			{ code: "vi", name: "Tiếng Việt", file: "vi.json" },
		],
		// Detect browser language on first visit, then persist the choice.
		detectBrowserLanguage: {
			useCookie: true,
			cookieKey: "i18n_redirected",
			redirectOn: "root",
		},
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
	// SEO site config (consumed by nuxt-site-config — pulled in by sitemap/schema/og).
	// Single fixed domain — absolute URL so sitemap + schema resolve correctly.
	site: {
		url: "https://solitar.link",
		name: "Solitar",
	},
	// Global <html lang> (H3). No title template is applied (seo-utils intentionally
	// not used), so page titles render exactly as authored (H4).
	app: {
		head: {
			htmlAttrs: { lang: "en" },
		},
	},
	// Sitemap (C1): single flat /sitemap.xml (one locale — skip i18n index split);
	// only public pages, exclude utility/dynamic routes.
	sitemap: {
		autoI18n: false,
		exclude: ["/settings", "/unlock"],
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
