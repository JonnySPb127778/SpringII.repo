package com.geekbrains.spring.web.services;

import com.geekbrains.spring.web.dto.ProductDto;
import com.geekbrains.spring.web.entities.Category;
import com.geekbrains.spring.web.entities.Product;
import com.geekbrains.spring.web.exceptions.ResourceNotFoundException;
import com.geekbrains.spring.web.repositories.CategoriesRepository;
import com.geekbrains.spring.web.repositories.ProductsRepository;
import com.geekbrains.spring.web.repositories.specifications.ProductsSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductsService {
    private final ProductsRepository productsRepository;
    private final CategoriesRepository categoriesRepository;

    public Page<Product> findAll(String partCategory, String partTitle, Integer minPrice, Integer maxPrice, Integer page) {
        Specification<Product> spec = Specification.where(null);
        if (minPrice != null) {
            spec = spec.and(ProductsSpecifications.priceGreaterOrEqualsThan(minPrice));
        }
        if (maxPrice != null) {
            spec = spec.and(ProductsSpecifications.priceLessThanOrEqualsThan(maxPrice));
        }
        if (partTitle != null) {
            spec = spec.and(ProductsSpecifications.titleLike(partTitle));
        }
        // TODO После затирания критерия поика на фронте и запуске поиска, возвращается не null, а пустая строка
        if (partCategory != null && !partCategory.equals("")) {
            Category category = findCategoryByTitle(partCategory);
            // TODO По хорошему надо было бы передавать на фронт список Категорий и делать "выпадаечку",
            //      но я ограничился защитой от "дурака" при вводе данных
            if(category != null) spec = spec.and(ProductsSpecifications.categoryEqual(category));
        }

        return productsRepository.findAll(spec, PageRequest.of(page - 1, 8));
    }

    public Optional<Product> findById(Long id) {
        return productsRepository.findById(id);
    }

    @Transactional // TODO Не знаю нужен ли он здесь? Если нет, то и этот метод можно убрать
    public Category findCategoryByTitle(String categoryTitle) {
        return categoriesRepository.getByTitle(categoryTitle);
    }

    public void deleteById(Long id) {
        productsRepository.deleteById(id);
    }

    public Product save(Product product) {
        return productsRepository.save(product);
    }

    @Transactional
    public Product update(ProductDto productDto) {
        Product product = productsRepository.findById(
                productDto.getId()).orElseThrow(() -> new ResourceNotFoundException("Невозможно обновить продукта, не надйен в базе, id: " + productDto.getId()));
        product.setPrice(productDto.getPrice());
        product.setTitle(productDto.getTitle());
        return product;
    }
}
