<template>
	<div class="w-full flex flex-col md:flex-row items-start gap-5">
		<div class="w-full flex flex-col gap-5">
			<div class="">
				<h1 class="text-6xl font-semibold">{{ $t("hero.title") }}</h1>
			</div>
			<div class="text-fg-muted flex flex-col gap-5">
				<p>
					{{ $t("hero.subtitle_1") }}
				</p>
				<p>
					{{ $t("hero.subtitle_2") }}
				</p>
			</div>
			<Counter />
		</div>
		<div class="w-full">
			<ShortenForm />
		</div>
	</div>
</template>
