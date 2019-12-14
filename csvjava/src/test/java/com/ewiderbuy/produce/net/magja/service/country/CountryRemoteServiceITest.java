/**
 *
 */
package com.ewiderbuy.produce.net.magja.service.country;

import static org.junit.Assert.assertFalse;

import java.util.List;

import com.ewiderbuy.produce.net.magja.model.country.Country;
import com.ewiderbuy.produce.net.magja.service.RemoteServiceFactory;
import com.ewiderbuy.produce.net.magja.service.ServiceException;
import com.ewiderbuy.produce.net.magja.service.region.RegionRemoteServiceITest;
import com.ewiderbuy.produce.net.magja.soap.MagentoSoapClient;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Test for country service.
 *
 * @author andre
 * @author Simon Zambrovski
 */
public class CountryRemoteServiceITest {

  private CountryRemoteService service;
  private final static Logger log = LoggerFactory.getLogger(RegionRemoteServiceITest.class);

  @Before
  public void setUp() throws Exception {
    final RemoteServiceFactory remoteServiceFactory = new RemoteServiceFactory(MagentoSoapClient.getInstance());
    service = remoteServiceFactory.getCountryRemoteService();
  }

  @Test
  public void testList() throws ServiceException {

    List<Country> countries = service.list();
    for (Country country : countries) {
      log.info("{}", country.toString());
    }
    assertFalse(countries.isEmpty());
  }

}
