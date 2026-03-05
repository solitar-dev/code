import { presetWind4, definePreset, transformerVariantGroup } from "unocss";
import { shortcuts } from "./shortcuts";
import { colors } from "./colors";

export const presetSolitar = definePreset({
	name: "solitar-preset",

	presets: [presetWind4({ preflights: { reset: true } })],
	transformers: [transformerVariantGroup()],

	theme: {
		colors,
	},
	shortcuts,
});
