package com.product.api.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.product.api.entity.Categoria;
import com.product.api.repository.RepoCategoria;

@Service
public class SvcCategoriaImp implements SvcCategoria{

    @Autowired
    RepoCategoria repo;


    @Override
	public ResponseEntity<List<Categoria>> getCategorias() {
		return new ResponseEntity<>(repo.getCategorias(), HttpStatus.OK);
	}


}