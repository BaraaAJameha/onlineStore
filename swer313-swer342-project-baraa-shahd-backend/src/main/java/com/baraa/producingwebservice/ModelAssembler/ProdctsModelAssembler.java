package com.baraa.producingwebservice.ModelAssembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.baraa.producingwebservice.Controller.ProductsController;
import com.baraa.producingwebservice.Model.Products;

@Component
public class ProdctsModelAssembler implements RepresentationModelAssembler<Products, EntityModel<Products>> {

	@Override
	public EntityModel<Products> toModel(Products prodcts) {
		return EntityModel.of(prodcts, //
				// linkTo(methodOn(ProductsController.class).one(Products.getProductCode())).withSelfRel(),
				linkTo(methodOn(ProductsController.class).all()).withRel("Prodcts"));
	}

}