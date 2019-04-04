/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JSON.quickconnectfamily;

/**
 *
 * @author mthoming
 */
public class JSONException extends Exception {

	private static final long serialVersionUID = -9091548358671542859L;

	/**
	 * 
	 */
	public JSONException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 */
	public JSONException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

    public JSONException(String message, Throwable aThrowable){
        super(message,aThrowable);
    }



}
