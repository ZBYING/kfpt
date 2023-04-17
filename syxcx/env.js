/**
 * 全局配置文件
 */
// export const BASE_URL = process.env.NODE_ENV === 'development' ? 'http://172.21.12.177:9003' : ''; //后台根域名
// export const BASE_URL = process.env.NODE_ENV === 'development' ? 'http://172.21.12.53:18080' : ''; //后台根域名
export const BASE_URL = process.env.NODE_ENV === 'development' ? 'http://192.168.31.69:8019' : '';  //后台根域名

export const mokeFlag = false; //是否使用假数据进入true使用，false 不使用