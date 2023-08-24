<template>
	<layout-default>
		<div class="flex flex-col items-center"></div>
		<common-button @click="send">send</common-button>
	</layout-default>
</template>

<script setup lang="ts">
const { $socket } = useSocket();

$socket.subscribe("/user/sub/lineup", message => {
	console.log("message:::", message);
});
$socket.send("/echo", { test: "test" });
const send = () => {
	$socket.send("/lineup", { test: "test" });
};
</script>
