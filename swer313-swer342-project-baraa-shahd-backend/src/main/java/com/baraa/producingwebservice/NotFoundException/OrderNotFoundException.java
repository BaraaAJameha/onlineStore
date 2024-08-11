package com.baraa.producingwebservice.NotFoundException;



public class OrderNotFoundException extends RuntimeException{
	 
	public OrderNotFoundException(Integer id) {
	    super("Order with OrderNumber " + id+ " does not exist.");
	  }
}
