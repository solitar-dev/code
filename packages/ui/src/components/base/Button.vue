<script setup lang="ts">
import type { ButtonHTMLAttributes } from "vue";

type Props = {
	label: string;
	type?: ButtonHTMLAttributes["type"];
};

withDefaults(defineProps<Props>(), {
	label: "",
	type: "button",
});
</script>

<template>
	<button :type>{{ label }}</button>
</template>
