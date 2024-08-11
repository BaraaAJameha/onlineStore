package com.baraa.producingwebservice.Repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.baraa.producingwebservice.Model.OrderDetails;


public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Integer> {

}

