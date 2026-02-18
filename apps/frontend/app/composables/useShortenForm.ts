import { useRegle } from "@regle/core";
import { regex, minLength, minValue, oneOf, required, maxLength } from "@regle/rules";

type Form = {
	longUrl: string;
	alias?: string;
	neverExpire: boolean;
	expireTime: 30;
	expireUnit: ExpireUnit;
};

export default function () {
	const formData = ref<Form>({
		longUrl: "",
		alias: "",
		neverExpire: true,
		expireTime: 30,
		expireUnit: "second",
	});

	const rules = {
		longUrl: {
			required,
			regex: regex(
				/^https?:\/\/(?:www\.)?[-a-zA-Z0-9@:%._+~#=]{1,256}\.[a-zA-Z0-9()]{1,6}\b(?:[-a-zA-Z0-9()@:%_+.~#?&/=]*)$/,
			),
		},
		alias: {
			minLength: minLength(7),
			maxLength: maxLength(255),
			regex: regex(/^[a-zA-Z0-9_-]*$/),
		},
		expireTime: { required, minValue: minValue(0, { allowEqual: false }) },
		expireUnit: { required, oneOf: oneOf(expireUnits) },
	};

	return useRegle(formData, rules);
}
