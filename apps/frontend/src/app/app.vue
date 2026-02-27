<template>
	<Toaster
		position="top-right"
		:toast-options="{
			unstyled: true,
			classes: {
				error: 'bg-red-800 rounded-lg p-3',
				icon: 'hidden',
			},
		}" />
	<Header />
	<NuxtPage />
	<Footer />
</template>
