/**
 * @author andre
 *
 */
package com.csv.java.net.magja.service.category;

import com.csv.java.net.magja.model.category.CategoryAttribute;
import com.csv.java.net.magja.service.GeneralService;
import com.csv.java.net.magja.service.ServiceException;

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
