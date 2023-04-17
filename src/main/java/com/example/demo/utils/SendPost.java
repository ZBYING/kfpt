package com.example.demo.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class SendPost {

    @Autowired
    private RestTemplate restTemplate;

    public JSONObject SendPost(Map map, String url) {

        // 请求头设置
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // 组装请求体
        HttpEntity<Map<String, String>> request = new HttpEntity<Map<String, String>>(map, headers);
        System.out.println("REQBODY：" + map);
        // 发送post请求，并输出结果
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
        String body = responseEntity.getBody(); // 获取响应体
        HttpStatus statusCode = responseEntity.getStatusCode(); // 获取响应码
        int statusCodeValue = responseEntity.getStatusCodeValue(); // 获取响应码值
        HttpHeaders responseHeaders = responseEntity.getHeaders(); // 获取响应头

        System.out.println("body：" + body);
        System.out.println("statusCode：" + statusCode);
        System.out.println("statusCodeValue：" + statusCodeValue);
        System.out.println("headers：" + responseHeaders);

        return JSON.parseObject(body);
    }
}
