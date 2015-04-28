package com.zht.common.task;

import org.springframework.stereotype.Component;

@Component(value="taskTest")
public class TaskTest {
	
	 public TaskTest() {
		  System.out.println("构造函数");  
	}

     public void myTest(){  
           System.out.println("进入测试......");  
     }  
}
