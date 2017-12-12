/**
 * File: Entry.java
 * Author: Ryan Sellar
 * Date: Nov. 09 2017
 */
 
/* This class contains all necessary data extracted from a given image file and saved for
   for later use. This data is: file location, file name, file size. It functions like a 
   structure (in C) so the fields are public, there are no getters or setters. */
public class Entry {
	
	public String fileLocation;
	public String fileName;	
	public long fileSize;
	
	//Empty Constructor
	public Entry(){
		fileLocation = "";
		fileName = "";		
		fileSize = 0;
	}
	
	//Constructor
	public Entry(String location, String name, int size){
		fileLocation = location;
		fileName = name;		
		fileSize = size;
	}
	
	//String conversion prints out entry data
	public String toString(){
		String entryData = "\nFile location: " + fileLocation + "\nFilename: " + fileName + "\nFile size: " + fileSize + "\n";
		return entryData;
	}
}
