<script setup lang="ts">
type Props = {
	value?: string;
};

withDefaults(defineProps<Props>(), {
	value: "",
});
</script>

<template>
	<kbd class="border border-default rounded-lg px-1 py-0.5 bg-surface font-mono">{{ value }}</kbd>
</template>
