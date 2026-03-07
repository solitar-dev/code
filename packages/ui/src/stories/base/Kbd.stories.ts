import type { Meta, StoryObj } from "@storybook/vue3-vite";
import Kbd from "../../components/base/kbd.vue";

const meta = {
	title: "Base/Kbd",
	component: Kbd,
} satisfies Meta<typeof Kbd>;

export default meta;
type Story = StoryObj<typeof meta>;

export const Letter: Story = {
	args: {
		label: "s",
	},
};

export const Character: Story = {
	args: {
		label: ".",
	},
};
