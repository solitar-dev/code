<script setup lang="ts">
const colorMode = useColorMode();

function toggleTheme() {
	colorMode.preference = colorMode.value === "light" ? "dark" : "light";
}
</script>

<template>
	<button
		@click="toggleTheme"
		class="navbar-item flex justify-center items-center"
		type="button"
		title="Toggle Color Scheme">
		<i class="i-carbon-moon hidden dark:block"></i>
		<i class="i-carbon-sun dark:hidden"></i>
	</button>
</template>
