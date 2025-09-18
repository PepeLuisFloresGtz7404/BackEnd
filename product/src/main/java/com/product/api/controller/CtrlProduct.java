package com.product.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.api.entity.Categoria;
import com.product.api.service.SvcCategoria;

@RestController
@RequestMapping("/category")
public class CtrlProduct {
	
	@Autowired
	SvcCategoria svc;
	
	@GetMapping
	public List<Categoria> getCategorias(){
		return svc.getCategorias();
	}
}
