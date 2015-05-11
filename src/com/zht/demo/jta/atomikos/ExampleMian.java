package com.zht.demo.jta.atomikos;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zht.demo.jta.atomikos.dao.impl.DemoEntityDaoImpl;
import com.zht.demo.jta.atomikos.service.impl.DemoEntityServiceImpl;
import com.zht.demo.mogodb.entity.Account;
import com.zht.demo.mogodb.entity.Address;
import com.zht.demo.mogodb.entity.Customer;
import com.zht.demo.mogodb.service.CustomerService;

public class ExampleMian {

	public static void main(String[] args) {     
		  
		  ConfigurableApplicationContext context
		   = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");       
		  try{
			  
		 
		  DemoEntityServiceImpl demoEntityService = context.getBean(DemoEntityServiceImpl.class); 
		  demoEntityService.queryAndSaveDeleteTest();
		  System.out.println("over");
		  }catch(Exception e){
			  e.printStackTrace();
			  System.exit(0);
		  }
	}
		

}
