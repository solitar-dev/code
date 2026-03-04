<script setup lang="ts">
type Props = {
	name: string;
};

defineProps<Props>();
</script>

<template>
	<div class="flex flex-col gap-2">
		<p class="text-lg">{{ name }}</p>
		<slot name="body" />
	</div>
</template>
