package com.product.api.service;

import java.util.List;

import com.product.api.dto.DtoCategoryIn;
import com.product.api.entity.Categoria;
import com.product.commons.ApiResponse;

public interface SvcCategoria {

     
     public List<Categoria> findAll();
     public List<Categoria> findActive();
     public ApiResponse create(DtoCategoryIn in);
     public ApiResponse update(DtoCategoryIn in, int id);
     public ApiResponse enable(int id);
     public ApiResponse disable(int id);
     
}