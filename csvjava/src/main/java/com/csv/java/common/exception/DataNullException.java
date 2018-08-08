package com.csv.java.common.exception;


import com.csv.java.common.result.ResultEnum;

/**
 * DataNullException
 * @author wkm
 * @since 2018/7/30
 */
public class DataNullException extends CustomException{
   public DataNullException(ResultEnum resultEnum) {
       super(resultEnum);
   }
}
