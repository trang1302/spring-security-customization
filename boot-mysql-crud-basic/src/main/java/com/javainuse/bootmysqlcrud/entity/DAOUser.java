package com.javainuse.bootmysqlcrud.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(schema ="landportal",name = "member")
public class DAOUser {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;


  private String userid;

  private String email;

 
  private String pw;





  public DAOUser() {
  }

  public DAOUser(String username, String email, String pw) {
    this.userid = username;
    this.email = email;
    this.pw = pw;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUserid() {
    return userid;
  }

  public void setUserid(String userid) {
    this.userid = userid;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPw() {
    return pw;
  }

  public void setPw(String pw) {
    this.pw = pw;
  }

 
}