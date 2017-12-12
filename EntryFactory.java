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
	
	//Constructor
	public EntryFactory(){}
	
	//Build Entry
	public Entry buildEntry(String fileLocation){
		File file = null;
		file = new File(fileLocation);
		
		Entry newEntry = new Entry();
		newEntry.fileLocation = fileLocation;
		newEntry.fileName = file.getName();
		newEntry.fileSize = file.length();
				
		return newEntry;
	}
	
	//Test function
	//Enter the path location of the file as an argument
	public static void main(String[] args){
		if (args.length > 0){
			EntryFactory testFactory = new EntryFactory();
			Entry testEntry = testFactory.buildEntry(args[0]);
		} else {
			System.out.println("Usage: java EntryFactory <path to file>");
		}
	}
	
}
