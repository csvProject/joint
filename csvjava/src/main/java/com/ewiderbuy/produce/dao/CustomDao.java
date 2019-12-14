package com.ewiderbuy.produce.dao;

import java.util.LinkedHashMap;
import java.util.List;

 /**
  * CustomDao 自定义sql查询
  * @author wkm
  * @since 2018/8/15
  */
public interface CustomDao {
    /*
    * @Param sql 传入sql自定义sql查询语句
    * @Return {key:value}
    * */
    List<LinkedHashMap<String,Object>> customSelect(String sql);

     /*
      * @Param sql 传入sql自定义sql查询语句
      * */
    void customUpdate(String sql);

    /*
      * @Param sql 传入sql自定义sql查询语句
      * */
    void customInsert(String sql);
}
