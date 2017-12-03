package com.ingenico.assignment.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Map;
import java.util.Properties;

class Customer {

	private static Customer INSTANCE;

	static {
		try {
			Properties properties = new Properties();
			InputStream propertiesStream = Customer.class.getClassLoader()
					.getResourceAsStream("customerConfig.properties");
			properties.load(propertiesStream);
			String property = properties.getProperty("merchantId");
			INSTANCE = new Customer(property);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Customer getInstance() {
		return INSTANCE;
	}

	private String merchantId;

	private Customer(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getMerchantCustomerId() {
		return merchantId;
	}

	public Map<String, String> getBillingAddress() {
		return Collections.singletonMap("countryCode", "NL");
	}

}
