package com.product.api.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "category")
public class Categoria{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("category_id")
	@Column(name = "category_id")
	private int category_id;
	
	@JsonProperty("category")
	@Column(name = "category")
	private String category;
	
	@JsonProperty("tag")
	@Column(name = "tag")
	private String tag;
	
	@JsonProperty("status")
	@Column(name = "status")
	private int status;
	
    
    public Categoria(){}

    public Categoria(int category_id, String category, String tag, int status){
        this.category_id = category_id;
        this.category = category;
        this.tag = tag;
        this.status = status;
    }

    public void setCategoria_id(int category_id){
        this.category_id = category_id;
    }

    public void setCategoria(String category){
        this.category = category;
    }

    public void setTag(String tag){
        this.tag = tag;
    }

    public void setStatus(int status){
        this.status = status;
    }

    public int getCategoria_id(){
        return category_id;
    }

    public String getCategoria(){
        return category;
    }

    public String getTag(){
        return tag;
    }

    public int getStatus(){
        return status;
    }

    public String toString(){
        return "- Category ID: " + category_id + "\n- Category: " + category + "\n- Tag: " + tag + "\n- Status: " + status;
    }
}