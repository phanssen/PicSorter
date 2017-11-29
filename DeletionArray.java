/**
 * Deletion Array: stores duplicates for deletion
 * Author: Ryan Sellar
 * Date: Nov. 18th 2018
 */

//import com.sun.jna.platform.FileUtils;
import java.io.File;
import java.nio.file.*;
import java.util.ArrayList;
import java.io.IOException;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
 
/* An extension of an Array List designed for holding entries marked for deletion 
   Its primary purpose should only be to delete files or otherwise move them 
   to a place where the user can decide what to do with them  */
public class DeletionArray extends ArrayList<Entry>{
	
	//Deletes all entries in the table
 	public void deleteAll(){
		for (Entry entry : this){
			try { 
				Files.delete(Paths.get(entry.getFileLocation()));
			} catch (IOException e){
				System.out.println(e);
			}
			
		}
	} 
	
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
			File e  = new File (entry.getFileLocation());
			e.renameTo(new File (target.getPath() + "\\" + entry.getFileName()));
			e.delete();
		}
	} 
	
	public static void main(String[] args){
		DeletionArray delArr = new DeletionArray();
		EntryFactory factory = new EntryFactory();
		
		delArr.add(factory.buildEntry("C:\\Users\\metis\\Desktop\\TestFolder\\01.png"));
		delArr.add(factory.buildEntry("C:\\Users\\metis\\Desktop\\TestFolder\\02.png"));
		delArr.add(factory.buildEntry("C:\\Users\\metis\\Desktop\\TestFolder\\03.png"));
				
		System.out.println(delArr);
		
		delArr.moveAll("C:\\Users\\metis\\Desktop\\TestFolder\\InnerFolder");
		
		//delArr.deleteAll();
		
		System.out.println(delArr);		
	}
}