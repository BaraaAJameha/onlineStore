package com.baraa.producingwebservice.Controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.baraa.producingwebservice.Model.*;
import com.baraa.producingwebservice.ModelAssembler.*;
import com.baraa.producingwebservice.Repository.*;
import com.baraa.producingwebservice.NotFoundException.*;

@RestController
public class ProductLinesController {
	@Autowired
	private final ProductLinesRepository repository;
	private final ProductLinesModelAssembler assembler;

	ProductLinesController(ProductLinesRepository repository, ProductLinesModelAssembler assembler) {
		this.repository = repository;
		this.assembler = assembler;
	}

	@GetMapping("/productLines/{ProductLine}")
	public EntityModel<ProductLines> one(@PathVariable String ProductLine) {

		ProductLines productLines = repository.findById(ProductLine) //
				.orElseThrow(() -> new ProductLinesNotFoundException(ProductLine));
		
		

		return assembler.toModel(productLines);
	}

	@GetMapping("/productLines")
	public CollectionModel<EntityModel<ProductLines>> all() {

		List<EntityModel<ProductLines>> productLines = repository.findAll().stream() //
				.map(assembler::toModel) //
				.collect(Collectors.toList());

		return CollectionModel.of(productLines, linkTo(methodOn(ProductLinesController.class).all()).withSelfRel());
	}

	@PostMapping("/productLines")
	ResponseEntity<?> newProductLines(@Valid@RequestBody ProductLines newProductLines) {

		EntityModel<?> entityModel = assembler.toModel(repository.save(newProductLines));

		return ResponseEntity //
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
				.body(entityModel);
	}

	@PutMapping("/productLines/{ProductLine}")
	ResponseEntity<?> replaceProductLines(@RequestBody ProductLines newProductLines, @PathVariable String ProductLine) {

		ProductLines updatedProductLines = repository.findById(ProductLine) //
				.map(productLines -> {
					productLines.setProductLine(newProductLines.getProductLine());
					productLines.setTextDescription(newProductLines.getTextDescription());
					productLines.setHtmlDescription(newProductLines.getHtmlDescription());
					productLines.setImage(newProductLines.getImage());

					return repository.save(productLines);

				}) //
				.orElseGet(() -> {
					newProductLines.setProductLine(ProductLine);
					return repository.save(newProductLines);
				});

		EntityModel<?> entityModel = assembler.toModel(updatedProductLines);

		return ResponseEntity //
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
				.body(entityModel);
	}

	@DeleteMapping("/productLines/{ProductLine}")
	ResponseEntity<?> deleteProductLines(@PathVariable String ProductLine) {

		repository.deleteById(ProductLine);

		return ResponseEntity.noContent().build();
	}

}
