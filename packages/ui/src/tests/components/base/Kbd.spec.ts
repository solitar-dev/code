import { expect, test } from "vitest";
import { render } from "vitest-browser-vue";
import { Kbd } from "../../..";

test("render kbd", async () => {
	const { getByText } = render(Kbd, {
		props: { value: "A" },
	});

	await expect.element(getByText("A")).toBeInTheDocument();
});
