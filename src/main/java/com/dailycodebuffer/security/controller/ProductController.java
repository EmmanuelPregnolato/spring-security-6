package com.dailycodebuffer.security.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product")
@Slf4j
public class ProductController {

    private record Product(Integer productId, String productName, double price) {}

    List<Product> products = new ArrayList<Product>(
            List.of(
                    new Product(1, "iPhone", 999.999),
                    new Product(2, "Mac Pro", 2099.99)
            )
    );

    @GetMapping
    public List<Product> getProducts() {
        return products;
    }

    @PostMapping
    public Product saveProduct(@RequestBody Product product) {
        products.add(product);
        return product;
    }


}
