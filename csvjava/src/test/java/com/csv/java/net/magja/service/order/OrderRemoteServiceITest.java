package com.csv.java.net.magja.service.order;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import com.csv.java.OnlineDataService.PHPSerializeUtil;
import com.csv.java.net.magja.model.address.BasicAddress;
import com.csv.java.net.magja.model.order.*;
import com.csv.java.net.magja.service.RemoteServiceFactory;
import com.csv.java.net.magja.service.ServiceException;
import com.csv.java.net.magja.soap.MagentoSoapClient;
import de.ailis.pherialize.MixedArray;
import de.ailis.pherialize.Pherialize;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ImmutableList;

/**
 * Order Service Test.
 *
 * @author andre
 * @author Simon Zambrovski
 */
public class OrderRemoteServiceITest {
  private final static Logger log = LoggerFactory.getLogger(OrderRemoteService.class);
  private OrderRemoteService service;

  @Before
  public void setUp() throws Exception {
    final RemoteServiceFactory remoteServiceFactory = new RemoteServiceFactory(MagentoSoapClient.getInstance());
    service = remoteServiceFactory.getOrderRemoteService();
  }

  @Test
  public void addCommentToOrder() {

    Order order = new Order();
    //order.setId(510002456);
    order.setOrderNumber("510002456");

    try {
      service.addComment(order, "processing", "Hello World", false);
    } catch (ServiceException e) {
      fail(e.getMessage());
    }

  }

  @Test
  public void cancelOrder() {

    Order order = new Order();
    order.setId(100000001);

    try {
      service.cancel(order);
    } catch (ServiceException e) {
      fail(e.getMessage());
    }

  }



  @Test
  public void getById() throws ServiceException {
    final Order order = service.getById(510011062);
    log.info("Order {}: {}", order);
    assertNotNull(order);
    String contents = "";
    for ( int i =0,len = order.getOrderStatusHistories().size();i < len;i++) {
      OrderStatusHistory orderStatusHistory = order.getOrderStatusHistories().get(i);
      String comment = orderStatusHistory.getComment() == null?"":orderStatusHistory.getComment();
      if (!"".equals(comment)) {
        contents = orderStatusHistory.getCreatedAt() + " " +
                comment + ";" + contents;
      }
    }
    System.out.println(contents);

    // System.out.println(order.getCustomer().toString());
    // System.out.println(order.getShippingAddress().toString());
    // System.out.println(order.getBillingAddress().toString());

    for (final OrderItem item : order.getItems()) {


      String productOptions = item.getProductOptions();

      productOptions = productOptions==null?"":productOptions;
      int a = PHPSerializeUtil.getSizeId(productOptions);


      log.info("Item #{}: {}", item.getId(), item);
    }
  }

  @Test
  public void holdOrder() throws ServiceException {

    Order order = new Order();
    order.setId(100000003);

    service.hold(order);

  }

  @Test
  public void unholdOrder() {
    /*
     * Order order = new Order(); order.setId(100000003);
     *
     * try { service.unhold(order); } catch (ServiceException e) {
     * fail(e.getMessage()); }
     */
  }

  @Test
  public void testList() throws ServiceException {
    Filter filter1 = new Filter();
    String [] aa={"510010000","1010010010"};
    //filter1.getItems().add(new FilterItem("increment_id", "in", aa));
    filter1.getItems().add(new FilterItem("increment_id", "gt", "510010000"));
    //filter1.getItems().add(new FilterItem("status", "eq", "success"));
    List<Order> list = service.list(filter1);
    for (Order order : list)
      System.out.println("order.getStatus():"+order.getStatus().toString());

    // TODO: is not working tha find with filter
    // make sure to have a order with billing_name = Joao da Silva
    Filter filter = new Filter();
    filter.getItems().add(new FilterItem("billing_name", "like", "%Silva%"));
    // filter.getItems().add(new FilterItem("billing_lastname", "=",
    // "Martins"));

    List<Order> filtered = service.list(filter);
    for (Order order : filtered) {
      System.out.println(order.toString());
    }

  }

  @Test
  public void createValidOrderFormShouldSucceed() throws ServiceException {
    ImmutableList<OrderFormItem> items = ImmutableList.of(new OrderFormItem(1566L, 1.0), new OrderFormItem(1556L, 1.0));
    long customerId = 3L;
    OrderForm orderForm = new OrderForm(customerId, "IDR", items);
    Object order = service.create(orderForm);
    System.out.println("Order Id " + order);
    assertNotNull(order);
  }

  @Test(expected = IllegalArgumentException.class)
  public void createWithNullCustomerIdShouldFail() throws ServiceException {
    ImmutableList<OrderFormItem> items = ImmutableList.of(new OrderFormItem(194L, 1.0), new OrderFormItem(195L, 3.0));
    OrderForm orderForm = new OrderForm(null, "IDR", items);
    Object order = service.create(orderForm);
    assertNull(order);
  }

  @Test(expected = IllegalArgumentException.class)
  public void createWithNoItemsShouldFail() throws ServiceException {
    List<OrderFormItem> items = new ArrayList<OrderFormItem>();
    OrderForm orderForm = new OrderForm(3L, "IDR", items);
    Object order = service.create(orderForm);
    assertNull(order);
  }

  @Test
  public void createNewOrderWithCustomAddress() throws ServiceException {
    /*
     * set address
     */
    BasicAddress customerAddress = new BasicAddress();
    customerAddress.setFirstName("Atang");
    customerAddress.setLastName("Sutisna");
    customerAddress.setStreet("Jl. Setiabudi Bandung No.6c");
    customerAddress.setCity("Bandung");
    customerAddress.setRegion("Jawa Barat");
    customerAddress.setPostCode("41123");
    customerAddress.setCountryCode("ID");
    customerAddress.setTelephone("022-09898989898");
    customerAddress.setCompany("Rachmart Family");

    log.info("customer address {}", customerAddress);
    List<OrderFormItem> items = ImmutableList.of(new OrderFormItem(1223L, 1.0) // ,
    // new OrderFormItem(1221L, 1.0)
    );
    OrderForm orderForm = new OrderForm(2L, "IDR", items, customerAddress, customerAddress);

    log.info("orderform {}", orderForm);
    log.info("shippingAddress {}", orderForm.getShippingAddress());
    log.info("billingAddress {}", orderForm.getBillingAddress());
    Object order = service.createEx(orderForm);
    assertNotNull(order);
  }

  @Test
  public void createNewOrderWithCustomAddressAndShippingAmount() throws ServiceException {
    /*
     * set address
     */
    BasicAddress customerAddress = new BasicAddress();
    customerAddress.setFirstName("Atang");
    customerAddress.setLastName("Sutisna");
    customerAddress.setStreet("Jl. Setiabudi Bandung No.6c");
    customerAddress.setCity("Bandung");
    customerAddress.setRegion("Jawa Barat");
    customerAddress.setPostCode("41123");
    customerAddress.setCountryCode("ID");
    customerAddress.setTelephone("022-09898989898");
    customerAddress.setCompany("Rachmart Family");

    log.info("customer address {}", customerAddress);
    List<OrderFormItem> items = ImmutableList.of(new OrderFormItem(1222L, 1.0) // ,
    // new OrderFormItem(1221L, 1.0)
    );
    OrderForm orderForm = new OrderForm(2L, "IDR", items, customerAddress, customerAddress);
    orderForm.setPaymentMethod("banktransfer");
    orderForm.setShippingMethod("flatrate_flatrate");
    orderForm.setShippingDescription("JNE Reguler");
    orderForm.setShippingAmount(15000.0);

    log.info("orderform {}", orderForm);
    log.info("shippingAddress {}", orderForm.getShippingAddress());
    log.info("billingAddress {}", orderForm.getBillingAddress());
    Object order = service.createEx(orderForm);
    log.info("Created order {}", order);
    assertNotNull(order);
  }
}
