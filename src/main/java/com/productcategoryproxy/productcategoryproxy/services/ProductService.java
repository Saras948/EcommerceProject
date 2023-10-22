package com.productcategoryproxy.productcategoryproxy.services;

import com.productcategoryproxy.productcategoryproxy.dtos.ProductDto;
import com.productcategoryproxy.productcategoryproxy.models.Categories;
import com.productcategoryproxy.productcategoryproxy.models.Product;
import jakarta.annotation.Nullable;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
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

    private <T> ResponseEntity<T> requestForEntity(HttpMethod httpMethod, String url, @Nullable Object request,
                                                   Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.requestFactory(
                HttpComponentsClientHttpRequestFactory.class
        ).build();
        RequestCallback requestCallback =restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
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
    public Product addNewProduct(ProductDto productDto) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        restTemplate.postForEntity("https://fakestoreapi.com/products",productDto,ProductDto.class);
        return getProduct(productDto);
    }


    @Override
    public Product updateProduct(Long productId, Product product)
    {
        ProductDto productDto = new ProductDto();
        productDto.setDescription(product.getDescription());
        productDto.setCategory(product.getCategory().getName());
        productDto.setImage(product.getImageUrl());
        productDto.setPrice(product.getPrice());
        productDto.setTitle(product.getTitle());

        ResponseEntity<ProductDto> productDtoResponseEntity = requestForEntity(
                HttpMethod.PATCH,
                "https://fakestoreapi.com/products/{id}",
                productDto,
                ProductDto.class,
                productId);
        return getProduct(productDtoResponseEntity.getBody());
    }
    @Override
    public Product deleteProduct(Long productId)
    {
        RestTemplate restTemplate = restTemplateBuilder.build();
        restTemplate.delete("https://fakestoreapi.com/products/{id}",productId);
        ResponseEntity<ProductDto> productDto = restTemplate.getForEntity("https://fakestoreapi.com/products/{id}",
                ProductDto.class,productId);
        Product product = getProduct(productDto.getBody());
        product.setDeleted(true);
        return product;
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
