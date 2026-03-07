<script setup lang="ts">
type NavigationItem = {
	name: string;
	path: string;
	kbd: string;
};

const items: NavigationItem[] = [
	{
		name: $t("nav.url_shortener"),
		path: "/",
		kbd: "s",
	},
	{
		name: $t("nav.qr_generator"),
		path: "/qr",
		kbd: "q",
	},
	{
		name: $t("nav.settings"),
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

const [isShowMenu, toggleMenu] = useToggle();
</script>

<template>
	<header class="w-full border-b border-b-border fixed top-0 z-50 bg-bg/80 backdrop-blur-lg">
		<nav class="container flex justify-between items-center p-3 gap-3">
			<div class="">
				<NuxtLink to="/" class="font-brand text-2xl">Solitar</NuxtLink>
			</div>
			<ul class="flex items-center gap-5">
				<li v-for="item in items" class="hidden sm:block">
					<NuxtLink :to="item.path"
						><SButton variant="tertiary" class="font-mono flex gap-1">
							<template #label
								>{{ item.name }} <SKbd :label="item.kbd"
							/></template> </SButton
					></NuxtLink>
				</li>
				<li class="block sm:hidden">
					<SButton @click="toggleMenu()" variant="tertiary">
						<template #label>
							<i class="i-tabler-menu" v-if="!isShowMenu"></i>
							<i class="i-tabler-x" v-if="isShowMenu"></i>
						</template>
					</SButton>
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
