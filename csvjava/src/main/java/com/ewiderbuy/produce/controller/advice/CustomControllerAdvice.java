package com.ewiderbuy.produce.controller.advice;


import com.ewiderbuy.produce.common.exception.CustomException;
import com.ewiderbuy.produce.common.exception.DataNullException;
import com.ewiderbuy.produce.common.result.Result;
import com.ewiderbuy.produce.common.result.ResultUtil;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
  * CustomControllerAdvice Controller自定义处理
  * @author wkm
  * @since 2018/7/30
  */
@ControllerAdvice
public class CustomControllerAdvice {
     /**
      * 应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器
      * @param binder
      */
     @InitBinder
     public void initBinder(WebDataBinder binder) {}

     /**
      * 把值绑定到Model中，使全局@RequestMapping可以获取到该值
      * @param model
      */
     @ModelAttribute
     public void addAttributes(Model model) {
//         magentomodel.addAttribute("author", "Magical Sam");
     }

     /**
      * 全局异常捕捉处理
      * @param ex
      * @return
      */
     @ResponseBody
//     @ResponseStatus(HttpStatus.CONFLICT)
     @ResponseStatus(code=HttpStatus.CONTINUE,reason="未知错误")
     @ExceptionHandler(value = Exception.class)
     public Result errorHandler(Exception ex, HttpServletResponse response) {
         ex.printStackTrace();
         if (ex instanceof CustomException) {
             CustomException customException = (CustomException) ex;
             if(ex instanceof DataNullException){
                 return ResultUtil.success(customException.getCode(), customException.getMessage(),null);
             }else{
                 return ResultUtil.error(customException.getCode(), customException.getMessage());
             }
         }else {
             return ResultUtil.error(-100, "未知错误",response);
         }
     }
}
