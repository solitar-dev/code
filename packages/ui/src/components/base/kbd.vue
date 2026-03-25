<script setup lang="ts">
import { computed } from "vue";

type Props = {
	label?: string;
	class?: string | string[] | Record<string, boolean>;
};

const props = withDefaults(defineProps<Props>(), { label: "" });

const kbdClass = computed(() => [
	"border border-default bg-surface rounded-lg px-2 w-fit",

	props.class,
]);
</script>

<template>
	<div :class="kbdClass">
		{{ label }}
	</div>
</template>
