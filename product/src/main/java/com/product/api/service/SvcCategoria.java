package com.product.api.service;

import com.product.api.entity.Categoria;
import java.util.List;

import org.springframework.http.ResponseEntity;

public interface SvcCategoria {

     public ResponseEntity<List<Categoria>> getCategorias();

}