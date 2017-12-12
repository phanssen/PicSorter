/**
 * File: EntryFactory.java
 * Author: Ryan Sellar
 * Date: Nov. 09 2017
 */ 
 
import java.io.File;
 
/* The entry factory is designed as a simple input output class. It takes as its input 
   the path to a file and builds an entry object out of the file's data. The new entry 
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
}
