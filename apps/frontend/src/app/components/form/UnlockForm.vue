<script setup lang="ts">
import { required } from "@regle/rules";
type Props = {
	shortCode: string;
};

const { shortCode } = defineProps<Props>();

type FormData = {
	password: string;
};

const state = ref<FormData>({
	password: "",
});

const { r$ } = useRegle(state, {
	password: {
		required: withMessage(required, "Password cannot be empty"),
	},
});

const { $api, $toast } = useNuxtApp();
const urlRepository = repository($api);

async function goHome() {
	await navigateTo("/");
}

async function unlock() {
	const result = await r$.$validate();

	if (!result.valid) {
		return;
	}

	const { password } = result.data;

	try {
		const originalUrl = await urlRepository.getLongUrl({ shortCode, password });

		await navigateTo(originalUrl, {
			external: true,
			redirectCode: 301,
		});
	} catch (error: any) {
		const e = error.data as { title: string; detail: string };

		$toast.error(e.detail);
	}
}
</script>

<template>
	<div class="container h-screen flex justify-center items-center">
		<form class="border border-border rounded-lg max-w-xl" @submit.prevent="unlock">
			<div class="p-5 flex flex-col gap-3">
				<h1 class="text-2xl font-bold">The URL is locked</h1>

				<p class="text-fg-muted">
					The link you're about to visit has been locked by the creator. Please provide a
					password to unlock it
				</p>

				<div class="">
					<div
						class="relative flex items-center border border-border rounded-lg w-full max-w-2xl group focus-within:(border-border-active) bg-bg-muted">
						<div class="absolute left-0 flex items-center pointer-events-none p-2">
							<i
								class="i-tabler-lock-password text-fg-muted group-focus-within:text-fg"></i>
						</div>
						<input
							type="password"
							class="font-mono outline-none w-full py-2 px-8"
							spellcheck="false"
							autocomplete="off"
							id="password"
							name="password"
							v-model="r$.$value.password" />
					</div>
					<p class="text-red-900">{{ r$.password.$errors[0] }}</p>
				</div>
			</div>
			<div
				class="bg-bg-muted p-5 w-full flex justify-between items-center border-t border-t-border rounded-b-lg font-mono">
				<button
					class="p-2 border border-border rounded-lg hover:(cursor-pointer bg-gray-200) bg-bg"
					type="button"
					@click="goHome">
					Return
				</button>
				<button class="btn-primary p-2" type="submit">Unlock</button>
			</div>
		</form>
	</div>
</template>
