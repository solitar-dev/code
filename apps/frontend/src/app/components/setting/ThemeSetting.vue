<script setup lang="ts">
type Theme = {
	id: string;
	name: string;
};

const themes: Theme[] = [
	{
		id: "system",
		name: "auto",
	},
	{
		id: "light",
		name: "light",
	},
	{
		id: "dark",
		name: "dark",
	},
];

const colorMode = useColorMode();

function switchTheme(theme: string) {
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
						colorMode.preference === theme.id
							? 'bg-gray-1000 text-gray-100 hover:(bg-gray-1000/90)'
							: 'hover:(bg-gray-200)',
					]"
					v-for="theme in themes"
					:key="theme.id"
					@click="switchTheme(theme.id)">
					{{ theme.name }}
				</button>
			</div>
		</template>
	</SettingBase>
</template>
