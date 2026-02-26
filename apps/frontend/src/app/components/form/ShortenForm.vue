<script setup lang="ts">
const url = ref("");
const noExpire = ref<boolean>(true);

const state = ref({
	longUrl: "",
	alias: "",
	password: "",
	expirationTime: 30,
	expirationTimeUnit: "seconds",
});

const { r$ } = useRegle(state, {});
</script>

<template>
	<form class="flex flex-col gap-3 border border-border p-5 rounded-lg">
		<div class="flex flex-col gap-1">
			<label for="">Long URL<span class="text-red">*</span></label>
			<div
				class="relative flex items-center border border-border rounded-lg w-full max-w-2xl group focus-within:(border-border-active) bg-bg-muted">
				<div class="absolute left-0 flex items-center pointer-events-none p-2">
					<i class="i-tabler-link text-fg-muted group-focus-within:text-fg"></i>
				</div>
				<input
					type="text"
					placeholder="paste the link here"
					class="font-mono outline-none w-full py-2 px-8"
					spellcheck="false"
					autocomplete="off"
					v-model="url" />
			</div>
		</div>
		<div class="flex flex-col gap-1">
			<label for="">Alias</label>
			<div
				class="relative flex items-center border border-border rounded-lg w-full max-w-2xl group focus-within:(border-border-active) bg-bg-muted">
				<div class="absolute left-0 flex items-center pointer-events-none p-2">
					<i class="i-tabler-abc text-fg-muted group-focus-within:text-fg"></i>
				</div>
				<input
					type="text"
					placeholder="custom alias (optional)"
					class="font-mono outline-none w-full py-2 px-8"
					spellcheck="false"
					autocomplete="off"
					v-model="url" />
			</div>
		</div>
		<div class="flex flex-col gap-1">
			<label for="">Password</label>
			<div
				class="relative flex items-center border border-border rounded-lg w-full max-w-2xl group focus-within:(border-border-active) bg-bg-muted">
				<div class="absolute left-0 flex items-center pointer-events-none p-2">
					<i class="i-tabler-lock-password text-fg-muted group-focus-within:text-fg"></i>
				</div>
				<input
					type="text"
					placeholder="password (optional)"
					class="font-mono outline-none w-full py-2 px-8"
					spellcheck="false"
					autocomplete="off"
					v-model="url" />
			</div>
		</div>
		<div class="w-full flex items-center gap-1">
			<label for="no-expire">No expire</label>
			<input type="checkbox" id="no-expire" v-model="noExpire" />
		</div>
		<div class="flex flex-col gap-1" v-show="!noExpire">
			<label for="">Time<span class="text-red">*</span></label>
			<div class="flex gap-1">
				<div
					class="relative flex items-center border border-border rounded-lg w-full max-w-2xl group focus-within:(border-border-active) bg-bg-muted">
					<div class="absolute left-0 flex items-center pointer-events-none p-2">
						<i class="i-tabler-number-123 text-fg-muted group-focus-within:text-fg"></i>
					</div>
					<input
						type="text"
						class="font-mono outline-none w-full py-2 px-8"
						spellcheck="false"
						autocomplete="off"
						v-model="url" />
				</div>
				<div
					class="relative flex items-center border border-border rounded-lg w-full max-w-2xl group focus-within:(border-border-active) bg-bg-muted">
					<div class="absolute left-0 flex items-center pointer-events-none p-2">
						<i class="i-tabler-ruler-2 text-fg-muted group-focus-within:text-fg"></i>
					</div>
					<input
						type="text"
						placeholder="paste the link here"
						class="font-mono outline-none w-full py-2 px-8"
						spellcheck="false"
						autocomplete="off"
						v-model="url" />
				</div>
			</div>
		</div>
		<button class="w-full btn-primary p-2 flex justify-center items-center gap-1">
			<i class="i-tabler-north-star"></i>
			Shorten
		</button>
	</form>
</template>
