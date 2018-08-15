package com.csv.java.dao;





import com.csv.java.entity.SupplierDto;

import java.util.List;

public interface SupplierDao {

    //多条件查询
    List<SupplierDto> findSupplierByCondi(SupplierDto indto);

    //名称，code同查
    List<SupplierDto> findSupplierByCodenm(String cdnm);
}
