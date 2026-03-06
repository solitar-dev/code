<script setup lang="ts">
type Props = {
	label?: string;
	type?: HTMLButtonElement["type"];
};

withDefaults(defineProps<Props>(), {
	label: "",
	type: "button",
});
</script>

<template>
	<button :type>
		<slot name="label">
			{{ label }}
		</slot>
	</button>
</template>
