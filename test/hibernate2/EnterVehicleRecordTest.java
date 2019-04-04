/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate2;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Random;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mthoming
 */
public class EnterVehicleRecordTest {
    
    public EnterVehicleRecordTest() {
    }

    /**
     * Test of handleIt method, of class EnterVehicleRecord.
     */
    @Test
    public void testHandleIt() {
        System.out.println("handleIt");
        HashMap<String, Object> dataMap = null;
        EnterVehicleRecord instance = new EnterVehicleRecord();
        instance.handleIt(dataMap);
        // TODO review the generated test code and remove the default call to fail.

//            Session s = connection.Controller.getSessionFactory().openSession();
//            s = connection.Controller.getSessionFactory().openSession();
//            Transaction tr = s.beginTransaction();
//
//            byte[] array = new byte[7];
//            new Random().nextBytes(array);
//            String vin = new String(array, Charset.forName("UTF-8"));
//                       
//            System.out.println(vin);
//            
//            int year = 1977;
//            String make = "Datsun";
//            String model = "210 wagon";
//            String body = "wagon";
//            String color = "orange";
//            
//            pojos.Automobile am = new pojos.Automobile();
//            am.setVin(vin);
//            am.setYear(year);
//            am.setMake(make);
//            am.setModel(model);
//            am.setBody(body);
//            am.setColor(color);
//
//            s.save(am);
//
//            tr.commit();
//
//            System.out.println("\n Adding the following record: " + am.getVin() + "," + am.getYear() + ", " 
//                    + am.getMake() + ", " + am.getModel() + ", " + am.getBody() 
//                        + ", " + am.getColor());
//            System.out.println("\n Record added successfully!");
//
//            s.close();
//
//            tr = null;
    }
    
}
