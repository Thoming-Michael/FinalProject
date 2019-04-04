/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate2;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author mthoming
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({hibernate2.CustomerTest.class, hibernate2.LookupByYearTest.class, hibernate2.EnterVehicleRecordTest.class, hibernate2.LookupVehicleTest.class, hibernate2.HandlerTest.class, hibernate2.LookupByMakeTest.class, hibernate2.ApplicationControllerTest.class, hibernate2.HibernateUtilsTest.class, hibernate2.FrontControllerTest.class, hibernate2.LookupByModelTest.class, hibernate2.LookupSalesRecordTest.class})
public class Hibernate2Suite {
    
}
