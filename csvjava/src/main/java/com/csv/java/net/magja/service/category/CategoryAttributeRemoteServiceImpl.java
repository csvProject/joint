/**
 * @author andre
 *
 */
package com.csv.java.net.magja.service.category;

import com.csv.java.net.magja.magento.ResourcePath;
import com.csv.java.net.magja.model.category.CategoryAttribute;
import com.csv.java.net.magja.model.category.CategoryAttributeOption;
import com.csv.java.net.magja.service.GeneralServiceImpl;
import com.csv.java.net.magja.service.ServiceException;
import com.csv.java.net.magja.soap.SoapClient;
import org.apache.axis2.AxisFault;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CategoryAttributeRemoteServiceImpl extends
		GeneralServiceImpl<CategoryAttribute> implements
		CategoryAttributeRemoteService {

	private static final long serialVersionUID=-6950318891229060141L;

	public CategoryAttributeRemoteServiceImpl(SoapClient soapClient) {
		super(soapClient);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * net.magja.magja.service.category.CategoryAttributeRemoteService#listAll
	 * (java.lang.String)
	 */
	@Override
	public List<CategoryAttribute> listAll(String storeView)
			throws ServiceException {

		List<CategoryAttribute> results = new ArrayList<CategoryAttribute>();

		List<Map<String, Object>> attributes = null;
		try {
			attributes = (List<Map<String, Object>>) soapClient.callSingle(
					ResourcePath.CategoryAttributeList, "");
		} catch (AxisFault e) {
			if(debug) e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

		if (attributes == null)
			return results;

		for (Map<String, Object> att : attributes) {

			CategoryAttribute attribute = new CategoryAttribute();

			for (Map.Entry<String, Object> attr : att.entrySet())
				attribute.set(attr.getKey(), attr.getValue());

			// verify options
			if (att.get("type") != null) {
				String type = (String) att.get("type");
				if (type.equals("select") || type.equals("multiselect")) {

//					List<Object> optParamList = new LinkedList<Object>();
//					optParamList.add(att.get("attribute_id"));
//					optParamList.add(storeView);

					List<Map<String, Object>> optList = null;
					try {
						optList = soapClient.callArgs(
								ResourcePath.CategoryAttributeOptions,
								new Object[] {
										att.get("attribute_id"), storeView
								});
					} catch (AxisFault e) {
						if(debug) e.printStackTrace();
						throw new ServiceException(e.getMessage());
					}

					if (optList != null) {
						for (Map<String, Object> optAtt : optList) {

							CategoryAttributeOption option = new CategoryAttributeOption();
							option.setLabel((String) optAtt.get("label"));
							option.setValue(optAtt.get("value"));

							attribute.getOptions().add(option);
						}
					}
				}
			}

			results.add(attribute);
		}

		return results;
	}

}
