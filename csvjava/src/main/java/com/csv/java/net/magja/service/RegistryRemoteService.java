/**
 *
 */
package com.csv.java.net.magja.service;

import com.csv.java.net.magja.soap.SoapClient;

/**
 * Responsible for creating Remote Service instances.
 *
 * @author ceefour
 */
public interface RegistryRemoteService {

  void setSoapClient(SoapClient instance);

}
