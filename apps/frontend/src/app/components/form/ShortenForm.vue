<script setup lang="ts">
import { maxLength, minLength, minValue, number, regex, required, string } from "@regle/rules";
import { joinURL } from "ufo";

type FormData = {
	longUrl: string;
	password?: string;
	alias?: string;
	expireTime: number;
	expireTimeUnit: ExpireUnit;
};

const noExpire = ref<boolean>(true);

const state = ref<FormData>({
	longUrl: "",
	alias: "",
	password: "",
	expireTime: 30,
	expireTimeUnit: "second",
});

const { r$ } = useRegle(state, {
	longUrl: {
		required: withMessage(required, "URL is required"),
		regex: withMessage(
			regex(
				/^https?:\/\/(?:www\.)?[-a-zA-Z0-9@:%._+~#=]{1,256}\.[a-zA-Z0-9()]{1,6}\b(?:[-a-zA-Z0-9()@:%_+.~#?&/=]*)$/,
			),
			"Invalid URL",
		),
	},
	alias: {
		string: withMessage(string, "Alias must be a string"),
		minLength: withMessage(minLength(7), "Alias must be at least 7 characters"),
		maxLength: withMessage(maxLength(255), "Alias must not exceed 255 characters"),
	},
	password: {
		string: withMessage(string, "Password must be a string"),
		minLength: withMessage(minLength(3), "Password must be at least 3 characters"),
		maxLength: withMessage(maxLength(255), "Password must not exceed 255 characters"),
	},
	expireTime: {
		required,
		number: withMessage(number, "Expiration time must be a number"),
		minValue: withMessage(minValue(0), "Expiration time must be greater than 0"),
	},
	expireTimeUnit: {
		required,
	},
});

const { $api, $toast } = useNuxtApp();
const urlRepository = repository($api);
const siteConfig = useSiteConfig();
const modal = useTemplateRef("modal");

async function onSubmit() {
	const result = await r$.$validate();

	if (!result.valid) {
		return;
	}

	const { longUrl, alias, password, expireTime, expireTimeUnit } = result.data;

	const body: UrlShortenerBody = {
		url: longUrl,
		...(alias && { alias }),
		...(password && { password }),
		...(!noExpire.value && {
			expireTime: generateExpireTime(expireTime, expireTimeUnit),
		}),
	};

	try {
		const data = await urlRepository.shortenUrl(body);

		modal.value?.open({ url: joinURL(siteConfig.url, data.shortCode) });
	} catch (error: any) {
		if (!error.data) {
			$toast.error("The server has been shutdown. Please try again later");
		} else {
			$toast.error(error.data.detail);
		}
	}
}
</script>

<template>
	<ShortenUrlResult ref="modal" />
	<form
		class="flex flex-col gap-3 border border-border p-5 rounded-lg"
		@submit.prevent="onSubmit">
		<div class="flex flex-col gap-1">
			<label for="longUrl">Long URL<span class="text-red-900">*</span></label>
			<div
				class="relative flex items-center border border-border rounded-lg w-full max-w-2xl group focus-within:(border-border-active) bg-bg-muted">
				<div class="absolute left-0 flex items-center pointer-events-none p-2">
					<i class="i-tabler-link text-fg-muted group-focus-within:text-fg"></i>
				</div>
				<input
					type="text"
					placeholder="paste the link here"
					class="font-mono outline-none w-full py-2 px-8"
					spellcheck="false"
					autocomplete="off"
					id="longUrl"
					name="longUrl"
					v-model="r$.$value.longUrl" />
			</div>
			<p class="text-red-900">{{ r$.longUrl.$errors[0] }}</p>
		</div>
		<div class="flex flex-col gap-1">
			<label for="alias">Alias</label>
			<div
				class="relative flex items-center border border-border rounded-lg w-full max-w-2xl group focus-within:(border-border-active) bg-bg-muted">
				<div class="absolute left-0 flex items-center pointer-events-none p-2">
					<i class="i-tabler-abc text-fg-muted group-focus-within:text-fg"></i>
				</div>
				<input
					type="text"
					placeholder="custom alias"
					class="font-mono outline-none w-full py-2 px-8"
					spellcheck="false"
					autocomplete="off"
					id="alias"
					name="alias"
					v-model="r$.$value.alias" />
			</div>
			<p class="text-red-900">{{ r$.alias.$errors[0] }}</p>
		</div>
		<div class="flex flex-col gap-1">
			<label for="password">Password</label>
			<div
				class="relative flex items-center border border-border rounded-lg w-full max-w-2xl group focus-within:(border-border-active) bg-bg-muted">
				<div class="absolute left-0 flex items-center pointer-events-none p-2">
					<i class="i-tabler-lock-password text-fg-muted group-focus-within:text-fg"></i>
				</div>
				<input
					type="password"
					placeholder="password (optional)"
					class="font-mono outline-none w-full py-2 px-8"
					spellcheck="false"
					autocomplete="off"
					id="password"
					name="password"
					v-model="r$.$value.password" />
			</div>
			<p class="text-red-900">{{ r$.password.$errors[0] }}</p>
		</div>
		<div class="w-full flex items-center gap-1">
			<label for="no-expire">No expire</label>
			<input type="checkbox" id="no-expire" v-model="noExpire" />
		</div>
		<div class="flex flex-col gap-1" v-show="!noExpire">
			<p>Time<span class="text-red-900">*</span></p>
			<div class="flex gap-1">
				<div
					class="flex items-center border border-border rounded-lg w-full max-w-2xl group focus-within:(border-border-active) bg-bg-muted">
					<input
						type="number"
						class="font-mono outline-none w-full p-2"
						spellcheck="false"
						autocomplete="off"
						id="expirationTime"
						name="expirationTime"
						v-model="r$.$value.expireTime" />
				</div>
				<div
					class="flex items-center border border-border rounded-lg w-full max-w-2xl group focus-within:(border-border-active) bg-bg-muted">
					<select
						name="expirationTimeUnit"
						id="expirationTimeUnit"
						class="w-full p-2 font-mono"
						v-model="r$.$value.expireTimeUnit">
						<option :value="expireTimeUnit" v-for="expireTimeUnit in expireTimeUnits">
							{{ expireTimeUnit }}s
						</option>
					</select>
				</div>
			</div>
		</div>
		<button class="w-full btn-primary p-2 flex justify-center items-center gap-1" type="submit">
			<i class="i-tabler-north-star"></i>
			Shorten
		</button>
	</form>
</template>

<style scoped>
#expirationTime::-webkit-inner-spin-button,
#expirationTime::-webkit-outer-spin-button {
	-webkit-appearance: none;
	margin: 0;
}

#expirationTime[type="number"] {
	-moz-appearance: textfield;
	appearance: textfield;
}
</style>
