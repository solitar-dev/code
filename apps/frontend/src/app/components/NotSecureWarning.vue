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
				<h1 class="text-2xl font-bold">Your connection is not private</h1>
				<div class="text-amber-900">
					<p class="font-mono truncate">
						{{ url }}
					</p>
				</div>
				<p class="text-fg-muted">
					The link you're about to visit does
					<span class="font-bold">not use HTTPS</span>, which means your connection is
					<span class="font-bold">not encrypted</span>. Your data could be intercepted by
					third parties.
				</p>
			</div>
			<div
				class="bg-bg-muted p-5 w-full flex justify-between items-center border-t border-t-border rounded-b-lg font-mono">
				<button
					class="text-red-900 p-2 rounded-lg hover:(cursor-pointer bg-red-100)"
					@click="redirectWithHttp">
					Accept Risk & Continue</button
				><button class="btn-primary p-2" @click="redirectWithHttps">Enforce HTTPS</button>
			</div>
		</div>
	</div>
</template>
