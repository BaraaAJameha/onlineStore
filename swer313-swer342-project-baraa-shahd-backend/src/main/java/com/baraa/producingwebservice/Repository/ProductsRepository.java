package com.baraa.producingwebservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.baraa.producingwebservice.Model.Products;

public interface ProductsRepository extends JpaRepository<Products, String> {

}
