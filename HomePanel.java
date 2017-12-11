import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.File; 
import java.util.ArrayList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HomePanel extends JPanel {
    JPanel updatedPanel = new JPanel();;
    JButton updatedButton;

    public HomePanel() {
        JButton openFolder = new JButton("Open folder");
        add(openFolder);
        openFolder.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                //open "browse directory"
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
                //select only folders, not individual files
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int result = fileChooser.showOpenDialog(openFolder);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    // DuplicateSetsPanel duplicatePanel = new DuplicateSetsPanel();
                    SelectDuplicate duplicatePanel = new SelectDuplicate();
                    EntryFactory factory = new EntryFactory();
                    HMap<Long, Entry> sizeMap = new HMap<Long, Entry>();
                    ArrayList<ArrayList<Entry>> duplicates = new ArrayList<ArrayList<Entry>>();
                    ArrayList<String> fileLocations = ScanFolder.listFilesForFolder(selectedFile);

                    for(String name : fileLocations){
                        Entry entry = factory.buildEntry(name);
                        sizeMap.insert(entry.getFileSize(), entry);
                    }
                    for(Long key : sizeMap.getKeys()){
                        if(sizeMap.get(key).size() > 1){
                            duplicates.add(sizeMap.get(key));
                        }
                    }
                    updatedPanel = duplicatePanel.displayDuplicates(duplicates);
                    System.out.println(duplicates);
                    updatedButton = new JButton("test");
                    add(updatedPanel);
                    repaint();
                    revalidate();
                }
            }
        });
    }
}