package com.example.demo.dto;

import lombok.Data;

@Data
public class getAuthorizerInfoDto {
    public String authorizer_appid;
    public String component_appid;
    private String access_token;
}
