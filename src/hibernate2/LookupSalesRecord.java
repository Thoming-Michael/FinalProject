/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate2;

import java.util.HashMap;
import JSON.quickconnectfamily.*;
import java.util.List;
import java.util.Scanner;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author mthoming
 */
public class LookupSalesRecord implements Handler {
    
    @Override
    public void handleIt(HashMap<String, Object> dataMap) {
        boolean run = true;
        
        while(run){
            try{

            Scanner systemInScanner = new Scanner(System.in);

            System.out.println("\n select a value to sort by: "
                    + "\n L - Customer Last Name"
                    + "\n S - Salesman Last Name"
                    + "\n D - Date of Sale"
                    + "\n X - Return to the main menu");
            
            String saleLookupChoice = systemInScanner.next();
            saleLookupChoice = saleLookupChoice.toUpperCase();            
           
            
            
            switch(saleLookupChoice) {
                case "L": {
                    Session s = connection.Controller.getSessionFactory().openSession(); 
                    
                    System.out.println("\n All Sale Listings Sorted by Customer Last Name:");
                    String HQL = "from Purchase order by customerKey";
                    Query q = s.createQuery(HQL);

                    List<pojos.Purchase> list = q.list();

                    for(pojos.Purchase pur : list){
                        System.out.println(pur.getDateSold()+", "+pur.getPrice());
                                
//                                pur.getCustomer()+", "+pur.getAutomobile()+", "
//                            +pur.getDateSold()+", "+pur.getPrice()+", "+pur.getSalesperson());
                    }                   
                }
                    break;

                case "S": { 
                    Session s = connection.Controller.getSessionFactory().openSession(); 
                    
                    System.out.println("\n All Sale Listings Sorted by Salesman Last Name:");
                    String HQL = "from Purchase order by salesPersonKey";
                    Query q = s.createQuery(HQL);

                    List<pojos.Purchase> list = q.list();

                    for(pojos.Purchase pur : list){
                        System.out.println(pur.getDateSold()+", "+pur.getPrice());
                    }
                }
                    break;
                    
                case "D": {  
                    Session s = connection.Controller.getSessionFactory().openSession(); 
                    
                    System.out.println("\n All Sale Listings Sorted by Date of Sale:");
                    String HQL = "from Purchase order by dateSold desc";
                    Query q = s.createQuery(HQL);

                    List<pojos.Purchase> list = q.list();

                    for(pojos.Purchase pur : list){
                        System.out.println(pur.getDateSold()+", "+pur.getPrice());
                    }
                }
                    break;
            }
            
            if("X".equals(saleLookupChoice)){
                run = false;
                FrontController.mainMenu();
            }

            } 
            catch (Exception e) {
                e.printStackTrace();
            }
            }
    }
    
}
