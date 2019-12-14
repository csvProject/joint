package com.ewiderbuy.produce.entity;

//商品分类dto
public class PTypeDto extends BaseDto{
    //商品分类ID
    private int ptypeId;
    //商品分类名称
    private String typeName;
    //商品分类code
    private String typeCode;

    public int getPtypeId() {
        return ptypeId;
    }

    public void setPtypeId(int ptypeId) {
        this.ptypeId = ptypeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }


}
