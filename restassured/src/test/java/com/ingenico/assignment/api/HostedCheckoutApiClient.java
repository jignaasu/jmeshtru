package com.ingenico.assignment.api;

import com.ingenico.assignment.model.HostedCheckoutRequest;
import com.jayway.restassured.response.Response;

public class HostedCheckoutApiClient extends AbstractApiClient {

	private static final String ENDPOINT = "/v1/{merchantId}/hostedcheckouts";

	public HostedCheckoutApiClient(String merchantId) {
		super(ENDPOINT.replace("{merchantId}", merchantId));
	}

	public Response post(HostedCheckoutRequest request) {
		return super.post(request);
	}
}
