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

public class HomePanel extends JPanel {
    JPanel openPanel;
    JPanel duplicatesPanel;
    JButton openFolder;
    GridLayout groupLayout;

    public HomePanel(JFrame frame) {        
        setLayout(new BorderLayout());
        setBackground(Color.lightGray);
        
        openPanel = new JPanel();
        openFolder = new JButton("Open folder");
        groupLayout = new GridLayout(0, 5);  //algorithm to take groups number and display based on that number
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        openPanel.setLayout(new GridBagLayout());
        openPanel.setBackground(Color.lightGray);
        openPanel.setBorder(BorderFactory.createMatteBorder(0,0,1,0,new Color(150, 150, 150)));
        openPanel.setPreferredSize(new Dimension(2000, 100));
        openPanel.add(openFolder, gbc);

        openFolder.setFocusPainted(false);
        openFolder.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                openFileChooser(openFolder, frame);
            }
        });

        add(openPanel, BorderLayout.PAGE_START);
    }

    public void openFileChooser(JButton button, JFrame frame) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); //select only folders, not individual files
        int result = fileChooser.showOpenDialog(button);
        
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            GenerateDuplicates duplicatePanel = new GenerateDuplicates();
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
            System.out.println(duplicates);
            if(duplicates.size() >= 1) {
                duplicatesPanel = duplicatePanel.displayDuplicates(duplicates);
                duplicatesPanel.setLayout(groupLayout);
                duplicatesPanel.setAlignmentY(Component.TOP_ALIGNMENT);
                duplicatesPanel.setBackground(Color.lightGray);
                add(duplicatesPanel, BorderLayout.CENTER);
                repaint();
                revalidate();
            } else {
                JOptionPane.showMessageDialog(frame,"No duplicate files found.");
            }
        }
    }
}
