package com.rishi.billing.software.repository;

import com.rishi.billing.software.entity.Customer;
import com.rishi.billing.software.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerRepository {
    private final List<Customer> clist = new ArrayList<>();

    public Customer addCustomer(Customer customer){
        clist.add(customer);
        return customer;
    }

    public Customer updateCustomer(int id ,Customer customer){
        Customer updateCustomer = getCustomerById(id);
        if(updateCustomer != null){
            updateCustomer.setName(customer.getName());
            updateCustomer.setPhone(customer.getPhone());
            updateCustomer.setEmail(customer.getEmail());
            updateCustomer.setAddress(customer.getAddress());
        }
        return updateCustomer;
    }

    public List<Customer> getAllCustomer(){
        return clist;
    }

    public Customer getCustomerById(int id){
        return clist.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
    }
}
