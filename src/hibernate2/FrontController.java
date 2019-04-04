/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate2;

import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author mthoming
 */
public class FrontController {
    public static void main(String[] args) {
        
        mainMenu();
    }
    
    public static void mainMenu() {
            Scanner systemInScanner = new Scanner(System.in);
            System.out.println("\n ***Welcome to the Vehicle Sales Database!***"
                    + "\n "
                    + "\n Please Choose from the following options:"
                    + "\n "
                    + "\n V - Look up a vehicle record"
                    + "\n R - Look up a past sales record"
                    + "\n M - List all car makes and models"
                    + "\n S - Enter a new vehicle record"
                    + "\n X - Exit program");
            String menuChoice = systemInScanner.next();
            menuChoice = menuChoice.toUpperCase();

            if("X".equals(menuChoice)){
                System.exit(0);
            }
            
            ApplicationController myAppController = new ApplicationController();

            myAppController.mapCommand("V", new LookupVehicle());
            myAppController.mapCommand("R",  new LookupSalesRecord());
            myAppController.mapCommand("S",   new EnterVehicleRecord());
            myAppController.mapCommand("M",   new HttpURLConnection());            

            HashMap colorMap = new HashMap();

            myAppController.handleRequest(menuChoice, colorMap);
    }
       
}
