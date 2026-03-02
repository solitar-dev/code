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
		title: $t("footer.column.about"),
		items: [
			{
				name: $t("footer.item.changelog"),
				link: "/changelog",
			},
			{
				name: $t("footer.item.status"),
				link: "https://status.solitar.io.vn",
			},
		],
	},
	{
		title: $t("footer.column.resource"),
		items: [
			{
				name: $t("footer.item.translate"),
				link: "/translate",
			},
			{
				name: $t("footer.item.api"),
				link: "/api",
			},
		],
	},
	{
		title: $t("footer.column.legal"),
		items: [
			{
				name: $t("footer.item.terms_of_use"),
				link: "/terms",
			},
			{
				name: $t("footer.item.privacy_policy"),
				link: "/privacy",
			},
			{
				name: $t("footer.item.report"),
				link: "/report",
			},
		],
	},
];
</script>

<template>
	<footer class="p-6 border-t border-t-border">
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
