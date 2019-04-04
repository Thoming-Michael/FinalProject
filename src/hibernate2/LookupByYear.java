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
public class LookupByYear implements Handler {
    
    @Override
    public void handleIt(HashMap<String, Object> dataMap) {
        try{
        //Search for a single record by ID
                    
            Scanner systemInScanner = new Scanner(System.in);

            ArrayList<String> carYears = new ArrayList<String>(Arrays.asList("1986", 
                    "1987", "1988", "1989", "1990", "1991", "1992", 
                    "1993", "1994", "1995", "1996", "1997", "1998", 
                    "1999", "2000", "2001", "2002", "2003", "2004", 
                    "2005", "2006", "2007", "2008", "2009", "2010", 
                    "2011", "2012", "2013", "2015"));            
            
            System.out.println("\n Please enter one of the following vehicle models: 1986," 
                    +"\n 1987, 1988, 1989, 1990, 1991, 1992"
                    +"\n 1993, 1994, 1995, 1996, 1997, 1998"
                    +"\n 1999, 2000, 2001, 2002, 2003, 2004" 
                    +"\n 2005, 2006, 2007, 2008, 2009, 2010" 
                    +"\n 2011, 2012, 2013, 2015");

            String userSelection = systemInScanner.next();
            
            boolean run = true;   
            while(run){
                try{
                    if(carYears.contains(userSelection)){
                        Session s = connection.Controller.getSessionFactory().openSession();  

                        System.out.println("\n Searching for all "+userSelection+"'s in the database");
                        String HQL = "from Automobile where year = '"+userSelection
                                +"' order by make, model, year desc";
                        Query q = s.createQuery(HQL);
                        
                        List<pojos.Automobile> list = q.list();
                        
                        for(pojos.Automobile amb : list){
                            System.out.println(amb.getVin()+", "+amb.getYear()+", "
                            +amb.getMake()+", "+amb.getModel()+", "+amb.getBody()
                            +", "+amb.getColor());
                        }
                    run = false;
                    break;
                    
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
