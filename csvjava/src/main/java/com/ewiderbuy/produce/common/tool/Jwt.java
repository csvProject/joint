package com.ewiderbuy.produce.common.tool;

import com.ewiderbuy.produce.common.result.Result;
import com.ewiderbuy.produce.common.result.ResultUtil;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.util.Base64;
import net.minidev.json.JSONObject;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Jwt {

    /**
     * 秘钥
     */
    private static final byte[] SECRET="c3b50bdc111774346e04147af1e42c20".getBytes(); //gtja Md5

    /**
     * token过期(token失效了)
     */
    public static final Integer EXPIRED=-1;

    /**
     * 校验失败（token不一致）
     */
    public static final Integer FAIL=0;

    /**
     * 校验成功
     */
    public static final Integer SUCCESS=1;

    /**
     * 代码抛异常（校验token时代码出错）
     */
    public static final Integer EXCEPT=2;

    /**
     * 生成token，该方法只在用户登录成功后调用
     * @param playLoad 集合，主要存储用户id，token生成时间，token过期时间等
     * @return token字符串
     * @throws KeyLengthException
     */
    public static String createToken(Map<String, Object> playLoad){
        ///B
        JSONObject userInfo = new JSONObject(playLoad);
        Payload payload = new Payload(userInfo);

        ///A
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS256);
        // 创建一个 JWS object
        JWSObject jwsObject = new JWSObject(header, payload);

        try {
            //创建 HMAC signer
            JWSSigner signer = new MACSigner(SECRET);
            jwsObject.sign(signer);
        } catch (JOSEException e) {
            System.err.println("签名失败" + e.getMessage());
            e.printStackTrace();
        }
        return jwsObject.serialize();
    }

    /**
     * 校验token是否合法，返回Map集合,集合中主要包含  isSuccess是否成功  status状态码   data鉴权成功后从token中提取的数据
     * 该方法在过滤器中调用，每次请求API时都校验
     * @param token
     * @return
     * @throws KeyLengthException
     */
    public static Result validToken(String token){
        JWSObject jwsObject=null;
        Payload payload=null;
        try {
            String[] tokenArr=token.split("\\.");
            payload = new Payload(new Base64(tokenArr[1]).decodeToString());
            ///A
            JWSHeader header = new JWSHeader(JWSAlgorithm.HS256);
            // 创建一个 JWS object
            jwsObject = new JWSObject(header, payload);

            //创建 HMAC signer
            JWSSigner signer = new MACSigner(SECRET);
            jwsObject.sign(signer);
        } catch (Exception e) {
            e.printStackTrace();
            //异常
            return ResultUtil.success(EXCEPT,"校验异常："+e.getMessage(),null,0);

        }

        if( jwsObject.serialize().equals(token)){
            JSONObject jsonOBj= payload.toJSONObject();
            long extTime=(long) jsonOBj.get("ext");
            long curTime=new Date().getTime();
            if(curTime>extTime){
                //过期了
                return ResultUtil.success(EXCEPT,"token过期",null,0);

            }else{
                //没有过期
                return ResultUtil.success(SUCCESS,"有效token",null,0);
            }
        }else{
            //校验失败
            //没有过期
            return ResultUtil.success(FAIL,"校验失败",null,0);

        }
    }


    /**
     * 校验token是否合法，返回Map集合,集合中主要包含  isSuccess是否成功  status状态码   data鉴权成功后从token中提取的数据
     * 该方法在过滤器中调用，每次请求API时都校验
     * @param token
     * @return
     * @throws KeyLengthException
     */
    public static int validIsToken(String token){
        JWSObject jwsObject=null;
        Payload payload=null;
        try {
            String[] tokenArr=token.split("\\.");
            payload = new Payload(new Base64(tokenArr[1]).decodeToString());
            ///A
            JWSHeader header = new JWSHeader(JWSAlgorithm.HS256);
            // 创建一个 JWS object
            jwsObject = new JWSObject(header, payload);

            //创建 HMAC signer
            JWSSigner signer = new MACSigner(SECRET);
            jwsObject.sign(signer);
        } catch (Exception e) {
            e.printStackTrace();
            // 校验异常
            return EXCEPT;
        }

        if( jwsObject.serialize().equals(token)){
            JSONObject jsonOBj= payload.toJSONObject();
            long extTime=(long) jsonOBj.get("ext");
            long curTime=new Date().getTime();
            if(curTime>extTime){
                //过期了
                return EXPIRED;
            }else{
                //没有过期 检验成功
                return SUCCESS;
            }

        }else{
            //校验失败
            //没有过期
            return FAIL;
        }
    }

    /**
     * 重置TOKEN时间
     * @param token
     * @return
     */
    public static String setToken(String token){
        Map<String, Object> resultMap=new HashMap<String, Object>();
        JWSObject jwsObject=null;
        Payload payload=null;
        try {
            String[] tokenArr=token.split("\\.");
            payload = new Payload(new Base64(tokenArr[1]).decodeToString());
            JWSHeader header = new JWSHeader(JWSAlgorithm.HS256);
            // 创建一个 JWS object
            jwsObject = new JWSObject(header, payload);

            //创建 HMAC signer
            JWSSigner signer = new MACSigner(SECRET);
            jwsObject.sign(signer);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        if( jwsObject.serialize().equals(token)){
            JSONObject jsonOBj= payload.toJSONObject();
            String userId = (String) jsonOBj.get("userId");
//            String adminFlag = (String) jsonOBj.get("adminFlag");
            return  ServiceUtil.getToken(userId);
        }
        return null;
    }

    public static void main(String[] args){
        System.out.println(500 * 60 * 60 * 5);
    }
}
