import { definePreset, presetWind4, transformerVariantGroup } from "unocss";

export const presetSolitar = definePreset(() => {
	return {
		name: "preset-solitar",

		presets: [presetWind4({ preflights: { reset: true } })],
		transformers: [transformerVariantGroup()],

		theme: {},
		shortcuts: {},
	};
});
