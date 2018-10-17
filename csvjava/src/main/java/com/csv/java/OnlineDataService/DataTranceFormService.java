package com.csv.java.OnlineDataService;

import static com.csv.java.OnlineDataService.ConstantDataService.*;

public interface DataTranceFormService {

    //销售订单到订单系统的订单状态转换
    public static int transformStatus(int payMethod, String status){
        String orderStatus = "";
        if (status.equals(ConstantDataService.StatusEnum.CANCELED.toString()) ) {
            //是canceled状态，无论哪种付费方式，都为-1
            orderStatus = OrderStatusEnum.CANCELED.toString();
        }else {
            if (payMethod == Integer.parseInt(PaymentidEnum.Bank.toString())) {
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
        }
        return Integer.parseInt(orderStatus);
    }

    //销售订单到订单系统的支付类型转换
    public static int transformPayMethod(String payMethod){
        //支付类型
        payMethod = payMethod==null?"":payMethod;
        //paypal支付
        if (payMethod.equals(PayMethodEnum.PAYPAL_STANDARD.toString())){
            return Integer.parseInt(PaymentidEnum.PAYPAL.toString());
        }
        //银行入金
        if (payMethod.equals(PayMethodEnum.CHECKMO.toString())){
            return Integer.parseInt(PaymentidEnum.Bank.toString());
        }
        //信用卡
        if (payMethod.equals(PayMethodEnum.MASAPAY_PAYMENT.toString())){
            return Integer.parseInt(PaymentidEnum.CREDIT_CARD.toString());
        }
        return  0 ;
    }
}
