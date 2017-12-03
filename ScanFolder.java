import java.io.File;
import java.util.*;


public class ScanFolder {
    public static File folder = new File("/Users/paigehanssen/Desktop/PicSorterTest");  //pathname is hardcoded rn, won't be later.
    static String temp = "";

    public static void main(String[] args) {
        System.out.println("Scanning Files...");
        System.out.println("Reading files under the folder "+ folder.getPath());
        listFilesForFolder(folder);
    }

    public static ArrayList<String> listFilesForFolder(final File folder) {
        ArrayList<String> names = new ArrayList<String>();
        for (final File fileEntry : folder.listFiles()) {
            //System.out.println(fileEntry);
            if (fileEntry.isDirectory()) {
                System.out.println("Reading files under the folder "+folder.getAbsolutePath());
                listFilesForFolder(fileEntry);
            } else {
                if (fileEntry.isFile()) {
                    temp = fileEntry.getName();
                    //filter files by type
                    //if ((temp.substring(temp.lastIndexOf('.') + 1, temp.length()).toLowerCase()).equals("mov"))
                        //System.out.println("File: " + fileEntry.getName());
                    names.add(temp);
                }

            }
        }
        return names;
    }
}
