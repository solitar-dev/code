<script setup lang="ts">
type SocialLink = {
	icon: string;
	link: string;
};

type FooterColumn = {
	title: string;
	items: {
		name: string;
		link: string;
	}[];
};

const socialLinks: SocialLink[] = [
	{
		icon: "i-tabler-brand-github",
		link: "https://github.com/solitar-dev/code",
	},
];

const footerItems: FooterColumn[] = [
	{
		title: "about",
		items: [
			{
				name: "changelog",
				link: "/changelog",
			},
			{
				name: "status",
				link: "https://status.solitar.io.vn",
			},
		],
	},
	{
		title: "resources",
		items: [
			{
				name: "translate",
				link: "/translate",
			},
			{
				name: "api",
				link: "/api",
			},
		],
	},
	{
		title: "legal",
		items: [
			{
				name: "terms of use",
				link: "/terms",
			},
			{
				name: "privacy policy",
				link: "/privacy",
			},
			{
				name: "report",
				link: "/report",
			},
		],
	},
];
</script>

<template>
	<footer class="p-6">
		<div
			class="flex flex-col sm:flex-row items-center justify-center sm:items-start gap-8 sm:gap-16">
			<div class="gap-3 flex flex-col items-center sm:items-start text-center sm:text-left">
				<div class="w-full">
					<NuxtLink to="/" class="text-5xl font-brand">ä</NuxtLink>
				</div>
				<div class="">
					<ul class="flex gap-2">
						<li v-for="item in socialLinks" class="">
							<NuxtLink :to="item.link" class="flex items-center text-lg">
								<i :class="item.icon"></i>
							</NuxtLink>
						</li>
					</ul>
				</div>
				<div class="font-mono space-y-2">
					<p class="">
						<NuxtLink
							to="https://github.com/solitar-dev/code/blob/main/LICENSE"
							:external="true"
							class="underline-link"
							target="_blank"
							>MIT</NuxtLink
						>
						2026 © Toby Nguyen
					</p>
				</div>
			</div>
			<div class="flex flex-col gap-2" v-for="column in footerItems">
				<p class="font-bold font-mono text-center">{{ column.title }}</p>
				<ul class="flex flex-col gap-1">
					<li v-for="item in column.items" class="text-center">
						<NuxtLink :to="item.link" class="font-mono underline-link">
							{{ item.name }}
						</NuxtLink>
					</li>
				</ul>
			</div>
		</div>
	</footer>
</template>
