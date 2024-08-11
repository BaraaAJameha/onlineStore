package com.baraa.producingwebservice.NotFoundException;



public class CustomerNotFoundException extends RuntimeException{


	  public CustomerNotFoundException(Integer id) {
	    super("Customer with CustomerNumber " + id+ " does not exist.");
	  }
}
