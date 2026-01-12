package com.rishi.billing.software.service;

import com.rishi.billing.software.entity.Product;
import com.rishi.billing.software.exception.ResourceNotFoundException;
import com.rishi.billing.software.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product addProduct(Product product){
        return productRepository.addProduct(product);
    }

    public Product getProductById(int id){
        return productRepository.getProductById(id);
    }

    public Product updatePrice_stock(int id ,double price, int stock){
        return productRepository.updatePrice_stock(id,price,stock);
    }

    public List<Product> getAllProduct(){
        return productRepository.getAllProduct();
    }

    public void deleteProuctById(int id){
        productRepository.deleteProuctById(id);
    }

}
