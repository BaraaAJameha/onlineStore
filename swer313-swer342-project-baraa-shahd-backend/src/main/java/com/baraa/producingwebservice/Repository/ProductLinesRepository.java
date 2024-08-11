package com.baraa.producingwebservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.baraa.producingwebservice.Model.ProductLines;

public interface ProductLinesRepository extends JpaRepository<ProductLines, String> {

}