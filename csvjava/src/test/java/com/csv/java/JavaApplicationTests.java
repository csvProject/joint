package com.csv.java;

import com.csv.java.OnlineDataService.OrderDataService;
import com.csv.java.OnlineDataService.impl.OrderDataServiceImpl;
import com.csv.java.common.tool.http.HttpsUtils;
import com.csv.java.cnv.magento.serivce.imp.MagentoAuthServiceImp;
import com.csv.java.net.magja.soap.SoapClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sun.misc.BASE64Encoder;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = JavaApplication.class)
public class JavaApplicationTests {

    @Autowired
    private OrderDataService orderDataService;

    @Test
    public void testsoap() {

        //orderDataService.GenenateOrderDataFromMagento();
        orderDataService.testTransactional();
    }

    @Test
    public void contextLoads() {
        //System.out.println(UUIDUtil.getUUID());
        MagentoAuthServiceImp.showProducts();
    }

    CloseableHttpClient httpClient;
    CloseableHttpResponse httpResponse;

    String oauth_consumer_key ="8a96w87dedy0rr5wlt8jetacdzx6dnsv";
    String oauth_consumer_secret= "9jgh6zru6yo7ojnp1awhnzcllrx8cdjf";
    String oauth_callback ="http://www.ewiderbuy.com/oauth_admin.php";
    String requst_token_url ="http://www.ewiderbuy.com/oauth/initiate";


    String oauth_request_method ="POST";

    String oauth_nonce;
    String oauth_timestamp;
    String oauth_signature_method = "HMAC-SHA1";
    String oauth_signature;
    String oauth_version = "1.0";
    @Test
    public void testoauth() {

        String basestring ="";
        oauth_nonce = set_nonce();
        oauth_timestamp =set_timestamp();
        try{
            basestring = set_basestring(oauth_nonce,oauth_timestamp);
        }catch (UnsupportedEncodingException ex) {

        }

        oauth_signature = hmacsha1(basestring,oauth_consumer_secret + "&");

        Logger logger = LoggerFactory.getLogger(HttpsUtils.class);
        String Charset = "UTF-8";


        try {
            HttpPost httpPost = new HttpPost("http://www.ewiderbuy.com/oauth/initiate?oauth_callback="+
                    URLEncoder.encode(oauth_callback, "utf-8"));
//            httpPost.addHeader("",);

            //httpPost.addHeader("Content-type","application/xml;charset=UTF8");

            String auth_head ="OAuth oauth_callback="+URLEncoder.encode(oauth_callback, "utf-8")+
                    ",oauth_consumer_key="+oauth_consumer_key+
                    ",oauth_nonce="+oauth_nonce+
                    ",oauth_signature_method="+oauth_signature_method+
                    ",oauth_signature="+oauth_signature+
                    ",oauth_timestamp="+oauth_timestamp+
                    ",oauth_version="+oauth_version;
            String aa ="";
            /*httpPost.addHeader("Authorization",auth_head);
            StringEntity body  = new StringEntity("","UTF-8");

            httpPost.setEntity(body);
            httpClient = HttpClients.createDefault();
            httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            if (httpEntity != null) {
                String jsObject = EntityUtils.toString(httpEntity, Charset);
                String aa;
            } else {

            }*/
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
           /* try {
                httpResponse.close();
                httpClient.close();
                logger.info("请求流关闭完成");
            } catch (IOException e) {
                logger.info("请求流关闭出错");
                e.printStackTrace();
            }*/
        }
    }

    private String set_nonce() {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 18; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    private String set_timestamp() {

        /*Date date = new Date();
        long time = date.getTime()/1000;
        return (time + "").substring(0, 10);*/

        long time = System.currentTimeMillis();
        String nowTimeStamp = String.valueOf(time / 1000);
        return nowTimeStamp;
    }

    private String set_basestring(String oauth_nonce,String oauth_timestamp) throws UnsupportedEncodingException {
        String bss;

        bss = oauth_request_method + "&"
                + URLEncoder.encode(requst_token_url, "utf-8") + "&";
        String bsss = "oauth_callback="
                + URLEncoder.encode(oauth_callback, "utf-8")
                + "&oauth_consumer_key=" + oauth_consumer_key + "&oauth_nonce="
                + oauth_nonce + "&oauth_signature_method="
                + oauth_signature_method + "&oauth_timestamp="
                + oauth_timestamp + "&oauth_version=" + oauth_version;
        bsss = URLEncoder.encode(bsss, "utf-8");
        return bss + bsss;
    }

    private String hmacsha1(String data, String key) {
        byte[] byteHMAC = null;
        try {
            Mac mac = Mac.getInstance("HmacSHA1");
            SecretKeySpec spec = new SecretKeySpec(key.getBytes(), "HmacSHA1");
            mac.init(spec);
            byteHMAC = mac.doFinal(data.getBytes());
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException ignore) {
        }
        String oauth = new BASE64Encoder().encode(byteHMAC);

        try{
            oauth = URLEncoder.encode(oauth, "utf-8");
        }catch (UnsupportedEncodingException ex) {

        }
        return oauth;
    }

}
