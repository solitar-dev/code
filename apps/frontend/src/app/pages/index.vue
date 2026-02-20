<script setup lang="ts"></script>

<template>
	<UPageHero
		description="The simplest way to create short links and QR codes. No signup required — just paste, shorten, and share. "
		orientation="horizontal">
		<template #title>
			<p>The only <span class="text-primary">tool</span> you need</p>
		</template>

		<template #footer>
			<Counter />
		</template>

		<UrlShortenerForm />
	</UPageHero>

	<USeparator />

	<Features />
</template>
