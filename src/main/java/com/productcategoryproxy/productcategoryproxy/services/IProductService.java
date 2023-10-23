package com.productcategoryproxy.productcategoryproxy.services;

import com.productcategoryproxy.productcategoryproxy.dtos.ProductDto;
import com.productcategoryproxy.productcategoryproxy.models.Product;

import java.util.List;

public interface IProductService {
    List<Product> getAllProduct();

    Product getSingleProduct(Long productId);

    Product addNewProduct(Product productDto);

    Product updateProduct(Long productId, Product product);

    Product deleteProduct(Long productId);
}
