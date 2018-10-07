package com.csv.java.net.magja.service.country;

import com.csv.java.net.magja.model.country.Country;
import com.csv.java.net.magja.service.GeneralService;
import com.csv.java.net.magja.service.ServiceException;

import java.util.List;

/**
 * Country service.
 * @author andre
 * @author Simon Zambrovski
 */
public interface CountryRemoteService extends GeneralService<Country> {

  /**
   * @return list of all countries
   * @throws ServiceException
   */
  List<Country> list() throws ServiceException;

  /**
   * @return get country by name
   * @throws ServiceException
   */
  Country getCountryByName(String countryName) throws ServiceException;

  /**
   * @return get country id by name
   * @throws ServiceException
   */
  String getCountryIdByName(String countryName) throws ServiceException;
}
