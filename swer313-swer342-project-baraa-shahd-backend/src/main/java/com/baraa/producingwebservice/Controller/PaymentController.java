package com.baraa.producingwebservice.Controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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


import com.baraa.producingwebservice.Model.*;
import com.baraa.producingwebservice.ModelAssembler.*;
import com.baraa.producingwebservice.Repository.*;
import com.baraa.producingwebservice.NotFoundException.*;

@RestController
public class PaymentController {

	private final PaymentRepository repository;
	private final PaymentModelAssembler assembler;

	PaymentController(PaymentRepository repository, PaymentModelAssembler assembler) {
		this.repository = repository;
		this.assembler = assembler;
	}

	@GetMapping("/payments")
	public CollectionModel<EntityModel<Payments>> findAll() {

		List<EntityModel<Payments>> payments = repository.findAll().stream() //
				.map(assembler::toModel) //
				.collect(Collectors.toList());

		return CollectionModel.of(payments, linkTo(methodOn(PaymentController.class).findAll()).withSelfRel());
	}

	// end::get-aggregate-root[]

	@PostMapping("/payments")
	ResponseEntity<?> newPayment(@Valid @RequestBody Payments newPayment) {

		EntityModel<?> entityModel = assembler.toModel(repository.save(newPayment));

		return ResponseEntity //
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
				.body(entityModel);
	}
	// Single item

	@GetMapping("/payments/{id}")
	public EntityModel<Payments> getPaymentById(@PathVariable int id) {

		Payments payment = repository.findById(id) //
				.orElseThrow(() -> new PaymentNotFoundException(id));

		return assembler.toModel(payment);
	}

	@PutMapping("/payments/{id}")
	ResponseEntity<?> replacePayment(@RequestBody Payments newPayment, @PathVariable int id) {

		Payments updatedPayment = repository.findById(id) //
				.map(payment -> {
					payment.setAmount(newPayment.getAmount());
					payment.setCheckNumber(newPayment.getCheckNumber());
					payment.setPaymentDate(newPayment.getPaymentDate());

					return repository.save(payment);
				}) //
				.orElseGet(() -> {
					newPayment.setCustomerNumber(id);
					return repository.save(newPayment);
				});

		EntityModel<Payments> entityModel = assembler.toModel(updatedPayment);

		return ResponseEntity //
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
				.body(entityModel);
	}

	@DeleteMapping("/payments/{id}")
	ResponseEntity<?> deletePayment(@PathVariable int id) {

		repository.deleteById(id);

		return ResponseEntity.noContent().build();
	}

}
