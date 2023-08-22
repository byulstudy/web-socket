<template>
	<layout-default>
		<div class="flex flex-col items-center"></div>
	</layout-default>
</template>

<script setup lang="ts">
const { $socket } = useSocket();

const send = () => {
	$socket.send("/echo", { test: "test" });
};

const bookTicket = async () => {
	const { data } = await useAsyncData(
		() => $fetch("/api/lineup", { method: "post" }),
		{},
	);
};
</script>
