package edu.depauw.csc480;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;

//import edu.depauw.csc480.model.Cart;
import edu.depauw.csc480.model.discountCode;
import edu.depauw.csc480.model.enterOrder;

/**
 * Simple client that retrieves data from an already created database. Running
 * this after Test will check that the same data may be retrieved from the
 * database and not just from the in-memory cache.
 */
public class Test2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("collegedb");
		EntityManager em = emf.createEntityManager();

		EntityTransaction tx = em.getTransaction();
		tx.begin();

		String query = "select * from discountCode d where d.code = 'SPRG20' ";
		TypedQuery<discountCode> q = em.createQuery(query, discountCode.class);
		discountCode off20 = q.getSingleResult();
		
		// Retrieves any orders that use the 20% discount code "SPRG20"
		Collection<enterOrder> eorders = off20.getenterOrders();
		for (enterOrder eo : eorders) {
			System.out.println(eo);
		}

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
