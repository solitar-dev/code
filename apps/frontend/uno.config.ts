import { defineConfig, presetIcons, transformerVariantGroup } from "unocss";
import { presetSolitar } from "@solitar/tooling-config/uno";

export default defineConfig({
	presets: [
		presetSolitar,
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

			primary: "#8473ff",
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
