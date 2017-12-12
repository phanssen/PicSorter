/**
 * File: This file is designed to test the capabilities of the hash table
 * as well as the time requirements of the hash map 
 * Author: Ryan Sellar
 * Date: Dec. 11th 2017
 */
 
import java.io.*;
import java.util.ArrayList;

class HashTest{
	
	private EntryFactory factory;
	private HMap<Long, Entry> hmap;

	public HashTest(){
		factory = new EntryFactory();
		hmap = new HMap<Long, Entry>();
	}
	
	//returns the time it takes to load all images into the hash map in nanoseconds
	public long testLoadTime(String filepath){
		File folder = new File(filepath);
		long start = 0;
		long end = 0;
		
		ArrayList<String> files = ScanFolder.listFilesForFolder(folder);
				
		start = System.nanoTime();
		
		for (String file_name : files){
			Entry entry = factory.buildEntry(file_name);
			hmap.insert(entry.fileSize, entry);
		}

		end = System.nanoTime();
		
		return end - start;		
	}
	
	public static void main(String args[]){
		HashTest hash_test = new HashTest();
		
		long time_result_nanoseconds = hash_test.testLoadTime(args[0]);
		double time_result_seconds = (double) time_result_nanoseconds / 1000000000;
		
		System.out.println("Total time taken to load the files: " + time_result_seconds + "\n");		
		
	}
	
}
