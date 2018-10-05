package com.csv.java.cnv.magento.model;

import com.csv.java.entity.BaseDto;

//request token dto
public class ApiTokenInDto extends BaseDto{
    //api token
    private String apiKey;
    //api secret
    private String apiSecret;
    //授权rul
    private String authorizeUrl;
    //请求token
    private String requestToken;
    //请求secret
    private String requestSecret;
    //访问token 校验码
    private String verifierStr;

    //访问 token
    private String accessToken;
    //访问 secret
    private String accessSecret;

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getApiSecret() {
        return apiSecret;
    }

    public void setApiSecret(String apiSecret) {
        this.apiSecret = apiSecret;
    }

    public String getAuthorizeUrl() {
        return authorizeUrl;
    }

    public void setAuthorizeUrl(String authorizeUrl) {
        this.authorizeUrl = authorizeUrl;
    }

    public String getRequestToken() {
        return requestToken;
    }

    public void setRequestToken(String requestToken) {
        this.requestToken = requestToken;
    }

    public String getRequestSecret() {
        return requestSecret;
    }

    public void setRequestSecret(String requestSecret) {
        this.requestSecret = requestSecret;
    }

    public String getVerifierStr() {
        return verifierStr;
    }

    public void setVerifierStr(String verifierStr) {
        this.verifierStr = verifierStr;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessSecret() {
        return accessSecret;
    }

    public void setAccessSecret(String accessSecret) {
        this.accessSecret = accessSecret;
    }
}
