package com.ewiderbuy.produce.service.impl;





import com.ewiderbuy.produce.dao.SupplierDao;
import com.ewiderbuy.produce.entity.SupplierDto;
import com.ewiderbuy.produce.service.SupplierService;
import com.ewiderbuy.produce.dao.SupplierDao;
import com.ewiderbuy.produce.entity.SupplierDto;
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
