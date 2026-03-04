<script setup lang="ts">
type Props = {
	url: string;
};

const { url } = defineProps<Props>();

async function redirectWithHttps() {
	const secureUrl = url.replace(/^http:/, "https:");
	await navigateTo(secureUrl, { external: true });
}

async function redirectWithHttp() {
	await navigateTo(url, { external: true });
}
</script>

<template>
	<div class="container h-screen flex justify-center items-center">
		<div class="border border-border rounded-lg max-w-xl">
			<div class="p-5 flex flex-col gap-3">
				<h1 class="text-2xl font-bold">{{ $t("not_secure_warning.title") }}</h1>
				<div class="text-amber-900">
					<p class="font-mono truncate">
						{{ url }}
					</p>
				</div>
				<p class="text-fg-muted">
					{{ $t("not_secure_warning.description") }}
				</p>
			</div>
			<div
				class="bg-bg-muted p-5 w-full flex justify-between items-center border-t border-t-border rounded-b-lg font-mono">
				<button
					class="text-red-900 p-2 rounded-lg hover:(cursor-pointer bg-red-100)"
					@click="redirectWithHttp">
					{{ $t("not_secure_warning.accept_risk") }}</button
				><button class="btn-primary p-2" @click="redirectWithHttps">
					{{ $t("not_secure_warning.enforce_https") }}
				</button>
			</div>
		</div>
	</div>
</template>
