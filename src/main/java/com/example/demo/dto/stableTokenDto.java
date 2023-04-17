package com.example.demo.dto;

import lombok.Data;

@Data
public class stableTokenDto {
    private String grant_type = "client_credential";
    private String appid;
    private String secret;
    private Boolean force_refresh = false;
}
