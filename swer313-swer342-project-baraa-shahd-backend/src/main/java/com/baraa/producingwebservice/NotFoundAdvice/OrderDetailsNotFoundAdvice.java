package com.baraa.producingwebservice.NotFoundAdvice;



import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.baraa.producingwebservice.NotFoundException.OrderDetailsNotFoundException;

@ControllerAdvice

public class OrderDetailsNotFoundAdvice {
	@ResponseBody
	@ExceptionHandler(OrderDetailsNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String OrderDetailsNotFoundHandler(OrderDetailsNotFoundException ex) {
		return ex.getMessage();
	}
}