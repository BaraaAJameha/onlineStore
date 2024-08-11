package com.baraa.producingwebservice.ModelAssembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.baraa.producingwebservice.Controller.ProductLinesController;
import com.baraa.producingwebservice.Model.ProductLines;

@Component
public class ProductLinesModelAssembler
		implements RepresentationModelAssembler<ProductLines, EntityModel<ProductLines>> {
	@Override
	public EntityModel<ProductLines> toModel(ProductLines productLines) {
		return EntityModel.of(productLines, //
				// linkTo(methodOn(ProductLinesController.class).one(ProductLines.getProductLine())).withSelfRel(),
				linkTo(methodOn(ProductLinesController.class).all()).withRel("productLines"));
	}

}
