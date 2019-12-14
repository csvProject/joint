package com.ewiderbuy.produce.net.magja.magento;

import com.ewiderbuy.produce.net.magja.soap.MagentoSoapClient;
import com.ewiderbuy.produce.net.magja.soap.SoapClient;
import com.ewiderbuy.produce.net.magja.soap.MagentoSoapClient;
import com.ewiderbuy.produce.net.magja.soap.SoapClient;

public class Connection {

  protected SoapClient client = null;

  public Connection() {
    client = MagentoSoapClient.getInstance();
  }
}
