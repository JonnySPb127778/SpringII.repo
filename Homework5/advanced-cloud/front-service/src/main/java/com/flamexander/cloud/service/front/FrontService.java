package com.flamexander.cloud.service.front;

import com.flamexander.cloud.common.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.flamexander.cloud.service.front.FrontConfig;
import org.springframework.web.client.RestOperations;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FrontService {


    @Autowired
    public  RestOperations restTemplate;

    public  List<ProductDto> getAllProducts() {
        return restTemplate.getForObject("http://product-service/api/v1/products", List.class);
    }
}
