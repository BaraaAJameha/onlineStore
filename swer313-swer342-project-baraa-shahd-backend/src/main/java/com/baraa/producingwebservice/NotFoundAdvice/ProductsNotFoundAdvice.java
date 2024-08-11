package com.baraa.producingwebservice.NotFoundAdvice;



import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.baraa.producingwebservice.NotFoundException.ProductsNotFoundException;


@ControllerAdvice

public class ProductsNotFoundAdvice {
	@ResponseBody
	@ExceptionHandler(ProductsNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String ProductsNotFoundHandler(ProductsNotFoundException ex) {
		return ex.getMessage();
	}
}
