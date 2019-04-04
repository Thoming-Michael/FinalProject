/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate2;

import java.util.HashMap;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mthoming
 */
public class ApplicationControllerTest {
    
    public ApplicationControllerTest() {
    }

    /**
     * Test of handleRequest method, of class ApplicationController.
     */
    @Test
    public void testHandleRequest() {
        System.out.println("handleRequest");
        String command = "";
        HashMap<String, Object> data = null;
        ApplicationController instance = new ApplicationController();
        instance.handleRequest(command, data);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of mapCommand method, of class ApplicationController.
     */
    @Test
    public void testMapCommand() {
        System.out.println("mapCommand");
        String aCommand = "";
        Handler acHandler = null;
        ApplicationController instance = new ApplicationController();
        instance.mapCommand(aCommand, acHandler);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
