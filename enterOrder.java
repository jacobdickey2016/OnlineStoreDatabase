package edu.depauw.csc480.model;

import java.sql.Timestamp;
//import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.Entity;
//import javax.persistence.Id;
import javax.persistence.ManyToOne;
//import javax.persistence.OneToMany;

/**
 * Model object for a row in the enterOrder table.
 * Accesses the underlying database through JPA.
 */
@Entity
public class enterOrder {
  
  @Basic
  private Timestamp timeOrdered;
    
  @Basic
  private Timestamp timeShipped;
  
  @ManyToOne
  private Cart cartNumber;
    
  @ManyToOne
  private discountCode discountCode;
  
  // No-arg constructor for JPA
  public enterOrder() {}
  
  public enterOrder(Cart cart_Number, discountCode discount_Code, Timestamp time_Ordered, Timestamp time_Shipped) {
      this.cartNumber = cart_Number;
      this.discountCode = discount_Code;
      this.timeOrdered = time_Ordered;
      this.timeShipped = time_Shipped;
  }
  
  @Override
  public String toString() {
    return cartNumber + ", " + discountCode + ", " + timeOrdered + " / " + timeShipped;
  }

  public Cart getcartNumber() {
    return cartNumber;
  }

  public discountCode getdiscount() {
    return discountCode;
  }

  public void setdiscount(discountCode discount_Code) {
    this.discountCode = discount_Code;
  }

  public Timestamp gettimeOrdered() {
    return timeOrdered;
  }
    
    public void settimeOrdered(Timestamp timeOrdered) {
        this.timeOrdered = timeOrdered;
    }
    
  public Timestamp gettimeShipped() {
    return timeShipped;
  }
    
    
    public void settimeShipped(Timestamp timeShipped) {
        this.timeShipped = timeShipped;
    }

}
