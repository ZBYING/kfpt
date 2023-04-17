<template>
	<view>
		<view class="cu-bar bg-white solid-bottom">
			<view class="action">
				<text class="cuIcon-title text-blue"></text>
				深色背景
			</view>
		</view>
		<view class="grid col-3 padding-sm">
			<view class="padding-sm" v-for="(item, index) in ColorList" :key="index" @click="next(index)">
				<view class="padding radius text-center shadow-blur" :class="'bg-' + item.name">
					<view class="text-lg">{{ item.title }}</view>
					<view class="margin-top-sm text-Abc">{{ item.name }}</view>
				</view>
			</view>
		</view>
		<view class="cu-bar bg-white solid-bottom">
			<view class="action">
				<text class="cuIcon-title text-blue"></text>
				淡色背景
			</view>
		</view>
		<view class="grid col-3 bg-white padding-sm">
			<view class="padding-sm" v-for="(item, index) in ColorList" :key="index" v-if="index < 12">
				<view class="padding radius text-center light" :class="'bg-' + item.name">
					<view class="text-lg">{{ item.title }}</view>
					<view class="margin-top-sm text-Abc">{{ item.name }}</view>
				</view>
			</view>
		</view>
		<view class="cu-bar bg-white solid-bottom margin-top">
			<view class="action">
				<text class="cuIcon-title text-blue"></text>
				渐变背景
			</view>
		</view>
		<view class="grid col-2 padding-sm">
			<view class="padding-sm">
				<view class="bg-gradual-red padding radius text-center shadow-blur">
					<view class="text-lg">魅红</view>
					<view class="margin-top-sm text-Abc">#f43f3b - #ec008c</view>
				</view>
			</view>
			<view class="padding-sm">
				<view class="bg-gradual-orange padding radius text-center shadow-blur">
					<view class="text-lg">鎏金</view>
					<view class="margin-top-sm text-Abc">#ff9700 - #ed1c24</view>
				</view>
			</view>
			<view class="padding-sm">
				<view class="bg-gradual-green padding radius text-center shadow-blur">
					<view class="text-lg">翠柳</view>
					<view class="margin-top-sm text-Abc">#39b54a - #8dc63f</view>
				</view>
			</view>
			<view class="padding-sm">
				<view class="bg-gradual-blue padding radius text-center shadow-blur">
					<view class="text-lg">靛青</view>
					<view class="margin-top-sm text-Abc">#0081ff - #1cbbb4</view>
				</view>
			</view>
			<view class="padding-sm">
				<view class="bg-gradual-purple padding radius text-center shadow-blur">
					<view class="text-lg">惑紫</view>
					<view class="margin-top-sm text-Abc">#9000ff - #5e00ff</view>
				</view>
			</view>
			<view class="padding-sm">
				<view class="bg-gradual-pink padding radius text-center shadow-blur">
					<view class="text-lg">霞彩</view>
					<view class="margin-top-sm text-Abc">#ec008c - #6739b6</view>
				</view>
			</view>
		</view>
		<view class="cu-bar bg-white margin-top">
			<view class="action">
				<text class="cuIcon-title text-blue"></text>
				图片背景
			</view>
		</view>
		<view class="bg-img bg-mask flex align-center" style="background-image: url('https://ossweb-img.qq.com/images/lol/web201310/skin/big10006.jpg');height: 414upx;">
			<view class="padding-xl text-white">
				<view class="padding-xs text-xxl text-bold">钢铁之翼</view>
				<view class="padding-xs text-lg">Only the guilty need fear me.</view>
			</view>
		</view>
	</view>
</template>

<script>
export default {
	setup() {
		let ColorList = [
				{
					title: '嫣红',
					name: 'red',
					color: '#e54d42'
				},
				{
					title: '桔橙',
					name: 'orange',
					color: '#f37b1d'
				},
				{
					title: '扭蛋',
					name: 'yellow',
					color: '#fbbd08'
				},
				{
					title: '授权二维码',
					name: 'olive',
					color: '#8dc63f'
				},
				{
					title: '获取token',
					name: 'green',
					color: '#39b54a'
				},
				{
					title: '天青',
					name: 'cyan',
					color: '#1cbbb4'
				},
				{
					title: '海蓝',
					name: 'blue',
					color: '#0081ff'
				},
				{
					title: '姹紫',
					name: 'purple',
					color: '#6739b6'
				},
				{
					title: '木槿',
					name: 'mauve',
					color: '#9c26b0'
				},
				{
					title: '桃粉',
					name: 'pink',
					color: '#e03997'
				},
				{
					title: '棕褐',
					name: 'brown',
					color: '#a5673f'
				},
				{
					title: '玄灰',
					name: 'grey',
					color: '#8799a3'
				},
				{
					title: '草灰',
					name: 'gray',
					color: '#aaaaaa'
				},
				{
					title: '墨黑',
					name: 'black',
					color: '#333333'
				},
				{
					title: '雅白',
					name: 'white',
					color: '#ffffff'
				}
			]
			
			const next = (index) => {
				console.log(index)
				if (index === 2) {
					uni.navigateTo({
						url: '/pages/gash/gash'
					});
				}
				if (index === 3) {
					uni.navigateTo({
						url: '/pages/message/message'
					});
				}
				if (index === 4) {
					uni.navigateTo({
						url: '/pages/tokenTable/tokenTable'
					});
				}
				if (index === 5) {
					uni.navigateTo({
						url: '/pages/webView/webView'
					});
				}
			}
			
			return { ColorList, next };
	}
};
</script>

<style></style>
