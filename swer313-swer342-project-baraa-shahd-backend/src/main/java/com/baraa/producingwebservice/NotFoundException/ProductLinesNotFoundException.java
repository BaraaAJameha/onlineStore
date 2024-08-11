package com.baraa.producingwebservice.NotFoundException;


public class ProductLinesNotFoundException extends RuntimeException {
	public ProductLinesNotFoundException(String ProductLine) {
		super("Could not find Product Lines " + ProductLine);
	}
}
