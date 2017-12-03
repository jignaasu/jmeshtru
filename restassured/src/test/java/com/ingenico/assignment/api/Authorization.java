package com.ingenico.assignment.api;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.StringJoiner;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class Authorization {

	private static final String HMAC_SHA_256 = "HmacSHA256";

	private static final Logger LOGGER = LoggerFactory.getLogger(Authorization.class);

	private Mac hmacSHA256;

	private final String relativeUrl;

	private String httpMethod;

	private String date;

	Authorization(String httpMethod, String relativeUrl, String date) {
		this.httpMethod = httpMethod;
		this.relativeUrl = relativeUrl;
		this.date = date;
		initializeHashingAlgorithm();
	}

	private void initializeHashingAlgorithm() {

		String privateKey = "88urBAOyWVLIiyBK+QZ+RaTDdAQv4fyF9iRHYx2wSRI=";

		try {
			hmacSHA256 = Mac.getInstance(HMAC_SHA_256);
			SecretKeySpec secretKeySpec = new SecretKeySpec(privateKey.getBytes(Charset.forName("UTF-8")), HMAC_SHA_256);
			hmacSHA256.init(secretKeySpec);
		} catch (NoSuchAlgorithmException | InvalidKeyException e) {
			LOGGER.error("Error while initializing the hash function", e);
		}

	}

	public String getAuthorizationHeaderValue() {
		String publicKey = "5445c2039acd4812";
		StringJoiner dataBuilder = new StringJoiner("\n", "", "\n");
		dataBuilder.add(httpMethod.toUpperCase())
				.add("application/json")
				.add(date)
				.add(relativeUrl);
		byte[] finalData = new byte[0];
		try {
			finalData = hmacSHA256.doFinal(dataBuilder.toString().getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			LOGGER.error("Could not encode in UTF-8", e);
		}
		String authorizationData = Base64.getEncoder().encodeToString(finalData);
		return String.format("GCS v1HMAC:%s:%s", publicKey, authorizationData);
	}
}
