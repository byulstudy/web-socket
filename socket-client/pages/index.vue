<template>
	<div class="text-center">
		<div style="width: 200px" class="mb-2 text-xl font-bold">줄서기</div>
		<button class="btn btn-blue" @click="lineup">신청</button>
	</div>
</template>
<script setup lang="ts">
import stringUtil from "~/utils/string.util";
const myId = stringUtil.guid();

const lineup = () => {
	$fetch("/api/lineup", {
		method: "post",
		body: {
			id: myId,
		},
	}).then(res => {
		console.log(res);
	});
	navigateTo({ path: "/lineup", query: { myId } });
};

const wait = async () => await new Promise(resolve => setTimeout(resolve, 50));

onMounted(async () => {
	let canFetch = true;
	while (canFetch) {
		await wait();
		await $fetch("/api/lineup", {
			method: "post",
			body: {
				id: stringUtil.guid(),
			},
		}).catch(() => {
			canFetch = false;
		});
	}
});
</script>
