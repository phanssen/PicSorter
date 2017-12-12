/**
 * File: Entry.java
 * Author: Ryan Sellar
 * Date: Nov. 09 2017
 */
 
/* This class contains all necessary data extracted from a given image file and saved for
   for later use. This data is: file location, file name, file size, dHash
   This is simply a holder class so we don't need any methods to get or set. The fields
   are made public. Think of it as a structure from C or C++   							*/

import java.math.BigInteger;
   
public class Entry {
	
	public String fileLocation;
	public String fileName;	
	public long fileSize;		//in bytes
	public BigInteger fileDHash;		//128-bit number created from dHash
	
	//Empty Constructor
	public Entry(){
		fileLocation = "";
		fileName = "";
		fileSize = 0;
		fileDHash = new BigInteger("0");
	}
	
	//Constructor
	public Entry(String location, String name, long size, BigInteger dHash){
		fileLocation = location;
		fileName = name;
		fileSize = size;
		fileDHash = dHash;
	}
	
	//String conversion prints out entry data
	public String toString(){
		String entryData = "\nFile location: "+fileLocation+"\nFilename: "+fileName+"\nFile size: "+fileSize+"\nFile dHash: "+fileDHash+"\n";
		return entryData;
	}
	
	//Test function
/* 	public static void main(String[] args){
		Entry testEntry = new Entry("C:\\users\\John\\Pictures", "example.jpg", 100000,);
		System.out.println(testEntry);
	} */
}
