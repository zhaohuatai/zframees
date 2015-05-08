package com.zht.demo.mogodb.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.zht.demo.mogodb.entity.Customer;


@Repository
public interface CustomerRepository extends MongoRepository<Customer,String> {
 
 List<Customer> findByNameAndAddressNumberAndAccountsAccountName(
   String name, String number, String accountName);
 
}