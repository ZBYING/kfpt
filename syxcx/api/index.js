// 申请相关接口
import { request } from "@/utils/request.js"
const apiUrl = '/wxcallback';
const api = {
	getComponent_access_token(data){
		return request({
			url: `${apiUrl}/component/stableToken`,
			method: "POST",
			data: data,
		})
	},
	getAppidList(data) {
		return request({
			url: `${apiUrl}/component/getAuthorizerList`,
			method: "POST",
			data: data,
		})
	},
	getAuthorizerInfo(data) {
		return request({
			url: `${apiUrl}/component/getAuthorizerInfo`,
			method: "POST",
			data: data,
		})
	},
	getAuthCode(data) {
		return request({
			url: `${apiUrl}/component/getAuthCode`,
			method: "POST",
			data: data,
		})
	},
	getQueryAuth(data) {
		return request({
			url: `${apiUrl}/component/getQueryAuth`,
			method: "POST",
			data: data,
		})
	}
}

export default api
