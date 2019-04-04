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
import java.io.*;

/**
 * This class contains several utility methods for generating and parsing JSON strings.  
 * Care has been taken to make these match the JavaScript JSON API as much as possible.
 *   Since these methods are static you may use them without worry from within any thread 
 *   or in multiple threads.
 * @author Lee S. Barney
 *
 */
public class JSONUtilities {

	public static int version = 1;
	public static int subversion = 5;
	public static boolean isBeta = true;
	/**
	 * Encodings available for parsed and generated JSON Strings
	 * @author Lee S. Barney
	 *
	 */
	public enum encoding {
		UNICODE,
		UTF8
	}
	
	
	public static String getVersion(){
		return "V "+version+"."+subversion+" "+ (JSONUtilities.isBeta ? "beta": "release");
	}
	/**
	 * Converts a Serializable object into a JSON formatted string
	 * @param aSerializableObject - the object to be JSONed.  This can be any Serializable Object except 
	 * a raw Object or anything that inherits from java.awt.container.
	 * @return a JSON formatted String or if null is passed in null is returned.
	 * @throws JSONException
	 */
	public static String stringify(Serializable aSerializableObject) throws JSONException{
		
		if(aSerializableObject == null){
			return null;
		}
		ByteArrayOutputStream theByteStream = new ByteArrayOutputStream();
		JSONOutputStream theStream = new JSONOutputStream(theByteStream);
		try {
			theStream.writeObject(aSerializableObject);
		}
		catch (IOException e){
			//do nohting since there will not be an IO Exception for a byte stream
		}
		return new String(theByteStream.toByteArray());
	}
	
	/**
	 * Converts a Serializable object into a JSON formatted string using the specified Encoding
	 * @param aSerializableObject - the object to be JSONed.  This can be any Serializable Object except 
	 * a raw Object or anything that inherits from java.awt.container.
	 * @param theEncoding - the encoding to use for the desired string.  It must be one 
	 * of the encodings declared in JSONUtilities.encoding.
	 * @return aJSON formatted String using the desired encoding
	 * @throws JSONException
	 */
	public static String stringify(Serializable aSerializableObject, JSONUtilities.encoding theEncoding) throws JSONException{
		if(aSerializableObject == null){
			return null;
		}
		if(theEncoding != encoding.UNICODE && theEncoding != encoding.UTF8){
			throw new JSONException("Unsupported encoding: "+theEncoding);
		}
		ByteArrayOutputStream theByteStream = new ByteArrayOutputStream();
		JSONOutputStream theStream = new JSONOutputStream(theByteStream);
		try {
			theStream.writeObject(aSerializableObject);
		}
		catch (IOException e){
			//do nothing since there will not be an IO exception when using a byte array stream
		}
		try {
			return theByteStream.toString(theEncoding == encoding.UNICODE ? "ISO-8859-1" : "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new JSONException("Unsupported encoding: "+theEncoding);
		}
	}
	

	/**
	 * Parses a string using the default platform encoding.  Either a HashMap or ArrayList is generated.
	 * @param aJSONString - the string to be parsed.  It is assumed that aJSONString uses the
	 *  default encoding for the platform.
	 * @return either a HashMap or an ArrayList depending on the contents of the parameter string
	 * @throws JSONException
	 */
	public static Object parse(String aJSONString) throws JSONException, ParseException{
		if(aJSONString == null){
			return null;
		}
		byte[] byteArray = aJSONString.getBytes();
		ByteArrayInputStream theByteStream = new ByteArrayInputStream(byteArray);
		JSONInputStream theStream = new JSONInputStream(theByteStream);
		return theStream.readObject();
	}
	

	/**
	 * Parses a string using the defined encoding.  Either a HashMap or ArrayList is generated.
	 * @param aJSONString - the string to be parsed.  For normal behavior it must be in the encoding
	 *  declared as the theEncoding parameter.
	 * @param theEncoding - the encoding of the String passed in as aJSONString.  It must be one 
	 * of the encodings declared in JSONUtilities.encoding.
	 * @return either a HashMap or an ArrayList depending on the contents of the parameter string
	 * @throws JSONException
	 */
	public static Object parse(String aJSONString, JSONUtilities.encoding theEncoding) throws JSONException, ParseException{
		if(aJSONString == null){
			return null;
		}
		if(theEncoding != encoding.UNICODE && theEncoding != encoding.UTF8){
			throw new JSONException("Unsupported encoding: "+theEncoding);
		}
		
		byte[] byteArray;
		try {
			byteArray = aJSONString.getBytes(theEncoding == encoding.UNICODE ? "ISO-8859-1" : "UTF-8");
		} 
		catch (UnsupportedEncodingException e) {
			throw new JSONException("Unsupported encoding: "+theEncoding);
		}
		ByteArrayInputStream theByteStream = new ByteArrayInputStream(byteArray);
		JSONInputStream theStream = new JSONInputStream(theByteStream);
		return theStream.readObject();
	}
}
