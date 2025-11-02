package com.product.api.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.product.api.dto.DtoProductImageIn;
import com.product.api.dto.DtoProductImageOut;
import com.product.api.entity.ProductImage;
import com.product.commons.ApiResponse;

@Service
public interface SvcProductImage {
	
	public ResponseEntity<DtoProductImageOut> getProductImage(Integer id);
    public ResponseEntity<ApiResponse> uploadProductImage(DtoProductImageIn in);
    public ResponseEntity<ApiResponse> deleteProductImage(Integer id);
    public ResponseEntity<ApiResponse> enableProductImage(Integer id);
}
