package edu.depauw.csc480.model;

import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.Entity;
//import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * Model object for a row in the discountCode table.
 * Accesses the underlying database through JPA.
 */

@Entity
public class discountCode {
  
  @Basic
  private String code;
  
  @Basic
  private int percentOff;

  @OneToMany(mappedBy="discountCode")
  private Collection<enterOrder> enterOrder;
  
  // No-arg constructor for JPA
  public discountCode() {}
  
  public discountCode(String code, int percentOff) {
    this.code = code;
    this.percentOff = percentOff;
  }

  @Override
  public String toString() {
    return code + " : " + percentOff ;
  }

  public String getcode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public int getpercentOff() {
    return percentOff;
  }

  public void setpercentOff(int percentOff) {
    this.percentOff = percentOff;
  }
    
    public Collection<enterOrder> getenterOrders() {
        return enterOrder;
    }
    
//    public Collection<Cart> getCarts() {
//        return Cart;
//    }

}
