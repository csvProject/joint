/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csv.java.cnv.magento;

import org.scribe.builder.api.DefaultApi10a;
import org.scribe.model.Token;

/**
 *
 * @author Owner
 */
public class MagentoThreeLeggedOAuth extends DefaultApi10a {

    private static String baseUrl;

    public MagentoThreeLeggedOAuth(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Override
    public String getRequestTokenEndpoint() {
        return baseUrl + "oauth/initiate";

    }

    @Override
    public String getAccessTokenEndpoint() {
        return baseUrl + "oauth/token";
    }

    @Override
    public String getAuthorizationUrl(Token requestToken) {
        //oauth/authorize?oauth_token= 、、customer用户使用
        //admin/oauth_authorize?oauth_token= 、、admin用户使用
        return baseUrl + "ekuan/oauth_authorize?oauth_token="
        + requestToken.getToken(); //this implementation is for admin roles only...
    }
}
