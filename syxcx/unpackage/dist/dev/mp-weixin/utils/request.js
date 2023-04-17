"use strict";
var common_vendor = require("../common/vendor.js");
var env = require("../env.js");
function request(options) {
  return new Promise((resolve, reject) => {
    if (options.loadTitle == "0")
      ;
    else {
      common_vendor.index.showLoading({
        title: options.loadTitle || "\u52A0\u8F7D\u4E2D...",
        mask: true
      });
    }
    let url = "";
    url = env.BASE_URL + options.url || "";
    if (!options.data) {
      options.data = {};
    }
    let reqData, data;
    console.log(`\u52A0\u5BC6\u524D\u62A5\u6587==>${options.url}====>`, options.data);
    reqData = JSON.stringify(options.data);
    data = reqData;
    common_vendor.index.request({
      url,
      method: options.method || "GET",
      data,
      dataType: options.dataType || "json",
      timeout: 3e5,
      header: {},
      success: (res) => {
        resolve(res);
        common_vendor.index.hideLoading();
      },
      fail: (res) => {
        console.log("\u8BF7\u6C42\u8DEF\u5F84", options.url);
        console.log("\u9519\u8BEF\u4FE1\u606F", res);
        common_vendor.index.hideLoading();
        common_vendor.index.showModal({
          content: res.errMsg,
          showCancel: false
        });
        reject(res);
      },
      complete: (res) => {
        common_vendor.index.hideLoading();
      }
    });
  });
}
exports.request = request;
