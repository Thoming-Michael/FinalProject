/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate2;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author mthoming
 */
public class Customer {

    /**
     * @param args the command line arguments
     */
    public static void mainOld(String[] args) {
        //Search for a single record by ID
        Session s = connection.Controller.getSessionFactory().openSession();
        
        System.out.println("Searching for a single record in the table: ");
        pojos.Salesperson sp = (pojos.Salesperson)s.load(pojos.Salesperson.class, "byb07");
        
        System.out.println("\n" + sp.getSalesPersonKey() + ", " + sp.getFirstName() 
                + ", " + sp.getLastName() + ", " + sp.getHireDate() + ", " + sp.getSalary() + "\n");
        
        //Get all records 
        System.out.println("\n Searching for all records in the table: \n");
        String HQL = "from Salesperson";
        Query q = s.createQuery(HQL);
        
        List<pojos.Salesperson> list = q.list();
        
        for(pojos.Salesperson spn : list){
            System.out.println(spn.getSalesPersonKey() + ", " + spn.getFirstName() 
                + ", " + spn.getLastName() + ", " + spn.getHireDate() + ", " + spn.getSalary());
        }
        
        //Get unique result using HQL
        System.out.println("\n Searching for 1 Salesperson with the last name 'Salazar': \n");
        HQL = "from Salesperson where lastName = 'Salazar'";
        q = s.createQuery(HQL);
        
        sp = (pojos.Salesperson)q.uniqueResult();
        System.out.println(sp.getSalesPersonKey() + ", " + sp.getFirstName() 
                + ", " + sp.getLastName() + ", " + sp.getHireDate() + ", " + sp.getSalary());

        
        //use greater-than and less-than
        System.out.println("\n Searching for all cars between 2000 and 2002 (non-inclusive): \n");
        Criteria c = s.createCriteria(pojos.Automobile.class);
        c.add(Restrictions.gt("year", 2000));
        c.add(Restrictions.lt("year", 2002));
        
        List<pojos.Automobile> li = c.list();
        
        for(pojos.Automobile am : li) {
            System.out.println(am.getVin() + "," + am.getYear() + ", " 
                    + am.getMake() + ", " + am.getModel() + ", " + am.getBody() 
                    + ", " + am.getColor());
        }
        
        //using "Between" operator
        System.out.println("\n Searching for all cars between 2000 and 2002 (inclusive): \n");
        c = s.createCriteria(pojos.Automobile.class);
        c.add(Restrictions.between("year", 2000, 2002));
        
        li = c.list();
        for (pojos.Automobile am : li) {
            System.out.println(am.getVin() + "," + am.getYear() + ", " 
                    + am.getMake() + ", " + am.getModel() + ", " + am.getBody() 
                    + ", " + am.getColor());
        }
        s.close();
        
        //Updating a record in the database
        Session s2 = connection.Controller.getSessionFactory().openSession();
        System.out.println("\n Updating a customer record...");
        s2 = connection.Controller.getSessionFactory().openSession();
        Transaction tr = s2.beginTransaction();
        
        pojos.Customer cust = (pojos.Customer)s2.load(pojos.Customer.class, "clint019");
        cust.setFirstName("Bob");
        cust.setPhone("202-589-1965");  //202-589-1965, 101-555-1212
        
        //Send the update command to the database session
        s2.update(cust);
        //commit the change in the transaction
        tr.commit();
        
        System.out.println("\n Update Successful... \n");
        
        System.out.println(cust.getCustomerKey() + ", " + cust.getFirstName() 
                + ", " + cust.getLastName() + ", " + cust.getPhone() + ", " + cust.getStreet1() 
                + ", " + cust.getStreet2() + ", " + cust.getCity() + ", " + cust.getState()
                + ", " + cust.getZipcode());
        
        //cust = (pojos.Customer)s.load(pojos.Customer.class, "00000001");
        
        s2.close();
        tr = null;
        
        //Deleting a record in the database
        Session s4 = connection.Controller.getSessionFactory().openSession();
        s4 = connection.Controller.getSessionFactory().openSession();
        tr = s4.beginTransaction();
        
        pojos.Automobile am2 = (pojos.Automobile)s4.load(pojos.Automobile.class, "18487021GK81H4XU1");
        
        System.out.println("\n Deleting the following record: " + am2.getVin() + "," + am2.getYear() + ", " 
                + am2.getMake() + ", " + am2.getModel() + ", " + am2.getBody() 
                    + ", " + am2.getColor());        
        
        s4.delete(am2);
        
        tr.commit();
        
        System.out.println("\n Record deleted successfully... ");
        
        s4.close();

        tr = null;
        
        //Adding a record in the database
        Session s3 = connection.Controller.getSessionFactory().openSession();
        s3 = connection.Controller.getSessionFactory().openSession();
        tr = s3.beginTransaction();
        
        pojos.Automobile am = new pojos.Automobile();
        am.setVin("18487021GK81H4XU1");
        am.setYear(2010);
        am.setMake("Honda");
        am.setModel("Odyssey");
        am.setBody("van");
        am.setColor("white");
        
        s3.save(am);
        
        tr.commit();
        
        System.out.println("\n Adding the following record: " + am.getVin() + "," + am.getYear() + ", " 
                + am.getMake() + ", " + am.getModel() + ", " + am.getBody() 
                    + ", " + am.getColor());
        System.out.println("\n Record added successfully!");
        
        s3.close();
        
        tr = null;
        
        System.exit(0);
        
    }    
}
