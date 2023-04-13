// 申请相关接口
import { request } from "@/utils/request.js"
const apiUrl = '/cgi-bin/component';
const api = {
	getComponent_access_token(data){
		return request({
			url: `${apiUrl}/api_component_token`,
			method: "POST",
			data: data,
		})
	},
	getPre_auth_code(data, token){
		return request({
			url: `${apiUrl}/api_create_preauthcode?component_access_token=${token}`,
			method: "POST",
			data: data,
		})
	}
}

export default api
