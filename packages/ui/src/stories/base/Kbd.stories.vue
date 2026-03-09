<script setup lang="ts">
import { defineMeta } from "sb-addon-vue-csf";
import Kbd from "../../components/base/kbd.vue";

const { Story } = defineMeta({
	title: "Base/Kbd",
	component: Kbd,
});
</script>

<template>
	<Story name="Letter" :args="{ label: 's' }" />

	<Story name="Character" :args="{ label: '.' }" />
</template>
