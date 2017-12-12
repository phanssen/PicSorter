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
	public String fileFormat;
	
	public long fileSize;		//how should size be measured? bytes? kilobytes?
	private int fileWidth;		//X dimension
	private int fileHeight;		//Y dimension
	
	//Empty Constructor
	public Entry(){
		fileLocation = "";
		fileName = "";
		fileFormat = "";
		
		fileSize = 0;
		fileWidth = 0;
		fileHeight = 0;
	}
	
	//Constructor
	public Entry(String location, String name, String format, int size, int X, int Y){
		fileLocation = location;
		fileName = name;
		fileFormat = format;
		
		fileSize = size;
		fileWidth = X;
		fileHeight = Y;
	}
	
	//Accessor functions
	public String getFileLocation(){
		return fileLocation;
	}
	
	public String getFileName(){
		return fileName;
	}
	
/* 	public String getFileFormat(){
		return fileFormat;
	} */
	
	public long getFileSize(){
		return fileSize;
	}
	
	public int getFileWidth(){
		return fileWidth;
	}
	
	public int getFileHeight(){
		return fileHeight;
	}
	
	//Modification functions
	public void setFileLocation(String location){
		fileLocation = location;
	}
	
	public void setFileName(String name){
		fileName = name;
	}
	
/* 	public void setFileFormat(String format){
		fileFormat = format;
	} */
	
	public void setFileSize(long size){
		fileSize = size;
	}
	
	public void setFileWidth(int X){
		fileWidth = X;
	}
	
	public void setFileHeight(int Y){
		fileHeight = Y;
	}
	
	//String conversion prints out entry data
	public String toString(){
		String entryData = "\nFile location: " + fileLocation + "\nFilename: " + fileName + "\nFile format: " + fileFormat + "\nFile size: " + fileSize + "\nFile dimensions: " + fileWidth + " x " + fileHeight + "\n";
		return entryData;
	}
	
	//Test function
	public static void main(String[] args){
		Entry testEntry = new Entry("C:\\users\\John\\Pictures", "example.jpg", "jpg", 100000, 500, 750);
		System.out.println(testEntry);
	}
}