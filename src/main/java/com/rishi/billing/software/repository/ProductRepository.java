package com.rishi.billing.software.repository;

import com.rishi.billing.software.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository {
    private final List<Product> list = new ArrayList<>();

    public Product addProduct(Product product){
        list.add(product);
        return product;
    }

    public Product getProductById(int id){
        return list.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    public Product updatePrice_stock(int id ,double price, int stock){
        Product updateProduct = getProductById(id);
        if(updateProduct != null){
            updateProduct.setPrice(price);
            updateProduct.setStockQuantity(stock);
        }
        return updateProduct;
    }

    public List<Product> getAllProduct(){
        return list;
    }

    public void deleteProuctById(int id){
        list.removeIf(p -> p.getId() == id);
    }
}
