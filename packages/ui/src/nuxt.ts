import { addComponentsDir, createResolver, defineNuxtModule } from "@nuxt/kit";

export default defineNuxtModule({
	meta: {
		name: "@solitar/ui",
		configKey: "solitarui",
		compatibility: {
			nuxt: ">=4.0.0",
		},
	},
	setup: () => {
		const resolver = createResolver(import.meta.url);

		addComponentsDir({
			path: resolver.resolve("./components"),
			prefix: "S",
			pathPrefix: false,
			global: true,
		});
	},
});
