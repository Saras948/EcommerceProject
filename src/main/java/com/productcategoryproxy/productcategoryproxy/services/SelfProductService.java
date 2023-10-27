package com.productcategoryproxy.productcategoryproxy.services;

import com.productcategoryproxy.productcategoryproxy.models.Product;
import com.productcategoryproxy.productcategoryproxy.repositories.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("SelfProductService")
public class SelfProductService implements IProductService{

    private ProductRepository productRepository;

    private Logger logger = LoggerFactory.getLogger(SelfProductService.class);

    public SelfProductService(ProductRepository productRepository)
    {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProduct() {
        logger.info("Class Name :: SelfProductService ::: Method Name -> getAllProduct :: Start  ");
        List<Product> products = productRepository.findAll();
        logger.info("Class Name :: SelfProductService ::: Method Name -> getAllProduct :: End : "+products.toString());
        return products;
    }

    @Override
    public Product getSingleProduct(Long productId) {
        logger.info("Class Name :: SelfProductService ::: Method Name -> getSingleProduct :: Start : "+productId);
        Optional<Product> product = productRepository.findById(productId);
    logger.info("Class Name :: SelfProductService ::: Method Name -> getSingleProduct :: Product : "+product.get().toString());
        Product product1 = null;
        if(product!=null)
        {
            logger.info("Class Name :: SelfProductService ::: Method Name -> getSingleProduct :: Product Found : "+product.toString());
            product1 = product.get();
        }
        else
        {
            logger.info("Class Name :: SelfProductService ::: Method Name -> getSingleProduct :: Product Not Found : "+productId);
        }
        logger.info("Class Name :: SelfProductService ::: Method Name -> getSingleProduct :: End : "+product);
        return product1;
    }

    @Override
    public Product addNewProduct(Product product) {
        logger.info("Class Name :: SelfProductService ::: Method Name -> addNewProduct :: Start : "+product);
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long productId, Product product) {
        logger.info("Class Name :: SelfProductService ::: Method Name -> updateProduct :: Start : ProductId : "+productId+" :: Product : "+product);
        Product existingProduct = productRepository.findById(productId).orElse(null);
        return existingProduct;
    }

    @Override
    public Product deleteProduct(Long productId) {
        logger.info("Class Name :: SelfProductService ::: Method Name -> deleteProduct :: Start : "+productId);
        Product product = productRepository.findById(productId).orElse(null);
        if(product!=null)
        {
            product.setDeleted(true);
            productRepository.save(product);
        }
        return product;
    }
}
