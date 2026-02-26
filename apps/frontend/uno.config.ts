import { defineConfig, presetWind4, presetIcons, transformerVariantGroup } from "unocss";

export default defineConfig({
	presets: [
		presetWind4({ preflights: { reset: true } }),
		presetIcons({
			extraProperties: {
				display: "inline-block",
			},
			scale: 1.2,
			warn: true,
		}),
	],
	transformers: [transformerVariantGroup()],
	theme: {
		font: {
			mono: "'Geist Mono', monospace",
			sans: "'Geist', system-ui, -apple-system, sans-serif",
			brand: "'Orbitron', sans-serif",
		},
		colors: {
			bg: {
				DEFAULT: "var(--bg)",
				muted: "var(--bg-muted)",
			},
			fg: {
				DEFAULT: "var(--gray-1000)",
				muted: "var(--gray-900)",
			},
			border: {
				DEFAULT: "var(--gray-400)",
				hover: "var(--gray-500)",
				active: "var(--gray-600)",
			},

			"gray-100": "var(--gray-100)",
			"gray-200": "var(--gray-200)",
			"gray-300": "var(--gray-300)",
			"gray-400": "var(--gray-400)",
			"gray-500": "var(--gray-500)",
			"gray-600": "var(--gray-600)",
			"gray-700": "var(--gray-700)",
			"gray-800": "var(--gray-800)",
			"gray-900": "var(--gray-900)",
			"gray-1000": "var(--gray-1000)",
		},
	},
	shortcuts: {
		container: "max-w-6xl mx-auto px-4 sm:px-6",
		"container-sm": "max-w-5xl mx-auto px-4 sm:px-6",

		"underline-link":
			"underline decoration-gray-1000/20 hover:decoration-gray-1000 decoration-offset-3 duration-200",

		"btn-primary":
			"bg-gray-1000 text-gray-100 hover:(cursor-pointer bg-gray-1000/90) rounded-lg",
	},
});
