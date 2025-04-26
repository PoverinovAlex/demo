package com.demo.DTO;

import com.demo.model.ProductInfo;

public class ProductInfoDTO {

    private int id;
    private int quantity;

    public ProductInfoDTO(int id, int quantity) {
        this.id = id;
        this.quantity = quantity;
    }
    public ProductInfoDTO(ProductInfo productInfo){
        id = productInfo.getId();
        quantity = productInfo.getQuantity();
    }

    public int getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
