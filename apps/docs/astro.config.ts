import { defineConfig } from "astro/config";
import starlight from "@astrojs/starlight";

export default defineConfig({
	site: "https://docs.solitar.link",

	integrations: [
		starlight({
			title: "Solitar",
			social: [
				{ icon: "github", label: "GitHub", href: "https://github.com/withastro/starlight" },
			],
			sidebar: [
				{
					label: "Legal",
					autogenerate: { directory: "legal" },
				},
			],
			head: [
				{
					tag: "link",
					attrs: {
						rel: "icon",
						type: "image/svg+xml",
						href: "/icon-light.svg",
						media: "(prefers-color-scheme: light)",
					},
				},
				{
					tag: "link",
					attrs: {
						rel: "icon",
						type: "image/svg+xml",
						href: "/icon-dark.svg",
						media: "(prefers-color-scheme: dark)",
					},
				},
				{
					tag: "link",
					attrs: {
						rel: "apple-touch-icon",
						sizes: "180x180",
						href: "/apple-icon.png",
					},
				},
			],
		}),
	],

	outDir: "./build",
});
