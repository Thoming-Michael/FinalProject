/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate2;

import org.hibernate.SessionFactory;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mthoming
 */
public class HibernateUtilsTest {
    
    public HibernateUtilsTest() {
    }

    /**
     * Test of getSessionFactory method, of class HibernateUtils.
     */
    @Test
    public void testGetSessionFactory() {
        System.out.println("getSessionFactory");
        SessionFactory expResult = null;
        SessionFactory result = HibernateUtils.getSessionFactory();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of shutdown method, of class HibernateUtils.
     */
    @Test
    public void testShutdown() {
        System.out.println("shutdown");
        HibernateUtils.shutdown();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
