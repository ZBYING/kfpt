"use strict";
var utils_request = require("../utils/request.js");
const apiUrl = "/wxcallback";
const api = {
  getComponent_access_token(data) {
    return utils_request.request({
      url: `${apiUrl}/component/stableToken`,
      method: "POST",
      data
    });
  },
  getAppidList(data) {
    return utils_request.request({
      url: `${apiUrl}/component/getAuthorizerList`,
      method: "POST",
      data
    });
  },
  getAuthorizerInfo(data) {
    return utils_request.request({
      url: `${apiUrl}/component/getAuthorizerInfo`,
      method: "POST",
      data
    });
  }
};
exports.api = api;
