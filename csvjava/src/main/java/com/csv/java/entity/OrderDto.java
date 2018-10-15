package com.csv.java.entity;

import java.util.Date;

//订单dto
public class OrderDto extends BaseDto{
        //主key
        private int orderId;
        //订货日期
        private String dhrq;
        //网站订单号
        private String websiteorderno;

        private String webuserid="";

        private String xdrq="0000-00-00";

        private String zdrq="0000-00-00";

        private String ydrq="0000-00-00";
        //订单状态
        private int orderstatus;

        private String sqr_bak_old="";
        //收件人客户名
        private String sqr;

        private String jiaming="";
        //邮政编码
        private String zipcode;
        //地址
        private String address;

        private String address2="";

        private String deliverytime="";
        //email或者客户ID
        private String email;
        //订单价格
        private Double totalprice;
        //网站ID
        private int websiteid;
        //支付类型
        private int paymentid;

        private String wcrq="0000-00-00";

        private String gjdq="";
        //电话
        private String phone;
        //币种
        private String currency;
        //备注
        private String contents;

        private int isfh=0;

        private String fhdate="0000-00-00";

        private String tracknumber="";
        //国家ID
        private int countryid;

        private String trackurl="";

        private String joindate="0000-00-00";
        //管理ID
        private int mgrid;

        private int detflag=0;

        private int modifyflag=0;

        //系统入库类型
        private int systype;

        public int getOrderId() {
                return orderId;
        }

        public void setOrderId(int orderId) {
                this.orderId = orderId;
        }

        public String getDhrq() {
                return dhrq;
        }

        public void setDhrq(String dhrq) {
                this.dhrq = dhrq;
        }

        public String getWebsiteorderno() {
                return websiteorderno;
        }

        public void setWebsiteorderno(String websiteorderno) {
                this.websiteorderno = websiteorderno;
        }

        public String getWebuserid() {
                return webuserid;
        }

        public void setWebuserid(String webuserid) {
                this.webuserid = webuserid;
        }

        public String getXdrq() {
                return xdrq;
        }

        public void setXdrq(String xdrq) {
                this.xdrq = xdrq;
        }

        public String getZdrq() {
                return zdrq;
        }

        public void setZdrq(String zdrq) {
                this.zdrq = zdrq;
        }

        public String getYdrq() {
                return ydrq;
        }

        public void setYdrq(String ydrq) {
                this.ydrq = ydrq;
        }

        public int getOrderstatus() {
                return orderstatus;
        }

        public void setOrderstatus(int orderstatus) {
                this.orderstatus = orderstatus;
        }

        public String getSqr_bak_old() {
                return sqr_bak_old;
        }

        public void setSqr_bak_old(String sqr_bak_old) {
                this.sqr_bak_old = sqr_bak_old;
        }

        public String getSqr() {
                return sqr;
        }

        public void setSqr(String sqr) {
                this.sqr = sqr;
        }

        public String getJiaming() {
                return jiaming;
        }

        public void setJiaming(String jiaming) {
                this.jiaming = jiaming;
        }

        public String getZipcode() {
                return zipcode;
        }

        public void setZipcode(String zipcode) {
                this.zipcode = zipcode;
        }

        public String getAddress() {
                return address;
        }

        public void setAddress(String address) {
                this.address = address;
        }

        public String getAddress2() {
                return address2;
        }

        public void setAddress2(String address2) {
                this.address2 = address2;
        }

        public String getDeliverytime() {
                return deliverytime;
        }

        public void setDeliverytime(String deliverytime) {
                this.deliverytime = deliverytime;
        }

        public String getEmail() {
                return email;
        }

        public void setEmail(String email) {
                this.email = email;
        }

        public Double getTotalprice() {
                return totalprice;
        }

        public void setTotalprice(Double totalprice) {
                this.totalprice = totalprice;
        }

        public int getWebsiteid() {
                return websiteid;
        }

        public void setWebsiteid(int websiteid) {
                this.websiteid = websiteid;
        }

        public int getPaymentid() {
                return paymentid;
        }

        public void setPaymentid(int paymentid) {
                this.paymentid = paymentid;
        }

        public String getWcrq() {
                return wcrq;
        }

        public void setWcrq(String wcrq) {
                this.wcrq = wcrq;
        }

        public String getGjdq() {
                return gjdq;
        }

        public void setGjdq(String gjdq) {
                this.gjdq = gjdq;
        }

        public String getPhone() {
                return phone;
        }

        public void setPhone(String phone) {
                this.phone = phone;
        }

        public String getCurrency() {
                return currency;
        }

        public void setCurrency(String currency) {
                this.currency = currency;
        }

        public String getContents() {
                return contents;
        }

        public void setContents(String contents) {
                this.contents = contents;
        }

        public int getIsfh() {
                return isfh;
        }

        public void setIsfh(int isfh) {
                this.isfh = isfh;
        }

        public String getFhdate() {
                return fhdate;
        }

        public void setFhdate(String fhdate) {
                this.fhdate = fhdate;
        }

        public String getTracknumber() {
                return tracknumber;
        }

        public void setTracknumber(String tracknumber) {
                this.tracknumber = tracknumber;
        }

        public int getCountryid() {
                return countryid;
        }

        public void setCountryid(int countryid) {
                this.countryid = countryid;
        }

        public String getTrackurl() {
                return trackurl;
        }

        public void setTrackurl(String trackurl) {
                this.trackurl = trackurl;
        }

        public String getJoindate() {
                return joindate;
        }

        public void setJoindate(String joindate) {
                this.joindate = joindate;
        }

        public int getMgrid() {
                return mgrid;
        }

        public void setMgrid(int mgrid) {
                this.mgrid = mgrid;
        }

        public int getDetflag() {
                return detflag;
        }

        public void setDetflag(int detflag) {
                this.detflag = detflag;
        }

        public int getModifyflag() {
                return modifyflag;
        }

        public void setModifyflag(int modifyflag) {
                this.modifyflag = modifyflag;
        }

        public int getSystype() {
                return systype;
        }

        public void setSystype(int systype) {
                this.systype = systype;
        }
}
