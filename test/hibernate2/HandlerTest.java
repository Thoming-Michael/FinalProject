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
public class HandlerTest {
    
    public HandlerTest() {
    }

    /**
     * Test of handleIt method, of class Handler.
     */
    @Test
    public void testHandleIt() {
        System.out.println("handleIt");
        HashMap<String, Object> data = null;
        Handler instance = new HandlerImpl();
        instance.handleIt(data);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class HandlerImpl implements Handler {

        public void handleIt(HashMap<String, Object> data) {
        }
    }
    
}
