package com.csv.java.service.impl;





import com.csv.java.dao.SupplierDao;
import com.csv.java.entity.SupplierDto;
import com.csv.java.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "supplierService")
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierDao supplierDao;


    public List<SupplierDto> findSupplierByCondi(SupplierDto indto){
        return supplierDao.findSupplierByCondi(indto);
    }

    public List<SupplierDto> findSupplierByCodenm(String cdnm){
        return supplierDao.findSupplierByCodenm(cdnm);
    }

}
