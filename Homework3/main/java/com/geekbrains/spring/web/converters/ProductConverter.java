package com.geekbrains.spring.web.converters;

import com.geekbrains.spring.web.dto.ProductDto;
import com.geekbrains.spring.web.entities.Product;
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
}
