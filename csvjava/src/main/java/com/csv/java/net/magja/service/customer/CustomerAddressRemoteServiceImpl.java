package com.csv.java.net.magja.service.customer;

import com.csv.java.net.magja.magento.ResourcePath;
import com.csv.java.net.magja.model.customer.CustomerAddress;
import com.csv.java.net.magja.service.GeneralServiceImpl;
import com.csv.java.net.magja.service.ServiceException;
import com.csv.java.net.magja.soap.SoapClient;
import org.apache.axis2.AxisFault;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Customer service implementation.
 * @author andre
 * @author Simon Zambrovski
 */
public class CustomerAddressRemoteServiceImpl extends GeneralServiceImpl<CustomerAddress> implements CustomerAddressRemoteService {

  private static final long serialVersionUID = 2269696861048421719L;

  public CustomerAddressRemoteServiceImpl(SoapClient soapClient) {
    super(soapClient);
  }

  /**
   * Create a object CustomerAddress from the attributes map
   *
   * @param attributes
   * @return CustomerAddress
   */
  private CustomerAddress buildCustomerAddress(Map<String, Object> attributes) {
    CustomerAddress address = new CustomerAddress();

    for (Map.Entry<String, Object> attr : attributes.entrySet())
      address.set(attr.getKey(), attr.getValue());

    return address;
  }

  @Override
  public void delete(Integer id) throws ServiceException {

    try {
      Boolean success = (Boolean) soapClient.callSingle(ResourcePath.CustomerAddressDelete, id);
      if (!success)
        throw new ServiceException("Error deleting the Customer Address");
    } catch (AxisFault e) {
      if (debug)
        e.printStackTrace();
      throw new ServiceException(e.getMessage());
    }

  }

  @Override
  public void deleteAll(Integer customerId) throws ServiceException {
    List<CustomerAddress> addresses = list(customerId);
    for (CustomerAddress address : addresses) {
      delete(address.getId());
    }
  }

  @Override
  public CustomerAddress getById(Integer id) throws ServiceException {

    Map<String, Object> remote_result = null;
    try {
      remote_result = (Map<String, Object>) soapClient.callSingle(ResourcePath.CustomerAddressInfo, id);
    } catch (AxisFault e) {
      if (debug)
        e.printStackTrace();
      throw new ServiceException(e.getMessage());
    }

    if (remote_result == null)
      return null;
    else
      return buildCustomerAddress(remote_result);

  }

  /*
   * (non-Javadoc)
   *
   * @see
   * net.magja.magja.service.customer.CustomerAddressRemoteService#list(
   * java.lang.Integer)
   */
  @Override
  public List<CustomerAddress> list(Integer customerId) throws ServiceException {

    List<CustomerAddress> addresses = new ArrayList<CustomerAddress>();

    List<Map<String, Object>> resultList = null;
    try {
      resultList = (List<Map<String, Object>>) soapClient.callSingle(ResourcePath.CustomerAddressList, customerId);
    } catch (AxisFault e) {
      if (debug)
        e.printStackTrace();
      throw new ServiceException(e.getMessage());
    }

    if (resultList == null)
      return addresses;

    for (Map<String, Object> add : resultList)
      addresses.add(buildCustomerAddress(add));

    return addresses;
  }

  @Override
  public void save(CustomerAddress customerAddress) throws ServiceException {

    if (customerAddress.getId() == null) {
      try {
        Object[] customerAddressObj = customerAddress.serializeToApi();
        Integer id = Integer.parseInt((String) soapClient.callArgs(ResourcePath.CustomerAddressCreate, customerAddressObj));
        customerAddress.setId(id);
      } catch (NumberFormatException e) {
        if (debug)
          e.printStackTrace();
        throw new ServiceException(e.getMessage());
      } catch (AxisFault e) {
        if (debug)
          e.printStackTrace();
        throw new ServiceException(e.getMessage());
      }
    } else {
      try {
        Boolean success = (Boolean) soapClient.callArgs(ResourcePath.CustomerAddressUpdate, customerAddress.serializeToApi());
        if (!success)
          throw new ServiceException("Error updating Customer Address");
      } catch (AxisFault e) {
        if (debug)
          e.printStackTrace();
        throw new ServiceException(e.getMessage());
      }
    }
  }

}
