package com.ewiderbuy.produce.net.magja.service.region;

import com.ewiderbuy.produce.net.magja.model.region.Region;
import com.ewiderbuy.produce.net.magja.service.GeneralService;
import com.ewiderbuy.produce.net.magja.service.ServiceException;
import com.ewiderbuy.produce.net.magja.service.GeneralService;

import java.util.List;

/**
 * Region service.
 * @author andre
 * @author Simon Zambrovski
 */
public interface RegionRemoteService extends GeneralService<Region> {

  /**
   * Retrieves all regions for a given country.
   * @param countryId
   * @return list of all regions of the specified country id
   * @throws ServiceException
   */
  List<Region> list(String countryId) throws ServiceException;

}
