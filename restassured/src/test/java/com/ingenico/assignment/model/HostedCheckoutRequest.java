package com.ingenico.assignment.model;

import java.util.Objects;

public class HostedCheckoutRequest {

	private int amount;
	private Currency currency;

	public HostedCheckoutRequest(Currency currency, int amount) {
		this.currency = Objects.requireNonNull(currency);
		if (amount < 1)
			throw new IllegalArgumentException("Amount is expected to be more than 0");

		this.amount = amount;
	}

	public Order getOrder() {
		return new Order(currency, amount);
	}

	public CheckoutSpecificInput getHostedCheckoutSpecificInput() {
		return new CheckoutSpecificInput();
	}
}
