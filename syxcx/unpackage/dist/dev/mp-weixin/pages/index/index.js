"use strict";
var common_vendor = require("../../common/vendor.js");
const Background = () => "../basics/background.js";
const _sfc_main = {
  components: { Background },
  data() {
    return {};
  }
};
if (!Array) {
  const _component_Background = common_vendor.resolveComponent("Background");
  _component_Background();
}
function _sfc_render(_ctx, _cache, $props, $setup, $data, $options) {
  return {};
}
var MiniProgramPage = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["render", _sfc_render], ["__file", "D:/syxcx/kfpt/syxcx/pages/index/index.vue"]]);
wx.createPage(MiniProgramPage);
