package com.csv.java.service.impl;




import com.csv.java.dao.CsvCustomFieldDao;
import com.csv.java.dao.CsvTemplateDetailDao;
import com.csv.java.dao.CsvTemplateInfoDao;
import com.csv.java.dao.CsvTemplateRuleDao;
import com.csv.java.entity.CsvCustomFieldDto;
import com.csv.java.entity.CsvTemplateInfoDto;
import com.csv.java.entity.CsvTemplateRuleDto;
import com.csv.java.service.CsvTemplateInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "csvTemplateInfoService")
public class CsvTemplateInfoServiceImpl implements CsvTemplateInfoService {

    @Autowired
    private CsvTemplateInfoDao csvTemplateInfoDao;

    @Autowired
    private CsvTemplateDetailDao csvTemplateDetailDao;

    @Autowired
    private CsvTemplateRuleDao csvTemplateRuleDao;

    @Autowired
    private CsvCustomFieldDao csvCustomFieldDao;

    public CsvTemplateInfoDto findCsvTempInfoById(int csvtempId){
        return csvTemplateInfoDao.findCsvTempInfoById(csvtempId);
    }

    public List<CsvTemplateInfoDto> findCsvTempInfoByCondi(CsvTemplateInfoDto indto){
        return csvTemplateInfoDao.findCsvTempInfoByCondi(indto);
    }

    public void delCsvTempInfoById(int csvtempId){
        csvTemplateInfoDao.delCsvTempInfoById(csvtempId);
        csvTemplateDetailDao.delCsvTempDetailBycsvtempId(csvtempId);
        csvCustomFieldDao.delCustomFieldBycsvtempId(csvtempId);
        csvTemplateRuleDao.delCsvTempRuleBycsvtempId(csvtempId);
    }

    public int updCsvTempInfoById(CsvTemplateInfoDto indto){
        //判断模板名称是否已存在
        int temponly  = csvTemplateInfoDao.checkCsvTempNmOnly(indto);
        if (temponly > 0) {
            return -2;
        }

        csvTemplateInfoDao.updCsvTempInfoById(indto);

        //删除模板下所有自定义字段
        csvCustomFieldDao.delCustomFieldBycsvtempId(indto.getCsvtempId());

        //批量添加模板自定义字段
        if (indto.getCsvCustomFieldDtoList() !=null) {
            for (CsvCustomFieldDto cvsCustomFieldDto : indto.getCsvCustomFieldDtoList()) {
                cvsCustomFieldDto.setCsvtempId(indto.getCsvtempId());
                csvCustomFieldDao.insertCustomField(cvsCustomFieldDto);
            }
        }
        return 0;
    }

    public int insertCsvTempInfo(CsvTemplateInfoDto indto){

        //判断模板名称是否已存在
        int temponly  = csvTemplateInfoDao.chkAddCsvTempNmOnly(indto.getCsvtempNm());
        if (temponly > 0) {
            return -2;
        }

        //判断同一平台，账号，商品类型，供应商下是否存在模板，
        temponly  = csvTemplateInfoDao.checkCsvTempInfoOnly(indto);

        if (temponly > 0) {
            return  -1;
        }

        //添加模板
        csvTemplateInfoDao.insertCsvTempInfo(indto);

        //删除模板下所有自定义字段
        csvCustomFieldDao.delCustomFieldBycsvtempId(indto.getCsvtempId());
        if (indto.getCsvCustomFieldDtoList() !=null) {
            //批量添加模板自定义字段
            for (CsvCustomFieldDto cvsCustomFieldDto : indto.getCsvCustomFieldDtoList()) {
                cvsCustomFieldDto.setCsvtempId(indto.getCsvtempId());
                csvCustomFieldDao.insertCustomField(cvsCustomFieldDto);
            }
        }

        //添加模板空规则
        CsvTemplateRuleDto csvTemplateInfoDto = new CsvTemplateRuleDto();
        csvTemplateInfoDto.setCsvSql("");
        csvTemplateInfoDto.setCsvtempId(indto.getCsvtempId());
        csvTemplateRuleDao.insertCsvTempRule(csvTemplateInfoDto);

        return 0;
    }
}
