/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate2;

/**
 * Example from "Doing More with Java" pg. 87
 * @author mthoming
 */
import java.util.HashMap;

public interface Handler {
    public void handleIt(HashMap<String, Object> data);
    
}
