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
import com.baraa.producingwebservice.NotFoundException.OrderNotFoundException;
import com.baraa.producingwebservice.Repository.*;

@RestController
public class OrderDetailsController {
	@Autowired
	private final OrderDetailsRepository repository;
	private final OrderDetailsModelAssembler assembler;

	OrderDetailsController(OrderDetailsRepository repository, OrderDetailsModelAssembler assembler) {
		this.repository = repository;
		this.assembler = assembler;
	}

	@GetMapping("/orderDetails/{orderNumber}")
	public EntityModel<OrderDetails> one(@PathVariable int orderNumber) {

		OrderDetails orderDetails = repository.findById(orderNumber) //
				.orElseThrow(() -> new OrderNotFoundException(orderNumber));

		return assembler.toModel(orderDetails);
	}

	@GetMapping("/orderDetails")
	public CollectionModel<EntityModel<OrderDetails>> all() {

		List<EntityModel<OrderDetails>> orderDetails = repository.findAll().stream() //
				.map(assembler::toModel) //
				.collect(Collectors.toList());
		return CollectionModel.of(orderDetails, linkTo(methodOn(ProductLinesController.class).all()).withSelfRel());

	}

	@PostMapping("/orderDetails")
	ResponseEntity<?> newCustomer(@Valid@RequestBody OrderDetails newOrderDetails) {

		EntityModel<?> entityModel = assembler.toModel(repository.save(newOrderDetails));

		return ResponseEntity //
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
				.body(entityModel);
	}

	@PutMapping("/orderDetails/{orderNumber}")
	ResponseEntity<?> replaceOrderDetails(@RequestBody OrderDetails newOrderDetails, @PathVariable int orderNumber) {

		OrderDetails updatedOrderDetails = repository.findById(orderNumber) //
				.map(orderDetails -> {

					orderDetails.setOrderNumber(newOrderDetails.getOrderNumber());
					orderDetails.setProductCode(newOrderDetails.getProductCode());
					orderDetails.setQuantityOrdered(newOrderDetails.getQuantityOrdered());
					orderDetails.setPriceEach(newOrderDetails.getPriceEach());
					orderDetails.setOrderLineNumber(newOrderDetails.getOrderLineNumber());

					return repository.save(orderDetails);

				}) //
				.orElseGet(() -> {
					newOrderDetails.setOrderNumber(orderNumber);
					return repository.save(newOrderDetails);
				});

		EntityModel<?> entityModel = assembler.toModel(updatedOrderDetails);

		return ResponseEntity //
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
				.body(entityModel);
	}

	@DeleteMapping("/orderDetails/{orderNumber}")
	ResponseEntity<?> deleteOrderDetails(@PathVariable int orderNumber) {

		repository.deleteById(orderNumber);

		return ResponseEntity.noContent().build();
	}

}