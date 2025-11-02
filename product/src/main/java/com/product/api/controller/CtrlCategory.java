package com.product.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.api.dto.DtoCategoryIn;
import com.product.api.entity.Categoria;
import com.product.api.service.SvcCategoria;
import com.product.commons.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/category")
@Tag(name = "Category", description = "Catálogo de categorias")
public class CtrlCategory {

    @Autowired
    SvcCategoria svc;

   
    
    @GetMapping
    @Operation(summary = "Consultar categorias", description = "Consulta todas las categorias registradas")
	public ResponseEntity<List<Categoria>> findAll(){
    	return ResponseEntity.ok(svc.findAll());
	}
    
    @GetMapping("/active")
    @Operation(summary = "Consultar categorias activas", description = "Consulta todas las categorias activas registradas")
    public ResponseEntity<List<Categoria>> findActive(){
        return ResponseEntity.ok(svc.findActive());
    }
	
    @PostMapping
    @Operation(summary = "Crear categorias", description = "Crear todas las categorias")
    public ResponseEntity<ApiResponse> create(@Valid @RequestBody DtoCategoryIn in) {
        return ResponseEntity.ok(svc.create(in));
    }
    
    
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar categorias", description = "Actualizar  las categorias ")
    public ResponseEntity<ApiResponse> update(@Valid @RequestBody DtoCategoryIn in, @PathVariable("id") int id){
        return ResponseEntity.ok(svc.update(in, id));
    }
    
    
    @PatchMapping("/{id}/enable")
    @Operation(summary = "Habilitar categorias", description = "Habilitar las categorias deshabilitadas")
    public ResponseEntity<ApiResponse> enable(@PathVariable int id) {
        return ResponseEntity.ok(svc.enable(id));
    }

    @PatchMapping("/{id}/disable")
    @Operation(summary = "Deshabilitar categorias", description = "Deshabilita las categorias habilitadas")
    public ResponseEntity<ApiResponse> disable(@PathVariable int id) {
        return ResponseEntity.ok(svc.disable(id));
    }
    
    
}

