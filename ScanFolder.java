import java.io.File;


public class ScanFolder {
    static String temp = "";

    public static void main(String[] args) {
        System.out.println("Scanning Files...");
        System.out.println("Reading files under the folder "+ folder.getPath());
        listFilesForFolder(folder);
    }

    public static ArrayList<String> listFilesForFolder(final File folder) {
        ArrayList<String> paths = new ArrayList<String>();
        for (final File fileEntry : folder.listFiles()) {
            //System.out.println(fileEntry);
            if (fileEntry.isDirectory()) {
                System.out.println("Reading files under the folder "+folder.getAbsolutePath());
                for(String path : listFilesForFolder(fileEntry)){
                    paths.add(path);   
                }
            } else {
                if (fileEntry.isFile()) {
                    temp = fileEntry.getName();
                    //filter files by type
                    //if ((temp.substring(temp.lastIndexOf('.') + 1, temp.length()).toLowerCase()).equals("mov"))
                        //System.out.println("File: " + fileEntry.getName());
                    paths.add(fileEntry);
                }
            }
        }
        return paths;
    }
}
