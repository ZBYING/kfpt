"use strict";
var common_vendor = require("../../common/vendor.js");
var api_index = require("../../api/index.js");
require("../../utils/request.js");
require("../../env.js");
const _sfc_main = {
  data() {
    return {
      menuArrow: true,
      menuBorder: false,
      menuCard: false,
      access_token: "",
      component_appid: "wx7046963dff72596c",
      list: []
    };
  },
  async created() {
    await this.getToken();
    await this.getAppId();
  },
  methods: {
    async getAppId() {
      let reqObj = {
        component_appid: this.component_appid,
        offset: 0,
        count: 100,
        access_token: this.access_token
      };
      await api_index.api.getAppidList(reqObj).then((res) => {
        console.log(res);
        this.list = res.data.list;
      }).catch((err) => {
        console.log(err);
      });
    },
    async getToken() {
      await api_index.api.getComponent_access_token({
        appid: this.component_appid
      }).then((res) => {
        console.log(res);
        this.access_token = res.data.access_token;
      }).catch((err) => {
        console.log(err);
      });
    },
    getInfo(appid) {
      common_vendor.index.navigateTo({
        url: `/pages/infoList/infoList?component_appid=${this.component_appid}&access_token=${this.access_token}&authorizer_appid=${appid}`
      });
    }
  }
};
function _sfc_render(_ctx, _cache, $props, $setup, $data, $options) {
  return {
    a: common_vendor.f($data.list, (item, index, i0) => {
      return {
        a: common_vendor.t(item.authorizer_appid),
        b: common_vendor.o(($event) => $options.getInfo(item.authorizer_appid)),
        c: index
      };
    }),
    b: common_vendor.n($data.menuArrow ? "arrow" : ""),
    c: common_vendor.n($data.menuBorder ? "sm-border" : ""),
    d: common_vendor.n($data.menuCard ? "card-menu margin-top" : "")
  };
}
var MiniProgramPage = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["render", _sfc_render], ["__file", "D:/syxcx/kfpt/syxcx/pages/tokenTable/tokenTable.vue"]]);
wx.createPage(MiniProgramPage);
