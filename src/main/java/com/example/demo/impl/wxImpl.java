package com.example.demo.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.demo.controller.BookController;
import com.example.demo.dto.*;
import com.example.demo.pojo.WxAuthCode;
import com.example.demo.utils.AesException;
import com.example.demo.utils.WXBizMsgCrypt;
import com.example.demo.utils.WXXmlToMapUtil;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
@Component
public class wxImpl {
    private final static Logger logger = Logger.getLogger(BookController.class);
    /**
     *
     * request class 引入请求类.
     *
     */
    @Autowired
    private com.example.demo.utils.SendPost sendPost;
    @Autowired
    private com.example.demo.mapper.WxAuthCodeMapper WxAuthCodeMapper;
    /**
     * 第三方平台appId
     */
    private final String PLATFORM_APP_ID = "wx7046963dff72596c";
    /**
     * 第三方平台 secret
     */
    private final String PLATFORM_APP_SECRET = "3efe758682c5720af211e9aa74fb4095";
    /**
     * 第三方平台 消息加解密Key
     */
    private final String PLATFORM_AES_KEY = "0123456789ABCDEF01234567890ABCDEF0123456789";

    /**
     * 第三方平台 消息校验Token
     */
    private final String PLATFORM_COMPONENT_TOKEN = "afa4sjAumsSbcp";

    /**
     *
     * 获取预授权码
     *
     */
    public void getPreAuthCode(String accessToken) {
        //提交参数设置
        Map<String,String> map=new HashMap<>();
        map.put("component_appid", PLATFORM_APP_ID);
        JSONObject jsoResBody = sendPost.SendPost(map,"https://api.weixin.qq.com/cgi-bin/component/api_create_preauthcode?component_access_token=" + accessToken);
        System.out.println("获取预授权码返回：" + jsoResBody);
        String authCode = jsoResBody.getString("pre_auth_code");
        /* 插入微信第三方平台appi， authoCode */
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("wxAppid", PLATFORM_APP_ID);
        if (WxAuthCodeMapper.selectCount(queryWrapper) > 0) {
            UpdateWrapper<WxAuthCode> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("wxAppid", PLATFORM_APP_ID).set("authCode", authCode);
            WxAuthCodeMapper.update(null, updateWrapper);
        } else {
            WxAuthCodeMapper.insert(new WxAuthCode(PLATFORM_APP_ID, authCode, "null"));
        }
    }

    /**
     *
     * 根据componentVerifyTicket 获取accessToken
     *
     */
    public String getAccessToken(String ticket) {
        //提交参数设置
        Map<String,String> map=new HashMap<>();
        map.put("component_appid", PLATFORM_APP_ID);
        map.put("component_appsecret", PLATFORM_APP_SECRET);
        /* 腾讯推送解密后数据 ticket */
        map.put("component_verify_ticket", ticket);

        logger.info("map======>," + map);

        JSONObject jsoResBody = sendPost.SendPost(map,"https://api.weixin.qq.com/cgi-bin/component/api_component_token");

        System.out.println("resBody:------>" + jsoResBody);
        String accessToken = jsoResBody.getString("component_access_token");
        System.out.println("accessToken:------>" + accessToken);
        /* 获取token后，获取预授权码 */
        return accessToken;
    }

    /**
     *
     * 获取腾讯推送数据信息，解密
     *
     */
    public String parsePequest(String timeStamp, String nonce, String msgSignature, String postData) {
        try {
            //这个类是微信官网提供的解密类,需要用到消息校验Token 消息加密Key和服务平台appid
            WXBizMsgCrypt pc = new WXBizMsgCrypt(PLATFORM_COMPONENT_TOKEN, PLATFORM_AES_KEY, PLATFORM_APP_ID);
            String xml = pc.decryptMsg(msgSignature, timeStamp, nonce, postData);
            Map<String, String> result = WXXmlToMapUtil.xmlToMap(xml);
            String componentVerifyTicket = MapUtils.getString(result, "ComponentVerifyTicket");

            System.out.println("componentVerifyTicket====>," + componentVerifyTicket);

            if (StringUtils.isNotEmpty(componentVerifyTicket)) {
                /* 获取accessToken */
                String accessToken = getAccessToken(componentVerifyTicket);
                /* 根据accessToken获取getPreAuthCode */
                getPreAuthCode(accessToken);

                // 存储平台授权票据,保存ticket
                // redisTemplate.opsForValue().set("component_verify_ticket", componentVerifyTicket, 60 * 10, TimeUnit.SECONDS);
                // String verifyTicket = redisTemplate.opsForValue().get("component_verify_ticket").toString();
            } else {
                throw new RuntimeException("微信开放平台，第三方平台获取【验证票据】失败");
            }
        } catch (AesException e) {
            e.printStackTrace();
        }
        return "success";
    }
    /**
     *
     * 获取绑定第三方平台小程序list
     *
     */
    public Object getAuthorizerList(getAuthorizerListDto getAuthorizerListDto)  {
        JSONObject jsoResBody = null;
        try {
            String accessToken = getAuthorizerListDto.getAccess_token();
            //提交参数设置
            Map reqMap = new HashMap<>();
            reqMap.put("component_appid", getAuthorizerListDto.getComponent_appid());
            reqMap.put("offset", 0);
            reqMap.put("count", 100);

            logger.info("map======>," + reqMap);
            System.out.println("getAuthorizerList-accessToken:------>" + reqMap);
            jsoResBody = sendPost.SendPost(reqMap,"https://api.weixin.qq.com/cgi-bin/component/api_get_authorizer_list?access_token=" + accessToken);
            System.out.println("getAuthorizerList-resBody:------>" + jsoResBody);
        } catch (Exception e) {
            logger.error("getAuthorizerList异常====>",e);
        }
        return jsoResBody;
    }
    /**
     *
     * 获取第三方平台token
     *
     */
    public Object getStableToken(stableTokenDto stableTokenDto)  {
        JSONObject jsoResBody = null;
        try {
            //提交参数设置
            Map reqMap = new HashMap<>();
            reqMap.put("appid", stableTokenDto.getAppid());
            reqMap.put("secret", PLATFORM_APP_SECRET);
            reqMap.put("force_refresh", stableTokenDto.getForce_refresh());
            reqMap.put("grant_type", stableTokenDto.getGrant_type());

            logger.info("getAuthorizerList-accessToken:------>" + reqMap);
            jsoResBody = sendPost.SendPost(reqMap,"https://api.weixin.qq.com/cgi-bin/stable_token");
            logger.info("getAuthorizerList-resBody:------>" + jsoResBody);
        } catch (Exception e) {
            logger.error("getAuthorizerList异常====>",e);
        }
        return jsoResBody;
    }
    /**
     *
     * 获取小程序授权详细信息
     *
     */
    public Object getAuthorizerInfo(getAuthorizerInfoDto getAuthorizerInfoDto)  {
        JSONObject jsoResBody = null;
        try {
            String accessToken = getAuthorizerInfoDto.getAccess_token();
            //提交参数设置
            Map reqMap = new HashMap<>();
            reqMap.put("component_appid", getAuthorizerInfoDto.getComponent_appid());
            reqMap.put("authorizer_appid", getAuthorizerInfoDto.getAuthorizer_appid());

            logger.info("getAuthorizerInfo-info:------>" + reqMap);
            jsoResBody = sendPost.SendPost(reqMap,"https://api.weixin.qq.com/cgi-bin/component/api_get_authorizer_info?access_token=" + accessToken);
            logger.info("getAuthorizerList-resBody:------>" + jsoResBody);
        } catch (Exception e) {
            logger.error("getAuthorizerList异常====>",e);
        }
        return jsoResBody;
    }
    /**
     *
     * 获取授权帐号调用令牌
     *
     */
    public Object getAuthorizerAccessToken(getAuthorizerTokenDto getAuthorizerTokenDto)  {
        JSONObject jsoResBody = null;
        try {
            String accessToken = getAuthorizerTokenDto.getComponent_access_token();
            //提交参数设置
            Map reqMap = new HashMap<>();
            reqMap.put("component_appid", getAuthorizerTokenDto.getComponent_appid());
            reqMap.put("authorizer_appid", getAuthorizerTokenDto.getAuthorizer_appid());
            reqMap.put("authorizer_refresh_token", getAuthorizerTokenDto.getAuthorizer_refresh_token());

            logger.info("getAuthorizerInfo-info:------>" + reqMap);
            jsoResBody = sendPost.SendPost(reqMap,"https://api.weixin.qq.com/cgi-bin/component/api_authorizer_token?access_token=" + accessToken);
            logger.info("getAuthorizerList-resBody:------>" + jsoResBody);
        } catch (Exception e) {
            logger.error("getAuthorizerList异常====>",e);
        }
        return jsoResBody;
    }
    /**
     *
     * 获取刷新令牌
     *
     */
    public Object getQueryAuth(getQueryAuthDto getQueryAuthDto)  {

        JSONObject jsoResBody = null;
        try {
            String component_access_token = getQueryAuthDto.getComponent_access_token();
            String component_appid = getQueryAuthDto.getComponent_appid();
            /* 查询授权码 */
            WxAuthCode wxAuthCode = WxAuthCodeMapper.selectById(component_appid);
            String authCode = wxAuthCode.getAuthCode();
            //提交参数设置
            Map reqMap = new HashMap<>();
            reqMap.put("component_appid", component_appid);
            reqMap.put("authorization_code", authCode);

            logger.info("getAuthorizerInfo-info:------>" + reqMap);
            jsoResBody = sendPost.SendPost(reqMap,"https://api.weixin.qq.com/cgi-bin/component/api_query_auth?component_access_token=" + component_access_token);
            logger.info("getAuthorizerList-resBody:------>" + jsoResBody);
        } catch (Exception e) {
            logger.error("getAuthorizerList异常====>",e);
        }
        return jsoResBody;
    }
    /**
     *
     * 获取授权码
     *
     */
    public String getAuthCode(getQueryAuthDto getQueryAuthDto)  {
        String authCode = null;
        try {
            //提交参数设置
            String appid = getQueryAuthDto.getComponent_appid();
            logger.info("getComponent_appid-appid:------>" + appid);
            WxAuthCode wxAuthCode = WxAuthCodeMapper.selectById(appid);
            logger.info("wxAuthCode===========>" + wxAuthCode);
            authCode = wxAuthCode.getAuthCode();
            logger.info("authCode===========>" + authCode);
        } catch (Exception e) {
            logger.error("getAuthCode异常====>",e);
        }
        return authCode;
    }
}
