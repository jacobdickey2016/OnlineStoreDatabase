package edu.depauw.csc480;

import java.sql.Timestamp;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;

import edu.depauw.csc480.model.Cart;
import edu.depauw.csc480.model.enterOrder;
import edu.depauw.csc480.model.discountCode;

/**
 * Simple client that inserts sample data then runs a query.
 */
public class Test {

    /**
     * @param args
     */
    @SuppressWarnings("deprecation")
	public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("collegedb");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        // Clear the tables
        em.createQuery("DELETE from discountCode").executeUpdate();
        em.createQuery("DELETE from enterOrder").executeUpdate();
        em.createQuery("DELETE from Cart").executeUpdate();
        
        try {
            tx.commit();
        } catch (RollbackException ex) {
            ex.printStackTrace();
            tx.rollback();
        }

        tx = em.getTransaction();
        tx.begin();

        //create items in the table "Cart" (item, customer, quantity, timeAdded)
        Cart cart_1_1 = new Cart( 23, 001435, 1, new Timestamp(2016, 04, 15, 06, 26, 00, 0));
        Cart cart_1_2 = new Cart(332, 001435, 1, new Timestamp(2016, 04, 15, 06, 27, 00, 0));
        Cart cart_1_3 = new Cart( 91, 001435, 1, new Timestamp(2016, 04, 15, 06, 29, 00, 0));
        Cart cart_1_4 = new Cart(676, 001435, 1, new Timestamp(2016, 04, 15, 06, 31, 00, 0));
        
        Cart cart_2_1 = new Cart( 11, 001436, 1, new Timestamp(2016, 04, 15, 06, 26, 00, 0));
        Cart cart_2_2 = new Cart(834, 001436, 1, new Timestamp(2016, 04, 15, 06, 27, 00, 0));
        Cart cart_2_3 = new Cart(534, 001436, 2, new Timestamp(2016, 04, 15, 06, 27, 00, 0));
        
        Cart cart_3_1 = new Cart(343, 000734, 1, new Timestamp(2016, 04, 15, 06, 26, 00, 0));
        Cart cart_3_2 = new Cart(134, 000734, 1, new Timestamp(2016, 04, 15, 06, 27, 00, 0));
        Cart cart_3_3 = new Cart(155, 000734, 2, new Timestamp(2016, 04, 15, 06, 27, 00, 0));
        Cart cart_3_4 = new Cart(453, 000734, 1, new Timestamp(2016, 04, 15, 06, 29, 00, 0));
        Cart cart_3_5 = new Cart(244, 000734, 2, new Timestamp(2016, 04, 15, 06, 31, 00, 0));
        
        Cart cart_4_1 = new Cart(273, 001437, 1, new Timestamp(2016, 04, 15, 06, 26, 00, 0));
        Cart cart_4_2 = new Cart(734, 001437, 1, new Timestamp(2016, 04, 15, 06, 27, 00, 0));
        Cart cart_4_3 = new Cart(694, 001437, 1, new Timestamp(2016, 04, 15, 06, 27, 00, 0));
        Cart cart_4_4 = new Cart(432, 001437, 1, new Timestamp(2016, 04, 15, 06, 29, 00, 0));
        Cart cart_4_5 = new Cart(246, 001437, 1, new Timestamp(2016, 04, 15, 06, 26, 00, 0));
        Cart cart_4_6 = new Cart(733, 001437, 1, new Timestamp(2016, 04, 15, 06, 27, 00, 0));
        
        em.persist(cart_1_1); em.persist(cart_1_2); em.persist(cart_1_3); 
        em.persist(cart_1_4);
        
        em.persist(cart_2_1); em.persist(cart_2_2); em.persist(cart_2_3);
        
        em.persist(cart_3_1); em.persist(cart_3_2); em.persist(cart_3_3); 
        em.persist(cart_3_4); em.persist(cart_3_5);
        
        em.persist(cart_4_1); em.persist(cart_4_2); em.persist(cart_4_3); 
        em.persist(cart_4_4); em.persist(cart_4_5); em.persist(cart_4_6);

        //create discount codes in the table "discountCode" (code, percentOff)
        discountCode spring20 = new discountCode("SPRG20", 20);
        discountCode springbreak30 = new discountCode("30SB16", 30);
        discountCode summer20 = new discountCode("SMMR20", 20);
        discountCode payday40 = new discountCode("PDAY40", 40);

        em.persist(spring20);
        em.persist(springbreak30);
        em.persist(summer20);
        em.persist(payday40);

        // Have to set enterOrder heads after creating discountCode,
        // because discountCode need to refer to enterOrders (cycle in foreign keys)
        // (cartNumber, discount, timeOrdered, timeShipped)
        
        //cart 1
        em.persist(new enterOrder(cart_1_1, null,     			//Cart, discountCode
        		new Timestamp(2016, 04, 10, 04, 26, 00, 0),  	//timeOrdered
        		new Timestamp(2016, 04, 16, 16, 32, 00, 0)));	//timeShipped
        em.persist(new enterOrder(cart_1_2, null,     			
        		new Timestamp(2016, 04, 10, 04, 26, 00, 0),  	
        		new Timestamp(2016, 04, 16, 16, 32, 00, 0)));	
        em.persist(new enterOrder(cart_1_3, null,     			
        		new Timestamp(2016, 04, 10, 04, 26, 00, 0),  	
        		new Timestamp(2016, 04, 16, 16, 32, 00, 0)));	
        em.persist(new enterOrder(cart_1_4, null,     			
        		new Timestamp(2016, 04, 10, 04, 26, 00, 0),  	
        		new Timestamp(2016, 04, 16, 16, 32, 00, 0)));
        
        //cart 2
        em.persist(new enterOrder(cart_2_1, spring20,     		//Cart, discountCode
        		new Timestamp(2016, 04, 11, 13, 17, 00, 0),  	//timeOrdered
        		new Timestamp(2016, 04, 16, 16, 32, 00, 0)));	//timeShipped
        em.persist(new enterOrder(cart_2_2, spring20,     			
        		new Timestamp(2016, 04, 11, 13, 17, 00, 0),  	
        		new Timestamp(2016, 04, 16, 16, 32, 00, 0)));	
        em.persist(new enterOrder(cart_2_3, spring20,     			
        		new Timestamp(2016, 04, 11, 13, 17, 00, 0),  	
        		new Timestamp(2016, 04, 16, 16, 32, 00, 0)));	
        
      //cart 3
        em.persist(new enterOrder(cart_3_1, null,     			//Cart, discountCode
        		new Timestamp(2016, 04, 11, 14, 20, 00, 0),  	//timeOrdered
        		new Timestamp(2016, 04, 16, 16, 32, 00, 0)));	//timeShipped
        em.persist(new enterOrder(cart_3_2, null,     			
        		new Timestamp(2016, 04, 11, 14, 20, 00, 0),  	
        		new Timestamp(2016, 04, 16, 16, 32, 00, 0)));	
        em.persist(new enterOrder(cart_3_3, null,     			
        		new Timestamp(2016, 04, 11, 14, 20, 00, 0),  	
        		new Timestamp(2016, 04, 16, 16, 32, 00, 0)));	
        em.persist(new enterOrder(cart_3_4, null,     			
        		new Timestamp(2016, 04, 11, 14, 20, 00, 0),  	
        		new Timestamp(2016, 04, 16, 16, 32, 00, 0)));
        em.persist(new enterOrder(cart_3_5, null,     			
        		new Timestamp(2016, 04, 11, 14, 20, 00, 0),  	
        		new Timestamp(2016, 04, 16, 16, 32, 00, 0)));
        
      //cart 4
        em.persist(new enterOrder(cart_4_1, payday40,     		//Cart, discountCode
        		new Timestamp(2016, 04, 12, 11, 00, 00, 0),  	//timeOrdered
        		new Timestamp(2016, 04, 16, 16, 32, 00, 0)));	//timeShipped
        em.persist(new enterOrder(cart_4_2, payday40,     			
        		new Timestamp(2016, 04, 12, 11, 00, 00, 0),  	
        		new Timestamp(2016, 04, 16, 16, 32, 00, 0)));	
        em.persist(new enterOrder(cart_4_3, payday40,     			
        		new Timestamp(2016, 04, 12, 11, 00, 00, 0),  	
        		new Timestamp(2016, 04, 16, 16, 32, 00, 0)));	
        em.persist(new enterOrder(cart_4_4, payday40,     			
        		new Timestamp(2016, 04, 12, 11, 00, 00, 0),  	
        		new Timestamp(2016, 04, 16, 16, 32, 00, 0)));
        em.persist(new enterOrder(cart_4_5, payday40,     			
        		new Timestamp(2016, 04, 12, 11, 00, 00, 0),  	
        		new Timestamp(2016, 04, 16, 16, 32, 00, 0)));
        em.persist(new enterOrder(cart_4_6, payday40,     			
        		new Timestamp(2016, 04, 12, 11, 00, 00, 0),  	
        		new Timestamp(2016, 04, 16, 16, 32, 00, 0)));
        
        try {
            tx.commit();
        } catch (RollbackException ex) {
            ex.printStackTrace();
            tx.rollback();
        }

        //tx.commit();
        
        tx = em.getTransaction();
        tx.begin();
        
        //Enter queries here
        //////////////////////////////////////////////////////////
        Collection<enterOrder> code = spring20.getenterOrders();
        for (enterOrder eo : code) {
            System.out.println(eo);
        }
        //////////////////////////////////////////////////////////
        
        try {
            tx.commit();
        } catch (RollbackException ex) {
            ex.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }

        System.out.println("All Done!");
    }
}