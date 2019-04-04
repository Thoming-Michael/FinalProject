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
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
//import java.text.ParseException;
//import jdk.nashorn.internal.parser.JSONParser;
//import org.json.JSONException;
import JSON.quickconnectfamily.JSONParser;

public class JSONInputStream extends JSONStream{
	
	JSONParser aParser;

	/**
	 * 
	 * @param theByteStream - the stream from which the JSON is to be read.
	 */
	public JSONInputStream(InputStream theByteStream) {
		if(theByteStream == null){
			throw new NullPointerException();
		}
		InputStreamReader inReader = new InputStreamReader(theByteStream);
		aParser = new JSONParser(inReader);
	}


	/**
	 * Reads a HashMap or ArrayList from the underlying stream
	 * @return an Object of type HashMap if the JSON being read is an object or of type
	 * ArrayList if the JSON being read is an array.
	 * @throws JSONException
	 */
	public Object readObject() throws JSONException, ParseException{
		if(theProtector != null){
			try {
				theProtector.claim();
			} catch (InterruptedException e) {
				throw new JSONException("Calling Thread interupted", e);
			}
		}
		try {
			Object parsedObject = aParser.parse();
			if(theProtector != null){
				theProtector.free();
			}
			return parsedObject;
		} catch (IOException e) {
			throw new JSONException("Unable to read JSON. ", e);
		}
	}
	/**
	 * Closes the stream used.
	 * @throws IOException
	 */
	public void close() throws IOException{
		aParser.closeReader();
	}
}
