package com.baraa.producingwebservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.baraa.producingwebservice.Model.Orders;


public interface OrderRepository extends JpaRepository<Orders, Integer> {

}
