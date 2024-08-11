package com.baraa.producingwebservice.Repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.baraa.producingwebservice.Model.Payments;

public interface PaymentRepository extends JpaRepository<Payments, Integer> {

}
