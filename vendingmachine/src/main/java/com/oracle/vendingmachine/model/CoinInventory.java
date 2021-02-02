package com.oracle.vendingmachine.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

public class CoinInventory {
	
	
	@NotNull(message = "coin field required")	
	private Coin coin;
	
	
	@Min(value = 1, message = "Coin  quantity should be greater than zero")
	@NotNull(message = "quantity field required")	
	private Integer quantity;

	public Coin getCoin() {
		return coin;
	}

	public void setCoin(Coin coin) {
		this.coin = coin;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantityInteger) {
		this.quantity = quantityInteger;
	}

	@Override
	public String toString() {
		return "CoinInventory [coin=" + coin + ", quantity=" + quantity + "]";
	}

}
