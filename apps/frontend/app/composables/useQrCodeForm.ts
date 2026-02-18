import { useRegle } from "@regle/core";
import { required, regex } from "@regle/rules";

type Form = {
	url: string;
};

export default function () {
	const formData = ref<Form>({
		url: "",
	});

	const rules = {
		url: {
			required,
			regex: regex(
				/^https?:\/\/(?:www\.)?[-a-zA-Z0-9@:%._+~#=]{1,256}\.[a-zA-Z0-9()]{1,6}\b(?:[-a-zA-Z0-9()@:%_+.~#?&/=]*)$/,
			),
		},
	};

	return useRegle(formData, rules);
}
