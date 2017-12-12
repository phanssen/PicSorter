/*
 * GenerateDuplicates class: Generates, stores, and displays a panel with an image from each duplicate set
 * Author: Jacob Tower
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import java.io.File;
import java.util.ArrayList;

public class GenerateDuplicates extends JFrame{

	private static final long serialVersionUID = -4983971561262660902L;
	//automatically computed serialVersionUID
	
	public static void main(String[] args) {
		test();
	}

	//stores the panel that holds all the duplicate sets
	private JPanel duplicatePanel;


	//This method displays one image for each set of duplicates in a JPanel within the window.
	//Each image displays the entire set of duplicates on click
	public JPanel displayDuplicates(ArrayList<ArrayList<Entry>> duplicates){
//		initializes window
		this.setSize(800, 600);
		this.setLocation(200, 50);
		
		JPanel holder = new JPanel();
		
//		create one JLabel for each set of duplicates, add it to the panel
		for(final ArrayList<Entry> duplicateGroup : duplicates){
//		Resize image to fit in grid
			ImageIcon smallIcon = scaleImage(duplicateGroup.get(0).getFileLocation());
			
//		Add image to JLabel, enable expansion on click
			JLabel button = new JLabel(smallIcon);
			button.addMouseListener(new MouseAdapter() {
				  @Override
//				  on click the JLabel calls expand to display the set of duplicates
				  public void mouseClicked(MouseEvent e) {
					 clear();
				     expand(duplicateGroup, button);
				     validate();
				     repaint();
				     setVisible(true);
				  }
				});
			holder.add(button);
		}
		
		this.add(holder);
		duplicatePanel = holder;
		return duplicatePanel;
	}
	
	//Scales specified image
	public static ImageIcon scaleImage(String filelocation) {
		ImageIcon smallIcon = new ImageIcon(filelocation); // load the image to a imageIcon
		Image image = smallIcon.getImage(); // transform it 
		Image newimg = image.getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		smallIcon = new ImageIcon(newimg);
		return smallIcon;
	}
	
	//clears all containers within the window
	public void clear(){
		this.getContentPane().removeAll();
	}
	
//	creates a new panel containing each duplicate image from the selected duplicate set
	public void expand(ArrayList<Entry> duplicates, JLabel selectedSet){
		JPanel allDuplicates = new JPanel();
//		load each individual duplicate image, display in grid
		for (Entry duplicateFile : duplicates){
			ImageIcon smallIcon = scaleImage(duplicateFile.getFileLocation());
			JLabel picture = new JLabel(smallIcon);
			picture.addMouseListener(new MouseAdapter() {
				  @Override
//				  on click the JLabel calls Select method to select the duplicate file
				  public void mouseClicked(MouseEvent e) {
					  SelectDuplicates.Select(duplicateFile, picture);
				  }
				});
			allDuplicates.add(picture);
		}
		
		//adds a button that moves any selected photos to a selected folder
		JButton move = new JButton("Move Duplicate Photos");
		move.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(SelectDuplicates.hasSelections()) {
					JFileChooser fileChooser = new JFileChooser();
					fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
					//select only folders, not individual files
					fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					int result = fileChooser.showOpenDialog(move);
					if (result == JFileChooser.APPROVE_OPTION) {
						File selectedFile = fileChooser.getSelectedFile();
						SelectDuplicates.getSelections().moveAll(selectedFile.getAbsolutePath());
						for(JLabel duplicate : SelectDuplicates.getImages()) {
							allDuplicates.remove(duplicate);
						}
						if(duplicates.size() == SelectDuplicates.getSelections().size()) {
							JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(move);
							topFrame.setVisible(false);
							SelectDuplicates.clearSelections();
							removeSet(selectedSet);
							return;
	                			}
	                			SelectDuplicates.clearSelections();
						repaint();
						revalidate();
						setVisible(true);
					}
				}
			}
		});
		allDuplicates.add(move);
		
		//adds a button that deletes the selected photos
		JButton deleteButton = new JButton("Move Selected Photos to Trash");
		deleteButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(SelectDuplicates.hasSelections()) {
					String message = "Are you sure you want to permanently delete these images?";
					String title = "Warning";
					int response = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
					
					if(response == JOptionPane.YES_OPTION) {
		            		SelectDuplicates.getSelections().deleteAll();
		            		for(JLabel duplicate : SelectDuplicates.getImages()) {
		            			allDuplicates.remove(duplicate);
		            		}
		            		if(duplicates.size() == SelectDuplicates.getSelections().size()) {
	                			JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(move);
	                			topFrame.setVisible(false);
	                			SelectDuplicates.clearSelections();
	                			removeSet(selectedSet);
	                			return;
	                		}
	                		SelectDuplicates.clearSelections();
		            		repaint();
		            		revalidate();
		            		setVisible(true);
					}
				}
			}
		});
		allDuplicates.add(deleteButton);
		this.add(allDuplicates);
	}
	
//	removes the JLabel representation of a set from the home screen
	public static void removeSet(JLabel set) {
		JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(set);
		duplicatePanel.remove(set);
		topFrame.revalidate();
		topFrame.repaint();
		topFrame.setVisible(true);
	}
	
//	displays previously generated duplicatePanel
	public void showDuplicates(){
		this.clear();
		this.add(duplicatePanel);
		this.validate();
		this.setVisible(true);
	}
	
// Test function for the GenerateDuplicates class.
	private static void test(){
//		create all classes needed to parse specified folder for duplicates
		GenerateDuplicates testWindow = new GenerateDuplicates();
		EntryFactory factory = new EntryFactory();
		HMap<Long, Entry> sizeMap = new HMap<Long, Entry>();
		ArrayList<ArrayList<Entry>> duplicates = new ArrayList<ArrayList<Entry>>();
		File folder = new File("/Users/paigehanssen/Desktop/PicSorterTest");
		ArrayList<String> fileLocations = ScanFolder.listFilesForFolder(folder);
		
//		Create an entry for each duplicate, build file size hashmap of all entries by file size
		for(String name : fileLocations){
			Entry e = factory.buildEntry(name);
			sizeMap.insert(e.getFileSize(), e);
		}
		
		//	fill arrayList of arrayLists with each set of duplicates 
		for(Long key : sizeMap.getKeys()){
			System.out.println(sizeMap.get(key));
			if(sizeMap.get(key).size() > 1){
				duplicates.add(sizeMap.get(key));
			}
		}
	}
}
