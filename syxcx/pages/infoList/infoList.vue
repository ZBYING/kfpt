<template>
	<view>
		<div @click="getMessage">
			绑定小程序详情:{{ infoObject }}
		</div>
		<div>
			绑定小程序调用接口使用access_token信息{{ authMessage }}
		</div>
	</view>
</template>

<script>
	import api from '@/api/index.js';
	export default {
		data() {
			return {
				infoObject: {},
				component_appid: '',
				authorizer_appid: '',
				access_token: '',
				authMessage: {}
			}
		},
		onLoad(option) {
			this.component_appid = option.component_appid;
			this.authorizer_appid = option.authorizer_appid;
			this.access_token = option.access_token;
			
			this.getAuthorizerInfoFn({
				component_appid: this.component_appid,
				authorizer_appid: this.authorizer_appid,
				access_token: this.access_token
			})
		},
		methods: {
			// /* 获取绑定小程序的详细信息  */
			async getAuthorizerInfoFn(data) {
				await api.getAuthorizerInfo(data).then(res => {
					console.log(res)
					this.infoObject = res.data;
				}).catch(err => {
					console.log(err)
				})
			},
			// /* 使用授权码获取授权信息 */
			async getMessage() {
				if (!(this.component_appid && this.access_token)) return alert("请加载完成后在点击")
				let reqObj = { 
					component_appid: this.component_appid, 
					component_access_token: this.access_token,
					authorizer_appid: this.authorizer_appid
				};
				await api.getQueryAuth(reqObj).then(res => {
					console.log(res)
					this.authMessage = res.data;
				}).catch(err => {
					console.log(err)
				})
			}
		},
	}
</script>

<style>

</style>
