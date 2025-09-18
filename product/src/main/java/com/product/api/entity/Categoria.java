package com.product.api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "categoria")
public class Categoria{

    @Id
    int categoria_id;
    String categoria;
    String tag;
    int estatus;
    
    public Categoria(){}

    public Categoria(int categoria_id, String categoria, String tag, int estatus){
        this.categoria_id = categoria_id;
        this.categoria = categoria;
        this.tag = tag;
        this.estatus = estatus;
    }

    public void setCategoria_id(int categoria_id){
        this.categoria_id = categoria_id;
    }

    public void setCategoria(String categoria){
        this.categoria = categoria;
    }

    public void setTag(String tag){
        this.tag = tag;
    }

    public void setStatus(int status){
        this.estatus = status;
    }

    public int getCategoria_id(){
        return categoria_id;
    }

    public String getCategoria(){
        return categoria;
    }

    public String getTag(){
        return tag;
    }

    public int getStatus(){
        return estatus;
    }

    public String toString(){
        return "- Categoría ID: " + categoria_id + "\n- Categoría: " + categoria + "\n- Tag: " + tag + "\n- Status: " + estatus;
    }
}