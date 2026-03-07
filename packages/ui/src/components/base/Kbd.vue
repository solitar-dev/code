<script setup lang="ts">
type Props = {
	label?: string;
};

withDefaults(defineProps<Props>(), { label: "" });
</script>

<template>
	<kbd class="border border-default bg-surface rounded-lg px-1">{{ label }}</kbd>
</template>
