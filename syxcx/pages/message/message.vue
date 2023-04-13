<template>
	<view>
		<button @click="getToken">获取access_token</button>
	</view>
</template>

<script>
	import api from '@/api/index.js';
	export default {
		data() {
			return {
				component_appid: "wx7046963dff72596c", //第三方平台appid
				component_appsecret: "0add6c39e29c7cbfd5b5ea52dc2619e9", //第三方平台appsecret
				component_verify_ticket: "ticket_value" //微信后台推送的ticket
			}
		},
		methods: {
			getToken() {
				let obj = {
					"component_appid": this.component_appid, //第三方平台appid
					"component_appsecret": this.component_appsecret, //第三方平台appsecret
					"component_verify_ticket": this.component_verify_ticket //微信后台推送的ticket
				}
				api.getComponent_access_token(obj).then(res => {
					console.log(res)
				}).catch(err => {
					console.log(err)
				})

			}
		}
	}
</script>

<style>

</style>