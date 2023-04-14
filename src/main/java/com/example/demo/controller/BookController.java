package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.dto.apiComponentToken;
import com.example.demo.dto.redirectUri;
import com.example.demo.utils.AesException;
import com.example.demo.utils.SendPost;
import com.example.demo.utils.WXBizMsgCrypt;
import com.example.demo.utils.WXXmlToMapUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
@Slf4j
@RestController
@RequestMapping("/wxcallback")
public class BookController {

    private final static Logger logger = Logger.getLogger(BookController.class);
    /**
     *
     * request class 引入请求类.
     *
     */
    @Autowired
    private SendPost SendPost;

    private final String component_verify_ticket = "qBzItlAedSAp9az7jw2wWXX11252gV0keOPzAi-kUQaPEa4PU_FYD_EuG9Iw6Y0c_SVpMzM0eLW0ygwol5is7A";
    /**
     * 第三方平台appId
     */
    private static final String PLATFORM_APP_ID = "wx7046963dff72596c";
    /**
     * 第三方平台 secret
     */
    private static final String PLATFORM_APP_SECRET = "f2fdebe6a65dba5e8e85179c0393f0ea";
    /**
     * 第三方平台 消息加解密Key
     */
    private static final String PLATFORM_AES_KEY = "0123456789ABCDEF01234567890ABCDEF0123456789";

    /**
     * 第三方平台 消息校验Token
     */
    private static final String PLATFORM_COMPONENT_TOKEN = "afa4sjAumsSbcp";

    /**
     * 引入请求类 用于接收平台推送给第三方平台帐号的消息与事件，如授权事件通知、component_verify_ticket等
     */
    @PostMapping("/biz/redirectUri")
    public void listBook(@RequestBody redirectUri redirectUri)
    {
        log.info("授权后回调方法/biz/redirectUri===>"+ redirectUri);
        System.out.println("/biz/redirectUri===>请求" + redirectUri);
    }

    /**
     * 引入请求类 用于接收平台推送给第三方平台帐号的消息与事件，如授权事件通知、component_verify_ticket等
     */
    @PostMapping("/biz/ticket")
    public void listBook(@RequestParam("timestamp") String timestamp,
                         @RequestParam("nonce") String nonce,
                         @RequestParam("msg_signature") String msgSignature,
                         @RequestBody String postData)
    {
        log.info("授权事件接收URL,验证票据===>");
        log.info("timestamp,===>" + timestamp);
        log.info("nonce,===>" + timestamp);
        log.info("msgSignature,===>" + msgSignature);
        log.info("postData,===>" + postData);
        parsePequest(timestamp, nonce, msgSignature, postData);

        System.out.println("apiComponentToken请求");

    }
    /* 用于代授权的公众号或小程序的接收平台推送的消息与事件 */
    @PostMapping("/biz/{APPID}")
    public void bizAppId(@RequestBody apiComponentToken obj, @PathVariable(value="APPID") String APPID) {
        System.out.println("apiComponentToken请求");
        System.out.println(APPID);
        System.out.println(obj);
    }

    public void getPreAuthCode(String accessToken) {
        //提交参数设置
        Map<String,String> map=new HashMap<>();
        map.put("component_appid", PLATFORM_APP_ID);
        JSONObject jsoResBody = SendPost.SendPost(map,"https://api.weixin.qq.com/cgi-bin/component/api_create_preauthcode?component_access_token=" + accessToken);
        System.out.println("获取预授权码返回：" + jsoResBody);
    }


    public void getAccessToken(String ticket) {
        //提交参数设置
        Map<String,String> map=new HashMap<>();
        map.put("component_appid", PLATFORM_APP_ID);
        map.put("component_appsecret", PLATFORM_APP_SECRET);
        /* 腾讯推送解密后数据 ticket */
        map.put("component_verify_ticket", ticket);

        log.info("map======>," + map);

        JSONObject jsoResBody = SendPost.SendPost(map,"https://api.weixin.qq.com/cgi-bin/component/api_component_token");

        System.out.println("resBody:------>" + jsoResBody);
        String accessToken = jsoResBody.getString("component_access_token");
        System.out.println("accessToken:------>" + accessToken);
        /* 获取token后，获取预授权码 */
        this.getPreAuthCode(accessToken);
    }


    public String parsePequest(String timeStamp, String nonce, String msgSignature, String postData) {
        try {
            //这个类是微信官网提供的解密类,需要用到消息校验Token 消息加密Key和服务平台appid
            WXBizMsgCrypt pc = new WXBizMsgCrypt(PLATFORM_COMPONENT_TOKEN, PLATFORM_AES_KEY, PLATFORM_APP_ID);
            String xml = pc.decryptMsg(msgSignature, timeStamp, nonce, postData);
            Map<String, String> result = WXXmlToMapUtil.xmlToMap(xml);// 将xml转为map
            String componentVerifyTicket = MapUtils.getString(result, "ComponentVerifyTicket");

            System.out.println("componentVerifyTicket====>," + componentVerifyTicket);

            if (StringUtils.isNotEmpty(componentVerifyTicket)) {
                getAccessToken(componentVerifyTicket);
                // 存储平台授权票据,保存ticket
//                redisTemplate.opsForValue().set("component_verify_ticket", componentVerifyTicket, 60 * 10, TimeUnit.SECONDS);
//                String verifyTicket = redisTemplate.opsForValue().get("component_verify_ticket").toString();
            } else {
                throw new RuntimeException("微信开放平台，第三方平台获取【验证票据】失败");
            }
        } catch (AesException e) {
            e.printStackTrace();
        }
        return "success";
    }
}
