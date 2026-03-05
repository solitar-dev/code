import { defineConfig } from "tsdown";
import Vue from "unplugin-vue/rolldown";

export default defineConfig({
	platform: "neutral",
	entry: ["src/index.ts", "src/preset.ts"],
	plugins: [Vue()],
	dts: { vue: true },
});
