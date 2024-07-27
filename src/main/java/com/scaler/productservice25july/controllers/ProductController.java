package com.scaler.productservice25july.controllers;

import com.scaler.productservice25july.dtos.CreateProductRequestDto;
import com.scaler.productservice25july.dtos.CreateProductResponseDto;
import com.scaler.productservice25july.models.Product;
import com.scaler.productservice25july.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

//    @Value("${productService}")
//    private String productServiceType;

    //@Qualifier
    private ProductService productService;

    public ProductController(@Qualifier("fakeStoreProductService") ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("")
    public CreateProductResponseDto createProduct(@RequestBody CreateProductRequestDto createProductRequestDto) {

        Product product = productService.createProduct(
                createProductRequestDto.toProduct()
        );

        return CreateProductResponseDto.fromProduct(product);
        //return "This product is priced at: " + createProductRequestDto.getPrice();
    }

    @GetMapping("")
    public void getAllProducts() {

    }

    @GetMapping("/{id}")
    public String getSingleProduct(@PathVariable("id") Long id) {
        return "Here is your product: "+ id;
    }

    @DeleteMapping("/{id}")
    public void deleteProduct() {

    }

    public void updateProduct() {}

    public void replaceProduct() {}

//    @RequestMapping(name = "ABHISHEK", value="/products/")
//    public String testing() {
//        return "Magic";
//    }
}
