import { defineConfig, presetWind4, presetIcons } from "unocss";

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
	theme: {
		font: {
			mono: "'Geist Mono', monospace",
			sans: "'Geist', system-ui, -apple-system, sans-serif",
		},
	},
});
