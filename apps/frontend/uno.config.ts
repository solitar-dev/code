import {
	defineConfig,
	presetAttributify,
	presetIcons,
	presetTypography,
	presetWebFonts,
	presetWind4,
	transformerAttributifyJsx,
	transformerVariantGroup,
} from "unocss";

const shades = Array.from({ length: 10 }, (_, i) => (i + 1) * 100);
const colorNames = ["gray", "teal", "red"];

const generatedColors = colorNames.reduce((acc: Record<string, string>, color) => {
	shades.forEach((shade) => {
		const key = `${color}-${shade}`;
		acc[key] = `var(--${key})`;
	});
	return acc;
}, {});

generatedColors["bg-100"] = "var(--bg-100)";
generatedColors["bg-200"] = "var(--bg-200)";

// Fixed color
const fixedColors = {
	lightModeValues: {
		"gray-900": "hsla(0, 0%, 40%, 1)",
		"gray-1000": "hsla(0, 0%, 9%, 1)",
	},
	darkModeValues: {
		"gray-900": "hsla(0, 0%, 63%, 1)",
		"gray-1000": "hsla(0, 0%, 93%, 1)",
	},
};

export default defineConfig({
	presets: [
		presetWind4({ preflights: { reset: true } }),
		presetAttributify(),
		presetWebFonts({
			provider: "fontsource",
			fonts: { mono: "JetBrains Mono", sans: "Geist", brand: "Orbitron" },
		}),
		presetIcons({
			extraProperties: {
				display: "inline-block",
				height: "1.2em",
				width: "1.2em",
				"vertical-align": "text-bottom",
			},
		}),
		presetTypography(),
	],
	transformers: [transformerVariantGroup(), transformerAttributifyJsx()],
	theme: {
		colors: {
			...generatedColors,

			light: fixedColors.lightModeValues,
			dark: fixedColors.darkModeValues,
		},
	},
	shortcuts: {
		"text-primary": "text-gray-1000",
		"text-secondary": "text-gray-900",

		"bg-100": "bg-bg-100",
		"bg-200": "bg-bg-200",

		"navbar-item": "text-secondary text-lg hover:(text-primary cursor-pointer) duration-200",

		"underline-primary":
			"underline decoration-offset-5 decoration-gray-1000/20 hover:(text-primary cursor-pointer decoration-gray-1000) duration-200",
	},
});
