package com.csv.java.net.magja.service;

import com.csv.java.net.magja.model.BaseMagentoModel;
import com.csv.java.net.magja.soap.SoapClient;

import java.io.Serializable;

/**
 * General
 * @author andre
 * @author Simon Zambrovski
 */
@SuppressWarnings("rawtypes")
public interface GeneralService<T extends BaseMagentoModel> extends Serializable {

  /**
   * Retrieves the SOAP client.
   *
   * @return SOAP client for this service.
   */
  SoapClient getSoapClient();

  /**
   * Set debugging for this service.
   *
   * @param debug
   *          if true, debugging is enabled.
   */
  void setDebug(Boolean debug);
}
