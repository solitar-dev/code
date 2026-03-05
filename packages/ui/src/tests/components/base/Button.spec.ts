import { expect, test } from "vitest";
import { render } from "vitest-browser-vue";
import { Button } from "../../..";

test("renders the label prop correctly via fallback", async () => {
	const { getByRole } = render(Button, {
		props: { label: "Click Me" },
	});

	await expect.element(getByRole("button")).toBeInTheDocument("Click Me");
});

test("renders default slot content over label prop", async () => {
	// If both a label prop and default slot are provided, the slot should win
	const { getByRole } = render(Button, {
		props: { label: "Ignored Label" },
		slots: { default: "Slot Content" },
	});

	await expect.element(getByRole("button")).toBeInTheDocument("Slot Content");
});

test("applies the correct button type attribute", async () => {
	const { getByRole } = render(Button, {
		props: { type: "submit" },
	});

	await expect.element(getByRole("button")).toHaveAttribute("type", "submit");
});

test("renders prefix, default, and suffix slots", async () => {
	const { getByText } = render(Button, {
		slots: {
			prefix: "<span>IconPrefix</span>",
			default: "<span>MainContent</span>",
			suffix: "<span>IconSuffix</span>",
		},
	});

	await expect.element(getByText("IconPrefix")).toBeInTheDocument();
	await expect.element(getByText("MainContent")).toBeInTheDocument();
	await expect.element(getByText("IconSuffix")).toBeInTheDocument();
});

test("applies disabled attribute and classes when disabled is true", async () => {
	const { getByRole } = render(Button, {
		props: { disabled: true },
	});

	const buttonElement = getByRole("button");

	await expect.element(buttonElement).toBeDisabled();
	await expect.element(buttonElement).toHaveClass("bg-active");
});

test("does not apply disabled attribute when disabled is false", async () => {
	const { getByRole } = render(Button, {
		props: { disabled: false },
	});

	const buttonElement = getByRole("button");

	await expect.element(buttonElement).not.toBeDisabled();
	await expect.element(buttonElement).toHaveClass("hover:bg-hover");
	await expect.element(buttonElement).toHaveClass("hover:cursor-pointer");
});
