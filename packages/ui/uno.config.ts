import { presetSolitar } from "@solitar/tooling-config/uno";
import { defineConfig, presetIcons } from "unocss";

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

	content: {
		pipeline: {
			include: [/\.(vue|svelte|[jt]sx|stories.ts|mdx?|astro|elm|php|phtml|html)($|\?)/],
		},
	},
});
