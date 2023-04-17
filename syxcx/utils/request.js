import { BASE_URL } from '@/env'


// 查询服务号消息列表
export function request(options) {
	//生成时间戳
	return new Promise((resolve, reject) => {
		if (options.loadTitle == "0") {} else {
			uni.showLoading({
				title: options.loadTitle || '加载中...',
				mask: true
			});
		}
		let url = '';
		
		url = BASE_URL + options.url || '';
		// 如果没有参数赋值为{}
		if (!options.data) {
			options.data = {};
		}
		
		/* 报文需要加密处理 */
		let reqData, data;
		console.log(`加密前报文==>${options.url}====>`, options.data)
		reqData = JSON.stringify(options.data)
		data = reqData;
		/* 发送请求报文 */
		uni.request({
			url,
			method: options.method || "GET",
			data,
			dataType: options.dataType || "json",
			timeout: 300000, //单位ms
			header: {
				// 'content-type': 'application/x-www-form-urlencoded',
			},
			success: (res) => {
				/* 通讯解密 */
				let key, code;
				resolve(res)
				// code = res.data.code;
				/* ******************************************************************************** */
				uni.hideLoading();
				//  if (code != "0000") {
				// 	uni.showModal({
				// 		content: res.data.msg,
				// 		showCancel: false
				// 	})
				// 	reject(res.data)
				// } else {
				// 	console.log("响应报文:", {...res.data.data}, '<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<')
				// 	resolve(res.data)
				// }
			},
			fail: (res) => {
				console.log("请求路径", options.url)
				console.log("错误信息", res)
				uni.hideLoading();
				uni.showModal({
					content: res.errMsg,
					showCancel: false
				})
				reject(res)
			},
			complete: (res) => {
				uni.hideLoading();
			}
		})

	})
}
