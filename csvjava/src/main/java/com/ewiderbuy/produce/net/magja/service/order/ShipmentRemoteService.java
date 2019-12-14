package com.ewiderbuy.produce.net.magja.service.order;

import com.ewiderbuy.produce.net.magja.model.order.Filter;
import com.ewiderbuy.produce.net.magja.model.order.Shipment;
import com.ewiderbuy.produce.net.magja.model.order.ShipmentTrack;
import com.ewiderbuy.produce.net.magja.service.GeneralService;
import com.ewiderbuy.produce.net.magja.service.ServiceException;
import com.ewiderbuy.produce.net.magja.service.GeneralService;

import java.util.List;
import java.util.Map;
/**
 * Shipment service.
 * @author andre
 * @author Simon Zambrovski
 */
public interface ShipmentRemoteService extends GeneralService<Shipment> {

  List<Shipment> list(Filter filter) throws ServiceException;

  Shipment getById(Integer id) throws ServiceException;

  void save(Shipment shipment, String comment, Boolean email, Boolean includeComment) throws ServiceException;

  void addComment(Shipment shipment, String comment, Boolean email, Boolean includeComment) throws ServiceException;

  void addTrack(Shipment shipment, ShipmentTrack track) throws ServiceException;

  void removeTrack(Shipment shipment, ShipmentTrack track) throws ServiceException;

  Map<String, String> getCarriers(Integer orderId) throws ServiceException;

  void sendInfo(Shipment shipment, String comment) throws ServiceException;

}
