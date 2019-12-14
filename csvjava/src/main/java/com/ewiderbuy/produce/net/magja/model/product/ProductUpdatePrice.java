package com.ewiderbuy.produce.net.magja.model.product;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author atang
 *
 */
@SuppressWarnings("serial")
public class ProductUpdatePrice implements Serializable {
  private String sku;
  private BigDecimal local_price;
  private BigDecimal price;

  public ProductUpdatePrice() {
    super();
  }

  public ProductUpdatePrice(String sku, BigDecimal local_price, BigDecimal price) {
    super();
    this.sku = sku;
    this.local_price = local_price;
    this.price = price;
  }

  /**
   * @return the local_price
   */
  public BigDecimal getLocal_price() {
    return local_price;
  }

  /**
   * @param local_price
   *          the local_price to set
   */
  public void setLocal_price(BigDecimal local_price) {
    this.local_price = local_price;
  }

  /**
   * @return the price
   */
  public BigDecimal getPrice() {
    return price;
  }

  /**
   * @param price
   *          the price to set
   */
  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  /*
   * (non-Javadoc)
   *
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return String.format("ProductUpdatePrice [sku=%s, local_price=%s, price=%s]", sku, local_price, price);
  }

  /**
   * @return the sku
   */
  public String getSku() {
    return sku;
  }

  /**
   * @param sku
   *          the sku to set
   */
  public void setSku(String sku) {
    this.sku = sku;
  }

}
