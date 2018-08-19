export class CsvTemplateInfo {
  //CSV模板ID
  csvtempId:number;
  //平台ID
  platformId:number;
  //平台账号ID
  pfaccountId:number;
  //产品分类ID
  ptypeId:number;
  //供应商ID
  sId:number;
  //模板名称
  csvtempNm:string;
  //最低价公式
  lowExpr:string;
  //推荐件公式
  gdExpr:string;
  //原价公式
  origiExpr:string;
  //创建日期
  joinDate:Date;
  //创建人
  joinBy:number;
  //使用标志
  isUse:number;
  //备注
  memo:string;
  //平台名称
  platformNm:string;
  //平台账号名称
  pfaccountNm:string;
  //产品分类名称
  ptypeNm:string;
  //供应商名称
  sNm:string;
  constructor(){
    this.isUse = 0;
  }
}



export class CsvTemplateDetail{
  //CSV模板字段ID
  csvFieldId:number;
  //CSV模板ID
  csvtempId:number;
  //模板字段key
  fieldKey:string;
  //模板字段key描述
  fieldNm:string;
  //模板字段value
  fieldValue:string;
  //模板字段类别
  fieldType:string;
  //模板字段顺序
  fieldSort:number;
}
