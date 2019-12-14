/**
 * @author andre
 *
 */
package com.ewiderbuy.produce.net.magja.service.category;

import com.ewiderbuy.produce.net.magja.model.category.CategoryAttribute;
import com.ewiderbuy.produce.net.magja.service.GeneralService;
import com.ewiderbuy.produce.net.magja.service.ServiceException;
import com.ewiderbuy.produce.net.magja.model.category.CategoryAttribute;
import com.ewiderbuy.produce.net.magja.service.GeneralService;

import java.util.List;

public interface CategoryAttributeRemoteService extends GeneralService<CategoryAttribute> {

	/**
	 * List all categories attributes from a specific store view
	 * @param storeView
	 * @return List<CategoryAttribute>
	 * @throws ServiceException
	 */
	public abstract List<CategoryAttribute> listAll(String storeView) throws ServiceException;



}
