package com.geekbrains.spring.web.endpoints;

import com.geekbrains.spring.web.converters.ProductConverter;
import com.geekbrains.spring.web.entities.Product;
import com.geekbrains.spring.web.exceptions.ResourceNotFoundException;
import com.geekbrains.spring.web.services.ProductsService;


import com.geekbrains.spring.ws.products.GetAllProductsRequest;
import com.geekbrains.spring.ws.products.GetAllProductsResponse;
import com.geekbrains.spring.ws.products.GetProductByTitleRequest;
import com.geekbrains.spring.ws.products.GetProductByTitleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;

@Endpoint
@RequiredArgsConstructor
public class ProductEndpoint {
    private static final String NAMESPACE_URI = "http://www.geekbrains.com/spring/ws/products" ;
    private final ProductsService productsService;
    private final ProductConverter productConverter;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetProductByTitleRequest")
    @ResponsePayload
    public GetProductByTitleResponse getProductByTitleResponse(@RequestPayload GetProductByTitleRequest request) {
        GetProductByTitleResponse response = new GetProductByTitleResponse();
        response.setProductSoap(
                productConverter.entityToSoap (
                        productsService.findByTitle(request.getTitle()).orElseThrow (
                                () -> new ResourceNotFoundException(
                                        "Невозможно обновить продукта, не надйен в базе, е: " + request.getTitle()
                                )
                        )
                )
        );
        return response;
    }

    /*
        Пример запроса: POST http://localhost:8189/ws
        Header -> Content-Type: text/xml

        <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:f="http://www.geekbrains.com/spring/ws/productsSoap">
            <soapenv:Header/>
            <soapenv:Body>
                <f:getAllProductsRequest/>
            </soapenv:Body>
        </soapenv:Envelope>
     */

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetAllProductsRequest")
    @ResponsePayload
    public GetAllProductsResponse getAllProductsResponse(@RequestPayload GetAllProductsRequest request) {
        GetAllProductsResponse response = new GetAllProductsResponse();
        List<Product> products = productsService.getAll();

        for (int i = 0; i < products.size(); i++) {
            response.getProducts().add(productConverter.entityToSoap(products.get(i)));
        }

        return response;
    }
}
