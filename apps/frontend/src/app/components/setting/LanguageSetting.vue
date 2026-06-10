<script setup lang="ts">
type LocaleCode = "en" | "vi";

type Language = {
	code: LocaleCode;
	// Displayed in its own language so it reads correctly in any locale.
	name: string;
};

const languages: Language[] = [
	{
		code: "en",
		name: "English",
	},
	{
		code: "vi",
		name: "Tiếng Việt",
	},
];

const { locale, setLocale } = useI18n();

function switchLanguage(code: LocaleCode) {
	setLocale(code);
}
</script>

<template>
	<SettingBase name="language">
		<template #body>
			<div
				class="flex items-center justify-center w-full border border-border rounded-lg p-1 gap-2">
				<button
					:class="[
						'w-full p-1 rounded-lg hover:cursor-pointer',
						locale === language.code
							? 'bg-gray-1000 text-gray-100 hover:(bg-gray-1000/90)'
							: 'hover:(bg-gray-200)',
					]"
					v-for="language in languages"
					:key="language.code"
					@click="switchLanguage(language.code)">
					{{ language.name }}
				</button>
			</div>
		</template>
	</SettingBase>
</template>
