package com.ewiderbuy.produce.entity;

//订单dto
public class OrderDetailDto extends BaseDto{
        private int id;
        //订单号
        private int orderId;
        //订单编号
        private String dOrderNo;
        private String confirmDate = "0000-00-00";
        //sku
        private String sku;

        //尺寸ID
        private int sizeId;
        private int dealModeId=0;
        private int dealMgrId=0;
        private String dealDate= "0000-00-00";
        private int thFlag=0;
        //录入日期datetime
        private String joinDate;
        //数量
        private int qty;
        //客户要求
        private String customerRequest;
        //明细订单状态
        private int dOrderStatus;
        private String fhDate= "0000-00-00";
        private String expressNo="";
        private String expressWay="";
        private int transferCorp=0;
        private String sendSysNo="";
        private int sendJapan=0;
        private int pid=0;
        private int deleFlag=0;
        private String delReason="";
        //datetime
        private String delDatetime= "0000-00-00";
        private int delMgrid=0;
        //紧急状态ID
        private int propertyId=0;
        //管理ID
        private int mgrId;
        //仓库出库标识
        private int whw;
        private int reportIsSent=0;
        private int isReceive=0;
        private int ttMgrId=0;
        private int expNum=0;
        //datetime
        private String expDatetime= "0000-00-00";
        private int flag=0;
        private int flaga=0;
        private String purchaseNo="";
        private String sExpressNo="";
        private int isComplete=0;
        //datetime
        private String completeDate= "0000-00-00";
        private String arriveExpressno="";
        private String arriveDate= "0000-00-00";
        private int arriveMgrId=0;

        public int getId() {
                return id;
        }

        public void setId(int id) {
                this.id = id;
        }

        public int getOrderId() {
                return orderId;
        }

        public void setOrderId(int orderId) {
                this.orderId = orderId;
        }

        public String getdOrderNo() {
                return dOrderNo;
        }

        public void setdOrderNo(String dOrderNo) {
                this.dOrderNo = dOrderNo;
        }

        public String getConfirmDate() {
                return confirmDate;
        }

        public void setConfirmDate(String confirmDate) {
                this.confirmDate = confirmDate;
        }

        public String getSku() {
                return sku;
        }

        public void setSku(String sku) {
                this.sku = sku;
        }

        public int getSizeId() {
                return sizeId;
        }

        public void setSizeId(int sizeId) {
                this.sizeId = sizeId;
        }

        public int getDealModeId() {
                return dealModeId;
        }

        public void setDealModeId(int dealModeId) {
                this.dealModeId = dealModeId;
        }

        public int getDealMgrId() {
                return dealMgrId;
        }

        public void setDealMgrId(int dealMgrId) {
                this.dealMgrId = dealMgrId;
        }

        public String getDealDate() {
                return dealDate;
        }

        public void setDealDate(String dealDate) {
                this.dealDate = dealDate;
        }

        public int getThFlag() {
                return thFlag;
        }

        public void setThFlag(int thFlag) {
                this.thFlag = thFlag;
        }

        public String getJoinDate() {
                return joinDate;
        }

        public void setJoinDate(String joinDate) {
                this.joinDate = joinDate;
        }

        public int getQty() {
                return qty;
        }

        public void setQty(int qty) {
                this.qty = qty;
        }

        public String getCustomerRequest() {
                return customerRequest;
        }

        public void setCustomerRequest(String customerRequest) {
                this.customerRequest = customerRequest;
        }

        public int getdOrderStatus() {
                return dOrderStatus;
        }

        public void setdOrderStatus(int dOrderStatus) {
                this.dOrderStatus = dOrderStatus;
        }

        public String getFhDate() {
                return fhDate;
        }

        public void setFhDate(String fhDate) {
                this.fhDate = fhDate;
        }

        public String getExpressNo() {
                return expressNo;
        }

        public void setExpressNo(String expressNo) {
                this.expressNo = expressNo;
        }

        public String getExpressWay() {
                return expressWay;
        }

        public void setExpressWay(String expressWay) {
                this.expressWay = expressWay;
        }

        public int getTransferCorp() {
                return transferCorp;
        }

        public void setTransferCorp(int transferCorp) {
                this.transferCorp = transferCorp;
        }

        public String getSendSysNo() {
                return sendSysNo;
        }

        public void setSendSysNo(String sendSysNo) {
                this.sendSysNo = sendSysNo;
        }

        public int getSendJapan() {
                return sendJapan;
        }

        public void setSendJapan(int sendJapan) {
                this.sendJapan = sendJapan;
        }

        public int getPid() {
                return pid;
        }

        public void setPid(int pid) {
                this.pid = pid;
        }

        public int getDeleFlag() {
                return deleFlag;
        }

        public void setDeleFlag(int deleFlag) {
                this.deleFlag = deleFlag;
        }

        public String getDelReason() {
                return delReason;
        }

        public void setDelReason(String delReason) {
                this.delReason = delReason;
        }

        public String getDelDatetime() {
                return delDatetime;
        }

        public void setDelDatetime(String delDatetime) {
                this.delDatetime = delDatetime;
        }

        public int getDelMgrid() {
                return delMgrid;
        }

        public void setDelMgrid(int delMgrid) {
                this.delMgrid = delMgrid;
        }

        public int getPropertyId() {
                return propertyId;
        }

        public void setPropertyId(int propertyId) {
                this.propertyId = propertyId;
        }

        public int getMgrId() {
                return mgrId;
        }

        public void setMgrId(int mgrId) {
                this.mgrId = mgrId;
        }

        public int getWhw() {
                return whw;
        }

        public void setWhw(int whw) {
                this.whw = whw;
        }

        public int getReportIsSent() {
                return reportIsSent;
        }

        public void setReportIsSent(int reportIsSent) {
                this.reportIsSent = reportIsSent;
        }

        public int getIsReceive() {
                return isReceive;
        }

        public void setIsReceive(int isReceive) {
                this.isReceive = isReceive;
        }

        public int getTtMgrId() {
                return ttMgrId;
        }

        public void setTtMgrId(int ttMgrId) {
                this.ttMgrId = ttMgrId;
        }

        public int getExpNum() {
                return expNum;
        }

        public void setExpNum(int expNum) {
                this.expNum = expNum;
        }

        public String getExpDatetime() {
                return expDatetime;
        }

        public void setExpDatetime(String expDatetime) {
                this.expDatetime = expDatetime;
        }

        public int getFlag() {
                return flag;
        }

        public void setFlag(int flag) {
                this.flag = flag;
        }

        public int getFlaga() {
                return flaga;
        }

        public void setFlaga(int flaga) {
                this.flaga = flaga;
        }

        public String getPurchaseNo() {
                return purchaseNo;
        }

        public void setPurchaseNo(String purchaseNo) {
                this.purchaseNo = purchaseNo;
        }

        public String getsExpressNo() {
                return sExpressNo;
        }

        public void setsExpressNo(String sExpressNo) {
                this.sExpressNo = sExpressNo;
        }

        public int getIsComplete() {
                return isComplete;
        }

        public void setIsComplete(int isComplete) {
                this.isComplete = isComplete;
        }

        public String getCompleteDate() {
                return completeDate;
        }

        public void setCompleteDate(String completeDate) {
                this.completeDate = completeDate;
        }

        public String getArriveExpressno() {
                return arriveExpressno;
        }

        public void setArriveExpressno(String arriveExpressno) {
                this.arriveExpressno = arriveExpressno;
        }

        public String getArriveDate() {
                return arriveDate;
        }

        public void setArriveDate(String arriveDate) {
                this.arriveDate = arriveDate;
        }

        public int getArriveMgrId() {
                return arriveMgrId;
        }

        public void setArriveMgrId(int arriveMgrId) {
                this.arriveMgrId = arriveMgrId;
        }
}
