/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate2;

import java.util.HashMap;
import JSON.quickconnectfamily.*;
import org.hibernate.Session;
import java.util.Scanner;

/**
 *
 * @author mthoming
 */
public class LookupVehicle implements Handler {
    
    @Override
    public void handleIt(HashMap<String, Object> dataMap) {
        boolean run = true;
        
        while(run){
            try{

            Scanner systemInScanner = new Scanner(System.in);

            System.out.println("\n select a value to search by: "
                    + "\n M - Make"
                    + "\n O - Model"
                    + "\n Y - Year"
                    + "\n Press any other key to return to the main menu");
            
            String vehicleLookupChoice = systemInScanner.next();
            vehicleLookupChoice = vehicleLookupChoice.toUpperCase();           
           
            ApplicationController myAppController = new ApplicationController();

            myAppController.mapCommand("M", new LookupByMake());
            myAppController.mapCommand("O",  new LookupByModel());
            myAppController.mapCommand("Y",   new LookupByYear());

            HashMap colorMap = new HashMap();

            myAppController.handleRequest(vehicleLookupChoice, colorMap);
            
            if(vehicleLookupChoice != "M" && vehicleLookupChoice != "Y" && vehicleLookupChoice != "O"){
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
