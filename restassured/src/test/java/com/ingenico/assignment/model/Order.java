package com.ingenico.assignment.model;

import java.util.HashMap;
import java.util.Map;

class Order {

	private int amount;
	private Currency currency;

	public Order(Currency currency, int amount) {
		this.currency = currency;
		this.amount = amount;
	}

	public Map<String, Object> getAmountOfMoney() {
		Map<String, Object> data = new HashMap<>();
		data.put("currencyCode", currency.toString());
		data.put("amount", amount);
		return data;
	}

	public Customer getCustomer() {
		return Customer.getInstance();
	}
}
