<script setup lang="ts">
type NavbarItem = {
	path: string;
	name: string;
	icon: string;
};

const items: NavbarItem[] = [
	{ path: "/", name: "Short URL", icon: "i-material-symbols-light:link" },
	{ path: "/qr", name: "QR Code Generator", icon: "i-material-symbols:qr-code" },
];
</script>

<template>
	<header class="w-full flex justify-center items-center border-b border-b-gray-400">
		<nav class="w-11/12 flex flex-col gap-3 sm:flex-row items-center justify-between py-5">
			<div class="">
				<NuxtLink to="/" class="font-brand text-2xl text-primary">Solitär</NuxtLink>
			</div>
			<ul class="flex flex-row gap-5">
				<li class="navbar-item" v-for="it in items">
					<NuxtLink :to="it.path" class="flex flex-row items-center justify-center gap-1"
						><i :class="[it.icon, 'hidden sm:block']" /><span>{{
							it.name
						}}</span></NuxtLink
					>
				</li>

				<li class="navbar-item flex flex-row items-center">
					<NuxtLink
						href="https://github.com/solitar-dev/code"
						target="_blank"
						class="flex flex-row items-center"
						><i class="i-carbon-logo-github"></i
					></NuxtLink>
				</li>
				<ThemeToggle />
			</ul>
		</nav>
	</header>
</template>
