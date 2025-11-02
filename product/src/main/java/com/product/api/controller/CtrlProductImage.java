package com.product.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.api.dto.DtoProductImageIn;
import com.product.api.dto.DtoProductImageOut;
import com.product.api.service.SvcProductImage;
import com.product.commons.ApiResponse;
import com.product.exception.ApiException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/product-image")
@Tag(name = "Product Image", description = "Catálogo de las imagenes de los productos")
public class CtrlProductImage {

    @Autowired
    SvcProductImage svc;

    
    @PostMapping("/{id}/image")
    @Operation(summary = "Creamos imagenes", description = "Creamos las imagenes de los productos")
    public ResponseEntity<ApiResponse> createProductImage(@Valid @RequestBody DtoProductImageIn in, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            throw new ApiException(HttpStatus.BAD_REQUEST, bindingResult.getFieldError().getDefaultMessage());

        return svc.uploadProductImage(in);
    }
    
    @GetMapping("/{id}/image")
    @Operation(summary = "Consultamos las imagenes", description = "Consultamos todas las imagenes de los productos")
    public ResponseEntity<DtoProductImageOut> getProductImage(@PathVariable Integer id) {
        return svc.getProductImage(id);
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminamos imagenes", description = "Eliminamos  imagenes de los productos por ID")
    public ResponseEntity<ApiResponse> disableProduct(@PathVariable Integer id) {
        return svc.deleteProductImage(id);
    }
    
    @PatchMapping("/{id}")
    @Operation(summary = "Habilitar las imagenes", description = "Habilitamos las imagenes de los productos por ID")
    public ResponseEntity<ApiResponse> enableProduct(@PathVariable Integer id) {
        return svc.enableProductImage(id);
    }
}
