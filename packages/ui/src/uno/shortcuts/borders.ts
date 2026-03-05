export const borders = () => {
	const sides = ["t", "r", "b", "l"];
	const colors = {
		default: "gray-400",
		hover: "gray-500",
		active: "gray-600",
	};

	return Object.entries(colors).reduce(
		(acc, [state, color]) => ({
			...acc,
			[`border-${state}`]: `border-${color}`,
			...sides.reduce(
				(sideAcc, side) => ({
					...sideAcc,
					[`border-${side}-${state}`]: `border-${side}-${color}`,
				}),
				{},
			),
		}),
		{},
	);
};
