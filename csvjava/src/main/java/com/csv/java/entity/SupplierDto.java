package com.csv.java.entity;

//供应商dto
public class SupplierDto extends BaseDto{
    //供应商ID
    private int sId;
    //供应商名称
    private String supplierTitle;
    //备注
    private String contents;

    public int getsId() {
        return sId;
    }

    public void setsId(int sId) {
        this.sId = sId;
    }

    public String getSupplierTitle() {
        return supplierTitle;
    }

    public void setSupplierTitle(String supplierTitle) {
        this.supplierTitle = supplierTitle;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
}
