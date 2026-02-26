<script setup lang="ts">
type NavigationItem = {
	name: string;
	path: string;
	kbd: string;
};

const items: NavigationItem[] = [
	{
		name: "url shortener",
		path: "/",
		kbd: "s",
	},
	{
		name: "qr generator",
		path: "/qr",
		kbd: "q",
	},
	{
		name: "settings",
		path: "/settings",
		kbd: ",",
	},
];

items.forEach((item) => {
	onKeyStroke(item.kbd, (e) => {
		if (!isEditableElement(e.target)) {
			e.preventDefault();
			navigateTo(item.path);
		}
	});
});

const isShowMenu = ref<boolean>(false);

function onToggle() {
	isShowMenu.value = !isShowMenu.value;
}
</script>

<template>
	<header class="w-full border-b border-b-border">
		<nav class="container flex justify-between items-center p-3 gap-3">
			<div class="">
				<NuxtLink to="/" class="font-brand text-2xl">Solitar</NuxtLink>
			</div>
			<ul class="flex items-center gap-5">
				<li v-for="item in items" class="hidden sm:block">
					<NuxtLink
						:to="item.path"
						class="font-mono hover:bg-gray-200 rounded-lg p-2 duration-200 flex gap-1"
						>{{ item.name }} <Kbd :value="item.kbd"
					/></NuxtLink>
				</li>
				<li class="block sm:hidden">
					<button
						class="flex items-center justify-center hover:bg-gray-200 p-2 rounded-lg"
						@click="onToggle">
						<i class="i-tabler-menu" v-if="!isShowMenu"></i>
						<i class="i-tabler-x" v-if="isShowMenu"></i>
					</button>
				</li>
			</ul>
		</nav>
		<ul class="flex sm:hidden items-center flex-col pb-2" v-if="isShowMenu">
			<li v-for="item in items" class="">
				<NuxtLink :to="item.path" class="font-mono rounded-lg p-2 flex gap-1"
					>{{ item.name }}
				</NuxtLink>
			</li>
		</ul>
	</header>
</template>
