/**
 *
 */
package com.csv.java.net.magja.soap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.csv.java.net.magja.magento.ResourcePath;
import org.apache.axis2.AxisFault;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author andrefabbro
 *
 */
public class MagentoSoapClientTest {

  private static final String HOST_SERVER_ONE = "http://localhost.blackhawk/magento/index.php/api/soap/";
  private static final String HOST_SERVER_TWO = "http://localhost.blackhawk/rockstore/index.php/api/soap/";
  private static final String HOST_SERVER_THREE = "http://localhost.blackhawk/magento/index.php/api/soap/";

  private static final Logger log = LoggerFactory.getLogger(MagentoSoapClientTest.class);

  /**
   * In order to run this test, you have to setup three magento instances, and
   * fill the api key and user and host as show in soap config.
   *
   * This tests if the magentoSoapClient instances its exactly the same
   * instances as created before
   *
   * @throws Exception
   */
  @Ignore
  @Test
  public void testInstances() throws Exception {

    SoapConfig soapConfig1 = new SoapConfig("soap", "test123", HOST_SERVER_ONE);
    SoapConfig soapConfig2 = new SoapConfig("soap", "test123", HOST_SERVER_TWO);
    SoapConfig soapConfig3 = new SoapConfig("soap", "test123", HOST_SERVER_THREE);

    SoapClient client_default_one = MagentoSoapClient.getInstance();

    SoapClient client_one = MagentoSoapClient.getInstance(soapConfig1);

    SoapClient client_two_one = MagentoSoapClient.getInstance(soapConfig2);
    SoapClient client_two_two = MagentoSoapClient.getInstance(soapConfig2);
    SoapClient client_two_three = MagentoSoapClient.getInstance(soapConfig2);

    SoapClient client_three_one = MagentoSoapClient.getInstance(soapConfig3);
    SoapClient client_three_two = MagentoSoapClient.getInstance(soapConfig3);
    SoapClient client_three_three = MagentoSoapClient.getInstance(soapConfig3);

    assertTrue(client_one == client_default_one);
    assertTrue(client_two_one == client_two_two);
    assertTrue(client_two_one == client_two_three);
    assertTrue(client_three_one == client_three_two);
    assertTrue(client_three_one == client_three_three);

    SoapClient client_default_two = MagentoSoapClient.getInstance();
    assertTrue(client_one == client_default_two);

    assertEquals(client_default_one.getConfig().getRemoteHost(), HOST_SERVER_ONE);
    assertEquals(client_default_two.getConfig().getRemoteHost(), HOST_SERVER_ONE);
    assertEquals(client_one.getConfig().getRemoteHost(), HOST_SERVER_ONE);
    assertEquals(client_two_one.getConfig().getRemoteHost(), HOST_SERVER_TWO);
    assertEquals(client_two_two.getConfig().getRemoteHost(), HOST_SERVER_TWO);
    assertEquals(client_two_three.getConfig().getRemoteHost(), HOST_SERVER_TWO);
    assertEquals(client_three_one.getConfig().getRemoteHost(), HOST_SERVER_THREE);
    assertEquals(client_three_two.getConfig().getRemoteHost(), HOST_SERVER_THREE);
    assertEquals(client_three_three.getConfig().getRemoteHost(), HOST_SERVER_THREE);
  }

  @SuppressWarnings("unchecked")
  @Test
  @Ignore
  public void testTimeOutSession() throws Exception {
    log.info("Start - {}", System.currentTimeMillis());
    SoapClient soapClient = MagentoSoapClient.getInstance();
    List<Map<String, Object>> remote_list = (List<Map<String, Object>>) soapClient.callSingle(ResourcePath.CountryList, "");
    log.info("{}", remote_list);
    log.info("Wait 1 min - {}", System.currentTimeMillis());
    Thread.sleep(60000);
    List<Map<String, Object>> remote_list2 = (List<Map<String, Object>>) soapClient.callSingle(ResourcePath.CountryList, "");
    log.info("{}", remote_list2);
    log.info("Finished - {}", System.currentTimeMillis());
  }

  /**
   * catalog_category.level
   *
   * Retrieve one level of categories by website/store view/parent category
   *
   * Return: array
   *
   * Arguments:
   *
   * mixed website - website code or Id (optional) mixed storeView - store view
   * code or Id (optional) mixed parentCategory - parent category Id (optional)
   *
   * @throws AxisFault
   */
  @Test
  @Ignore
  public void testCategoryLevelBlank() throws AxisFault {
    SoapConfig annafiSoap = new SoapConfig("sysadmin", "", "http://ceefour.annafi/demo/api/soap");
    SoapClient client = MagentoSoapClient.getInstance(annafiSoap);
    @SuppressWarnings("unchecked")
    List<Map<String, Object>> categories = (List<Map<String, Object>>) client.callSingle(ResourcePath.CategoryLevel, Arrays.asList(null, null, 45));
    // log.info( mapper.writeValueAsString(categories) );
    assertEquals(0, categories.size());
  }

}
