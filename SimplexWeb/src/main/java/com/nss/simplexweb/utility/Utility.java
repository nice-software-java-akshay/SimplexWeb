package com.nss.simplexweb.utility;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.nss.simplexweb.enums.UTILITY_CONSTANT;
import com.nss.simplexweb.user.model.EndPoint;

@Component("utility")
public class Utility {

	@Autowired
	private RequestMappingHandlerMapping requestMappingHandlerMapping;
	
	////Ref: https://stackoverflow.com/questions/9766800/how-to-show-all-controllers-and-mappings-in-a-view
	public ArrayList<EndPoint> getEndPoints() {
		ArrayList<EndPoint> endPointsList = null;
		Set<RequestMappingInfo> mappingsInfo = requestMappingHandlerMapping.getHandlerMethods().keySet();
		
		for(RequestMappingInfo mapInfo : mappingsInfo) {
			EndPoint endPoint = new EndPoint();
			endPoint.setPatternsCondition(endPoint.getPatternsCondition());
			endPoint.setMethodsCondition(mapInfo.getMethodsCondition());
			endPoint.setConsumesCondition(endPoint.getConsumesCondition());
			endPoint.setProducesCondition(endPoint.getProducesCondition());
			endPoint.setParamsCondition(endPoint.getParamsCondition());
			endPoint.setHeadersCondition(endPoint.getHeadersCondition());
			endPoint.setCustomCondition(endPoint.getCustomCondition());
			
			if(endPointsList == null) {
				endPointsList = new ArrayList<>();
			}
			System.out.println("endPoint : " + endPoint);
			endPointsList.add(endPoint);
		}
		System.out.println("endPointsList : " + endPointsList);
		return endPointsList;
	}

	public static String generateRandomPassword(int length) {
		String generatedString = RandomStringUtils.randomAlphanumeric(length);
		return generatedString;
	}
	
	public static String generateRandomEnquiryNumber(int length) {
		final String prefix = UTILITY_CONSTANT.ENQUIRY_PREFIX;
		String generatedString = prefix + RandomStringUtils.randomNumeric(8);
		return generatedString;
	}

	
	public static Properties propertiesFileReader(final String FILE_NAME) {
		Properties props = null;
		try {
			Resource resource = new ClassPathResource(File.separator + FILE_NAME + ".properties");
			props = PropertiesLoaderUtils.loadProperties(resource);
			props.list(System.out);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return props;
	}
	
	
	/**
	 * This method makes a "deep clone" of any Java object it is given.
	 */
	 public static Object deepClone(Object object) {
	   try {
	     ByteArrayOutputStream baos = new ByteArrayOutputStream();
	     ObjectOutputStream oos = new ObjectOutputStream(baos);
	     oos.writeObject(object);
	     ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
	     ObjectInputStream ois = new ObjectInputStream(bais);
	     Object clone = ois.readObject();
	     //System.out.println("deepClone : " + ois.readObject().toString());
	     return clone;
	   }
	   catch (Exception e) {
	     e.printStackTrace();
	     return null;
	   }
	 }
}
