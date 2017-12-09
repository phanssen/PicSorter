/*
	ScanFolder: Scans selected folder for photos
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

    // public void scan() {
    //     System.out.println("Scanning Files...");
    //     System.out.println("Reading files under the folder "+ folder.getPath());
    //     listFilesForFolder(folder);
    // }

    public static ArrayList<String> listFilesForFolder(final File folder) {
        ArrayList<String> paths = new ArrayList<String>();
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                //System.out.println("Reading files under the folder "+folder.getAbsolutePath());
                for(String path : listFilesForFolder(fileEntry)){
                    paths.add(path);   
                }
            } else {
                if (fileEntry.isFile()) {
                    temp = fileEntry.getName();
                    //filter files by type
                    //if ((temp.substring(temp.lastIndexOf('.') + 1, temp.length()).toLowerCase()).equals("mov"))
                    System.out.println(fileEntry.getName());
                    paths.add(fileEntry.getAbsolutePath());
                }
            }
        }
        return paths;
    }

    //main method
    public static void main(String[] args) {
        System.out.println("Scanning Files...");
        System.out.println("Reading files under the folder "+ folder.getPath());
        listFilesForFolder(folder);
    }
}
