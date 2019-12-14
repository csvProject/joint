package com.ewiderbuy.produce.entity;

//系统代码dto
public class SysCodeDto extends BaseDto{
    //代码分类ID
    private int sysTypeCd;
    //代码分类名称
    private String sysTypeNm;
    //代码code
    private String sysCd;
    //代码名称
    private String sysNm;
    //代码描述
    private String sysDesc;

    public int getSysTypeCd() {
        return sysTypeCd;
    }

    public void setSysTypeCd(int sysTypeCd) {
        this.sysTypeCd = sysTypeCd;
    }

    public String getSysTypeNm() {
        return sysTypeNm;
    }

    public void setSysTypeNm(String sysTypeNm) {
        this.sysTypeNm = sysTypeNm;
    }

    public String getSysCd() {
        return sysCd;
    }

    public void setSysCd(String sysCd) {
        this.sysCd = sysCd;
    }

    public String getSysNm() {
        return sysNm;
    }

    public void setSysNm(String sysNm) {
        this.sysNm = sysNm;
    }

    public String getSysDesc() {
        return sysDesc;
    }

    public void setSysDesc(String sysDesc) {
        this.sysDesc = sysDesc;
    }
}
