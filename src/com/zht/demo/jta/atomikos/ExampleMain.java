package com.zht.demo.jta.atomikos;


import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zht.demo.jta.atomikos.service.impl.DemoEntityServiceImpl;

public class ExampleMain {

	public static void main(String[] args) {     
		  try{  
		  ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");       
		
		  DemoEntityServiceImpl demoEntityService = context.getBean(DemoEntityServiceImpl.class); 
		 
//		  demoEntityService.dynamicDataSourceTest();
		 
		  
		  demoEntityService.onedatasourceTest();
		  System.out.println("over");
		  }catch(Exception e){
			  e.printStackTrace();
			  System.exit(0);
		  }
	}
		

}
