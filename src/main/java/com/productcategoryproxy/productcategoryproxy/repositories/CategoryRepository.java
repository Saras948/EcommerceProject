package com.productcategoryproxy.productcategoryproxy.repositories;

import com.productcategoryproxy.productcategoryproxy.models.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Categories,Long> {
}
