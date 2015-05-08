package com.zht.demo.mogodb;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zht.demo.mogodb.entity.Account;
import com.zht.demo.mogodb.entity.Address;
import com.zht.demo.mogodb.entity.Customer;
import com.zht.demo.mogodb.service.CustomerService;

public class ExampleMian {
	 
	 public static void main( String[] args ) {     
	  
	  ConfigurableApplicationContext context
	   = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");       
	  
	  CustomerService customerService = context.getBean(CustomerService.class);       
	  
	  // delete all Customer records
	  customerService.dropCustomerCollection();  
	  
	     Address address = new Address();
	     address.setNumber("81");
	     address.setStreet("Mongo Street");
	     address.setTown("City");
	     address.setPostcode("CT81 1DB");
	    
	     Account account = new Account();
	     account.setAccountName("Personal Account");
	     List<Account> accounts = new ArrayList<Account>();
	     accounts.add(account);
	     
	     Customer customer = new Customer();
	     customer.setAddress(address);
	     customer.setName("Mr Bank Customer");
	     customer.setAccounts(accounts);
	     
	     // insert a Customer record into the database
	  customerService.insertCustomer(customer);     
	      
	  // find all Customer records
	  List<Customer> allCustomers = customerService.findAllCustomers();     
	  for (Customer foundCustomer : allCustomers) {
	   System.out.println(foundCustomer.getId() + " " + foundCustomer.getName());   
	   System.out.println(foundCustomer.getAddress().getTown());   
	   System.out.println(foundCustomer.getAccounts().get(0).getAccountName());   
	  }
	  
	  // find by customer name, address number and account name
	  List<Customer> specficCustomers = customerService.findAllCustomers();     
	  for (Customer foundCustomer : specficCustomers) {
	   System.out.println(foundCustomer.getId() + " " + foundCustomer.getName());   
	   System.out.println(foundCustomer.getAddress().getTown());   
	   System.out.println(foundCustomer.getAccounts().get(0).getAccountName());   
	  }
	  
	 } 

}
