/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate2;

import java.util.HashMap;
import JSON.quickconnectfamily.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author mthoming
 */
public class LookupByModel implements Handler {
    
    @Override
    public void handleIt(HashMap<String, Object> dataMap) {
        try{
        //Search for a single record by ID
                    
            Scanner systemInScanner = new Scanner(System.in);

            ArrayList<String> carModels = new ArrayList<String>(Arrays.asList("3", 
                    "4Runner", "535i", "6", "Acadia", "Accord", "Camaro", 
                    "Camry", "Civic", "Colorado", "Corolla", "Corvette", "Cougar", 
                    "Encore", "ES 350", "Excursion", "F150", "Focus", "Impala", 
                    "Lesabre", "Miata", "Mustang", "Odyssey", "Optima", "Prius", 
                    "Protege", "Sedona", "Sephia", "Tarus", "Yukon"));            
            
            System.out.println("\n Please enter one of the following vehicle models: "
                    + "\n 3, 4Runner, 535i, 6, Acadia, Accord, Camaro, Camry, Civic"
                    + "\n Colorado, Corolla, Corvette, Cougar, Encore, ES 350"
                    + "\n Excursion, F150, Focus, Impala, Lesabre, Miata, Mustang"
                    + "\n Odyssey, Optima, Prius, Protege, Sedona, Sephia, Tarus, Yukon");

            String userSelection = systemInScanner.next();
            
            boolean run = true;   
            while(run){
                try{
                    if(carModels.contains(userSelection)){
                        Session s = connection.Controller.getSessionFactory().openSession();  

                        System.out.println("\n Searching for all "+userSelection+"'s in the database");
                        String HQL = "from Automobile where model = '"+userSelection+"' order by year desc";
                        Query q = s.createQuery(HQL);
                        
                        List<pojos.Automobile> list = q.list();
                        
                        for(pojos.Automobile amb : list){
                            System.out.println(amb.getVin()+", "+amb.getYear()+", "
                            +amb.getMake()+", "+amb.getModel()+", "+amb.getBody()
                            +", "+amb.getColor());
                        }
                    run = false;
                    break;
                    //FrontController.mainMenu();
                    } else  {
                        System.out.println("Please enter an item from the list.");
                        break;
                    }                
                
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
        
            }

        } 
        catch (Exception e) {
            e.printStackTrace();
        }

    }
    
}
