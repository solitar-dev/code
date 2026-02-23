<script setup lang="ts">
import type { FormSubmitEvent } from "@nuxt/ui";
import { type } from "arktype";

const route = useRoute();

const shortCode = route.query.c!.toString();

if (!shortCode) {
	await navigateTo("/");
}

const { $api } = useNuxtApp();
const urlRepository = repository($api);
const toast = useToast();

const schema = type({
	password: "3 <= string <= 255",
});

type Schema = typeof schema.infer;

const state = ref<Schema>({
	password: "",
});

async function onSubmit(event: FormSubmitEvent<Schema>) {
	event.preventDefault();

	const password = event.data.password;

	try {
		const originalUrl = await urlRepository.getLongUrl({ shortCode, password });

		await navigateTo(originalUrl, {
			external: true,
			redirectCode: 302,
		});
	} catch (error: any) {
		const e = error.data as { title: string; detail: string };

		toast.add({
			title: e.title,
			description: e.detail,
			color: "error",
		});
	}
}
</script>

<template>
	<div class="grid place-items-center h-screen">
		<div class="flex flex-col justify-center items-center gap-5">
			<UIcon name="i-carbon:locked" class="size-12 text-warning" />
			<UForm :schema="schema" :state="state" class="space-y-4" @submit="onSubmit">
				<UPageCard class="max-w-lg" :reverse="false">
					<template #title>
						<p>Protected by password</p>
					</template>

					<template #description>
						<div class="flex flex-col gap-3">
							<p>
								This link has been protected by a password, please enter the
								password to unlock it
							</p>
							<UFormField name="password" label="Password">
								<UInput
									type="password"
									v-model="state.password"
									class="w-full"
									size="xl" />
							</UFormField>
						</div>
					</template>

					<template #footer>
						<div class="flex flex-row gap-3">
							<UButton
								label="Unlock"
								icon="i-carbon:unlocked"
								type="submit"
								class="hover:cursor-pointer"
								color="success" />
						</div>
					</template>
				</UPageCard>
			</UForm>
		</div>
	</div>
</template>
