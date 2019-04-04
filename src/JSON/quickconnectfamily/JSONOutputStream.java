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
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class JSONOutputStream extends JSONStream{
	private BufferedWriter theWriter;
	private int levelCountLimit;
	//this is a hack work around since Android is returning true when a string is sent the isInstance("java.awt.Container") method call.
	private boolean isAndroid = false;
	private HttpURLConnection theConnection;



	public JSONOutputStream(HttpURLConnection aConnection) throws ProtocolException{
		theConnection = aConnection;
		theConnection.setRequestMethod("POST");
		theConnection.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded");

		theConnection.setUseCaches(false);
		theConnection.setRequestProperty("Content-Language", "en-US");

		theConnection.setUseCaches(false);
		theConnection.setDoInput(true);
		theConnection.setDoOutput(true);
	}
	/**
	 *
	 * @param aStream - the stream to which the JSON is to be written
	 */
	public JSONOutputStream(OutputStream aStream){
		if(aStream == null){
			throw new NullPointerException();
		}
		theWriter = new BufferedWriter( new OutputStreamWriter(aStream));
		levelCountLimit = 30;
		//hack work around.  See message regarding isAndroid above.
		try{
			Object aContainer = Class.forName("java.awt.Container");
			if(aContainer == null){
				isAndroid = true;
			}
		}
		catch(Exception e){
			isAndroid = true;
		}
	}

	/**
	 * Writes a Serializable Object to the underlying stream as a JSON string
	 * @param aSerializableObject - any Serializable object other than a raw Java Object and anything that inherits from java.awt.container
	 * @throws JSONException
	 */

	public void writeObject(Serializable aSerializableObject) throws JSONException, IOException{
		if(theProtector != null) {
			try {
				theProtector.claim();
			} catch (InterruptedException e) {
				throw new JSONException("Calling Thread interupted");
			}
		}

		writeObject(aSerializableObject, 0);

		if(theProtector != null){
			theProtector.free();
		}
	}
	@SuppressWarnings("rawtypes")

	private void writeObject(Serializable aSerializableObject, int levelCount) throws JSONException, IOException{
		/*
		 * Android doesn't have awt.
		 */
		try{
			if(aSerializableObject == null || aSerializableObject.getClass().equals(Object.class)){
				return;
			}

			//The following line returns true when run in Android when it should not.  Don't use it.
			//else if(|| aSerializableObject.getClass().isInstance("java.awt.Container")){
			 /*
			  * run up the inheritance tree and see if it is a container.
			  */
			Class aClass = aSerializableObject.getClass();
			if(!isAndroid){
				while((aClass = aClass.getSuperclass())  != null && !aClass.getName().equals("java.awt.Container")){
					//System.out.println("class Name: "+aClass.getName());
				}

				if(aClass != null && aClass.getName().equals("java.awt.Container")){
					return;
				}
			}
		}
		catch(Throwable t){
			//do the Android specific check
			if(aSerializableObject == null || aSerializableObject.getClass().equals(Object.class)){
				return;
			}
		}
		levelCount++;
		if(levelCount > 30){
			throw new JSONException("Depth limit of "+levelCountLimit+" exceeded in object "+aSerializableObject+" of class "+aSerializableObject.getClass().getName()+".");
		}

		if(aSerializableObject instanceof Boolean){
			theWriter.write(((Boolean)aSerializableObject).toString());
		}

		else if(aSerializableObject instanceof Date){
			Timestamp aStamp = new Timestamp(((Date)aSerializableObject).getTime());
			theWriter.write("\""+aStamp.toString()+"\"");
		}
		else if(aSerializableObject instanceof Map){
			Map aMap = (Map)aSerializableObject;
			theWriter.write("{");
			Set keys = aMap.keySet();
			Iterator keyIt = keys.iterator();
			int count = 0;
			while(keyIt.hasNext()){
				Object key = keyIt.next();
				Object value = aMap.get(key);
				if(value == null){
					continue;
				}
				if(count != 0){
					theWriter.write(",");
				}
				if(!(value instanceof Serializable)){
					throw new JSONException("Unable to JSON non-serializable object ("+value+") of type "+value.getClass().toString()+".");
				}
				theWriter.write("\""+key.toString()+"\":");
				writeObject(((Serializable)value), levelCount);

				count++;
			}
			theWriter.write("}");
		}
		else if(aSerializableObject instanceof List){
			List aList = (List)aSerializableObject;
			Iterator keyIt = aList.iterator();
			theWriter.write("[");
			while(keyIt.hasNext()){
				Object value = keyIt.next();
				if(value == null){
					value = "null";
				}
				if(!(value instanceof Serializable)){
					throw new JSONException("Unable to JSON non-serializable object ("+value+") of type "+value.getClass().toString()+".");
				}
				writeObject(((Serializable)value), levelCount);
				if(keyIt.hasNext()){
					theWriter.write(",");
				}
			}

			theWriter.write("]");
		}
		else if(aSerializableObject instanceof String){

			String appendString = (String)aSerializableObject;
			if(!appendString.equals("null")){
				appendString = "\""+escapeStringForJSON( ((String)aSerializableObject) )+"\"";
			}
			theWriter.append(appendString);
		}
		else if(aSerializableObject instanceof Number){
			theWriter.append(aSerializableObject.toString());
			/*
			if(aSerializableObject instanceof Integer){
				theWriter.append(((Integer)aSerializableObject).toString());
			}
			else if(aSerializableObject instanceof Long){
				theWriter.append(((Long)aSerializableObject).toString());
			}
			else if(aSerializableObject instanceof Short){
				theWriter.append(((Integer)aSerializableObject).toString());
			}
			else if(aSerializableObject instanceof Double){
				theWriter.append(((Double)aSerializableObject).toString());
			}
			else if(aSerializableObject instanceof Float){
				theWriter.append(((Float)aSerializableObject).shortValue()+"\"");
			}
			else{
				theWriter.append("\""+aSerializableObject.toString()+"\"");
			}
			*/
		}
		//object arrays
		else if(aSerializableObject instanceof Object[]){
			theWriter.append('[');
			Object[] theArray = (Object[])aSerializableObject;
			for(int i = 0; i < theArray.length; i++){
				Object anObject = theArray[i];
				if(anObject == null){
					anObject = "null";
				}
				else if(!(anObject instanceof Serializable)){
					continue;
				}
				writeObject(((Serializable)anObject), levelCount);
				if(i < theArray.length - 1){
					theWriter.append(',');
				}
			}
			theWriter.append(']');
		}
		//primative arrays
		else if(aSerializableObject.getClass().isArray()){
			Class primitiveArrayClass = aSerializableObject.getClass();
			if(int[].class == primitiveArrayClass){
				theWriter.append('[');
				int[] theArray = (int[])aSerializableObject;
				for(int i = 0; i < theArray.length; i++){
					int aValue = theArray[i];
					theWriter.append(Integer.toString(aValue));
					if(i < theArray.length - 1){
						theWriter.append(',');
					}
				}
				theWriter.append(']');
			}
			else if(short[].class == primitiveArrayClass){
				theWriter.append('[');
				short[] theArray = (short[])aSerializableObject;
				for(int i = 0; i < theArray.length; i++){
					short aValue = theArray[i];
					theWriter.append(Short.toString(aValue));
					if(i < theArray.length - 1){
						theWriter.append(',');
					}
				}
				theWriter.append(']');
			}
			else if(long[].class == primitiveArrayClass){
				theWriter.append('[');
				long[] theArray = (long[])aSerializableObject;
				for(int i = 0; i < theArray.length; i++){
					long aValue = theArray[i];
					theWriter.append(Long.toString(aValue));
					if(i < theArray.length - 1){
						theWriter.append(',');
					}
				}
				theWriter.append(']');
			}

			else if(double[].class == primitiveArrayClass){
				theWriter.append('[');
				double[] theArray = (double[])aSerializableObject;
				for(int i = 0; i < theArray.length; i++){
					double aValue = theArray[i];
					theWriter.append(Double.toString(aValue));
					if(i < theArray.length - 1){
						theWriter.append(',');
					}
				}
				theWriter.append(']');
			}
			else if(float[].class == primitiveArrayClass){
				theWriter.append('[');
				float[] theArray = (float[])aSerializableObject;
				for(int i = 0; i < theArray.length; i++){
					float aValue = theArray[i];
					theWriter.append(Float.toString(aValue));
					if(i < theArray.length - 1){
						theWriter.append(',');
					}
				}
				theWriter.append(']');
			}
			else if(char[].class == primitiveArrayClass){
				theWriter.append('[');
				char[] theArray = (char[])aSerializableObject;
				for(int i = 0; i < theArray.length; i++){
					char aValue = theArray[i];
					theWriter.append("\""+aValue+"\"");
					if(i < theArray.length - 1){
						theWriter.append(',');
					}
				}
				theWriter.append(']');
			}
			else if(byte[].class == primitiveArrayClass){
				theWriter.append('[');
				byte[] theArray = (byte[])aSerializableObject;
				for(int i = 0; i < theArray.length; i++){
					byte aValue = theArray[i];
					theWriter.append("\""+aValue+"\"");
					if(i < theArray.length - 1){
						theWriter.append(',');
					}
				}
				theWriter.append(']');
			}

		}
		else/*is instance of java.lang.Object*/{
			theWriter.write('{');
			writeAllAttributesOf(aSerializableObject, aSerializableObject.getClass(), levelCount);
			theWriter.write('}');
		}
		//theWriter.newLine();
		theWriter.flush();
	}


	private void writeAllAttributesOf(Serializable aSerializableObject, Class<?> aClass, int levelCount) throws JSONException, IOException{
		//aClass may be the final child class or one of the parent classes
		Field[] theFields = aClass.getDeclaredFields();
		boolean previousFieldWasWritten = false;
		try{
			for(int i = 0; i < theFields.length; i++){
				Field aField = theFields[i];
				aField.setAccessible(true);

				String fieldName = aField.getName();
				Object value = aField.get(aSerializableObject);
				if(!(value instanceof Serializable)){
					continue;
				}
				if( previousFieldWasWritten){
					theWriter.write(",");
				}
				int modifiers = aField.getModifiers();
				//ignore final attributes, attributes that are null, and any outer class references of inner classes
				if(!Modifier.isFinal(modifiers) && value != null && !fieldName.equals("this$0")){

					theWriter.write("\""+fieldName+"\":");
					writeObject(((Serializable)value), levelCount);
					previousFieldWasWritten = true;
				}

			}
		}
		catch(IllegalAccessException e){
			throw new JSONException("Unable to access one of the attributes of "+aSerializableObject);
		}
		//The Object class has no attributes.  Null has no attributes.
		if(aClass.getSuperclass() != null && aClass.getSuperclass() != Object.class ){
			//since all parent attributes come after the last child attribute add a comma
			theWriter.write(",");
			writeAllAttributesOf(aSerializableObject, aClass.getSuperclass(), levelCount);
		}
	}
	/**
	 * Closes the output stream and the underlying stream
	 */
	public void close() throws IOException{
		theWriter.close();
	}

	private String escapeStringForJSON(String text) {
		text = text.replaceAll("(\\r\\n?|\\n)", "\\\\n")
				.replaceAll("([^\\\\]?)\\\"", "$1\\\\\"")
				.replaceAll("(\\/)", "\\\\/")
				.replaceAll("(\\f)", "\\\\f")
				.replaceAll("(\\t)", "\\\\t")
				.replaceAll("([^\\\\])\\\\([^\\\\ntfb\\/\\\"])", "$1\\\\\\\\$2");
		return text;
	}
}
