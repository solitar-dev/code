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

			"red-100": "var(--red-100)",
			"red-200": "var(--red-200)",
			"red-300": "var(--red-300)",
			"red-400": "var(--red-400)",
			"red-500": "var(--red-500)",
			"red-600": "var(--red-600)",
			"red-700": "var(--red-700)",
			"red-800": "var(--red-800)",
			"red-900": "var(--red-900)",
			"red-1000": "var(--red-1000)",

			"amber-100": "var(--amber-100)",
			"amber-200": "var(--amber-200)",
			"amber-300": "var(--amber-300)",
			"amber-400": "var(--amber-400)",
			"amber-500": "var(--amber-500)",
			"amber-600": "var(--amber-600)",
			"amber-700": "var(--amber-700)",
			"amber-800": "var(--amber-800)",
			"amber-900": "var(--amber-900)",
			"amber-1000": "var(-amber-1000)",

			"green-100": "var(--green-100)",
			"green-200": "var(--green-200)",
			"green-300": "var(--green-300)",
			"green-400": "var(--green-400)",
			"green-500": "var(--green-500)",
			"green-600": "var(--green-600)",
			"green-700": "var(--green-700)",
			"green-800": "var(--green-800)",
			"green-900": "var(--green-900)",
			"green-1000": "var(--green-1000)",
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
