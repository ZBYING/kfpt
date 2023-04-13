"use strict";
var common_vendor = require("../../common/vendor.js");
const _sfc_main = {
  setup() {
    let ColorList = [
      {
        title: "\u5AE3\u7EA2",
        name: "red",
        color: "#e54d42"
      },
      {
        title: "\u6854\u6A59",
        name: "orange",
        color: "#f37b1d"
      },
      {
        title: "\u660E\u9EC4",
        name: "yellow",
        color: "#fbbd08"
      },
      {
        title: "\u6A44\u6984",
        name: "olive",
        color: "#8dc63f"
      },
      {
        title: "\u68EE\u7EFF",
        name: "green",
        color: "#39b54a"
      },
      {
        title: "\u5929\u9752",
        name: "cyan",
        color: "#1cbbb4"
      },
      {
        title: "\u6D77\u84DD",
        name: "blue",
        color: "#0081ff"
      },
      {
        title: "\u59F9\u7D2B",
        name: "purple",
        color: "#6739b6"
      },
      {
        title: "\u6728\u69FF",
        name: "mauve",
        color: "#9c26b0"
      },
      {
        title: "\u6843\u7C89",
        name: "pink",
        color: "#e03997"
      },
      {
        title: "\u68D5\u8910",
        name: "brown",
        color: "#a5673f"
      },
      {
        title: "\u7384\u7070",
        name: "grey",
        color: "#8799a3"
      },
      {
        title: "\u8349\u7070",
        name: "gray",
        color: "#aaaaaa"
      },
      {
        title: "\u58A8\u9ED1",
        name: "black",
        color: "#333333"
      },
      {
        title: "\u96C5\u767D",
        name: "white",
        color: "#ffffff"
      }
    ];
    const next = (index) => {
      console.log(index);
      if (index === 2) {
        common_vendor.index.reLaunch({
          url: "/pages/gash/gash"
        });
      }
    };
    return { ColorList, next };
  }
};
function _sfc_render(_ctx, _cache, $props, $setup, $data, $options) {
  return common_vendor.e({
    a: common_vendor.f($setup.ColorList, (item, index, i0) => {
      return {
        a: common_vendor.t(item.title),
        b: common_vendor.t(item.name),
        c: common_vendor.n("bg-" + item.name),
        d: index,
        e: common_vendor.o(($event) => $setup.next(index), index)
      };
    }),
    b: _ctx.index < 12
  }, _ctx.index < 12 ? {
    c: common_vendor.f($setup.ColorList, (item, index, i0) => {
      return {
        a: common_vendor.t(item.title),
        b: common_vendor.t(item.name),
        c: common_vendor.n("bg-" + item.name),
        d: index
      };
    })
  } : {});
}
var Component = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["render", _sfc_render], ["__file", "D:/syxcx/syxcx/pages/basics/background.vue"]]);
wx.createComponent(Component);
