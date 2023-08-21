<template>
	<div>
		<div style="width: 200px" class="mb-2 text-xl font-bold">
			처리중입니다{{ dots }}
		</div>
		<ul class="result-list">
			<li>
				<check-icon />
				총 대기인원 :
			</li>
			<li>
				<check-icon checked />
				내 대기순번 :
			</li>
		</ul>
		<div class="text-center mt-2">
			<button class="btn btn-blue" @click="cancel">취소</button>
		</div>
	</div>
</template>
<script setup lang="ts">
const dots = ref<string>(".");

const cancel = () => {
	navigateTo("/");
};

onMounted(() => {
	setInterval(() => {
		dots.value = dots.value.length === 3 ? "." : dots.value + ".";
	}, 500);
});
</script>
<style scoped lang="scss">
.result-list {
	@apply max-w-md space-y-1 text-gray-500 list-inside dark:text-gray-400;
	& li {
		@apply flex items-center;
	}
}
</style>
