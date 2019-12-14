package com.ewiderbuy.produce.service.impl;




import com.ewiderbuy.produce.common.tool.StringFormatForSQL;
import com.ewiderbuy.produce.dao.*;
import com.ewiderbuy.produce.entity.*;
import com.ewiderbuy.produce.service.CsvTemplateDetailService;
import com.ewiderbuy.produce.service.CsvTemplateInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
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

    @Autowired
    private CustomDao customDao;

    @Autowired
    private CsvTemplateDetailService csvTemplateDetailService;

    public CsvTemplateInfoDto findCsvTempInfoById(int csvtempId){
        return csvTemplateInfoDao.findCsvTempInfoById(csvtempId);
    }

    public List<CsvTemplateInfoDto> findCsvTempInfoByCondi(CsvTemplateInfoDto indto){
        return csvTemplateInfoDao.findCsvTempInfoByCondi(indto);
    }

    @Transactional
    public void delCsvTempInfoById(int csvtempId){
        csvTemplateInfoDao.delCsvTempInfoById(csvtempId);
        csvTemplateDetailDao.delCsvTempDetailBycsvtempId(csvtempId);
        csvCustomFieldDao.delCustomFieldBycsvtempId(csvtempId);
        csvTemplateRuleDao.delCsvTempRuleBycsvtempId(csvtempId);
    }

    @Transactional
    public int updCsvTempInfoById(CsvTemplateInfoDto indto){
        //判断模板名称是否已存在
        int temponly  = csvTemplateInfoDao.checkCsvTempNmOnly(indto);
        if (temponly > 0) {
            return -2;
        }

        //判断模板自定义字段公式是否有效
        if (indto.getCsvCustomFieldDtoList() !=null) {
            for (CsvCustomFieldDto cvsCustomFieldDto : indto.getCsvCustomFieldDtoList()) {
                Boolean ret = false;
                try{
                    List<LinkedHashMap<String,Object>> data = new LinkedList<>();
                    //'round(((#cnlowsalesprice+50)/#ryhl)/0.9/100,0)*100'
                    String formula = cvsCustomFieldDto.getCfieldValue().replaceAll(
                            "(?:\\#cnlowsalesprice|\\#rblowsalesprice|\\#omlowsalesprice|\\#baseprice|\\#mll|\\#ryhl|\\#myhl)",
                            "1");

                    String sql = "select " + formula + " as w from dual";
                    data = customDao.customSelect(sql);
                }catch(Exception ex){
                    return -3;
                }
            }
        }

        csvTemplateInfoDao.updCsvTempInfoById(indto);

        //获取当前模板下所有自定义公式
        List<CsvCustomFieldDto> oldCsvCfList = new ArrayList<>();
        oldCsvCfList = csvCustomFieldDao.findCsvCustomField(indto.getCsvtempId());

        //批量添加模板自定义公式
        if (indto.getCsvCustomFieldDtoList() !=null) {
            for (CsvCustomFieldDto newCvsCustomFieldDto : indto.getCsvCustomFieldDtoList()) {
                boolean blnHave = false;
                for (CsvCustomFieldDto oldCsvCf :oldCsvCfList ) {
                    //如果存在则更新
                    if (oldCsvCf.getCsvCustomFieldId() == newCvsCustomFieldDto.getCsvCustomFieldId()) {
                        newCvsCustomFieldDto.setLogId(indto.getLogId());
                        csvCustomFieldDao.updCustomFieldById(newCvsCustomFieldDto);
                        blnHave = true;
                        break;
                    }
                }

                //不存在则新增
                if (!blnHave){
                    newCvsCustomFieldDto.setCsvtempId(indto.getCsvtempId());
                    newCvsCustomFieldDto.setLogId(indto.getLogId());
                    csvCustomFieldDao.insertCustomField(newCvsCustomFieldDto);
                }
            }

            //如果表记录在画面记录里不存在则删除
            for (CsvCustomFieldDto oldCsvCf :oldCsvCfList){
                boolean blnHave = false;
                for (CsvCustomFieldDto newCvsCustomFieldDto : indto.getCsvCustomFieldDtoList()) {
                    if (oldCsvCf.getCsvCustomFieldId() == newCvsCustomFieldDto.getCsvCustomFieldId()) {
                        blnHave = true;
                        break;
                    }
                }
                //不存在则删除
                if (!blnHave){
                    csvCustomFieldDao.delCustomFieldById(oldCsvCf.getCsvCustomFieldId());
                }
            }

        }

        return 0;
    }

    @Transactional
    public int insertCsvTempInfo(CsvTemplateInfoDto indto){

        return this.doCsvTempInfoForCopyorInsert(indto,1);

    }

    @Transactional
    public int copyCsvTempInfo(CsvTemplateInfoDto indto){
        int ret ;
        ret = this.doCsvTempInfoForCopyorInsert(indto,2);
        if (ret <0) {
            return ret;
        }
        CsvTempBatDto csvTempBatDto = new CsvTempBatDto();
        csvTempBatDto.setCsvtempId(indto.getCsvtempId());
        csvTempBatDto.setLogId(indto.getLogId());

        csvTempBatDto.setCsvTemplateDetailDtoList(indto.getCsvTemplateDetailDtoList());
        csvTemplateDetailService.updCsvTempDetailBat(csvTempBatDto);
/*
        //获取当前模板下所有定义字段
        List<CsvTemplateDetailDto> csvTemplateDetailDtoList = new ArrayList<>();
        csvTemplateDetailDtoList = csvTemplateDetailDao.findCsvTempDetailBycsvtempId(indto.getCsvtempId());

        //更新模板字段中设有自定义公式的名称
        csvTemplateDetailDtoList = StringFormatForSQL.CustomReplacement(csvTemplateDetailDtoList,
                oldCsvCf.getCsvCustomFieldId(),
                oldCsvCf.getCsvCustomFieldId(),
                oldCsvCf.getCfieldNm(),
                newCvsCustomFieldDto.getCfieldNm());*/
        return 0;
    }

    //处理新增或复制
    private int doCsvTempInfoForCopyorInsert(CsvTemplateInfoDto indto,int flag){
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

        //判断模板自定义字段公式是否有效
        if (indto.getCsvCustomFieldDtoList() !=null) {
            for (CsvCustomFieldDto cvsCustomFieldDto : indto.getCsvCustomFieldDtoList()) {
                Boolean ret = false;
                try{
                    List<LinkedHashMap<String,Object>> data = new LinkedList<>();
                    //'round((($cnlowsalesprice+50)/$ryhl)/0.9/100,0)*100'
                    String formula = cvsCustomFieldDto.getCfieldValue().replaceAll(
                            "(?:\\#cnlowsalesprice|\\#rblowsalesprice|\\#omlowsalesprice|\\#baseprice|\\#mll|\\#ryhl|\\#myhl)",
                            "1");

                    String sql = "select " + formula + " as w from dual";
                    data = customDao.customSelect(sql);
                }catch(Exception ex){
                    return -3;
                }
            }
        }

        //添加模板
        csvTemplateInfoDao.insertCsvTempInfo(indto);

        //删除模板下所有自定义公式
        csvCustomFieldDao.delCustomFieldBycsvtempId(indto.getCsvtempId());
        if (indto.getCsvCustomFieldDtoList() !=null) {
            //批量添加模板自定义公式
            for (CsvCustomFieldDto cvsCustomFieldDto : indto.getCsvCustomFieldDtoList()) {
                cvsCustomFieldDto.setCsvtempId(indto.getCsvtempId());
                cvsCustomFieldDto.setLogId(indto.getLogId());
                int oldCsvCustomFieldId = cvsCustomFieldDto.getCsvCustomFieldId();
                csvCustomFieldDao.insertCustomField(cvsCustomFieldDto);

                if (flag == 2){//如果是复制，需要把复制来的模板字段里已有的自定义公式ID更新为新的自定义公式的ID
                    //更新模板字段中设有自定义公式的ID和名称
                    indto.setCsvTemplateDetailDtoList(StringFormatForSQL.CustomReplacement(
                            indto.getCsvTemplateDetailDtoList(),
                            oldCsvCustomFieldId,
                            cvsCustomFieldDto.getCsvCustomFieldId(),
                            cvsCustomFieldDto.getCfieldNm(),
                            cvsCustomFieldDto.getCfieldNm()));
                }
            }
        }

        //添加模板空规则
        CsvTemplateRuleDto csvTemplateInfoDto = new CsvTemplateRuleDto();
        csvTemplateInfoDto.setCsvSql("");
        csvTemplateInfoDto.setCsvtempId(indto.getCsvtempId());
        csvTemplateInfoDto.setLogId(indto.getLogId());
        csvTemplateRuleDao.insertCsvTempRule(csvTemplateInfoDto);

        return 0;
    }
}
