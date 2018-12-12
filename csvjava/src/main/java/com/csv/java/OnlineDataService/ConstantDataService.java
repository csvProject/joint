package com.csv.java.OnlineDataService;


public interface ConstantDataService {

    //销售系统订单状态
    enum StatusEnum {
        CANCELED("canceled", 1),
        PENDING("pending", 2),
        PROCESSING("processing", 3),
        PENDING_PAYMENT("pending_payment", 4),
        PAYMENT_REVIEW("payment_review", 5);
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
        PAYPAL_STANDARD("paypal_standard", 1),
        MASAPAY_PAYMENT("masapay_payment", 2),
        CHECKMO("checkmo", 3);
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
        Bank("1", 1),
        CREDIT_CARD("2", 2),
        PAYPAL("6", 3);
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
        UNPAID("6", 1),
        PAID("1", 2),
        CANCELED("-1",3);
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
