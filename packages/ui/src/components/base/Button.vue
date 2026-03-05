<script setup lang="ts">
import type { ButtonHTMLAttributes } from "vue";

type Props = {
	label?: string;
	type?: ButtonHTMLAttributes["type"];
	disabled?: boolean;
};

withDefaults(defineProps<Props>(), {
	label: "",
	type: "button",
	disabled: false,
});
</script>

<template>
	<button
		:class="[
			'border border-default p-2 rounded-lg flex items-center justify-center gap-1',
			disabled ? 'bg-active' : 'hover:(bg-hover cursor-pointer)',
		]"
		:disabled
		:type>
		<slot name="prefix" />
		<slot>
			{{ label }}
		</slot>
		<slot name="suffix" />
	</button>
</template>
