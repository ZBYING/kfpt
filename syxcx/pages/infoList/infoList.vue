<template>
	<view>
		<div>
			{{ infoObject }}
		</div>
	</view>
</template>

<script>
	import api from '@/api/index.js';
	export default {
		data() {
			return {
				infoObject: {}
			}
		},
		onLoad(option) {
			this.getAuthorizerInfoFn({
				component_appid: option.component_appid,
				authorizer_appid: option.authorizer_appid,
				access_token: option.access_token
			})
		},
		methods: {
			/* 获取第三方平台token  */
			async getAuthorizerInfoFn(data) {
				await api.getAuthorizerInfo(data).then(res => {
					console.log(res)
					this.infoObject = res.data;
				}).catch(err => {
					console.log(err)
				})
			}
		},
	}
</script>

<style>

</style>
