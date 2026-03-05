import Vue from "@vitejs/plugin-vue";
import { defineConfig } from "vite";
import UnoCSS from "unocss/vite";

export default defineConfig({
	root: "./playground",
	plugins: [Vue(), UnoCSS()],
});
