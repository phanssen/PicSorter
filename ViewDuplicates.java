import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.File; 
import java.util.ArrayList;

public class ViewDuplicates extends JPanel {
    public static JPanel newPanel(File selectedFile) {
        SelectDuplicate testWindow = new SelectDuplicate();
		EntryFactory factory = new EntryFactory();
        HMap<Long, Entry> sizeMap = new HMap<Long, Entry>();
        ArrayList<ArrayList<Entry>> duplicates = new ArrayList<ArrayList<Entry>>();
        ArrayList<String> fileLocations = ScanFolder.listFilesForFolder(selectedFile);

        for(String name : fileLocations){
            Entry entry = factory.buildEntry(name);
            sizeMap.insert(entry.getFileSize(), entry);
        }
        for(Long key : sizeMap.getKeys()){
            //System.out.println(sizeMap.get(key));
            if(sizeMap.get(key).size() > 1){
                duplicates.add(sizeMap.get(key));
            }
        }

        JPanel panel = testWindow.displayDuplicates(duplicates);
        return panel;
    }

    public static JPanel browse(JFileChooser fileChooser){
        // //open "browse directory"
		// JFileChooser fileChooser = new JFileChooser();
		// fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		// //select only folders, not individual files
		// fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		// int result = fileChooser.showOpenDialog(label);
		// if (result == JFileChooser.APPROVE_OPTION) {
            //File selectedFile = fileChooser.getSelectedFile();
            //create new panel
            JPanel displayPanel = newPanel(selectedFile);
            return displayPanel;
    }
}


// SelectDuplicate testWindow = new SelectDuplicate();
// EntryFactory factory = new EntryFactory();
// HMap<Long, Entry> sizeMap = new HMap<Long, Entry>();
// ArrayList<ArrayList<Entry>> duplicates = new ArrayList<ArrayList<Entry>>();
// File selectedFile = fileChooser.getSelectedFile();
// System.out.println("Selected file: " + selectedFile.getAbsolutePath());
// ArrayList<String> fileLocations = ScanFolder.listFilesForFolder(selectedFile);
// //Create an entry for each duplicate, build file size hashmap of all entries by file size
// for(String name : fileLocations){
// 	Entry entry = factory.buildEntry(name);
// 	sizeMap.insert(entry.getFileSize(), entry);
// }
// //fill arrayList of arrayLists with each set of duplicates 
// for(Long key : sizeMap.getKeys()){
// 	System.out.println(sizeMap.get(key));
// 	if(sizeMap.get(key).size() > 1){
// 		duplicates.add(sizeMap.get(key));
// 	}
// }

//causes a new JFrame to pop up with duplicates - create JPanel and make visible