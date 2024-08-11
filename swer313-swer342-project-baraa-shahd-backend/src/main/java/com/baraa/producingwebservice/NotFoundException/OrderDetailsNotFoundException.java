package com.baraa.producingwebservice.NotFoundException;



public class OrderDetailsNotFoundException extends RuntimeException {

	public OrderDetailsNotFoundException(Integer orderNumber) {
		super("Could not find Order Details " + orderNumber);
	}

}