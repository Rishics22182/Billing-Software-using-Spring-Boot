package com.rishi.billing.software.repository;

import com.rishi.billing.software.entity.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.JdbcAccessor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepository {

    private final JdbcTemplate jdbcTemplate;

    public ProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Product> productRowMapper = (rs, rowNum) -> {
        Product p = new Product();
        p.setId(rs.getInt("id"));
        p.setName(rs.getString("name"));
        p.setPrice(rs.getDouble("price"));
        p.setGstPercentage(rs.getDouble("gst_percentage"));
        p.setStockQuantity(rs.getInt("stock_quantity"));
        return p;
    };

    // Create
    public Product addProduct(Product product) {
        String sql = "INSERT INTO product (name, price, gst_percentage, stock_quantity) VALUES (?,?,?,?)";
        jdbcTemplate.update(
                sql,
                product.getName(),
                product.getPrice(),
                product.getGstPercentage(),
                product.getStockQuantity()
        );
        return product;
    }

    // Read by id
    public Product getProductById(int id) {
        String sql = "SELECT id, name, price, gst_percentage, stock_quantity FROM product WHERE id = ?";
        return jdbcTemplate.query(sql, productRowMapper, id)
                .stream()
                .findFirst()
                .orElse(null);
    }

    // Update only price and stock
    public Product updatePrice_stock(int id, double price, int stock) {
        String sql = "UPDATE product SET price = ?, stock_quantity = ? WHERE id = ?";
        int rows = jdbcTemplate.update(sql, price, stock, id);
        if (rows == 0) {
            return null;
        }
        return getProductById(id);
    }

    // Read all
    public List<Product> getAllProduct() {
        String sql = "SELECT id, name, price, gst_percentage, stock_quantity FROM product";
        return jdbcTemplate.query(sql, productRowMapper);
    }

    // Delete
    public void deleteProuctById(int id) {
        String sql = "DELETE FROM product WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}