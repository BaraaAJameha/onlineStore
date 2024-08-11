package com.baraa.producingwebservice.NotFoundException;


public class PaymentNotFoundException extends RuntimeException {


	 public PaymentNotFoundException(int id) {
		    super("Payment with CustomerNumber " + id+ " does not exist.");
		  }
}
