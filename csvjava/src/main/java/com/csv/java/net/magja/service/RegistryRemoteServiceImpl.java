package com.csv.java.net.magja.service;

import com.csv.java.net.magja.soap.SoapClient;

/**
 * @author ceefour
 *
 */
public class RegistryRemoteServiceImpl implements RegistryRemoteService {

  private SoapClient soapClient;

  public RegistryRemoteServiceImpl(SoapClient soapClient) {
    super();
    this.soapClient = soapClient;
  }

  /**
   * @return the soapClient
   */
  public SoapClient getSoapClient() {
    return soapClient;
  }

  /**
   * @param soapClient
   *          the soapClient to set
   */
  public void setSoapClient(SoapClient soapClient) {
    this.soapClient = soapClient;
  }

}
