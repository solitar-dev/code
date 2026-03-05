import { presetWind4, definePreset } from "unocss";

export const presetSolitar = definePreset({
	name: "solitar-preset",

	presets: [presetWind4({ preflights: { reset: true } })],
});
