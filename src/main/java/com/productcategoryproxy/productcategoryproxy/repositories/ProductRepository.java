package com.productcategoryproxy.productcategoryproxy.repositories;

import com.productcategoryproxy.productcategoryproxy.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {

    Product save(Product product);
}
