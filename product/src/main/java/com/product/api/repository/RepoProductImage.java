package com.product.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.product.api.entity.ProductImage;

@Repository

public interface RepoProductImage extends JpaRepository<ProductImage, Integer> {
	
	@Query(value ="SELECT * FROM product_image WHERE product_id = :product_id;", nativeQuery = true)
    ProductImage findByProduct_id(@Param("product_id") Integer product_id);
	
	@Query(value="SELECT * FROM product_image WHERE product_id = :product_id",nativeQuery=true) // Consulta personalizada opcional
    List<ProductImage> findAllProducts(Integer product_id);
	
	
}
