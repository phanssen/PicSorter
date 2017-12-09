/*
	Home: Home UI - user can upload photos and view duplicates
	Author: Paige Hanssen
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.File; 
import java.util.ArrayList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Home extends JFrame {
	private Panel duplicatePanel;

	//constructor
	public Home(String s) {
		//frame specifications
		setTitle(s);
		setSize(1000,800); //window size
		setLocation(100,200); //location where window opens
	
		//window listeners
		addWindowListener(new WindowAdapter() {
			//close window
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
	
	//create method to create a panel and labels
	public static void createPanel(String labelName, boolean clickable, JFrame frame) {
		JPanel panel = new JPanel(new FlowLayout());
		JLabel label = new JLabel(labelName);
		if(clickable == true) {
			label.addMouseListener(new MouseAdapter() {  
				public void mouseClicked(MouseEvent e) {
					//open "browse directory"
					JFileChooser fileChooser = new JFileChooser();
					fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
					//select only folders, not individual files
					fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					int result = fileChooser.showOpenDialog(label);
					if (result == JFileChooser.APPROVE_OPTION) {
						File selectedFile = fileChooser.getSelectedFile();
						DuplicateSetsPanel testWindow = new DuplicateSetsPanel();
						EntryFactory factory = new EntryFactory();
						HMap<Long, Entry> sizeMap = new HMap<Long, Entry>();
						ArrayList<ArrayList<Entry>> duplicates = new ArrayList<ArrayList<Entry>>();
						ArrayList<String> fileLocations = ScanFolder.listFilesForFolder(selectedFile);

						for(String name : fileLocations){
							Entry entry = factory.buildEntry(name);
							sizeMap.insert(entry.getFileSize(), entry);
						}
						for(Long key : sizeMap.getKeys()){
							System.out.println(sizeMap.get(key));
							if(sizeMap.get(key).size() > 1){
								duplicates.add(sizeMap.get(key));
							}
						}
						testWindow.ShowDuplicates();
						//JPanel view = testWindow.displayDuplicates(duplicates);

						//JPanel holder = ViewDuplicates.browse(fileChooser);
						// frame.getContentPane().removeAll();
						// frame.getContentPane().add(view);
						// System.out.println("Here");
					}
				}
			});
			panel.add(label);
			frame.add(panel);
			// panel.add(label);
			// return panel;
		} else {
			panel.add(label);
			frame.add(panel);
			// return panel;
		}
	}

	public static void main(String [] args) {
		JFrame frame = new Home("PicSorter");
		createPanel("Open folder", true, frame);
		// JPanel one = createPanel("Open folder", true, frame);
		// JPanel two = createPanel("viewDuplicates", false, frame);
		// JPanel three = createPanel("Pick up where you left off", false, frame);
		
		//add small panels to a main panel
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		//mainPanel.add(one);
		// mainPanel.add(two);
		// mainPanel.add(three);
		
		// //add main panel to frame, display
		//JFrame frame = new Home("PicSorter");
		//frame.add(mainPanel);
		frame.setVisible(true);
	}
}