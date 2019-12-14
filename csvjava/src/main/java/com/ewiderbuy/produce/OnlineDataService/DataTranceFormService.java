package com.ewiderbuy.produce.OnlineDataService;


import com.ewiderbuy.produce.entity.OrderDto;
import org.apache.commons.codec.binary.Base64;


import static com.ewiderbuy.produce.OnlineDataService.ConstantDataService.*;

public interface DataTranceFormService {

    //销售订单到订单系统的订单状态转换
    public static int transformStatus(OrderDto orderDto, String status){
        String orderStatus = "";
        int payMethod = orderDto.getPaymentid();
        if (status.equals(ConstantDataService.StatusEnum.CANCELED.toString()) ) {
            //是canceled状态，无论哪种付费方式，都为-1
            orderStatus = OrderStatusEnum.CANCELED.toString();
        }else {
            if (payMethod == Integer.parseInt(PaymentidEnum.Bank.toString())) {
                //银行入金付费完成由订单系统去更新，从销售库过来直接对应6
                orderStatus = OrderStatusEnum.UNPAID.toString();
            } else {
                //信用卡或paypal的时候，如果是processing或success订单库对应1 其他都是6
                if (status.equals(StatusEnum.PROCESSING.toString()) ||
                        status.equals(StatusEnum.SUCCESS.toString())  ) {
                    orderStatus = OrderStatusEnum.PAID.toString();

                } else if(status.equals(StatusEnum.PAYMENT_REVIEW.toString()) ||
                        status.equals(StatusEnum.PENDING.toString()) ||
                        status.equals(StatusEnum.PENDING_PAYMENT.toString())){
                    orderStatus = OrderStatusEnum.UNPAID.toString();
                } else{
                    orderStatus = "-1";
                    System.out.println("订单号（"+ orderDto.getOrderId() +"）的销售库状态异常，"+
                            "status为"+status+"未进行处理");
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

    public static String transformPHPencode(String trans){
        if ("".equals(trans)){
            return "";
        }
        String ret = Base64.encodeBase64URLSafeString(trans.getBytes());
        ret = ret.substring(0,1) + "123456789" +
                ret.substring(1);
        return ret;
    }
}
