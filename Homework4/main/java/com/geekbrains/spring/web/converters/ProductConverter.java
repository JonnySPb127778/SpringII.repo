package com.geekbrains.spring.web.converters;

import com.geekbrains.spring.web.dto.ProductDto;
import com.geekbrains.spring.web.entities.Product;
import com.geekbrains.spring.ws.products.ProductSoap;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter {
  /*  public Product dtoToEntity(ProductDto productDto) {
        return new Product(productDto.getId(), productDto.getCategory(), productDto.getTitle(), productDto.getUnit(), productDto.getPrice());
    }
*/
    public ProductDto entityToDto(Product product) {
        return new ProductDto(product.getId(), product.getCategory().getTitle(), product.getTitle(), product.getUnit().getTitle(), product.getPrice());
    }

    public ProductSoap entityToSoap(Product product) {
        ProductSoap productSoap = new ProductSoap();
        productSoap.setId(product.getId());
        productSoap.setCategoryTitle( product.getCategory().getTitle());
        productSoap.setTitle(product.getTitle());
        productSoap.setUnit(product.getUnit().getTitle());
        productSoap.setPrice(product.getPrice());
        return productSoap;
    }

}
