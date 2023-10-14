package com.productcategoryproxy.productcategoryproxy.services;

import com.productcategoryproxy.productcategoryproxy.dtos.ProductDto;
import com.productcategoryproxy.productcategoryproxy.models.Categories;
import com.productcategoryproxy.productcategoryproxy.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductService implements IProductService {

    RestTemplateBuilder restTemplateBuilder;

    ProductService(RestTemplateBuilder restTemplateBuilder)
    {
        this.restTemplateBuilder = restTemplateBuilder;
    }
    @Override
    public String getAllProduct()
    {

        return null;
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
    private Product getProduct(ProductDto productDto)
    {
        Product product = new Product();
        product.setTitile(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setImageUrl(productDto.getImage());
        Categories categories = new Categories();
        categories.setName(productDto.getCategory());
        product.setCategory(categories);
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

}
