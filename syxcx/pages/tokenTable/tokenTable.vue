<template>
	<view>
		<view class="cu-list menu" :class="[menuBorder?'sm-border':'',menuCard?'card-menu margin-top':'']">
			<view class="cu-item" :class="menuArrow?'arrow':''" v-for="(item, index) in list" :key="index">
				<button class="cu-btn content" open-type="contact" @click.native="getInfo(item.authorizer_appid)">
					<text class="cuIcon-btn text-olive"></text>
					<text class="text-grey">{{ item.authorizer_appid }}</text>
				</button>
			</view>
		</view>
	</view>
</template>

<script>
	import api from '@/api/index.js';
	export default {
		data() {
			return {
				menuArrow: true,
				menuBorder: false,
				menuCard: false,
				access_token: '',
				component_appid: 'wx7046963dff72596c',
				list: [],
			}
		},
		async created() {
			/* 获取getToken */
			await this.getToken();
			/* 获取AppIdList */
			await this.getAppId();
		},
		methods: {
			/* 获取第三方平台授权小程序appid列表  */
			async getAppId() {
				let reqObj = {
					component_appid: this.component_appid,
					offset: 0,
					count: 100,
					access_token: this.access_token
				};
				await api.getAppidList(reqObj).then(res => {
					console.log(res)
					this.list = res.data.list;
				}).catch(err => {
					console.log(err)
				})
			},
			/* 获取第三方平台token  */
			async getToken() {
				await api.getComponent_access_token({
					appid: this.component_appid,
				}).then(res => {
					console.log(res)
					this.access_token = res.data.access_token;
				}).catch(err => {
					console.log(err)
				})
			},
			getInfo(appid) {
				uni.navigateTo({
					url: `/pages/infoList/infoList?component_appid=${this.component_appid}&access_token=${this.access_token}&authorizer_appid=${appid}`,
				})
			}
		}
	}
</script>

<style>

</style>
