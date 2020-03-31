/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author elau
 */
public class CustomerEM {

    private EntityManager em;

    public CustomerEM() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EX-JPA-CustomerPU");
        em = emf.createEntityManager();
    }

    public EntityManager getEntityManager() {
        return em;
    }

    /**
     * add a customer to the database
     * @param customer the customer to be added
     * @return true if the customer has been added; false otherwise
     */
    public boolean addCustomer(Customer customer) {
        boolean result = false;

        Customer cust = em.find(Customer.class, customer.getCustid());

        if (cust != null) {
            // can find a customer with the same custid
            result = false;
        } else {
            // can now add the customer to the database
            try {
                em.getTransaction().begin();
                em.persist(customer);
                em.getTransaction().commit();

                result = true;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        
        return result;
    }
    
    /**
     * remove a customer having the custid
     * @param custid the cust id of the customer to be removed
     * @return true if removed; false otherwise
     */
    public boolean delCustomer(String custid) {
        boolean result = false;
        
        Customer cust = em.find(Customer.class, custid);
        
        if (cust == null) {
            // cannot find a customer - cannot delete
            result = false;
        } else {
            // can now remove the customer found
            try {
                em.getTransaction().begin();
                em.remove(cust);
                em.getTransaction().commit();
                
                result = true;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        
        return result;
    }
}
