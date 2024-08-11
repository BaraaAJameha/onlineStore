package com.baraa.producingwebservice.ModelAssembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.baraa.producingwebservice.Controller.PaymentController;
import com.baraa.producingwebservice.Model.Payments;

@Component
public class PaymentModelAssembler implements RepresentationModelAssembler<Payments, EntityModel<Payments>> {
	@Override
	public EntityModel<Payments> toModel(Payments payment) {

		return EntityModel.of(payment, //
				linkTo(methodOn(PaymentController.class).getPaymentById(payment.getCustomerNumber())).withSelfRel(),
				linkTo(methodOn(PaymentController.class).findAll()).withRel("payments"));
	}

}