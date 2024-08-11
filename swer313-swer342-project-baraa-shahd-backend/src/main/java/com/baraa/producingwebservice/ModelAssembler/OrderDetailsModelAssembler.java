package com.baraa.producingwebservice.ModelAssembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.baraa.producingwebservice.Controller.OrderDetailsController;
import com.baraa.producingwebservice.Model.OrderDetails;

@Component
public class OrderDetailsModelAssembler
		implements RepresentationModelAssembler<OrderDetails, EntityModel<OrderDetails>> {

	@Override
	public EntityModel<OrderDetails> toModel(OrderDetails orderDetails) {

		return EntityModel.of(orderDetails, //
				linkTo(methodOn(OrderDetailsController.class).one(orderDetails.getOrderNumber())).withSelfRel(),
				linkTo(methodOn(OrderDetailsController.class).all()).withRel("orderDetails"));
	}
}
