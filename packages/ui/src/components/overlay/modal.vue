<script setup lang="ts">
import { computed, useTemplateRef, watch } from "vue";

type Props = {
	title?: string;
	active: boolean;
	class?: string | string[] | Record<string, boolean>;
};

const props = withDefaults(defineProps<Props>(), {
	title: "",
});

const modalClass = computed(() => [
	"border border-default rounded-lg m-auto bg-base backdrop:bg-base/50 shadow-xl",
	props.class,
]);
const modal = useTemplateRef("modal");

watch(
	() => props.active,
	(isActive) => {
		if (isActive) {
			modal.value?.showModal();
		} else if (modal.value?.open) {
			modal.value?.close();
		}
	},
	{
		immediate: true,
	},
);
</script>

<template>
	<dialog ref="modal" :class="modalClass">
		<div class="px-5 py-8">
			<div class="">
				<slot name="title">
					<h1 class="text-default text-2xl font-semibold">{{ title }}</h1>
				</slot>
			</div>
			<div class="">
				<slot name="body" />
			</div>
		</div>
		<div class="px-5 py-3 bg-surface border-t border-t-default"><slot name="footer" /></div>
	</dialog>
</template>
