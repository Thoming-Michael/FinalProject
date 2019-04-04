/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate2;

import java.util.HashMap;
import JSON.quickconnectfamily.*;
import java.util.Scanner;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author mthoming
 */
public class EnterVehicleRecord implements Handler {
    
    @Override
    public void handleIt(HashMap<String, Object> dataMap) {
        try{

            Scanner systemInScanner = new Scanner(System.in);

            System.out.println("\n Enter the following values to insert a"
                    + "\n new vehicle record:"
                    + "\n VIN:");
            String vin = systemInScanner.next();
            
            System.out.println("\n Year:");
            int year = systemInScanner.nextInt();
            
            System.out.println("\n Make:");
            String make = systemInScanner.next();
            
            System.out.println("\n Model:");
            String model = systemInScanner.next();
            
            System.out.println("\n Body (compact, sedan, SUV, "
                    + "van, coupe, pickup:");
            String body = systemInScanner.next();
            
            System.out.println("\n Color:");
            String color = systemInScanner.next();
            
            //Adding a record in the database           
            Session s = connection.Controller.getSessionFactory().openSession();
            s = connection.Controller.getSessionFactory().openSession();
            Transaction tr = s.beginTransaction();

            pojos.Automobile am = new pojos.Automobile();
            am.setVin(vin);
            am.setYear(year);
            am.setMake(make);
            am.setModel(model);
            am.setBody(body);
            am.setColor(color);

            s.save(am);

            tr.commit();

            System.out.println("\n Adding the following record: " + am.getVin() + ", " + am.getYear() + ", " 
                    + am.getMake() + ", " + am.getModel() + ", " + am.getBody() 
                        + ", " + am.getColor());
            System.out.println("\n Record added successfully!");

            s.close();

            tr = null;       
            
            FrontController.mainMenu();
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
