package com.ewiderbuy.produce.service;






import com.ewiderbuy.produce.entity.SupplierDto;
import com.ewiderbuy.produce.entity.SupplierDto;

import java.util.List;

public interface SupplierService {
    List<SupplierDto> findSupplierByCondi(SupplierDto indto);

    List<SupplierDto> findSupplierByCodenm(String cdnm);
}
