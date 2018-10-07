/**
 * @author andre
 *
 */
package com.csv.java.net.magja.service;

import com.csv.java.net.magja.model.BaseMagentoModel;
import com.csv.java.net.magja.soap.SoapClient;

@SuppressWarnings("rawtypes")
public abstract class GeneralServiceImpl<T extends BaseMagentoModel> implements GeneralService<T> {

  private static final long serialVersionUID = -7262909756654576277L;

  protected Boolean debug = false;

  protected SoapClient soapClient;

  public GeneralServiceImpl(final SoapClient soapClient) {
    this.soapClient = soapClient;
  }

  @Override
  public SoapClient getSoapClient() {
    return soapClient;
  }

  /**
   * disable or enable debug informations
   *
   * @param Boolean
   * @return
   */
  public void setDebug(Boolean b) {
    this.debug = b;
  }

}
