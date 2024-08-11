package com.baraa.producingwebservice.Controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.baraa.producingwebservice.Model.Orders;
import com.baraa.producingwebservice.ModelAssembler.OrderModelAssembler;
import com.baraa.producingwebservice.NotFoundException.OrderNotFoundException;
import com.baraa.producingwebservice.Repository.OrderRepository;

@RestController
public class OrderController {
	private final OrderRepository repository;
	private final OrderModelAssembler assembler;

	OrderController(OrderRepository repository, OrderModelAssembler assembler) {
		this.repository = repository;
		this.assembler = assembler;
	}

	@GetMapping("/orders")
	public CollectionModel<EntityModel<Orders>> findAll() {

		List<EntityModel<Orders>> orders = repository.findAll().stream() //
				.map(assembler::toModel) //
				.collect(Collectors.toList());

		return CollectionModel.of(orders, linkTo(methodOn(OrderController.class).findAll()).withSelfRel());
	}

	// end::get-aggregate-root[]

	@PostMapping("/orders")
	ResponseEntity<?> newOrders(@Valid @RequestBody Orders newOrders) {

		@Valid Orders  orders = repository.save(newOrders);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{orderNumber}")
		.buildAndExpand(orders.getOrderNumber()).toUri();
		return ResponseEntity.created(location).build();
	}
	// Single item

	@GetMapping("/orders/{id}")
	public EntityModel<Orders> getOrderById(@PathVariable int id) {

		Orders order = repository.findById(id) //
				.orElseThrow(() -> new OrderNotFoundException(id));

		return assembler.toModel(order);
	}

	@PutMapping("/orders/{id}")
	ResponseEntity<?> replaceOrder(@RequestBody Orders newOrder, @PathVariable int id) {

		Orders updatedOrder = repository.findById(id) //
				.map(order -> {
					order.setComments(newOrder.getComments());
					order.setOrderDate(newOrder.getOrderDate());
					order.setRequiredDate(newOrder.getRequiredDate());
					order.setStatus(newOrder.getStatus());
					order.setCustomerNumber(newOrder.getCustomerNumber());

					return repository.save(order);
				}) //
				.orElseGet(() -> {
					newOrder.setOrderNumber(id);
					return repository.save(newOrder);
				});

		EntityModel<Orders> entityModel = assembler.toModel(updatedOrder);

		return ResponseEntity //
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
				.body(entityModel);
	}

	@DeleteMapping("/orders/{id}")
	ResponseEntity<?> deleteOrder(@PathVariable int id) {

		repository.deleteById(id);

		return ResponseEntity.noContent().build();
	}

}
