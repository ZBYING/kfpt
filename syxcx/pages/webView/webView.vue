<template>
	<view>
		<web-view :src="urlWeb"></web-view>
	</view>
</template>
<!-- CREATE TABLE wx_auth_code(
   wxAppid VARCHAR(256) PRIMARY KEY,
   authCode VARCHAR(256),
   bak VARCHAR(256)
); -->
<script>
	export default {
		data() {
			return {
				urlWeb: 'http://43.143.64.121:8081'
			}
		},
		methods: {
			
		}
	}
</script>

<style>

</style>
