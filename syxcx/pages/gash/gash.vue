<template>
	<view class="gash_content">
		<view>
			<image src="../../static/gashapon/bg.png" style="height: 1700rpx;
    width: 100%;"></image>
		</view>
		<view class="egg">
		<!-- 背景 -->
		  <image class="egg_ji" :src="bgImg" mode="widthFix"></image>
		  <image class="egg_w" :src="wImg" mode="widthFix"></image>
		 <image 
			class="ball" 
			:class="start ? 'ball_' + (index + 1) + ' ' + 'weiyi_' + (index + 1):'ball_' + (index + 1)" 
			:src="item.prizeImgUrl" 
			mode="widthFix"
			v-for="(item, index) in cardList"
			:key="index"
			></image>
		  <!-- 掉落的蛋-->
		  <image :hidden="qiu" :animation="ani" class="ball ball_end" src="/static/gashapon/1.png" mode="widthFix"></image>
		  <!--开关 go1改成go可以按钮旋转-->
		  <image class="play" :class="start?'go1':''" @click="eggPlay" :src="btnImg" mode="widthFix"></image>
		</view>
		<!-- alert -->
		<view class="cu-modal" :class="modalName=='Modal'?'show':''">
			<view class="cu-dialog">
				<view class="cu-bar bg-white justify-end">
					<view class="content">Hello Word</view>
					<view class="action" @tap="hideModal">
						<text class="cuIcon-close text-red"></text>
					</view>
				</view>
				<view class="padding-xl">
					恭喜你获得一颗糖果
				</view>
			</view>
		</view>
	</view>

	
</template>

<script>
	export default {
		props: {
			cardList: {
				type: Array,
				default: () => {
					return [{
						prizeImgUrl: '../../static/gashapon/1.png'
					},
					{
						prizeImgUrl: '../../static/gashapon/2.png'
					},{
						prizeImgUrl: '../../static/gashapon/1.png'
					},{
						prizeImgUrl: '../../static/gashapon/2.png'
					},{
						prizeImgUrl: '../../static/gashapon/1.png'
					},{
						prizeImgUrl: '../../static/gashapon/2.png'
					}]
				}
			},
			fileList: {
				type: Array,
				default: () => {
					return []
				}
			}
		},
		data() {
			return {
				start : false,
				qiu: true,
				nui: '',
				ani:'',
				bgImg: '../../static/gashapon/123.png',
				btnImg: '../../static/gashapon/btn.png',
				wImg: '../../static/gashapon/0.png',
				modalName: null,
				
			}
		},
		computed: {
		},
		onLoad(options) {
		},
		mounted() {
			/* 设置背景图 */
			//1:背景 2:标题 3轮盘 4按钮 5抽奖次数
			this.fileList.forEach(item => {
				//设置扭蛋机背景图
				if (item.busType == '3') {
					this.bgImg = item.filePath;
				}
				//设置扭蛋机按钮
				if (item.busType == '4') {
					this.btnImg = item.filePath;
				}
			});
		},
		methods: {
			showModal(e) {
				console.log(e)
				this.modalName = 'Modal'
			},
			hideModal(e) {
				this.modalName = null
			},
			/**
			 * 点击开始按钮
			 */
			 eggPlay(){
				 this.startEggGame();
				// this.$emit('httpApi');
			},
			/* 开始扭蛋 */
			startEggGame() {
				this.start = true
				setTimeout(() => {
					this.start = false
					this.qiu = false
					//球落下动画
					var animation = uni.createAnimation({
						duration: 1500,
						timingFunction: 'ease',
					});
					animation.opacity(1).step()
					this.ani = animation.export()
					console.log("扭蛋结束")
					this.showModal();
					// this.$emit('getMessage')
				}, 3000);
				
				this.qiu = true
				//将动画返回到开始位置
				var animation = uni.createAnimation({
					duration: 1500,
					timingFunction: 'ease',
				});
				animation.opacity(0).step()
				this.ani = animation.export()
			}
		},
	}
</script>

<style lang="css" scoped>
.egg{
 width: 100%;
 position: absolute;
 z-index: 3;
 top: 390rpx;
}
.egg_w {
	width: 100rpx;
	position: absolute;
	height: 136rpx;
	top: 469rpx;
	left: 322rpx;
}
.egg .egg_ji{
 width: 70%;
 margin-left: 15%;
 z-index: -3;
}
.egg .play{
 width: 170rpx;
 position: absolute;
 z-index: 4;
 /* top: 645rpx; */
 top: 585rpx;
 left: 287rpx;
}
.egg .ball{
 width: 75rpx;
 position: absolute;
 z-index: 2;
}
.egg .ball_1{
 top: 290rpx;
 left: 300rpx;
}
.egg .ball_2{
 top: 295rpx;
 left: 360rpx;
}
.egg .ball_3{
 top: 260rpx;
 left: 240rpx;
}
.egg .ball_4{
 top: 260rpx;
 left: 420rpx;
}
.egg .ball_5{
 top: 230rpx;
 left: 280rpx;
}
.egg .ball_6{
 top: 230rpx;
 left: 340rpx;
}
.egg .ball_7{
 top: 220rpx;
 left: 390rpx;
}
.egg .ball_end{
top: -170rpx;
 left: -305rpx;
}

.weiyi_1 {
 animation: around1 1.5s linear infinite;
}
.weiyi_2 {
 animation: around2 1.5s linear infinite;
}
.weiyi_3 {
 animation: around3 1.5s linear infinite;
} 
.weiyi_4 {
 animation: around4 1.5s linear infinite;
}
.weiyi_5 {
 animation: around5 1.5s linear infinite;
}
.weiyi_6 {
 animation: around6 1.5s linear infinite;
}
.weiyi_7 {
 animation: around7 1.5s linear infinite;
}
.go{
 animation: around 0.3s linear 1;
}


/* 位移 */
@keyframes around{
 100% {
 -webkit-transform: rotate(-180deg)
 }
}

@keyframes around1 {
 0% {
 -webkit-transform: translate(0rpx, 0rpx)
 }
 20% {
 -webkit-transform: translate(-100rpx, -200rpx)
 }
 40% {
 -webkit-transform: translate(40rpx, -280rpx)
 }
 60% {
 -webkit-transform: translate(150rpx, -200rpx)
 }
 80% {
 -webkit-transform: translate(150rpx, -50rpx)
 }
 100% {
 -webkit-transform: translate(0, 0)
 }
}

@keyframes around2 {
 0% {
 -webkit-transform: translate(0rpx, 0rpx)
 }
 20% {
 -webkit-transform: translate(100rpx, -200rpx)
 }
 40% {
 -webkit-transform: translate(-20rpx, -280rpx)
 }
 60% {
 -webkit-transform: translate(-150rpx, -200rpx)
 }
 80% {
 -webkit-transform: translate(-150rpx, -50rpx)
 }
 100% {
 -webkit-transform: translate(0, 0)
 }
}

@keyframes around3 {
 0% {
 -webkit-transform: translate(0rpx, 0rpx)
 }
 20% {
 -webkit-transform: translate(180rpx, 10rpx)
 }
 40% {
 -webkit-transform: translate(240rpx, -110rpx)
 }
 60% {
 -webkit-transform: translate(100rpx, -240rpx)
 }
 80% {
 -webkit-transform: translate(-50rpx, -130rpx)
 }
 100% {
 -webkit-transform: translate(0, 0)
 }
}

@keyframes around4 {
 0% {
 -webkit-transform: translate(0rpx, 0rpx)
 }
 20% {
 -webkit-transform: translate(-180rpx, 10rpx)
 }
 40% {
 -webkit-transform: translate(-240rpx, -110rpx)
 }
 60% {
 -webkit-transform: translate(-100rpx, -240rpx)
 }
 80% {
 -webkit-transform: translate(50rpx, -130rpx)
 }
 100% {
 -webkit-transform: translate(0, 0)
 }
}

@keyframes around5 {
 0% {
 -webkit-transform: translate(0rpx, 0rpx)
 }
 20% {
 -webkit-transform: translate(40rpx, 70rpx)
 }
 40% {
 -webkit-transform: translate(50rpx, -210rpx)
 }
 60% {
 -webkit-transform: translate(-80rpx, -100rpx)
 }
 80% {
 -webkit-transform: translate(190rpx, -50rpx)
 }
 100% {
 -webkit-transform: translate(0, 0)
 }
}

@keyframes around6 {
 0% {
 -webkit-transform: translate(0rpx, 0rpx)
 }
 20% {
 -webkit-transform: translate(-150rpx, -50rpx)
 }
 40% {
 -webkit-transform: translate(130rpx, -140rpx)
 }
 60% {
 -webkit-transform: translate(-110rpx, -180rpx)
 }
 80% {
 -webkit-transform: translate(-130rpx, -20rpx)
 }
 100% {
 -webkit-transform: translate(0, 0)
 }
}

@keyframes around7 {
 0% {
 -webkit-transform: translate(0rpx, 0rpx)
 }
 20% {
 -webkit-transform: translate(80rpx, -50rpx)
 }
 40% {
 -webkit-transform: translate(-180rpx, -100rpx)
 }
 60% {
 -webkit-transform: translate(50rpx, -150rpx)
 }
 80% {
 -webkit-transform: translate(-180rpx, -20rpx)
 }
 100% {
 -webkit-transform: translate(0, 0)
 }
}

</style>