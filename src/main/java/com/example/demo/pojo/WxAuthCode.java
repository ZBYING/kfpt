package com.example.demo.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("wx_auth_code")
public class WxAuthCode {
    @TableId(value = "wxAppid")
    private String wxAppid;
    @TableField(value = "authCode")
    private String authCode;

    @TableField(value = "bak")
    private String bak;

    public WxAuthCode() {}

    public WxAuthCode(String wxAppid, String authCode, String bak) {
        this.wxAppid = wxAppid;
        this.authCode = authCode;
        this.bak = bak;
    }

    public String getWxAppid() {
        return wxAppid;
    }

    public void setWxAppid(String wxAppid) {
        this.wxAppid = wxAppid;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

}
