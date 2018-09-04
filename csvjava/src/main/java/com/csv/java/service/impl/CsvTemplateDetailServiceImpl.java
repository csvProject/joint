package com.csv.java.service.impl;




import com.csv.java.common.tool.StringFormatForSQL;
import com.csv.java.dao.CsvTemplateDetailDao;
import com.csv.java.dao.CsvTemplateRuleDao;
import com.csv.java.dao.SysCodeDao;
import com.csv.java.entity.CsvTempBatDto;
import com.csv.java.entity.CsvTemplateDetailDto;
import com.csv.java.entity.CsvTemplateRuleDto;
import com.csv.java.entity.SysCodeDto;
import com.csv.java.service.CsvTemplateDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(value = "csvTemplateDetailService")
public class CsvTemplateDetailServiceImpl implements CsvTemplateDetailService {

    @Resource
    private SysCodeDao sysCodeDao;

    @Autowired
    private CsvTemplateDetailDao csvTemplateDetailDao;

    @Autowired
    private CsvTemplateRuleDao csvTemplateRuleDao;

    public List<CsvTemplateDetailDto> findCsvTempDetailBycsvtempId(int csvtempId){
        return csvTemplateDetailDao.findCsvTempDetailBycsvtempId(csvtempId);
    }

    public CsvTemplateDetailDto findCsvTempDetailById(int csvFieldId){
        return csvTemplateDetailDao.findCsvTempDetailById(csvFieldId);
    }

    public List<CsvTemplateDetailDto> findCsvTempDetailByKey(CsvTemplateDetailDto indto){
        return csvTemplateDetailDao.findCsvTempDetailByKey(indto);
    }

    public void delCsvTempDetailById(int csvFieldId){
        csvTemplateDetailDao.delCsvTempDetailById(csvFieldId);
    }

    public void updCsvTempDetailById(CsvTemplateDetailDto indto){
        csvTemplateDetailDao.updCsvTempDetailById(indto);


    }

    public void insertCsvTempDetail(CsvTemplateDetailDto indto){
        csvTemplateDetailDao.insertCsvTempDetail(indto);
    }

    public void updCsvTempDetailBat(CsvTempBatDto indto){
        //删除模板下所有字段
        csvTemplateDetailDao.delCsvTempDetailBycsvtempId(indto.getCsvtempId());

        //批量添加模板字段
        //处理代码代码---

        for (CsvTemplateDetailDto cvsDtlDto:indto.getCsvTemplateDetailDtoList()) {
            cvsDtlDto.setCsvtempId(indto.getCsvtempId());
            csvTemplateDetailDao.insertCsvTempDetail(cvsDtlDto);
        }
        //----

        //更新模板规则表中的csvsql处理----
        Map map = new HashMap<String,String>();
        List<SysCodeDto> l =  sysCodeDao.findSysCodeByTypeCd(1);
        for (SysCodeDto s: l
             ) {
            map.put(s.getSysNm(),s.getSysCd());
        }
        String csvSql="";
        csvSql = StringFormatForSQL.fieldListFormat(indto.getCsvTemplateDetailDtoList(),map);

        System.out.println(csvSql);
        //处理代码代码---

        //----
        CsvTemplateRuleDto csvTemplateRuleDto =new CsvTemplateRuleDto();
        csvTemplateRuleDto.setCsvSql(csvSql);
        csvTemplateRuleDto.setCsvtempId(indto.getCsvtempId());
        csvTemplateRuleDao.updCsvTempRuleById(csvTemplateRuleDto);
        //--------------------------

    }
}
