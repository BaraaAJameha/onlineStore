package com.baraa.producingwebservice.Repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.baraa.producingwebservice.Model.Customers;



public interface CustomerRepository extends JpaRepository<Customers, Integer> {

}
