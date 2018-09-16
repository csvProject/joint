package com.csv.java.common.result;


import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
  * ResultUtil
  * @author wkm
  * @since 2018/7/30
  */
public class ResultUtil {

    public static Result success(Object object) {

        return success(ResultEnum.SUCCESS.getCode(),ResultEnum.SUCCESS.getMsg(),object,0);
    }

    public static Result success(Object object,int dataCount) {

        return success(ResultEnum.SUCCESS.getCode(),ResultEnum.SUCCESS.getMsg(),object,dataCount);
    }

    public static Result success() {
        return success(ResultEnum.SUCCESS.getCode(),ResultEnum.SUCCESS.getMsg(),null,0);
    }
    public static Result success(Integer code, String msg ,Object object) {

        return success(code,msg,object,0);
    }

    public static Result success(Integer code, String msg ,Object object,int dataCount) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(object);
        result.setCount(dataCount);
        return result;
    }

    public static Result error(Integer code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

     public static Result error(Integer code, String msg, HttpServletResponse response) {
         Result result = new Result();
         result.setCode(code);
         result.setMsg(msg);
         try {
             response.setStatus(code);
             response.getWriter().append("server error");
         } catch (IOException e) {
             e.printStackTrace();
         }
         return null;
     }
}