package com.product;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
public class CtrlProduct {
	
	@GetMapping
	public List<Categoria> getCategorias(){
		List<Categoria> categorias = new ArrayList<Categoria>();
		categorias.add(new Categoria(1,"Comida","1111",1));
		categorias.add(new Categoria(2,"Ropa","1112",1));
		categorias.add(new Categoria(3,"Cosmeticos","1113",0));
		
		return categorias;
	}
}
