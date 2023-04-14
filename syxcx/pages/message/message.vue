<template>
	<view>
		<!-- <button @click="getToken">获取access_token</button> -->
		<a class="btn_a" :href="urlHref">获取二维码</a>
	</view>
</template>

<script>
	import api from '@/api/index.js';
	export default {
		data() {
			return {
				urlHref: "",
				component_appid: "wx7046963dff72596c", //第三方平台appid
				component_appsecret: "f2fdebe6a65dba5e8e85179c0393f0ea", //第三方平台appsecret
				pre_auth_code: "preauthcode@@@WcrRULz5x7xbnQB-CjXb35rhJx9qjvrqpR332-An6MgqXErCH_ggbSF0yI3qhbEp6_u0uNmU59Zv3bQWqtLmHw",
				redirectUri: '43.143.64.121:8081/wxcallback/biz/redirectUri'
			}
		},
		created() {
			this.urlHref = `https://mp.weixin.qq.com/cgi-bin/componentloginpage?component_appid=${this.component_appid}&pre_auth_code=${this.pre_auth_code}&redirect_uri=${this.redirectUri}
`
		},
		methods: {
			// getToken() {
			// 	let obj = {
			// 		"component_appid": this.component_appid, //第三方平台appid
			// 		"component_appsecret": this.component_appsecret, //第三方平台appsecret
			// 		"component_verify_ticket": this.component_verify_ticket //微信后台推送的ticket
			// 	}
			// 	api.getComponent_access_token(obj).then(res => {
			// 		console.log(res)
			// 	}).catch(err => {
			// 		console.log(err)
			// 	})

			// }
		}
	}
</script>

<style lang="scss" scoped>
a {

display: inline-block;
    margin: 0 0 -5px;
    width: 100%;
    height: 38.5px;
    background-color: #fff;
    border-radius: 2px;
    color: black;
    text-decoration: none;
    font-size: 13px;
    font-weight: 900;
    line-height: 3.2;
    transition-duration: 0.2s;
    transition-property: background-color;
    transition-timing-function: ease;
    text-align: center;
    padding: 1px;

}
</style>