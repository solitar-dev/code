import { definePreset, presetWind4, transformerVariantGroup } from "unocss";

const PALETTES = ["gray", "red", "amber", "green"] as const;
const STEPS = [100, 200, 300, 400, 500, 600, 700, 800, 900, 1000] as const;

const paletteColors = Object.fromEntries(
	PALETTES.flatMap((palette) =>
		STEPS.map((step) => [`${palette}-${step}`, `var(--${palette}-${step})`]),
	),
);

const colors = {
	base: "var(--bg-base)",
	surface: "var(--bg-surface)",
	...paletteColors,
};

function generateBorderClasses(): Record<string, string> {
	const states = { default: "400", hover: "500", active: "600" };

	return ["t", "l", "r", "b"].reduce(
		(acc, side) => {
			Object.entries(states).forEach(([state, weight]) => {
				acc[`border-${side}-${state}`] = `border-${side}-gray-${weight}`;
			});

			return acc;
		},
		{
			"border-default": "border-gray-400",
			"border-hover": "border-gray-500",
			"border-active": "border-gray-600",
		} as Record<string, string>,
	);
}

export const presetSolitar = definePreset(() => {
	return {
		name: "preset-solitar",

		presets: [presetWind4({ preflights: { reset: true } })],
		transformers: [transformerVariantGroup()],

		theme: {
			colors,
		},
		shortcuts: {
			// border
			...generateBorderClasses(),

			// background
			"bg-default": "bg-gray-100",
			"bg-hover": "bg-gray-200",
			"bg-active": "bg-gray-300",
			// text
			"text-default": "text-gray-1000",
			"text-muted": "text-gray-900",
		},
	};
});
