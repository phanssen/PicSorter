/**
 * File: Selection Array
 * Author: Ryan Sellar
 * Date: Nov. 18th 2018
 */


import java.io.File;
import java.nio.file.*;
import java.util.ArrayList;
import java.io.IOException;

//No longer necessary due to refactoring
//import com.sun.jna.platform.FileUtils;
// import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
 
/* An extension of an Array List designed for holding entries marked for deletion 
   Its primary purpose should only be to delete files or otherwise move them 
   to a place where the user can decide what to do with them  */
public class SelectionArray extends ArrayList<Entry>{
	
	//Deletes all entries in the table
 	public void deleteAll(){
		for (Entry entry : this){
			try { 
				Files.delete(Paths.get(entry.fileLocation));
			} catch (IOException e){
				System.out.println(e);
			}
			
		}
	} 

	//This method moves all entries to the trash using file-utils library
	//It was cut during refactoring to reduce the number of choices given to the user
	//i.e. most users would be confused in having to choose between delete and trash
/*  	public void trashAll(){
		FileUtils fileUtils = FileUtils.getInstance();
		if (fileUtils.hasTrash()){
			for (Entry entry : this){
				fileUtils.moveToTrash(entry.fileLocation);
			}
		}
	}  */
	
	
	//Moves all the entries in the table to the indicated filepath which should be a directory
	public void moveAll(String filepath){
		File target = new File(filepath);
		target.mkdirs();
		for (Entry entry: this){
			File e  = new File (entry.fileLocation);
			e.renameTo(new File (target.getPath() + "/" + entry.fileName));
			e.delete();
		}
	}
}
