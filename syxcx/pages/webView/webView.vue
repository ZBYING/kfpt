<template>
	<view>
		<web-view :src="urlWeb"></web-view>
	</view>
</template>
<script>
	export default {
		data() {
			return {
				urlWeb: 'http://43.143.64.121:8088'
			}
		},
		methods: {
			
		}
	}
</script>

<style>

</style>
