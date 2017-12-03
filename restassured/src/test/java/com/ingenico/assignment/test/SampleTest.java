package com.ingenico.assignment.test;
import org.junit.Test;

import com.ingenico.assignment.api.HostedCheckoutApiClient;
import com.ingenico.assignment.model.Currency;
import com.ingenico.assignment.model.HostedCheckoutRequest;
import com.jayway.restassured.response.Response;

public class SampleTest {

	@Test
	public void testRestAssured() {
		HostedCheckoutRequest hostedCheckoutRequest = new HostedCheckoutRequest(Currency.EUR, 100);
		Response response = new HostedCheckoutApiClient("1264").post(hostedCheckoutRequest);
		String partialRedirectUrl = response.then()
				.statusCode(201)
				.extract()
				.jsonPath()
				.getString("partialRedirectUrl");
		System.out.println(partialRedirectUrl);
	}
}
