package com.productcategoryproxy.productcategoryproxy.controllers;

import com.productcategoryproxy.productcategoryproxy.dtos.ProductDto;
import com.productcategoryproxy.productcategoryproxy.models.Product;
import com.productcategoryproxy.productcategoryproxy.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    IProductService productService;

    public ProductController(IProductService productService)
    {
        this.productService = productService;
    }
    @GetMapping("")
    public String getAllProduct()
    {
        return "Getting all the products";
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

    @PostMapping()
    public String addNewProduct(@RequestBody ProductDto productDto)
    {
        return "Adding new product " + productDto;
    }

    @PutMapping("/{id}")
    public String updateProduct(@PathVariable("id") Long productId)
    {
        return "Updating Product";
    }

    @PatchMapping("/{id}")
    public String patchProduct(@PathVariable("id") Long productId)
    {
        return "Patching Product";
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable("id") Long productId)
    {
        return "Deleting Product";
    }

}
