package com.csv.java.service.impl;




import com.csv.java.dao.CsvCustmerFieldDao;
import com.csv.java.dao.CsvTemplateDetailDao;
import com.csv.java.dao.CsvTemplateInfoDao;
import com.csv.java.dao.CsvTemplateRuleDao;
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
    private CsvCustmerFieldDao csvCustmerFieldDao;

    public CsvTemplateInfoDto findCsvTempInfoById(int csvtempId){
        return csvTemplateInfoDao.findCsvTempInfoById(csvtempId);
    }

    public List<CsvTemplateInfoDto> findCsvTempInfoByCondi(CsvTemplateInfoDto indto){
        return csvTemplateInfoDao.findCsvTempInfoByCondi(indto);
    }

    public void delCsvTempInfoById(int csvtempId){
        csvTemplateInfoDao.delCsvTempInfoById(csvtempId);
        csvTemplateDetailDao.delCsvTempDetailBycsvtempId(csvtempId);
        csvCustmerFieldDao.delCustmerFieldBycsvtempId(csvtempId);
        csvTemplateRuleDao.delCsvTempRuleBycsvtempId(csvtempId);
    }

    public void updCsvTempInfoById(CsvTemplateInfoDto indto){
        csvTemplateInfoDao.updCsvTempInfoById(indto);
    }

    public int insertCsvTempInfo(CsvTemplateInfoDto indto){

        int ret = 0;
        //判断同一平台，账号，商品类型，供应商下是否存在模板，
        int temponly  = csvTemplateInfoDao.checkCsvTempInfoOnly(indto);

        if (temponly > 0) {
        //处理代码未写
            ret = -1;
        }else{
            //添加模板
            csvTemplateInfoDao.insertCsvTempInfo(indto);

            //添加模板空规则
            CsvTemplateRuleDto csvTemplateInfoDto = new CsvTemplateRuleDto();
            csvTemplateInfoDto.setCsvSql("");
            csvTemplateInfoDto.setCsvtempId(indto.getCsvtempId());
            csvTemplateRuleDao.insertCsvTempRule(csvTemplateInfoDto);
        }

        return ret;
    }
}
