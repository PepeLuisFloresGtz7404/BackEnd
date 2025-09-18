package com.product.api.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.api.entity.Categoria;
import com.product.api.repository.RepoCategoria;

@Service
public class SvcCategoriaImp implements SvcCategoria{

    @Autowired
    RepoCategoria repo;

    @Override
    public List<Categoria> getCategorias() {
        // TODO Auto-generated method stub
        return repo.getCategorias();
    }



}