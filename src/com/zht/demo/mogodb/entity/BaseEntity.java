package com.zht.demo.mogodb.entity;
import org.springframework.data.annotation.Id;

public abstract class BaseEntity {

 @Id
 protected String id;
 private Long version;

 public BaseEntity() {
  super();
 }

 public String getId() {
  return id;
 }

 public void setId(String id) {
  this.id = id;
 }

 public Long getVersion() {
  return version;
 }

 public void setVersion(Long version) {
  this.version = version;
 }

}