package com.ewiderbuy.produce.net.magja.service.product;

import com.ewiderbuy.produce.net.magja.model.product.Product;
import com.ewiderbuy.produce.net.magja.model.product.ProductMedia;
import com.ewiderbuy.produce.net.magja.service.GeneralService;
import com.ewiderbuy.produce.net.magja.service.ServiceException;
import com.ewiderbuy.produce.net.magja.model.product.Product;
import com.ewiderbuy.produce.net.magja.service.GeneralService;
import com.ewiderbuy.produce.net.magja.service.ServiceException;

import java.util.List;

/**
 * Product media service.
 * @author andre
 * @author Simon Zambrovski
 */
public interface ProductMediaRemoteService extends GeneralService<ProductMedia> {

  /**
   * List the product medias for the product specified, the product just must to
   * have the id or sku attributes setted
   *
   * @param product
   * @return List<ProductMedia>
   * @throws ServiceException
   */
  List<ProductMedia> listByProduct(Product product) throws ServiceException;

  /**
   * Get the info of the product media by the product and file name specified,
   * the product just must to have the id or sku specified
   *
   * @param product
   * @param file
   * @return
   * @throws ServiceException
   */
  ProductMedia getByProductAndFile(Product product, String file) throws ServiceException;

  /**
   * Get MD5 for a file This is not a default Magento function! You have to
   * extend your Magento API. See:
   * http://code.google.com/p/magja/downloads/detail?name=media.md5.api.xml.diff
   * and
   * http://code.google.com/p/magja/downloads/detail?name=media.md5.api.xml.diff
   *
   * @param file
   * @return MD5 sum
   * @throws ServiceException
   */
  String getMd5(String file) throws ServiceException;

  /**
   * Save a new ProductMedia to the database, the object must have the product
   * id specified
   *
   * @param productMedia
   * @throws ServiceException
   */
  void create(ProductMedia productMedia) throws ServiceException;

  /**
   * Delete a Product Media from magento, the object must have the product id or
   * sku specified
   *
   * @param productMedia
   * @throws ServiceException
   */
  void delete(ProductMedia productMedia) throws ServiceException;

  Boolean update(ProductMedia productMedia) throws ServiceException;
}
