package com.scaler.productservice25july.services;

import com.scaler.productservice25july.dtos.fakestore.FakeStoreCreateProductRequestDto;
import com.scaler.productservice25july.dtos.fakestore.FakeStoreGetProductResponseDto;
import com.scaler.productservice25july.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service("fakeStoreProductService")
//@Primary
public class ProductServiceFakeStoreImpl implements ProductService{

    private RestTemplate restTemplate;

    public ProductServiceFakeStoreImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product createProduct(Product product) {
        FakeStoreCreateProductRequestDto request = new FakeStoreCreateProductRequestDto();
        request.setCategory(product.getCategoryName());
        request.setTitle(product.getTitle());
        request.setImage(product.getImageUrl());
        request.setDescription(product.getDescription());
        request.setPrice(product.getPrice());

        FakeStoreGetProductResponseDto response = restTemplate.postForObject(
                "https://fakestoreapi.com/products",
                request,
                FakeStoreGetProductResponseDto.class
        );

//        Product product1 = new Product();
//        product1.setId(response.getId());
//        product1.setTitle(response.getTitle());
//        product1.setDescription(response.getDescription());
//        product1.setImageUrl(response.getImage());
//        product1.setCategoryName(response.getCategory());
//        product1.setPrice(response.getPrice());

        return response.toProduct();
    }

    @Override
    public List<Product> getAllProducts() {
        FakeStoreGetProductResponseDto[] response = restTemplate.getForObject(
                "https://fakestoreapi.com/products",
                FakeStoreGetProductResponseDto[].class
        );

        List<FakeStoreGetProductResponseDto> responseDtoList = Stream.of(response).toList();

        List<Product> products = new ArrayList<>();
        for (FakeStoreGetProductResponseDto fakeStoreGetProductResponseDto : responseDtoList) {
            products.add(fakeStoreGetProductResponseDto.toProduct());


        }
        return products;
    }

    @Override
    public Product partialUpdateProduct(Long productId, Product product) {
        FakeStoreGetProductResponseDto productResponseDto = restTemplate.patchForObject(
                "https://fakestoreapi.com/products/"+productId,
                FakeStoreCreateProductRequestDto.fromProduct(product),
                FakeStoreGetProductResponseDto.class
        );
        return productResponseDto.toProduct();
    }

}
