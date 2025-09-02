package com.product;

public class Categoria{

    int categoria_id;
    String categoria;
    String tag;
    int status;

    public Categoria(int categoria_id, String categoria, String tag, int status){
        this.categoria_id = categoria_id;
        this.categoria = categoria;
        this.tag = tag;
        this.status = status;
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
        this.status = status;
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
        return status;
    }

    public String toString(){
        return "- Categoría ID: " + categoria_id + "\n- Categoría: " + categoria + "\n- Tag: " + tag + "\n- Status: " + status;
    }
}
