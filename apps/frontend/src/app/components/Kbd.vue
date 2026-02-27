<script setup lang="ts">
defineProps<{ value: string }>();
</script>

<template>
	<p class="border border-border rounded-lg px-2 bg-bg-muted">{{ value }}</p>
</template>
