<script setup lang="ts">
import { defineMeta } from "sb-addon-vue-csf";
import Button from "../../components/base/button.vue";

const { Story } = defineMeta({
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
});
</script>

<template>
	<!-- Variants -->

	<Story
		name="Default"
		:args="{
			label: 'Default',
			variant: 'default',
		}" />

	<Story
		name="Error"
		:args="{
			label: 'Delete',
			variant: 'error',
		}" />

	<Story
		name="Warning"
		:args="{
			label: 'Caution',
			variant: 'warning',
		}" />

	<Story
		name="Secondary"
		:args="{
			label: 'Secondary',
			variant: 'secondary',
		}" />

	<Story
		name="Tertiary"
		:args="{
			label: 'Tertiary',
			variant: 'tertiary',
		}" />

	<!-- Icons -->

	<Story
		name="WithLeadingIcon"
		:args="{
			label: 'Add Item',
			variant: 'default',
			leadingIcon: 'i-lucide-plus',
		}" />

	<Story
		name="WithTrailingIcon"
		:args="{
			label: 'Next',
			variant: 'default',
			trailingIcon: 'i-lucide-arrow-right',
		}" />

	<Story
		name="WithBothIcons"
		:args="{
			label: 'Settings',
			variant: 'secondary',
			leadingIcon: 'i-lucide-settings',
			trailingIcon: 'i-lucide-chevron-down',
		}" />

	<Story
		name="IconOnly"
		:args="{
			leadingIcon: 'i-lucide-search',
			variant: 'tertiary',
		}" />

	<!-- Slots -->

	<Story name="CustomLabelSlot" :args="{ variant: 'secondary' }">
		<template #default="{ args }">
			<Button v-bind="args">
				<template #label>
					<span style="font-style: italic">Custom <strong>Label</strong></span>
				</template>
			</Button>
		</template>
	</Story>

	<Story name="CustomPrefixSlot" :args="{ variant: 'default', label: 'Launch' }">
		<template #default="{ args }">
			<Button v-bind="args">
				<template #prefix>
					<span>🚀</span>
				</template>
			</Button>
		</template>
	</Story>

	<!-- Button types -->

	<Story
		name="SubmitButton"
		:args="{
			label: 'Submit Form',
			type: 'submit',
			variant: 'default',
		}" />

	<Story
		name="ResetButton"
		:args="{
			label: 'Reset Form',
			type: 'reset',
			variant: 'secondary',
		}" />

	<!-- All variants -->
	<Story name="AllVariants">
		<template #template>
			<div style="display: flex; flex-wrap: wrap; gap: 12px; align-items: center">
				<Button label="Default" variant="default" />
				<Button label="Error" variant="error" />
				<Button label="Warning" variant="warning" />
				<Button label="Secondary" variant="secondary" />
				<Button label="Tertiary" variant="tertiary" />
			</div>
		</template>
	</Story>
</template>
