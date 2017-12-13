/*
	HomePanel: Panel that contains open folder button
                Updates UI to display duplicates
	Author: Paige Hanssen
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.File;
import java.awt.Color;
import java.util.ArrayList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// Class HomePanel contains two panels: one with the 
// "Open Folder" button and one with the duplicate groups
public class HomePanel extends JPanel {
    public JPanel duplicatesPanel;
    private JButton openFolder;
    private JScrollPane scrollPane;

    //constructor
    public HomePanel(JFrame frame) {        
        setLayout(new BorderLayout());
        setBackground(Color.lightGray);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        openFolder = new JButton("Open folder");

        OpenPanel openPanel = new OpenPanel();

        /* Refactored openPanel into private class (line 90) */
        // openPanel = new JPanel();
        // openPanel.setLayout(new GridBagLayout());
        // openPanel.setBackground(Color.lightGray);
        // openPanel.setBorder(BorderFactory.createMatteBorder(0,0,1,0,new Color(150, 150, 150)));
        // openPanel.setPreferredSize(new Dimension(2000, 100));

        openFolder.setFocusPainted(false);
        openFolder.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                openFileChooser(openFolder, frame);
            }
        });

        openPanel.add(openFolder, gbc);
        add(openPanel, BorderLayout.PAGE_START);
    }

    //open browse directory, looks for chosen folder, creates everything for duplicate groups, adds to a panel
    public void openFileChooser(JButton button, JFrame frame) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); //select only folders, not individual files
        int result = fileChooser.showOpenDialog(button);

        if (result == JFileChooser.APPROVE_OPTION) {
            //construct duplicate groups
            File selectedFile = fileChooser.getSelectedFile();
            GenerateDuplicates generatedPanel = new GenerateDuplicates();
            EntryFactory factory = new EntryFactory();
            HMap<Long, Entry> sizeMap = new HMap<Long, Entry>();
            ArrayList<ArrayList<Entry>> duplicates = new ArrayList<ArrayList<Entry>>();
            ArrayList<String> fileLocations = ScanFolder.listFilesForFolder(selectedFile);

            for(String name : fileLocations){
                Entry entry = factory.buildEntry(name);
                sizeMap.insert(entry.fileSize, entry);
            }
            for(Long key : sizeMap.getKeys()){
                if(sizeMap.get(key).size() > 1){
                    duplicates.add(sizeMap.get(key));
                }
            }

            //if duplicate list size is less than one, no duplicates message, otherwise, create panel with duplicates
            if(duplicates.size() >= 1) {
                duplicatesPanel = generatedPanel.displayDuplicates(duplicates);
                duplicatesPanel.setLayout(new GridLayout(0,5,0,10));
                duplicatesPanel.setBackground(Color.lightGray);
                add(duplicatesPanel, BorderLayout.CENTER);
                scrollPane = new JScrollPane(duplicatesPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                add(scrollPane, BorderLayout.CENTER);
                repaint();
                revalidate();
            } else {
                JOptionPane.showMessageDialog(frame,"No duplicate files found.");
            }
        }
    }

    //OpenPanel class to set up panel for opening folder
    private class OpenPanel extends JPanel {
        public OpenPanel() {
            setLayout(new GridBagLayout());
            setBackground(Color.lightGray);
            setBorder(BorderFactory.createMatteBorder(0,0,1,0,new Color(150, 150, 150)));
            setPreferredSize(new Dimension(2000, 100));
        }
    }
}