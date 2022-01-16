package com.geekbrains.spring.web.dto;

import com.geekbrains.spring.web.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDto {
    private Long productId;
    private String productCategory;
    private String productTitle;
    private String productUnit;
    private int quantity;
    private int pricePerProduct;
    private int price;

    public OrderItemDto(Product product) {
        this.productId = product.getId();
        this.productCategory = product.getCategory().getTitle();
        this.productTitle = product.getTitle();
        this.productUnit = product.getUnit().getTitle();
        this.quantity = 1;
        this.pricePerProduct = product.getPrice();
        this.price = product.getPrice();
    }

    public void changeQuantity(int delta) {
        this.quantity += delta;
        this.price = this.quantity * this.pricePerProduct;
    }
}
