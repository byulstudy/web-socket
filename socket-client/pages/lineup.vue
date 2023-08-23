<template>
	<div>
		<div style="width: 200px" class="mb-2 text-xl font-bold">
			<span v-if="done">✨처리 완료!🎉</span>
			<span v-else>처리중입니다{{ dots }}</span>
		</div>
		<ul class="result-list">
			<li>
				<check-icon />
				총 대기인원 : {{ total }}
			</li>
			<li>
				<check-icon :checked="done" />
				내 대기순번 : {{ done ? "0" : rank ? rank : "알수없음" }}
			</li>
		</ul>
		<div class="text-center mt-2">
			<button class="btn btn-blue" @click="cancel">돌아가기</button>
		</div>
	</div>
</template>
<script setup lang="ts">
const dots = ref(".");
const total = ref(0);
const done = ref(false);
const rank = ref();

const { query } = useRoute();
const cancel = () => {
	navigateTo("/");
};

onMounted(() => {
	setInterval(() => {
		dots.value = dots.value.length === 3 ? "." : dots.value + ".";
		$socket.send("/rank", query.myId as string);
	}, 500);
	const { $socket } = useSocket();
	if ($socket.isConnected()) {
		$socket.subscribe("/process/" + query.myId, message => {
			done.value = true;
		});
		$socket.subscribe("/total", message => {
			total.value = Number(message.body) ? Number(message.body) : 0;
		});
		$socket.subscribe("/rank/" + query.myId, message => {
			rank.value = Number(message.body) ? Number(message.body) : undefined;
		});
	} else {
		console.error("socket is not connected");
	}
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
