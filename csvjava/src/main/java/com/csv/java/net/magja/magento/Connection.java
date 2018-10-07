package com.csv.java.net.magja.magento;

import com.csv.java.net.magja.soap.MagentoSoapClient;
import com.csv.java.net.magja.soap.SoapClient;

public class Connection {

  protected SoapClient client = null;

  public Connection() {
    client = MagentoSoapClient.getInstance();
  }
}
