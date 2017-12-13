/**
 * File: Entry.java
 * Author: Ryan Sellar
 * Date: Nov. 09 2017
 */
 
/* This class contains all necessary data extracted from a given image file and saved for
   for later use. This data is: file location, file name, file size, file dimensions, file
   format. It's only methods should be to modify or set data and to return data.        */


public class Entry {
	
	public String fileLocation;
	public String fileName;	
	public long fileSize;		//how should size be measured? bytes? kilobytes?
	
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
	
	//Test function
	public static void main(String[] args){
		Entry testEntry = new Entry("C:\\users\\John\\Pictures", "example.jpg", 100000);
		System.out.println(testEntry);
	}
}
