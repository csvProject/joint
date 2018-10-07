/**
 *
 */
package com.csv.java.net.magja.magento;

import com.csv.java.net.magja.soap.SoapClient;

/**
 * @author andre
 *
 */
public class ConnectionMock extends Connection {

	public ConnectionMock() {
		super();
	}

	public SoapClient getClient() {
		return client;
	}

}
