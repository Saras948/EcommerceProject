package com.productcategoryproxy.productcategoryproxy.services;

import com.productcategoryproxy.productcategoryproxy.dtos.ProductDto;
import com.productcategoryproxy.productcategoryproxy.models.Categories;
import com.productcategoryproxy.productcategoryproxy.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService implements IProductService {

    RestTemplateBuilder restTemplateBuilder;

    ProductService(RestTemplateBuilder restTemplateBuilder)
    {
        this.restTemplateBuilder = restTemplateBuilder;
    }
    @Override
    public List<Product> getAllProduct()
    {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<ProductDto[]> productDtos = restTemplate.getForEntity("https://fakestoreapi.com/products",
                ProductDto[].class);
        List<Product> products = new ArrayList<>();
        for(ProductDto productDto : productDtos.getBody())
        {
            products.add(getProduct(productDto));
        }
        return products;
    }

    @Override
    public Product getSingleProduct(Long productId)
    {

        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<ProductDto> productDto = restTemplate.getForEntity("https://fakestoreapi.com/products/{id}",
                ProductDto.class,productId);
        Product product = getProduct(productDto.getBody());
        return product;
    }


    @Override
    public String updateProduct(Long productId)
    {
        return null;
    }

    @Override
    public String patchProduct(Long productId)
    {
        return null;
    }
    @Override
    public String deleteProduct(Long productId)
    {
        return null;
    }

    private Product getProduct(ProductDto productDto)
    {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        Categories category = new Categories();
        category.setName(productDto.getCategory());
        product.setCategory(category);
        product.setImageUrl(productDto.getImage());
        return product;
    }

}
