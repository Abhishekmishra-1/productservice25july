package com.scaler.productservice25july.controllers;

import com.scaler.productservice25july.dtos.ErrorResponseDto;
import com.scaler.productservice25july.dtos.products.*;
import com.scaler.productservice25july.models.Product;
import com.scaler.productservice25july.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
    public GetAllProductsResponseDto getAllProducts() {
        List<Product> products = productService.getAllProducts();
        GetAllProductsResponseDto response = new GetAllProductsResponseDto();

        List<GetProductDto> getProductResponseDtos = new ArrayList<>();

        for (Product product : products) {
            getProductResponseDtos.add(GetProductDto.from(product));
        }

        response.setProducts(getProductResponseDtos);
        return response;
    }

    @GetMapping("/{id}")
    public String getSingleProduct(@PathVariable("id") Long id) {
        return "Here is your product: "+ id;
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable String id) {

    }

    @PatchMapping("/{id}")
    public PatchProductResponseDto updateProduct(@PathVariable("id") Long productId,
                                               @RequestBody CreateProductDto productDto) {
        Product product = productService.partialUpdateProduct(productId, productDto.toProduct());
        PatchProductResponseDto response = new PatchProductResponseDto();
        response.setProduct(GetProductDto.from(product));
        return response;
    }

    @PutMapping
    public void replaceProduct() {}



//    @RequestMapping(name = "ABHISHEK", value="/products/")
//    public String testing() {
//        return "Magic";
//    }
}
