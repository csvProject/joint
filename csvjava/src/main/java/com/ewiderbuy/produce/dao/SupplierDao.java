package com.ewiderbuy.produce.dao;





import com.ewiderbuy.produce.entity.SupplierDto;
import com.ewiderbuy.produce.entity.SupplierDto;

import java.util.List;

public interface SupplierDao {

    //多条件查询
    List<SupplierDto> findSupplierByCondi(SupplierDto indto);

    //名称，code同查
    List<SupplierDto> findSupplierByCodenm(String cdnm);
}
