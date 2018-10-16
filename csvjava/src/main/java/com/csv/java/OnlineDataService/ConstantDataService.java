package com.csv.java.OnlineDataService;


public interface ConstantDataService {

    //销售订单到订单系统的订单状态转换
    public static String transformStatus(String payMethod,String status){
        String orderStatus = "";
        /*if (status.equals(StatusEnum.CANCELED.toString()) ) {
            //是canceled状态，无论哪种付费方式，都为-1
            orderStatus = OrderStatusEnum.CANCELED.toString();
        }else {
            if (orderDto.getPaymentid() == Integer.parseInt(PaymentidEnum.Bank.toString())) {
                //银行入金付费完成由订单系统去更新，从销售库过来直接对应6
                orderStatus = OrderStatusEnum.UNPAID.toString();
            } else {
                //信用卡或paypal的时候，如果是processing订单库对应1 其他都是6
                if (status.equals(StatusEnum.PROCESSING.toString())) {
                    orderStatus = OrderStatusEnum.PAID.toString();
                } else {
                    orderStatus = OrderStatusEnum.UNPAID.toString();
                }
            }
        }*/
        return orderStatus;
    }

    //销售系统订单状态
    enum StatusEnum {
        CANCELED("canceled", 1), PENDING("pending", 2), PROCESSING("processing", 3);
        // 成员变量
        private String name;
        private int index;
        // 构造方法
        private StatusEnum(String name, int index) {
            this.name = name;
            this.index = index;
        }
        //覆盖方法
        @Override
        public String toString() {
            return this.name;
        }

    }

    //销售系统支付种类
    enum PayMethodEnum {
        PAYPAL_STANDARD("paypal_standard", 1), MASAPAY_PAYMENT("masapay_payment", 2), CHECKMO("checkmo", 3);
        // 成员变量
        private String name;
        private int index;
        // 构造方法
        private PayMethodEnum(String name, int index) {
            this.name = name;
            this.index = index;
        }
        //覆盖方法
        @Override
        public String toString() {
            return this.name;
        }

    }

    //订单系统支付种类idpaymentid
    enum PaymentidEnum {
        Bank("1", 1), CREDIT_CARD("2", 2), PAYPAL("6", 3);
        // 成员变量
        private String value;
        private int index;
        // 构造方法
        private PaymentidEnum(String value, int index) {
            this.value = value;
            this.index = index;
        }
        //覆盖方法
        @Override
        public String toString() {
            return this.value;
        }

    }

    //订单系统订单状态
    enum OrderStatusEnum {
        UNPAID("6", 1), PAID("1", 2),CANCELED("-1",3);
        // 成员变量
        private String value;
        private int index;
        // 构造方法
        private OrderStatusEnum(String value, int index) {
            this.value = value;
            this.index = index;
        }
        //覆盖方法
        @Override
        public String toString() {
            return this.value;
        }

    }
}
