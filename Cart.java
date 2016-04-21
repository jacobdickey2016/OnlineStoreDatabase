package edu.depauw.csc480.model;

import java.sql.Timestamp;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * Model object for a row in the Cart table.
 * Accesses the underlying database through JPA.
 */

@Entity
public class Cart {
	
//  @Id 
//  @GeneratedValue(strategy=GenerationType.AUTO)
//  private long id;
	 
//  @Basic
//  private long id;

  @Basic
  private int customer;

  @Basic
  private int item;

  @Basic
  private int quantity;

  @Basic
  private Timestamp timeAdded;
  
  @OneToMany(mappedBy="cartNumber")
  private Collection<enterOrder> enterOrder;
  
  // No-arg constructor for JPA
  public Cart() {}

  public Cart(int customer, int item, int quantity, Timestamp timestamp) {
    this.customer = customer;
	this.item = item;
    this.quantity = quantity;
	this.timeAdded = timestamp;
  }


  @Override
  public String toString() {
    return customer + ": " + item + " " + quantity + ", " + timeAdded;
  }
  
    public int getcustomer() {
    return customer;
  }

  public int getitem() {
    return item;
  }

  public void setitem(int item) {
    this.item = item;
  }

    public int getquantity() {
    return quantity;
  }

  public void setquantity(int quantity) {
    this.quantity = quantity;
  }

  public Timestamp gettimeAdded() {
    return timeAdded;
  }

  public Collection<enterOrder> getenterOrders() {
    return enterOrder;
  }

//  public Collection<discountCode> getdiscountCodes() {
//    return discountCode;
//  }
}
