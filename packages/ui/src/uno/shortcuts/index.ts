import { borders } from "./borders";

export const shortcuts = {
	// Background
	"bg-default": "bg-gray-100",
	"bg-hover": "bg-gray-200",
	"bg-active": "bg-gray-300",
	// Text
	"text-foreground": "text-gray-1000",
	"text-foreground-muted": "text-gray-900",
	// Border
	...borders(),
};
