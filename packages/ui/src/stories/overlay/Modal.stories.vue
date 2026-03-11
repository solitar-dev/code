<script setup lang="ts">
import { defineMeta } from "sb-addon-vue-csf";
import Modal from "../../components/overlay/modal.vue";
import Button from "../../components/base/button.vue";

const { Story } = defineMeta({
	title: "Overlay/Modal",
	component: Modal,
});
</script>

<template>
	<Story name="Basic" :args="{ active: false }">
		<template #template="{ args }">
			<Button label="Show modal" @click="args.active = !args.active" />

			<Modal title="Hello" :active="args.active" class="w-11/12 max-w-xl">
				<template #body>
					<p>
						Lorem ipsum dolor sit amet consectetur adipisicing elit. Nobis, eius itaque
						vero saepe impedit adipisci aperiam laboriosam explicabo iusto. Cupiditate,
						hic ipsa? Recusandae voluptas aperiam labore quo provident maxime suscipit.
					</p>
				</template>
				<template #footer>
					<div class="w-full flex justify-between">
						<Button label="Close" variant="secondary" @click="args.active = false" />
						<div class="flex flex-row gap-2">
							<Button label="Copy" />
							<Button label="Download" />
						</div>
					</div>
				</template>
			</Modal>
		</template>
	</Story>
</template>
