import { defineConfig } from "vitest/config";
import { playwright } from "@vitest/browser-playwright";
import vue from "@vitejs/plugin-vue";

export default defineConfig({
	plugins: [vue()],
	test: {
		browser: {
			provider: playwright(),
			enabled: true,
			instances: [{ browser: "chromium" }],
		},
	},
});
