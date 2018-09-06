package com.csv.java.service.impl;

import com.csv.java.dao.CsvCustomFieldDao;
import com.csv.java.dao.CustomDao;
import com.csv.java.entity.CsvCustomFieldBatDto;
import com.csv.java.entity.CsvCustomFieldDto;
import com.csv.java.service.CsvCustomFieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

@Service(value = "csvCustmerFieldService")
public class CsvCustomFieldServiceImpl implements CsvCustomFieldService {

    @Autowired
    private CsvCustomFieldDao csvCustomFieldDao;

    @Autowired
    private CustomDao customDao;

    public List<CsvCustomFieldDto> findCsvCustomField(int csvtempId){
        return csvCustomFieldDao.findCsvCustomField(csvtempId);
    }

    public void updCsvCustomFieldBat(CsvCustomFieldBatDto indto){
        //删除模板下所有自定义字段
        csvCustomFieldDao.delCustomFieldBycsvtempId(indto.getCsvtempId());

        //批量添加模板自定义字段
        if (indto.getCsvCustomFieldDtoList() !=null) {
            for (CsvCustomFieldDto cvsCustomFieldDto : indto.getCsvCustomFieldDtoList()) {
                cvsCustomFieldDto.setCsvtempId(indto.getCsvtempId());
                csvCustomFieldDao.insertCustomField(cvsCustomFieldDto);
            }
        }
    }

    public Boolean verifyFormula(String formula){
        Boolean ret = false;
        try{
            List<LinkedHashMap<String,Object>> data = new LinkedList<>();
            //'round((($cnlowsalesprice+50)/$ryhl)/0.9/100,0)*100'
            formula = formula.replaceAll("(?:\\$cnlowsalesprice|\\$rblowsalesprice|\\$omlowsalesprice|\\$baseprice|\\$mll|\\$ryhl|\\$myhl)", "1");

            String sql = "select " + formula + " as w from dual";
            data = customDao.customSelect(sql);
            ret = true;
        }catch(Exception ex){
            ret = false;
        }
        return ret;
    }

}
