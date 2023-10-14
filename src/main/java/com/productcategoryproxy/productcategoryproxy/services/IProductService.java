package com.productcategoryproxy.productcategoryproxy.services;

import com.productcategoryproxy.productcategoryproxy.models.Product;

public interface IProductService {
    String getAllProduct();

    Product getSingleProduct(Long productId);

    String updateProduct(Long productId);

    String patchProduct(Long productId);

    String deleteProduct(Long productId);
}
