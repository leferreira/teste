package br.com.lefer.compass.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.lefer.compass.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	@Query("SELECT p FROM Product p WHERE (:q is null or (p.name = :q and p.description = :q)) and (:minPrice is null or p.price >= :minPrice) and (:maxPrice is null or p.price <= :maxPrice)")
	List<Product> search(String q, BigDecimal minPrice, BigDecimal maxPrice);

}
