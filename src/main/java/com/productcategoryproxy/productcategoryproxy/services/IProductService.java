package com.productcategoryproxy.productcategoryproxy.services;

import com.productcategoryproxy.productcategoryproxy.models.Product;

import java.util.List;

public interface IProductService {
    List<Product> getAllProduct();

    Product getSingleProduct(Long productId);

    String updateProduct(Long productId);

    String patchProduct(Long productId);

    String deleteProduct(Long productId);
}
