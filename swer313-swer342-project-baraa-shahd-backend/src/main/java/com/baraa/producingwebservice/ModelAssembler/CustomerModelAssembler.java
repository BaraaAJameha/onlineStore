package com.baraa.producingwebservice.ModelAssembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.baraa.producingwebservice.Controller.CustomerController;
import com.baraa.producingwebservice.Model.Customers;

@Component
public class CustomerModelAssembler implements RepresentationModelAssembler<Customers, EntityModel<Customers>> {

	@Override
	public EntityModel<Customers> toModel(Customers customer) {

		return EntityModel.of(customer, //
				linkTo(methodOn(CustomerController.class).getCustomerById(customer.getCustomerNumber())).withSelfRel(),
				linkTo(methodOn(CustomerController.class).findAll()).withRel("customers"));
	}

}
