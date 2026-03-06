import type { Meta, StoryObj } from "@storybook/vue3-vite";
import Button from "../../components/base/button.vue";

const meta = {
	title: "Base/Button",
	component: Button,
} satisfies Meta<typeof Button>;

export default meta;
type Story = StoryObj<typeof meta>;

export const Default: Story = {
	render: (args) => ({
		components: { Button },
		setup() {
			return { args };
		},
		template: '<Button v-bind="args" />',
	}),
	args: {
		label: "Default",
		type: "button",
	},
};
