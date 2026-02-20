export default defineNuxtPlugin({
	setup() {
		const api = $fetch.create({
			baseURL: useRuntimeConfig().public.apiBaseUrl,
		});

		return { provide: { api } };
	},
});
