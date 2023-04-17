"use strict";
var api_index = require("../../api/index.js");
var common_vendor = require("../../common/vendor.js");
require("../../utils/request.js");
require("../../env.js");
const _sfc_main = {
  data() {
    return {
      infoObject: {}
    };
  },
  onLoad(option) {
    this.getAuthorizerInfoFn({
      component_appid: option.component_appid,
      authorizer_appid: option.authorizer_appid,
      access_token: option.access_token
    });
  },
  methods: {
    async getAuthorizerInfoFn(data) {
      await api_index.api.getAuthorizerInfo(data).then((res) => {
        console.log(res);
        this.infoObject = res.data;
      }).catch((err) => {
        console.log(err);
      });
    }
  }
};
function _sfc_render(_ctx, _cache, $props, $setup, $data, $options) {
  return {
    a: common_vendor.t($data.infoObject)
  };
}
var MiniProgramPage = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["render", _sfc_render], ["__file", "D:/syxcx/kfpt/syxcx/pages/infoList/infoList.vue"]]);
wx.createPage(MiniProgramPage);
