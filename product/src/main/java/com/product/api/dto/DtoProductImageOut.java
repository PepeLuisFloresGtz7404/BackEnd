package com.product.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DtoProductImageOut {
    
    @JsonProperty("product_image_id")
    private Integer productImageId;
    
    @JsonProperty("product_id")
    private Integer productId;
    
    @JsonProperty("image")
    private String image;
    
    @JsonProperty("status")
    private Integer status;

    // Constructor vacío
    public DtoProductImageOut() {
    }

    // Constructor con parámetros
    public DtoProductImageOut(Integer productImageId, Integer productId, String image, Integer status) {
        this.productImageId = productImageId;
        this.productId = productId;
        this.image = image;
        this.status = status;
    }

    // Getters y Setters
    public Integer getProductImageId() {
        return productImageId;
    }

    public void setProductImageId(Integer productImageId) {
        this.productImageId = productImageId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}