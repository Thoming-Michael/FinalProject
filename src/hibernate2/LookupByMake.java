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
import static org.hibernate.internal.util.StringHelper.toUpperCase;

/**
 *
 * @author mthoming
 */
public class LookupByMake implements Handler {
    
    @Override
    public void handleIt(HashMap<String, Object> dataMap) {
        try{
        //Search for a single record by ID
                    
            Scanner systemInScanner = new Scanner(System.in);

            System.out.println("\n Select from the following options: "
                    + "\n B - BMW"
                    + "\n U - Buick"
                    + "\n C - Chevrolet"
                    + "\n F - Ford"
                    + "\n G - GMC"
                    + "\n H - Honda"
                    + "\n K - Kia"
                    + "\n L - Lexus"
                    + "\n M - Mazda"
                    + "\n E - Mercury"
                    + "\n T - Toyota");

            String makeChoice = systemInScanner.next();
            makeChoice = makeChoice.toUpperCase();

            Session s = connection.Controller.getSessionFactory().openSession();      

            switch(makeChoice) {
                case "B": { 
                    System.out.println("\n Searching for all BMWs in the database:");
                    String HQL = "from Automobile where make = 'BMW' order by year desc";
                    Query q = s.createQuery(HQL);
                    //need to search using "BMW" and populate the results into a list

                    List<pojos.Automobile> list = q.list();

                    for(pojos.Automobile amb : list){
                        System.out.println(amb.getVin()+", "+amb.getYear()+", "
                            +amb.getMake()+", "+amb.getModel()+", "+amb.getBody()
                            +", "+amb.getColor());
                    }
                    
                    FrontController.mainMenu();                    
                }
                    break;

                case "U": { 
                    System.out.println("\n Searching for all Buicks in the database:");
                    String HQL = "from Automobile where make = 'Buick' order by year desc";
                    Query q = s.createQuery(HQL);

                    List<pojos.Automobile> list = q.list();

                    for(pojos.Automobile amb : list){
                        System.out.println(amb.getVin()+", "+amb.getYear()+", "
                            +amb.getMake()+", "+amb.getModel()+", "+amb.getBody()
                            +", "+amb.getColor());
                    }
                }
                    break;
                    
                case "C": {  
                    System.out.println("\n Searching for all Chevrolets in the database:");
                    String HQL = "from Automobile where make = 'Chevrolet' order by year desc";
                    Query q = s.createQuery(HQL);

                    List<pojos.Automobile> list = q.list();

                    for(pojos.Automobile amb : list){
                        System.out.println(amb.getVin()+", "+amb.getYear()+", "
                            +amb.getMake()+", "+amb.getModel()+", "+amb.getBody()
                            +", "+amb.getColor());
                    }
                }
                    break;
                    
                case "F": {  
                    System.out.println("\n Searching for all Fords in the database:");
                    String HQL = "from Automobile where make = 'Ford' order by year desc";
                    Query q = s.createQuery(HQL);

                    List<pojos.Automobile> list = q.list();

                    for(pojos.Automobile amb : list){
                        System.out.println(amb.getVin()+", "+amb.getYear()+", "
                            +amb.getMake()+", "+amb.getModel()+", "+amb.getBody()
                            +", "+amb.getColor());
                    }
                }
                    break;
                    
                case "G": {  
                    System.out.println("\n Searching for all GMCs in the database:");
                    String HQL = "from Automobile where make = 'GMC' order by year desc";
                    Query q = s.createQuery(HQL);

                    List<pojos.Automobile> list = q.list();

                    for(pojos.Automobile amb : list){
                        System.out.println(amb.getVin()+", "+amb.getYear()+", "
                            +amb.getMake()+", "+amb.getModel()+", "+amb.getBody()
                            +", "+amb.getColor());
                    }
                }
                    break;
                    
                case "H": {  
                    System.out.println("\n Searching for all Hondas in the database:");
                    String HQL = "from Automobile where make = 'Honda' order by year desc";
                    Query q = s.createQuery(HQL);

                    List<pojos.Automobile> list = q.list();

                    for(pojos.Automobile amb : list){
                        System.out.println(amb.getVin()+", "+amb.getYear()+", "
                            +amb.getMake()+", "+amb.getModel()+", "+amb.getBody()
                            +", "+amb.getColor());
                    }
                }
                    break;
                    
                case "K": {  
                    System.out.println("\n Searching for all Kias in the database:");
                    String HQL = "from Automobile where make = 'Kia' order by year desc";
                    Query q = s.createQuery(HQL);

                    List<pojos.Automobile> list = q.list();

                    for(pojos.Automobile amb : list){
                        System.out.println(amb.getVin()+", "+amb.getYear()+", "
                            +amb.getMake()+", "+amb.getModel()+", "+amb.getBody()
                            +", "+amb.getColor());
                    }
                }
                    break;
                    
                case "L": {  
                    System.out.println("\n Searching for all Lexus' in the database:");
                    String HQL = "from Automobile where make = 'Lexus' order by year desc";
                    Query q = s.createQuery(HQL);

                    List<pojos.Automobile> list = q.list();

                    for(pojos.Automobile amb : list){
                        System.out.println(amb.getVin()+", "+amb.getYear()+", "
                            +amb.getMake()+", "+amb.getModel()+", "+amb.getBody()
                            +", "+amb.getColor());
                    }
                }
                    break;
                    
                case "M": {  
                    System.out.println("\n Searching for all Mazdas in the database:");
                    String HQL = "from Automobile where make = 'Mazda' order by year desc";
                    Query q = s.createQuery(HQL);

                    List<pojos.Automobile> list = q.list();

                    for(pojos.Automobile amb : list){
                        System.out.println(amb.getVin()+", "+amb.getYear()+", "
                            +amb.getMake()+", "+amb.getModel()+", "+amb.getBody()
                            +", "+amb.getColor());
                    }
                }
                    break;
                    
                case "E": {  
                    System.out.println("\n Searching for all Mercurys in the database:");
                    String HQL = "from Automobile where make = 'Mercury' order by year desc";
                    Query q = s.createQuery(HQL);

                    List<pojos.Automobile> list = q.list();

                    for(pojos.Automobile amb : list){
                        System.out.println(amb.getVin()+", "+amb.getYear()+", "
                            +amb.getMake()+", "+amb.getModel()+", "+amb.getBody()
                            +", "+amb.getColor());
                    }
                }
                    break;
                    
                case "T": {  
                    System.out.println("\n Searching for all Toyotas in the database:");
                    String HQL = "from Automobile where make = 'Toyota' order by year desc";
                    Query q = s.createQuery(HQL);

                    List<pojos.Automobile> list = q.list();

                    for(pojos.Automobile amb : list){
                        System.out.println(amb.getVin()+", "+amb.getYear()+", "
                            +amb.getMake()+", "+amb.getModel()+", "+amb.getBody()
                            +", "+amb.getColor());
                    }
                }    
                    break;   
            }
        
        } 
        catch (Exception e) {
            e.printStackTrace();
        }

    }
    
}
