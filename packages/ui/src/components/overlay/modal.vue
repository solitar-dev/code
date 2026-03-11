<script setup lang="ts">
import { computed, useTemplateRef, watch } from "vue";

type Props = {
	title?: string;
	class?: string | string[] | Record<string, boolean>;
	isOpen: boolean;
	openModal: () => void;
	closeModal: () => void;
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
	() => props.isOpen,
	(isOpen) => {
		if (isOpen) {
			modal.value?.showModal();
		} else if (modal.value?.open) {
			modal.value?.close();
		}
	},
	{
		immediate: true,
	},
);

function handleBackdropClick(event: MouseEvent) {
	if (event.target === modal.value) {
		props.closeModal();
	}
}
</script>

<template>
	<dialog ref="modal" :class="modalClass" @click="handleBackdropClick">
		<div class="p-5 flex flex-col">
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
