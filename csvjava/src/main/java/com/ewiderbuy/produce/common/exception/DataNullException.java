package com.ewiderbuy.produce.common.exception;


import com.ewiderbuy.produce.common.result.ResultEnum;

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
