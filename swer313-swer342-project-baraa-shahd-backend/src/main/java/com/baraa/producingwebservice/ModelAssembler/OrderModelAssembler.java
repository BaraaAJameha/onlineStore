package com.baraa.producingwebservice.ModelAssembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.baraa.producingwebservice.Controller.OrderController;
import com.baraa.producingwebservice.Model.Orders;

@Component
public class OrderModelAssembler implements RepresentationModelAssembler<Orders, EntityModel<Orders>> {

	@Override
	public EntityModel<Orders> toModel(Orders order) {

		return EntityModel.of(order, //
				linkTo(methodOn(OrderController.class).getOrderById(order.getOrderNumber())).withSelfRel(),
				linkTo(methodOn(OrderController.class).findAll()).withRel("orders"));
	}

}
