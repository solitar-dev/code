import type { Meta, StoryObj } from "@storybook/vue3-vite";
import Button from "../../components/base/button.vue";

const meta = {
	title: "Base/Button",
	component: Button,
	argTypes: {
		label: { control: "text" },
		type: {
			control: "select",
			options: ["button", "submit", "reset"],
		},
		variant: {
			control: "select",
			options: ["default", "error", "warning", "secondary", "tertiary"],
		},
		leadingIcon: { control: "text" },
		trailingIcon: { control: "text" },
	},
	args: {
		type: "button",
		variant: "default",
	},
} satisfies Meta<typeof Button>;

export default meta;
type Story = StoryObj<typeof meta>;

// Variants

export const Default: Story = {
	args: {
		label: "Default",
		variant: "default",
	},
};

export const Error: Story = {
	args: {
		label: "Delete",
		variant: "error",
	},
};

export const Warning: Story = {
	args: {
		label: "Caution",
		variant: "warning",
	},
};

export const Secondary: Story = {
	args: {
		label: "Secondary",
		variant: "secondary",
	},
};

export const Tertiary: Story = {
	args: {
		label: "Tertiary",
		variant: "tertiary",
	},
};

// Icons

export const WithLeadingIcon: Story = {
	args: {
		label: "Add Item",
		variant: "default",
		leadingIcon: "i-lucide-plus",
	},
};

export const WithTrailingIcon: Story = {
	args: {
		label: "Next",
		variant: "default",
		trailingIcon: "i-lucide-arrow-right",
	},
};

export const WithBothIcons: Story = {
	args: {
		label: "Settings",
		variant: "secondary",
		leadingIcon: "i-lucide-settings",
		trailingIcon: "i-lucide-chevron-down",
	},
};

export const IconOnly: Story = {
	args: {
		leadingIcon: "i-lucide-search",
		variant: "tertiary",
	},
};

// Slots

export const CustomLabelSlot: Story = {
	render: (args) => ({
		components: { Button },
		setup: () => ({ args }),
		template: `
      <Button v-bind="args">
        <template #label>
          <span style="font-style: italic;">Custom <strong>Label</strong></span>
        </template>
      </Button>
    `,
	}),
	args: {
		variant: "secondary",
	},
};

export const CustomPrefixSlot: Story = {
	render: (args) => ({
		components: { Button },
		setup: () => ({ args }),
		template: `
      <Button v-bind="args">
        <template #prefix>
          <span>🚀</span>
        </template>
      </Button>
    `,
	}),
	args: {
		label: "Launch",
		variant: "default",
	},
};

// Button types

export const SubmitButton: Story = {
	args: {
		label: "Submit Form",
		type: "submit",
		variant: "default",
	},
};

export const ResetButton: Story = {
	args: {
		label: "Reset Form",
		type: "reset",
		variant: "secondary",
	},
};

// All variants

export const AllVariants: Story = {
	render: () => ({
		components: { Button },
		template: `
      <div style="display: flex; flex-wrap: wrap; gap: 12px; align-items: center;">
        <Button label="Default"   variant="default"   />
        <Button label="Error"     variant="error"     />
        <Button label="Warning"   variant="warning"   />
        <Button label="Secondary" variant="secondary" />
        <Button label="Tertiary"  variant="tertiary"  />
      </div>
    `,
	}),
};
