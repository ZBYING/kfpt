package com.example.demo.dto;

import lombok.Data;

@Data
public class getQueryAuthDto {
    private String component_access_token;
    private String component_appid;
    private String authorization_code;
}
