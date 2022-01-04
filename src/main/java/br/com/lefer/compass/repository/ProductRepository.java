package br.com.lefer.compass.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.lefer.compass.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

}