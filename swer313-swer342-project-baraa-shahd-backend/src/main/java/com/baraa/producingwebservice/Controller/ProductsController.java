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

import com.baraa.producingwebservice.Model.Products;
import com.baraa.producingwebservice.ModelAssembler.ProdctsModelAssembler;
import com.baraa.producingwebservice.NotFoundException.ProductsNotFoundException;
import com.baraa.producingwebservice.Repository.ProductsRepository;

@RestController
public class ProductsController {
	@Autowired
	private final ProductsRepository repository;
	private final ProdctsModelAssembler assembler;

	ProductsController(ProductsRepository repository, ProdctsModelAssembler assembler) {

		this.repository = repository;
		this.assembler = assembler;
	}

	@GetMapping("/products/{id}")
	EntityModel<Products> one(@PathVariable String productCode) {

		Products products = repository.findById(productCode) //
				.orElseThrow(() -> new ProductsNotFoundException(productCode));
		return assembler.toModel(products);
	}

	@GetMapping("/products")
	public CollectionModel<EntityModel<Products>> all() {

		List<EntityModel<Products>> products = repository.findAll().stream() //
				.map(assembler::toModel) //
				.collect(Collectors.toList());

		return CollectionModel.of(products, linkTo(methodOn(ProductsController.class).all()).withSelfRel());
	}

	@PostMapping("/products")
	ResponseEntity<?> newProductLines(@Valid@RequestBody Products newProducts) {

		EntityModel<?> entityModel = assembler.toModel(repository.save(newProducts));

		return ResponseEntity //
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
				.body(entityModel);
	}

	@PutMapping("/products/{productCode}")
	ResponseEntity<?> replaceProducts(@RequestBody Products newProducts, @PathVariable String productCode) {

		Products updatedProducts = repository.findById(productCode) //

				.map(products -> {

					products.setProductCode(newProducts.getProductCode());
					products.setProductName(newProducts.getProductName());
					products.setProductLine(newProducts.getProductLine());
					products.setProductScale(newProducts.getProductScale());
					products.setProductVendor(newProducts.getProductVendor());
					products.setProductDescription(newProducts.getProductDescription());
					products.setBuyPrice(newProducts.getBuyPrice());
					products.setQuantityInStock(newProducts.getQuantityInStock());
					products.setMSRP(newProducts.getMSRP());
					return repository.save(products);

				}).orElseGet(() -> {
					newProducts.setProductCode(productCode);
					return repository.save(newProducts);
				});

		EntityModel<?> entityModel = assembler.toModel(updatedProducts);

		return ResponseEntity //
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
				.body(entityModel);
	}

	@DeleteMapping("/products/{productCode}")
	ResponseEntity<?> deleteOrderDetails(@PathVariable String productCode) {

		repository.deleteById(productCode);

		return ResponseEntity.noContent().build();
	}

}
