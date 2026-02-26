<template>
	<div class="w-full flex flex-col md:flex-row items-start gap-5">
		<div class="w-full flex flex-col gap-5">
			<div class="">
				<h1 class="text-6xl">
					The <span class="text-purple">all-in-one</span> tool that you need.
				</h1>
			</div>
			<div class="text-fg-muted flex flex-col gap-5">
				<p>
					Solitar is an open source, powerful, everyday platform that lets you shorten
					long links, create instant QR codes.
				</p>
				<p>
					Solitar delivers instant redirects with zero ad-interstitials, automate your
					workflow with our REST API and set flexible expiration dates for your links.
				</p>
			</div>
			<Counter />
		</div>
		<div class="w-full">
			<ShortenForm />
		</div>
	</div>
</template>
