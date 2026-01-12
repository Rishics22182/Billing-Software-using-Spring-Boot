package com.rishi.billing.software.service;

import com.rishi.billing.software.entity.Customer;
import com.rishi.billing.software.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer addCustomer(Customer customer){
        return customerRepository.addCustomer(customer);
    }

    public Customer updateCustomer(int id ,Customer customer){
        return customerRepository.updateCustomer(id,customer);
    }

    public List<Customer> getAllCustomer(){
        return customerRepository.getAllCustomer();
    }

    public Customer getCustomerById(int id){
        return customerRepository.getCustomerById(id);
    }
}

