/*
	ScanFolder: Scans selected folder for photos, adds photo pathname to arraylist
	Author: Paige Hanssen
 */
import java.io.File;
import java.util.*;

public class ScanFolder {
    static String folderName = "";
    static File folder;
    static String temp = "";

    //constructor
    public ScanFolder(String selectedFolder) {
        folderName = selectedFolder;
        folder = new File(folderName);
    }

    //go through folers and find ONLY picture files, add file name to arraylist of strings
    public static ArrayList<String> listFilesForFolder(final File folder) {
        ArrayList<String> paths = new ArrayList<String>();
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                for(String path : listFilesForFolder(fileEntry)){
                    paths.add(path);
                }
            } else {
                if (fileEntry.isFile()) {
                    temp = fileEntry.getName();
                    //filter files by type
                    if ((temp.substring(temp.lastIndexOf('.') + 1, temp.length()).toLowerCase()).equals("jpg") || (temp.substring(temp.lastIndexOf('.') + 1, temp.length()).toLowerCase()).equals("jpeg") || (temp.substring(temp.lastIndexOf('.') + 1, temp.length()).toLowerCase()).equals("png"))
                        paths.add(fileEntry.getAbsolutePath());
                }
            }
        }
        return paths;
    }
}
