package com.baraa.producingwebservice.NotFoundAdvice;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.baraa.producingwebservice.NotFoundException.OrderNotFoundException;

import org.springframework.http.HttpStatus;

@ControllerAdvice
public class OrderNotFoundAdvice {

	@ResponseBody
	  @ExceptionHandler(OrderNotFoundException.class)
	  @ResponseStatus(HttpStatus.NOT_FOUND)
	  String orderNotFoundHandler(OrderNotFoundException ex) {
	    return ex.getMessage();
	  }
}
