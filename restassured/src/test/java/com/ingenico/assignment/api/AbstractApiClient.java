package com.ingenico.assignment.api;

import static com.jayway.restassured.RestAssured.config;
import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.config.EncoderConfig.encoderConfig;
import static java.time.format.DateTimeFormatter.RFC_1123_DATE_TIME;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import org.apache.http.HttpHeaders;

import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.internal.mapper.ObjectMapperType;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;

class AbstractApiClient {

	private static final String BASE_URI = "https://api-sandbox.globalcollect.com";

	private static final RequestSpecification requestSpecification = new RequestSpecBuilder()
			.setConfig(config().encoderConfig(encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false)))
			.setBaseUri(BASE_URI)
			.setContentType(ContentType.JSON)
			.setAccept(ContentType.JSON)
			.build();

	private String relativeUrl;

	AbstractApiClient(String relativeUrl) {
		this.relativeUrl = relativeUrl;
	}

	private static RequestSpecification getRequestSpecification() {
		return requestSpecification;
	}

	Response post(Object data) {
		String date = getDate();
		return given(getRequestSpecification())
				.header(HttpHeaders.DATE, date)
				.header(HttpHeaders.AUTHORIZATION, getAuthorizationValue("POST", date))
				.body(data, ObjectMapperType.JACKSON_2)
				.log()
				.all(true)
				.when()
					.post(relativeUrl);
	}

	private String getAuthorizationValue(String httpMethod, String date) {
		return new Authorization(httpMethod, relativeUrl, date).getAuthorizationHeaderValue();
	}

	private static String getDate() {
		return RFC_1123_DATE_TIME.format(ZonedDateTime.now(ZoneOffset.UTC));
	}

}
