package com.csv.java.service;






import com.csv.java.entity.SupplierDto;

import java.util.List;

public interface SupplierService {
    List<SupplierDto> findSupplierByCondi(SupplierDto indto);

    List<SupplierDto> findSupplierByCodenm(String cdnm);
}
