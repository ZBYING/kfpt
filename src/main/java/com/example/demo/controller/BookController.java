package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.pojo.WxAuthCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.jdbc.Null;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/wxcallback")
public class BookController {

    private final static Logger logger = Logger.getLogger(BookController.class);

    @Autowired
    private com.example.demo.impl.wxImpl wxImpl;

    @Autowired
    private com.example.demo.mapper.WxAuthCodeMapper WxAuthCodeMapper;
    /**
     * 引入请求类 用于接收平台推送给第三方平台帐号的消息与事件，如授权事件通知、component_verify_ticket等
     *
     * @return success
     */
    @PostMapping("/biz/ticket")
    public String listBook(@RequestParam("timestamp") String timestamp,
                           @RequestParam("nonce") String nonce,
                           @RequestParam("msg_signature") String msgSignature,
                           @RequestBody String postData)
    {
        log.info("授权事件接收URL,验证票据/biz/ticket===>");
        log.info("timestamp,===>" + timestamp);
        log.info("nonce,===>" + timestamp);
        log.info("msgSignature,===>" + msgSignature);
        log.info("postData,===>" + postData);
        return wxImpl.parsePequest(timestamp, nonce, msgSignature, postData);
    }
    /* 用于代授权的公众号或小程序的接收平台推送的消息与事件 */
    @PostMapping("/biz/{APPID}")
    public void bizAppId(@RequestBody apiComponentToken obj, @PathVariable(value="APPID") String APPID) {
        System.out.println("apiComponentToken请求");
        System.out.println(APPID);
        System.out.println(obj);
    }
    /**
     * 引入请求类 获取access_token
     *
     * @return access_token
     */
    @PostMapping("/component/stableToken")
    @CrossOrigin
    public Object get_stable_token(@RequestBody stableTokenDto stableToken) {
        logger.info("stableToken请求====>获取第三方平台access_token");
        logger.info(stableToken);
        return wxImpl.getStableToken(stableToken);
    }
    /**
     * 引入请求类 用于接收平台推送给第三方平台帐号的消息与事件，如授权事件通知、component_verify_ticket等
     *
     * @return
     */
    @PostMapping("/component/getAuthorizerList")
    @CrossOrigin
    public Object get_authorizer_list(@RequestBody getAuthorizerListDto getAuthorizerListDto) {
        logger.info("getAuthorizerList请求====>获取授权给平台的小程序appid");
        logger.info(getAuthorizerListDto);

        return wxImpl.getAuthorizerList(getAuthorizerListDto);
    }
    /**
     * 引入请求类 获取绑定小程序详细信息
     *
     * @return
     */
    @PostMapping("/component/getAuthorizerInfo")
    @CrossOrigin
    public Object getAuthorizerInfo(@RequestBody getAuthorizerInfoDto getAuthorizerInfoDto) {
        logger.info("getAuthorizerInfo请求====>获取授权给平台的小程序getAuthorizerInfo信息");
        logger.info(getAuthorizerInfoDto);

        return wxImpl.getAuthorizerInfo(getAuthorizerInfoDto);
    }

    /**
     * 引入请求类 获取绑定小程序详细信息
     *
     * @return
     */
    @PostMapping("/component/getAuthorizerAccessToken")
    @CrossOrigin
    public Object getAuthorizerAccessToken(@RequestBody getAuthorizerTokenDto getAuthorizerTokenDto) {
        logger.info("getAuthorizerAccessToken请求====>获取getAuthorizerAccessTokeno信息");
        logger.info(getAuthorizerTokenDto);

        return wxImpl.getAuthorizerAccessToken(getAuthorizerTokenDto);
    }

    /**
     * 引入请求类 使用授权码获取授权信息
     *
     * @return
     */
    @PostMapping("/component/getQueryAuth")
    @CrossOrigin
    public Object getQueryAuth(@RequestBody getQueryAuthDto getQueryAuthDto) {
        logger.info("getQueryAuth请求====>获取getQueryAuth信息");
        logger.info(getQueryAuthDto);
        return wxImpl.getQueryAuth(getQueryAuthDto);
    }
}
