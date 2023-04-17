package com.example.demo.dto;

import lombok.Data;

@Data
public class getAuthorizerListDto {
    private String component_appid;
    private String offset;
    private String count;
    private String access_token;

}
