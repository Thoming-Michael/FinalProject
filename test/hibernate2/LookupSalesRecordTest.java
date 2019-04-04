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
public class LookupSalesRecordTest {
    
    public LookupSalesRecordTest() {
    }

    /**
     * Test of handleIt method, of class LookupSalesRecord.
     */
    @Test
    public void testHandleIt() {
        System.out.println("handleIt");
        HashMap<String, Object> dataMap = null;
        LookupSalesRecord instance = new LookupSalesRecord();
        instance.handleIt(dataMap);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
