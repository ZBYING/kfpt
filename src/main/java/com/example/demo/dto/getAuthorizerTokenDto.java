package com.example.demo.dto;

import lombok.Data;

@Data
public class getAuthorizerTokenDto {
    private String component_access_token;
    private String component_appid;
    private String authorizer_appid;
    private String authorizer_refresh_token;
}
