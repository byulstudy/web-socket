<template>
	<div class="text-center">
		<div style="width: 200px" class="mb-2 text-xl font-bold">줄서기</div>
		<button class="btn btn-blue" @click="lineup">신청</button>
	</div>
</template>
<script setup lang="ts">
const lineup = () => {
	// TODO call lineup API
	navigateTo("/lineup");
};
</script>
