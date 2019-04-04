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
import java.util.concurrent.Semaphore;

public class JSONStreamProtector{
	private Semaphore semaphore = new Semaphore(1, true);
	/**
	 * This method protects JSONStreams from having concurrent write or read errors 
	 * due to multiple threads doing concurrent access.
	 * @param aJsonStream - The JSONOutputStream or JSONInputStream to be protected
	 */
	public void protectJSONStream(JSONStream aJsonStream){
		aJsonStream.setProtector(this);
	}
	protected void claim() throws InterruptedException{
		semaphore.acquire(1);
	}
	protected void free(){
		semaphore.release(1);
	}
}
