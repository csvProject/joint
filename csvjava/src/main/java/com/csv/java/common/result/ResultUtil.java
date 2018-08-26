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
        Result result = new Result();
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMsg(ResultEnum.SUCCESS.getMsg());
        result.setData(object);
        return result;
    }

    public static Result success() {
        return success(null);
    }
    public static Result success(Integer code, String msg ,Object object) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(object);
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