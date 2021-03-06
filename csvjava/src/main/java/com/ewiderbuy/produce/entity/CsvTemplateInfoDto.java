package com.ewiderbuy.produce.entity;

import java.util.Date;
import java.util.List;

//模板信息dto
public class CsvTemplateInfoDto extends BaseDto{
    //CSV模板ID
    private int csvtempId;

    //平台ID
    private int platformId;

    //平台账号ID
    private int pfaccountId;

    //产品分类ID
    private int ptypeId;

    //供应商ID
    private int sId;

    //模板名称
    private String csvtempNm;

    //最低价公式
    private String lowExpr;

    //推荐件公式
    private String gdExpr;

    //原价公式
    private String origiExpr;

    //创建日期
    private Date joinDate;

    //创建人
    private int joinBy;

    //使用标志
    private int isUse;

    //备注
    private String memo;

    //平台名称
    private String platformNm;

    //平台账号名称
    private String pfaccountNm;

    //产品分类名称
    private String ptypeNm;

    //供应商名称
    private String sNm;

    //csv头部是否显示
    private int headShow;

    //模板自定义字段LIST
    private List<CsvCustomFieldDto> csvCustomFieldDtoList;

    //模板字段LIST
    private List<CsvTemplateDetailDto> csvTemplateDetailDtoList;

    public int getCsvtempId() {
        return csvtempId;
    }

    public void setCsvtempId(int csvtempId) {
        this.csvtempId = csvtempId;
    }

    public int getPlatformId() {
        return platformId;
    }

    public void setPlatformId(int platformId) {
        this.platformId = platformId;
    }

    public int getPfaccountId() {
        return pfaccountId;
    }

    public void setPfaccountId(int pfaccountId) {
        this.pfaccountId = pfaccountId;
    }

    public int getPtypeId() {
        return ptypeId;
    }

    public void setPtypeId(int ptypeId) {
        this.ptypeId = ptypeId;
    }

    public int getsId() {
        return sId;
    }

    public void setsId(int sId) {
        this.sId = sId;
    }

    public String getCsvtempNm() {
        return csvtempNm;
    }

    public void setCsvtempNm(String csvtempNm) {
        this.csvtempNm = csvtempNm;
    }

    public String getLowExpr() {
        return lowExpr;
    }

    public void setLowExpr(String lowExpr) {
        this.lowExpr = lowExpr;
    }

    public String getGdExpr() {
        return gdExpr;
    }

    public void setGdExpr(String gdExpr) {
        this.gdExpr = gdExpr;
    }

    public String getOrigiExpr() {
        return origiExpr;
    }

    public void setOrigiExpr(String origiExpr) {
        this.origiExpr = origiExpr;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public String getMemo() {
        return memo;
    }

    public int getJoinBy() {
        return joinBy;
    }

    public void setJoinBy(int joinBy) {
        this.joinBy = joinBy;
    }

    public int getIsUse() {
        return isUse;
    }

    public void setIsUse(int isUse) {
        this.isUse = isUse;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getPlatformNm() {
        return platformNm;
    }

    public void setPlatformNm(String platformNm) {
        this.platformNm = platformNm;
    }

    public String getPfaccountNm() {
        return pfaccountNm;
    }

    public void setPfaccountNm(String pfaccountNm) {
        this.pfaccountNm = pfaccountNm;
    }

    public String getPtypeNm() {
        return ptypeNm;
    }

    public void setPtypeNm(String ptypeNm) {
        this.ptypeNm = ptypeNm;
    }

    public String getsNm() {
        return sNm;
    }

    public void setsNm(String sNm) {
        this.sNm = sNm;
    }

    public int getHeadShow() {
        return headShow;
    }

    public void setHeadShow(int headShow) {
        this.headShow = headShow;
    }

    public List<CsvCustomFieldDto> getCsvCustomFieldDtoList() {
        return csvCustomFieldDtoList;
    }

    public void setCsvCustomFieldDtoList(List<CsvCustomFieldDto> csvCustomFieldDtoList) {
        this.csvCustomFieldDtoList = csvCustomFieldDtoList;
    }

    public List<CsvTemplateDetailDto> getCsvTemplateDetailDtoList() {
        return csvTemplateDetailDtoList;
    }

    public void setCsvTemplateDetailDtoList(List<CsvTemplateDetailDto> csvTemplateDetailDtoList) {
        this.csvTemplateDetailDtoList = csvTemplateDetailDtoList;
    }
}
