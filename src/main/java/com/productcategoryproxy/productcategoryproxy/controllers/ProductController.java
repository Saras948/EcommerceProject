package com.productcategoryproxy.productcategoryproxy.controllers;

import com.productcategoryproxy.productcategoryproxy.dtos.ProductDto;
import com.productcategoryproxy.productcategoryproxy.models.Categories;
import com.productcategoryproxy.productcategoryproxy.models.Product;
import com.productcategoryproxy.productcategoryproxy.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    IProductService productService;

    public ProductController(IProductService productService)
    {
        this.productService = productService;
    }
    @GetMapping("")
    public ResponseEntity<List<Product>> getAllProduct()
    {
        List<Product> products = this.productService.getAllProduct();
        return new ResponseEntity<>(products,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getSingleProduct(@PathVariable("id") Long productId)
    {
        try {
            Product product = this.productService.getSingleProduct(productId);
            return new ResponseEntity<>(product, HttpStatus.OK);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    public Product addNewProduct(@RequestBody ProductDto productDto)
    {
        return productService.addNewProduct(productDto);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long productId)
    {
        return null;
    }

    @PatchMapping("/{id}")
    public Product patchProduct(@PathVariable("id") Long productId,@RequestBody ProductDto productDto)
        {
            Product product = new Product();
            product.setId(productDto.getId());
            product.setCategory(new Categories());
            product.getCategory().setName(productDto.getCategory());
            product.setTitle(productDto.getTitle());
            product.setPrice(productDto.getPrice());
            product.setDescription(productDto.getDescription());
            return productService.updateProduct(productId, product);
        }

    @DeleteMapping("/{id}")
    public Product deleteProduct(@PathVariable("id") Long productId)
    {
        //Deleting Product
        return productService.deleteProduct(productId);
    }

}
