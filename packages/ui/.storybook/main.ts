import type { StorybookConfig } from "@storybook/vue3-vite";
import { dirname } from "path";
import { fileURLToPath } from "url";

function getAbsolutePath(value: string) {
	return dirname(fileURLToPath(import.meta.resolve(`${value}/package.json`)));
}

const config: StorybookConfig = {
	stories: ["../src/**/*.stories.@(js|jsx|mjs|ts|tsx)"],
	addons: [],
	framework: getAbsolutePath("@storybook/vue3-vite"),
};

export default config;
