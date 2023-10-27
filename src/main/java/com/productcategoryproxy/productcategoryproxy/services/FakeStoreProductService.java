package com.productcategoryproxy.productcategoryproxy.services;

import com.productcategoryproxy.productcategoryproxy.clients.fakestore.client.FakeStoreClient;
import com.productcategoryproxy.productcategoryproxy.clients.fakestore.dto.FakeStoreProductDto;
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

@Service("FakeStoreService")
public class FakeStoreProductService implements IProductService {

    private FakeStoreClient fakeStoreClient;

    FakeStoreProductService(FakeStoreClient fakeStoreClient)
    {
        this.fakeStoreClient = fakeStoreClient;
    }




    @Override
    public List<Product> getAllProduct()
    {
        List<FakeStoreProductDto> products = fakeStoreClient.getAllProduct();
        List<Product> productList = new ArrayList<>();
        for(FakeStoreProductDto productDto : products)
        {
            productList.add(getProduct(productDto));
        }
        return productList;
    }

    @Override
    public Product getSingleProduct(Long productId)
    {
        FakeStoreProductDto productDto = fakeStoreClient.getSingleProduct(productId);
        Product product = getProduct(productDto);
        return product;
    }

    @Override
    public Product addNewProduct(Product product) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setId(product.getId());
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setCategory(product.getCategory().getName());
        fakeStoreProductDto.setImage(product.getImageUrl());

        FakeStoreProductDto productDto = fakeStoreClient.addNewProduct(fakeStoreProductDto);
        return getProduct(productDto);
    }


    @Override
    public Product updateProduct(Long productId, Product product)
    {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setId(product.getId());
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setCategory(product.getCategory().getName());
        fakeStoreProductDto.setImage(product.getImageUrl());

        FakeStoreProductDto productDto = fakeStoreClient.updateProduct(productId,fakeStoreProductDto);
        return getProduct(productDto);
    }
    @Override
    public Product deleteProduct(Long productId)
    {
        FakeStoreProductDto productDto = fakeStoreClient.deleteProduct(productId);
        Product product = getProduct(productDto);
        product.setDeleted(true);
        return product;
    }

    private Product getProduct(FakeStoreProductDto productDto)
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
