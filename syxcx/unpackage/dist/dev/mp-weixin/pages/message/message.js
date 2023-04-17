"use strict";
var common_vendor = require("../../common/vendor.js");
const _sfc_main = {
  data() {
    return {
      urlHref: "",
      component_appid: "wx7046963dff72596c",
      component_appsecret: "f2fdebe6a65dba5e8e85179c0393f0ea",
      pre_auth_code: "preauthcode@@@o_G_gKhoTxKGO3UoZgd-Cn-wWv87LjG-nWr6gKgSsNCjvapGeGSY_K4yyxoXAVe39uC4d9IWl3Xja1xDy3BBgw",
      redirectUri: ""
    };
  },
  created() {
    this.urlHref = `https://mp.weixin.qq.com/cgi-bin/componentloginpage?component_appid=${this.component_appid}&pre_auth_code=${this.pre_auth_code}&redirect_uri=${this.redirectUri}
`;
  },
  methods: {}
};
function _sfc_render(_ctx, _cache, $props, $setup, $data, $options) {
  return {
    a: $data.urlHref
  };
}
var MiniProgramPage = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["render", _sfc_render], ["__scopeId", "data-v-a91f51d4"], ["__file", "D:/syxcx/kfpt/syxcx/pages/message/message.vue"]]);
wx.createPage(MiniProgramPage);
