/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate2;

import java.util.HashMap;
import JSON.quickconnectfamily.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 *
 * @author mthoming
 */
public class HttpURLConnection implements Handler {
    
    private final String USER_AGENT = "Mozilla/5.0";
    
    @Override
    public void handleIt(HashMap<String, Object> dataMap) {
        try{
		String url = "https://www.motability.co.uk/cars-scooters-and-powerchairs/types-of-cars/by-makes-and-models";
                
                HttpURLConnection http = new HttpURLConnection();
                	
		URL obj = new URL(url);
		java.net.HttpURLConnection con = (java.net.HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");
                System.out.println("Send Http GET request");
                        
		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		//System.out.println(response.toString());
                                             
                Document doc = Jsoup.parse(response.toString()); 

                //System.out.println(doc);
   
                //create an arraylist and insert all values with "td" tags into it
                ArrayList<Element> rows = doc.select("td");
                //output the entire list to the user's view
                //System.out.println(rows);
                
                //create iterator variable to use in counting rows during iteration
                int i = 0;
                //create a variable that gets the values tagged as "td" in each row
                Element link = doc.select("td").get(i);
                //loop through each row in ROWS, grab the "td" tagged data and 
                //print it out with the html formatting stripped off
                for(i = 0; i < rows.size(); i++) {
                    link = doc.select("td").get(i);
                    System.out.println(link.html());
                }            
            
            FrontController.mainMenu();
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
