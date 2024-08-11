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

import com.baraa.producingwebservice.Model.Customers;
import com.baraa.producingwebservice.ModelAssembler.CustomerModelAssembler;
import com.baraa.producingwebservice.NotFoundException.CustomerNotFoundException;
import com.baraa.producingwebservice.Repository.CustomerRepository;

@RestController
public class CustomerController {

	private final CustomerRepository repository;
	private final CustomerModelAssembler assembler;

	CustomerController(CustomerRepository repository, CustomerModelAssembler assembler) {
		this.repository = repository;
		this.assembler = assembler;
	}

	@GetMapping("/customers")
	public CollectionModel<EntityModel<Customers>> findAll() {

		List<EntityModel<Customers>> customers = repository.findAll().stream() //
				.map(assembler::toModel) //
				.collect(Collectors.toList());

		return CollectionModel.of(customers, linkTo(methodOn(CustomerController.class).findAll()).withSelfRel());
	}

	// end::get-aggregate-root[]

	@PostMapping("/customers")
	ResponseEntity<?> newCustomer(@Valid @RequestBody Customers newCustomer) {

		EntityModel<?> entityModel = assembler.toModel(repository.save(newCustomer));

		return ResponseEntity //
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
				.body(entityModel);
	}
	// Single item


	@GetMapping("/customers/{id}")
	public EntityModel<Customers> getCustomerById(@PathVariable int id) {

		Customers customer = repository.findById(id) //
				.orElseThrow(() -> new CustomerNotFoundException(id));

		return assembler.toModel(customer);
	}

	@PutMapping("/customers/{id}")
	ResponseEntity<?> replaceCustomer(@RequestBody Customers newCustomer, @PathVariable int id) {

		Customers updatedCustomer = repository.findById(id) //
				.map(customer -> {
					customer.setCustomerFirstName(newCustomer.getCustomerFirstName());
					customer.setCustomerLastName(newCustomer.getCustomerLastName());
					customer.setCustomerName(newCustomer.getCustomerName());
					customer.setAdressLine1(newCustomer.getAdressLine1());
					customer.setAdressLine2(newCustomer.getAdressLine2());
					customer.setCity(newCustomer.getCity());
					customer.setCountry(newCustomer.getCountry());
					customer.setCreditLimit(newCustomer.getCreditLimit());
					customer.setPhone(newCustomer.getPhone());
					customer.setPostalCode(newCustomer.getPostalCode());
					customer.setState(newCustomer.getState());
					/*customer.setSalesRepEmployeeNumber(newCustomer.getSalesRepEmployeeNumber())*/;

					return repository.save(customer);
				}) //
				.orElseGet(() -> {
					newCustomer.setCustomerNumber(id);
					return repository.save(newCustomer);
				});

		EntityModel<?> entityModel = assembler.toModel(updatedCustomer);

		return ResponseEntity //
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
				.body(entityModel);
	}

	@DeleteMapping("/customers/{id}")
	ResponseEntity<?> deleteCustomer(@PathVariable int id) {

		repository.deleteById(id);

		return ResponseEntity.noContent().build();
	}

}