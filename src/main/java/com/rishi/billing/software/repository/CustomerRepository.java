package com.rishi.billing.software.repository;

import com.rishi.billing.software.entity.Customer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerRepository {

    private final JdbcTemplate jdbcTemplate;

    public CustomerRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Customer> customerRowMapper = (rs, rowNum) -> {
        Customer c = new Customer();
        c.setId(rs.getInt("id"));
        c.setName(rs.getString("name"));
        c.setPhone(rs.getInt("phone"));
        c.setEmail(rs.getString("email"));
        c.setAddress(rs.getString("address"));
        return c;
    };

    // Add Customer
    public Customer addCustomer(Customer customer){
        String sql = "INSERT INTO customer(id,name, phone, email, address) VALUES (?,?,?,?,?)";
        jdbcTemplate.update(
                sql,
                customer.getId(),
                customer.getName(),
                customer.getPhone(),
                customer.getEmail(),
                customer.getAddress()
        );
        return customer;
    }

    // Update Customer
    public Customer updateCustomer(int id, Customer customer){
        String sql = "UPDATE customer SET name=?, phone=?, email=?, address=? WHERE id=?";
        jdbcTemplate.update(
                sql,
                customer.getName(),
                customer.getPhone(),
                customer.getEmail(),
                customer.getAddress(),
                id
        );
        return getCustomerById(id);
    }

    // Get Customer by ID
    public Customer getCustomerById(int id){
        String sql = "SELECT id, name, phone, email, address FROM customer WHERE id = ?";
        return jdbcTemplate.query(sql, customerRowMapper, id)
                .stream()
                .findFirst()
                .orElse(null);
    }

    // Get All Customers
    public List<Customer> getAllCustomer(){
        String sql = "SELECT id, name, phone, email, address FROM customer";
        return jdbcTemplate.query(sql, customerRowMapper);
    }

}