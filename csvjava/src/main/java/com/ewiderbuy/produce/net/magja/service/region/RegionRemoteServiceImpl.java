/**
 * @author andre
 *
 */
package com.ewiderbuy.produce.net.magja.service.region;

import com.ewiderbuy.produce.net.magja.magento.ResourcePath;
import com.ewiderbuy.produce.net.magja.model.region.Region;
import com.ewiderbuy.produce.net.magja.service.GeneralServiceImpl;
import com.ewiderbuy.produce.net.magja.service.ServiceException;
import com.ewiderbuy.produce.net.magja.soap.SoapClient;
import com.ewiderbuy.produce.net.magja.model.region.Region;
import com.ewiderbuy.produce.net.magja.soap.SoapClient;
import org.apache.axis2.AxisFault;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RegionRemoteServiceImpl extends GeneralServiceImpl<Region> implements RegionRemoteService {

  private static final long serialVersionUID = 3543094741234701831L;

  public RegionRemoteServiceImpl(SoapClient soapClient) {
    super(soapClient);
  }

  /*
   * (non-Javadoc)
   *
   * @see
   * net.magja.magja.service.region.RegionRemoteService#list(java.lang.
   * String)
   */
  @Override
  public List<Region> list(String countryId) throws ServiceException {
    List<Region> regions = new ArrayList<Region>();

    List<Map<String, Object>> remote_list = null;
    try {
      remote_list = soapClient.callSingle(ResourcePath.RegionList, countryId);
    } catch (AxisFault e) {
      if (debug)
        e.printStackTrace();
      throw new ServiceException(e.getMessage());
    }

    if (remote_list == null)
      return regions;

    for (Map<String, Object> map : remote_list) {

      Region region = new Region();

      for (Map.Entry<String, Object> attr : map.entrySet())
        region.set(attr.getKey(), attr.getValue());

      regions.add(region);
    }

    return regions;
  }

}
