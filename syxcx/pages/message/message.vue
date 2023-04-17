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
				redirectUri: ""
			}
		},
		created() {
			this.getAuthCode();
		},
		methods: {
			getAuthCode() {
				let obj = {
					"component_appid": this.component_appid, //第三方平台appid
				}
				api.getAuthCode(obj).then(res => {
					console.log(res.data)
					this.urlHref = `https://mp.weixin.qq.com/cgi-bin/componentloginpage?component_appid=${this.component_appid}&pre_auth_code=${res.data}&redirect_uri=${this.redirectUri}
					`
				}).catch(err => {
					console.log(err)
				})

			}
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