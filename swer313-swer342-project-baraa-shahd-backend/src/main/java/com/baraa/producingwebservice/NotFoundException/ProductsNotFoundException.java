package com.baraa.producingwebservice.NotFoundException;



public class ProductsNotFoundException extends RuntimeException {

	public ProductsNotFoundException(String productCode) {
		super("Could not find Product " + productCode);
	}

}
