package com.flamexander.cloud.service.front;

import com.flamexander.cloud.common.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/v1/front")
@RequiredArgsConstructor
@CrossOrigin("*")   //("http://localhost:3000") // Homework: *
public class FrontController {
    private final FrontService frontService;

    @GetMapping("/products")
    public List<ProductDto> getAllProducts() {
        return frontService.getAllProducts();
    }

}
