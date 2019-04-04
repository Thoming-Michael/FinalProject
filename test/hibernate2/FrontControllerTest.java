/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate2;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mthoming
 */
public class FrontControllerTest {
    
    public FrontControllerTest() {
    }

    /**
     * Test of main method, of class FrontController.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        FrontController.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of mainMenu method, of class FrontController.
     */
    @Test
    public void testMainMenu() {
        System.out.println("mainMenu");
        FrontController.mainMenu();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
