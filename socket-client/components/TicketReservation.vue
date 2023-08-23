<template>
	<common-button @click="bookTicket">예약하기</common-button>
</template>

<script setup lang="ts">
const bookTicket = async () => {
	const now = $dayjs();
	const { data } = await useAsyncData(
		() => $fetch("/api/lineup", { method: "post", body: { at: now } }),
		{},
	);
	await useRouter().push("/lineup");
};
</script>
