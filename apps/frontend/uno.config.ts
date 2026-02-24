import { defineConfig, presetWind4 } from "unocss";

export default defineConfig({
	presets: [presetWind4({ preflights: { reset: true } })],
	theme: {
		font: {
			mono: "'Geist Mono', monospace",
			sans: "'Geist', system-ui, -apple-system, sans-serif",
		},
	},
});
