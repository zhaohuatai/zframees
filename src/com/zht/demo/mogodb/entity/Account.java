package com.zht.demo.mogodb.entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Account extends BaseEntity {

 private String accountName;
 
 public String getAccountName() {
  return accountName;
 }

 public void setAccountName(String accountName) {
  this.accountName = accountName;
 }

}