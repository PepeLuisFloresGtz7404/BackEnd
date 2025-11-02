package com.product.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.product.api.dto.DtoProductOut;
import com.product.api.entity.Product;

@Repository
public interface RepoProduct extends JpaRepository<Product, Integer> {
	@Query(value = "SELECT p.product_id, p.name, p.surname, p.rfc, p.mail, p.phone_number, p. address, p.category"
            + "FROM product p "
            + "INNER JOIN category c ON c.category_id = p.category_id "
                + "WHERE c.customer_id = :product_id;", nativeQuery = true)
            DtoProductOut getProduct(Integer product_id);
}
