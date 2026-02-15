<script setup lang="ts">
import type { InferRegleRoot } from "@regle/core";

defineProps<{ r$: InferRegleRoot<typeof useShortenForm> }>();
</script>

<template>
	<div class="w-full border border-gray-400 rounded-lg">
		<div class="p-7 text-secondary flex flex-col gap-5">
			<div class="space-y-1">
				<label for="longUrl" class="flex gap-1 text-primary"
					><i class="i-material-symbols-light:link text-xl" />Long URL</label
				>
				<input
					type="url"
					:class="[
						r$.longUrl.$errors[0] != null
							? 'border-red-400 focus:border-red-600'
							: 'border-gray-400 focus:border-gray-600',
						'focus:(outline-none) w-full [&:not(:placeholder-shown)]:text-primary border bg-bg-100 p-3 rounded-lg',
					]"
					id="longUrl"
					name="longUrl"
					v-model="r$.$value.longUrl"
					placeholder="https://example.com/my/long/url" />
				<p class="text-secondary text-red-900">{{ r$.longUrl.$errors[0] }}</p>
			</div>
			<div class="flex flex-col sm:flex-row gap-5">
				<div class="w-full space-y-1">
					<label for="alias" class="flex gap-1 text-primary"
						><i class="i-tabler:tag text-lg" />Custom Alias (optional)</label
					>
					<input
						type="text"
						:class="[
							r$.alias.$errors[0] != null
								? 'border-red-400 focus:border-red-600'
								: 'border-gray-400 focus:border-gray-600',
							'focus:(outline-none) w-full bg-bg-100 [&:not(:placeholder-shown)]:text-primary border p-3 rounded-lg',
						]"
						id="alias"
						name="alias"
						v-model="r$.$value.alias"
						placeholder="my-awesome-link" />
					<p class="text-secondary text-red-900">{{ r$.alias.$errors[0] }}</p>
				</div>
				<div class="w-full space-y-1">
					<label for="expiration" class="flex gap-1 text-primary"
						><i class="i-tabler-clock-hour-5 text-xl" />Expiration</label
					>
					<div
						class="p-3 border border-gray-400 rounded-lg flex flex-row gap-1 bg-bg-100">
						<input type="checkbox" id="expiration" v-model="r$.$value.neverExpire" />
						<label for="expiration" class="text-primary">Never expire</label>
					</div>
				</div>
			</div>
			<div class="flex flex-wrap gap-3" v-show="!r$.$value.neverExpire">
				<input
					type="number"
					class="p-3 bg-bg-100 border border-gray-400 rounded-lg text-primary"
					name="expireTime"
					v-model="r$.$value.expireTime" />
				<select
					class="p-3 bg-bg-100 border border-gray-400 rounded-lg text-secondary"
					name="expireUnit"
					v-model="r$.$value.expireUnit">
					<option
						v-for="unit in expireUnits"
						:key="unit"
						:value="unit"
						class="capitalize">
						{{ unit }}s
					</option>
				</select>
			</div>
			<div class="">
				<button
					type="button"
					class="p-3 rounded-lg bg-teal-700 hover:(bg-teal-800 cursor-pointer) text-dark-gray-1000 font-semibold w-full duration-200"
					@click="$emit('handleSubmit')">
					Shorten
				</button>
			</div>
		</div>
	</div>
</template>
