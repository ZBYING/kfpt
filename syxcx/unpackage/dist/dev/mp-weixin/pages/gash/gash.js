"use strict";
var common_vendor = require("../../common/vendor.js");
const _sfc_main = {
  props: {
    cardList: {
      type: Array,
      default: () => {
        return [
          {
            prizeImgUrl: "../../static/gashapon/1.png"
          },
          {
            prizeImgUrl: "../../static/gashapon/2.png"
          },
          {
            prizeImgUrl: "../../static/gashapon/1.png"
          },
          {
            prizeImgUrl: "../../static/gashapon/2.png"
          },
          {
            prizeImgUrl: "../../static/gashapon/1.png"
          },
          {
            prizeImgUrl: "../../static/gashapon/2.png"
          }
        ];
      }
    },
    fileList: {
      type: Array,
      default: () => {
        return [];
      }
    }
  },
  data() {
    return {
      start: false,
      qiu: true,
      nui: "",
      ani: "",
      bgImg: "../../static/gashapon/123.png",
      btnImg: "../../static/gashapon/btn.png",
      wImg: "../../static/gashapon/0.png",
      modalName: null
    };
  },
  computed: {},
  onLoad(options) {
  },
  mounted() {
    this.fileList.forEach((item) => {
      if (item.busType == "3") {
        this.bgImg = item.filePath;
      }
      if (item.busType == "4") {
        this.btnImg = item.filePath;
      }
    });
  },
  methods: {
    showModal(e) {
      console.log(e);
      this.modalName = "Modal";
    },
    hideModal(e) {
      this.modalName = null;
    },
    eggPlay() {
      this.startEggGame();
    },
    startEggGame() {
      this.start = true;
      setTimeout(() => {
        this.start = false;
        this.qiu = false;
        var animation2 = common_vendor.index.createAnimation({
          duration: 1500,
          timingFunction: "ease"
        });
        animation2.opacity(1).step();
        this.ani = animation2.export();
        console.log("\u626D\u86CB\u7ED3\u675F");
        this.showModal();
      }, 3e3);
      this.qiu = true;
      var animation = common_vendor.index.createAnimation({
        duration: 1500,
        timingFunction: "ease"
      });
      animation.opacity(0).step();
      this.ani = animation.export();
    }
  }
};
function _sfc_render(_ctx, _cache, $props, $setup, $data, $options) {
  return {
    a: $data.bgImg,
    b: $data.wImg,
    c: common_vendor.f($props.cardList, (item, index, i0) => {
      return {
        a: common_vendor.n($data.start ? "ball_" + (index + 1) + " weiyi_" + (index + 1) : "ball_" + (index + 1)),
        b: item.prizeImgUrl,
        c: index
      };
    }),
    d: $data.qiu,
    e: $data.ani,
    f: common_vendor.n($data.start ? "go1" : ""),
    g: common_vendor.o((...args) => $options.eggPlay && $options.eggPlay(...args)),
    h: $data.btnImg,
    i: common_vendor.o((...args) => $options.hideModal && $options.hideModal(...args)),
    j: common_vendor.n($data.modalName == "Modal" ? "show" : "")
  };
}
var MiniProgramPage = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["render", _sfc_render], ["__scopeId", "data-v-748f9a66"], ["__file", "D:/syxcx/kfpt/syxcx/pages/gash/gash.vue"]]);
wx.createPage(MiniProgramPage);
