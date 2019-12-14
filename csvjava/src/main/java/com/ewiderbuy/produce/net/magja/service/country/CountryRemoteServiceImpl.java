package com.ewiderbuy.produce.net.magja.service.country;

import com.ewiderbuy.produce.net.magja.magento.ResourcePath;
import com.ewiderbuy.produce.net.magja.model.country.Country;
import com.ewiderbuy.produce.net.magja.service.GeneralServiceImpl;
import com.ewiderbuy.produce.net.magja.service.ServiceException;
import com.ewiderbuy.produce.net.magja.soap.SoapClient;
import com.ewiderbuy.produce.net.magja.soap.SoapClient;
import org.apache.axis2.AxisFault;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Country service implementation.
 * @author andre
 * @author Simon Zambrovski
 */
public class CountryRemoteServiceImpl extends GeneralServiceImpl<Country> implements CountryRemoteService {

  private static final long serialVersionUID = 1671845484676469453L;

  public CountryRemoteServiceImpl(SoapClient soapClient) {
    super(soapClient);
  }

  @Override
  public List<Country> list() throws ServiceException {

    List<Country> countries = new ArrayList<Country>();

    List<Map<String, Object>> remote_list = null;
    try {
      remote_list = (List<Map<String, Object>>) soapClient.callSingle(ResourcePath.CountryList, "");
    } catch (AxisFault e) {
      if (debug)
        e.printStackTrace();
      throw new ServiceException(e.getMessage());
    }

    if (remote_list == null)
      return countries;

    for (Map<String, Object> map : remote_list) {

      Country country = new Country();

      for (Map.Entry<String, Object> attr : map.entrySet())
        country.set(attr.getKey(), attr.getValue());

      countries.add(country);
    }

    return countries;
  }

  @Override
  public Country getCountryByName(String countryName) throws ServiceException {
    List<Country> countries = list();
    for (Country country : countries) {
      if (country.getName().equals(countryName)) {
        return country;
      }
    }

    // Country not found
    throw new ServiceException(countryName + " not found");
  }

  @Override
  public String getCountryIdByName(String countryName) throws ServiceException {
    Country country = getCountryByName(countryName);

    return country.getCountryId();
  }
}
