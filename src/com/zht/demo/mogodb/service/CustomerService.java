package com.zht.demo.mogodb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zht.demo.mogodb.entity.Customer;
import com.zht.demo.mogodb.repository.CustomerRepository;


@Service
public class CustomerService {  

 @Autowired
 CustomerRepository customerRepository;
  
 public void insertCustomer(Customer customer) {    
  customerRepository.save(customer);
 }
 
 public List<Customer> findAllCustomers() {           
  return customerRepository.findAll();
 }       
 
 public void dropCustomerCollection() {        
  customerRepository.deleteAll();   
 }
 
}