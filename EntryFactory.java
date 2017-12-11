/**
 * File: EntryFactory.java
 * Author: Ryan Sellar
 * Date: Nov. 09 2017
 */ 
 
import java.io.File;
 
/* The entry factory is designed as a simple input output class. It takes as its input 
   the path to a file and build an entry object out of the file's data. The new entry 
   object is then returned for further use. */ 
public class EntryFactory{
	
	private DHash dHash;
	
	//Constructor
	public EntryFactory(){
		dHash = new DHash();
	}
	
	//Build Entry
	public Entry buildEntry(String fileLocation){
		File file = null;
		
		try { 
			file = new File(fileLocation);
		} catch (Exception e){
			System.out.println(e);
		}
		
		if (file == null) return null;	//in case assignment fails
		
		if (file.length() > 0){			//this checks for whether the file exists or not
			Entry newEntry = new Entry(fileLocation, file.getName(), file.length(), dHash.getHash(fileLocation));
			return newEntry;
		} else {
			return null;
		}
	}
	
	//Test function
	//Enter the path location of the file as an argument
	public static void main(String[] args){
		if (args.length > 0){
			EntryFactory testFactory = new EntryFactory();
			Entry testEntry = testFactory.buildEntry(args[0]);
			System.out.println(testEntry);
		} else {
			System.out.println("Usage: java EntryFactory <path to file>");
		}
	}
	
}
