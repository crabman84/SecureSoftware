/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex.jpa;

/**
 *
 * @author elau
 */
public class CustomerApp {
    
    public static void main(String [] args) {
        Customer customer1 = new Customer("00001", "Edmonds Lau", "1234567890", 100);
        Customer customer2 = new Customer("00002", "Edmonds Lau", "2345678901", 90);

        boolean result;
        
        CustomerEM custEM = new CustomerEM();

        // add customer 1 - not in the database
        System.out.println("Add customer 1 - not in the database");
        result = custEM.addCustomer(customer1);
        showAddResult(customer1, result);
        
        System.out.println("Add customer1 again - already in the database");
        result = custEM.addCustomer(customer1);
        showAddResult(customer1, result);
         
        System.out.println("Delete customer1 - already in the database");
        result = custEM.delCustomer(customer1.getCustid());
        showDelResult(customer1, result);  
        
        System.out.println("Delete customer1 again - now not in the database");
        result = custEM.delCustomer(customer1.getCustid());
        showDelResult(customer1, result);
        
        System.out.println("Delete customer2 - not in the database");
        result = custEM.delCustomer(customer2.getCustid());
        showDelResult(customer2, result);        
    }
    
    public static void showAddResult(Customer customer, boolean result) {
        if (result) {
            System.out.println("Customer whose id is " + customer.getCustid() + " has been added to the database.");
        } else {
            System.out.println("Customer whose id is " + customer.getCustid() + " cannot be added to the database!");
        }
    }
    
    public static void showDelResult(Customer customer, boolean result) {
        if (result) {
            System.out.println("Customer whose id is " + customer.getCustid() + " has been deleted from the database.");
        } else {
            System.out.println("Customer whose id is " + customer.getCustid() + " cannot be deleted from the database!");
        }
    }
    
}
