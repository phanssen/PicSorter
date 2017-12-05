/**
 * File: EntryFactory.java
 * Author: Ryan Sellar
 * Date: Nov. 09 2017
 */ 
 
import java.io.File;
// import java.io.IOException;
// import java.awt.Image;
// import javax.imageio.ImageIO;
//import org.apache.commons.io.FilenameUtils;
 
/* The entry factory is designed as a simple input output class. It takes as its input 
   the path to a file and build an entry object out of the file's data. The new entry 
   object is then returned for further use. */ 
public class EntryFactory{
	
	//Constructor
	public EntryFactory(){}
	
	//Build Entry
	public Entry buildEntry(String fileLocation){
		File file = null;
// 		Image image = null;
		
// 		try {								//try-catch for IO exception
			file = new File(fileLocation);
// 			image = ImageIO.read(file);
// 		} catch (IOException e){
// 			System.out.println("Error: "+e);
// 			return null;
// 		}
		
// 		if (file == null || image == null) return null;	//In case assignment fails
		
		Entry newEntry = new Entry();
		newEntry.setFileLocation(fileLocation);
		newEntry.setFileName(file.getName());
		//newEntry.setFileFormat(FilenameUtils.getExtension(newEntry.getFileName()));
		newEntry.setFileSize(file.length());
// 		newEntry.setFileWidth(image.getWidth(null));
// 		newEntry.setFileHeight(image.getHeight(null));
				
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
