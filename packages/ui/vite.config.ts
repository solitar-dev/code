import vue from "@vitejs/plugin-vue";
import UnoCSS from "unocss/vite";
import { defineConfig } from "vite";
import path from "node:path";

export default defineConfig({
	plugins: [vue(), UnoCSS()],
	resolve: {
		alias: {
			"@": path.resolve(__dirname, "src"),
		},
	},
	build: {
		lib: {
			entry: path.resolve(__dirname, "src/index.ts"),
			name: "SolitarUI",
			formats: ["es"],
			fileName: "index",
		},
		rollupOptions: {
			external: ["vue"],
		},
	},
});
