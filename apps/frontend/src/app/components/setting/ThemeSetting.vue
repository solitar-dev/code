<script setup lang="ts">
const themes = ["auto", "light", "dark"];

const colorMode = useColorMode();

function switchTheme(theme: string) {
	if (theme === "auto") {
		colorMode.preference = "system";
		return;
	}

	colorMode.preference = theme;
}
</script>

<template>
	<SettingBase name="theme">
		<template #body>
			<div
				class="flex items-center justify-center w-full border border-border rounded-lg p-1 gap-2">
				<button
					:class="[
						'w-full p-1 rounded-lg hover:cursor-pointer',
						colorMode.value === theme
							? 'bg-gray-1000 text-gray-100 hover:(bg-gray-1000/90)'
							: 'hover:(bg-gray-200)',
					]"
					v-for="theme in themes"
					:key="theme"
					@click="switchTheme(theme)">
					{{ theme }}
				</button>
			</div>
		</template>
	</SettingBase>
</template>
