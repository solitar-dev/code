<script setup lang="ts">
import { computed } from "vue";

type Props = {
	label?: string;
	type?: HTMLButtonElement["type"];
	class?: string | string[] | Record<string, boolean>;
	leadingIcon?: string;
	trailingIcon?: string;
	variant?: "default" | "error" | "warning" | "secondary" | "tertiary";
};

const props = withDefaults(defineProps<Props>(), {
	type: "button",
	variant: "default",
});

const variantClasses: Record<string, string> = {
	default: "bg-gray-1000 hover:bg-gray-1000/90 text-gray-100",
	error: "bg-red-700 hover:bg-red-800 text-gray-100",
	warning: "bg-amber-700 hover:bg-amber-800 text-gray-1000",
	secondary: "border border-default hover:bg-hover text-gray-1000",
	tertiary: "hover:bg-hover text-gray-1000",
};

const buttonClass = computed(() => [
	"p-2 rounded-lg hover:cursor-pointer flex flex-row items-center justify-center gap-1",
	variantClasses[props.variant],
	props.class,
]);
</script>

<template>
	<button :type :class="buttonClass">
		<slot name="prefix">
			<i v-if="leadingIcon" :class="leadingIcon"></i>
		</slot>
		<slot name="label">
			{{ label }}
		</slot>
		<slot name="suffix">
			<i v-if="trailingIcon" :class="trailingIcon"></i>
		</slot>
	</button>
</template>
