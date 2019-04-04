/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate2;

import java.util.HashMap;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mthoming
 */
public class LookupByYearTest {
    
    public LookupByYearTest() {
    }

    /**
     * Test of handleIt method, of class LookupByYear.
     */
    @Test
    public void testHandleIt() {
        System.out.println("handleIt");
        HashMap<String, Object> dataMap = null;
        LookupByYear instance = new LookupByYear();
        instance.handleIt(dataMap);
        // TODO review the generated test code and remove the default call to fail.

        Session s = connection.Controller.getSessionFactory().openSession(); 
        String HQL = "from Automobile where year = '2007' order by year desc";
        Query q = s.createQuery(HQL);
        
        List<pojos.Automobile> list = q.list();
        
        pojos.Automobile amb2;
        
        for(pojos.Automobile amb : list){
            System.out.println(amb.getVin()+", "+amb.getYear()+", "
                +amb.getMake()+", "+amb.getModel()+", "+amb.getBody()
                +", "+amb.getColor());
            
            int year = amb.getYear();
            System.out.println(year);
            
            assertEquals(amb.getYear(), year);
        }        
    }
    
}
