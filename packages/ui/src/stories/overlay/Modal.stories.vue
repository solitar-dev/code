<script lang="ts">
import { createReusableTemplate, createRenderTemplate } from "sb-addon-vue-csf";

const [DefineTemplate, ReuseTemplate] = createReusableTemplate();
export const defaultTemplate = createRenderTemplate(ReuseTemplate);
</script>

<script setup lang="ts">
import { defineMeta } from "sb-addon-vue-csf";
import Modal from "../../components/overlay/modal.vue";
import Button from "../../components/base/button.vue";
import { useModal } from "../../composables/useModal";

const { Story } = defineMeta({
	title: "Overlay/Modal",
	component: Modal,
	render: defaultTemplate,
});

const { isOpen, openModal, closeModal } = useModal();
</script>

<template>
	<DefineTemplate v-slot="{ args }">
		<Button label="Show modal" @click="openModal" />

		<Modal
			title="Hello"
			class="w-11/12 max-w-xl"
			:is-open
			:close-modal="closeModal"
			:open-modal="openModal">
			<template #body>
				<p>
					Lorem ipsum dolor sit amet consectetur adipisicing elit. Nobis, eius itaque vero
					saepe impedit adipisci aperiam laboriosam explicabo iusto. Cupiditate, hic ipsa?
					Recusandae voluptas aperiam labore quo provident maxime suscipit.
				</p>
			</template>
			<template #footer>
				<div class="w-full flex justify-between">
					<Button label="Close" variant="secondary" @click="closeModal" />
					<div class="flex flex-row gap-2">
						<Button label="Copy" />
					</div>
				</div>
			</template>
		</Modal>
	</DefineTemplate>

	<Story name="Basic" />
</template>
