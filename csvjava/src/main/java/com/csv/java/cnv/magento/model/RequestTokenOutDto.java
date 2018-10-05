package com.csv.java.cnv.magento.model;

import com.csv.java.entity.BaseDto;

//request token dto
public class RequestTokenOutDto extends BaseDto{
    //request token
    private String  token;
    //requet secret
    private String secret;
    //授权rul
    private String authorizeUrl;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getAuthorizeUrl() {
        return authorizeUrl;
    }

    public void setAuthorizeUrl(String authorizeUrl) {
        this.authorizeUrl = authorizeUrl;
    }
}
