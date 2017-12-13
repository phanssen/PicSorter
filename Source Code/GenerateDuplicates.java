/*
 * GenerateDuplicates class: Generates, stores, and displays a panel with an image from each duplicate set
 * Author: Jacob Tower
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.*;
import java.util.ArrayList;

public class GenerateDuplicates extends JFrame{

	private static final long serialVersionUID = -4983971561262660902L;
	//automatically computed serialVersionUID

	private static JPanel duplicatePanel;


	//Displays one image for each set of duplicates in a JPanel within the window.
	public JPanel displayDuplicates(ArrayList<ArrayList<Entry>> duplicates){
		//initializes window 
		this.setSize(800, 600);
		this.setLocation(200, 50);
		
		JPanel holder = new JPanel();
		
		//create one JLabel for each set of duplicates, add it to the panel
		for(final ArrayList<Entry> duplicateGroup : duplicates){
			//Resize image to fit in grid
			ImageIcon smallIcon = scaleImage(duplicateGroup.get(0).fileLocation);
			
			//Add image to JLabel, enable expansion on click
			JLabel button = new JLabel(smallIcon);
			button.addMouseListener(new MouseAdapter() {
				  @Override
				  //on click the JLabel calls expand to display the set of duplicates
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
		ImageIcon smallIcon = new ImageIcon(filelocation); 
		Image image = smallIcon.getImage(); 
		Image newimg = image.getScaledInstance(150, 150,  java.awt.Image.SCALE_SMOOTH);
		smallIcon = new ImageIcon(newimg);
		return smallIcon;
	}
	
	
	//clears all containers within the window
	public void clear(){
		this.getContentPane().removeAll();
	}
	
	
/*	
Refactored code by moving the implementation for the moveButton and deleteButton to private classes
instead of being within the expand method - Original code is commented out at the end of the expand method below	
*/
	
	
	//creates a new panel containing each duplicate image from the selected duplicate set
	public void expand(ArrayList<Entry> duplicates, JLabel selectedSet){
		JPanel allDuplicates = new JPanel();		
		for (Entry duplicateFile : duplicates){
			ImageIcon smallIcon = scaleImage(duplicateFile.fileLocation);
			JLabel picture = new JLabel(smallIcon);
			picture.addMouseListener(new MouseAdapter() {
				  @Override
				  //on click the JLabel calls Select method to select the duplicate file
				  public void mouseClicked(MouseEvent e) {
					  SelectDuplicates.Select(duplicateFile, picture);
				  }
				});
			allDuplicates.add(picture);
		}
		
		//adds a button that moves any selected photos to a selected folder
		moveButton move = new moveButton("Move Selected Photos");
		move.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				move.Move(allDuplicates, duplicates, selectedSet);
			}
		});
		allDuplicates.add(move);
		
		//adds a button that deletes the selected photos
		deleteButton delete = new deleteButton("Delete Selected Photos");
		delete.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				delete.Delete(allDuplicates, duplicates, selectedSet);
			}
		});
		allDuplicates.add(delete);
		this.add(allDuplicates);
		
		//Adds a window listener to clear the selectionArray if the duplicate window is closed
		JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(allDuplicates);
		topFrame.addWindowListener(new WindowAdapter()
		{
		    public void windowClosing(WindowEvent e)
		    {
		        SelectDuplicates.clearSelections();
		    }
		});
		
/*		
This is the code prior to refactoring:
*/
		
		
//		JButton move = new JButton("Move Selected Photos");
//		move.addActionListener(new ActionListener(){
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				if(SelectDuplicates.hasSelections()) {
//					JFileChooser fileChooser = new JFileChooser();
//	                fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
//	                //select only folders, not individual files
//	                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//	                int result = fileChooser.showOpenDialog(move);
//	                if (result == JFileChooser.APPROVE_OPTION) {
//	                		File selectedFile = fileChooser.getSelectedFile();
//	                		SelectDuplicates.getSelections().moveAll(selectedFile.getAbsolutePath());
//	                		for(JLabel duplicate : SelectDuplicates.getImages()) {
//	                			allDuplicates.remove(duplicate);
//	                		}
//	                		if(duplicates.size() == SelectDuplicates.getSelections().size()) {
//	                			JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(move);
//	                			topFrame.setVisible(false);
//	                			SelectDuplicates.clearSelections();
//	                			removeSet(selectedSet);
//	                			return;
//	                		}
//	                		SelectDuplicates.clearSelections();
//	                		repaint();
//	                		revalidate();
//	                		setVisible(true);
//	                }
//				}
//			}
//		});
//		allDuplicates.add(move);
//		
//		//adds a button that deletes the selected photos
//		JButton deleteButton = new JButton("Move Selected Photos to Trash");
//		deleteButton.addActionListener(new ActionListener(){
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				if(SelectDuplicates.hasSelections()) {
//					String message = "Are you sure you want to permanently delete these images?";
//					String title = "Warning";
//					int response = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
//					
//					if(response == JOptionPane.YES_OPTION) {
//		            		SelectDuplicates.getSelections().deleteAll();
//		            		for(JLabel duplicate : SelectDuplicates.getImages()) {
//		            			allDuplicates.remove(duplicate);
//		            		}
//		            		if(duplicates.size() == SelectDuplicates.getSelections().size()) {
//	                			JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(move);
//	                			topFrame.setVisible(false);
//	                			SelectDuplicates.clearSelections();
//	                			removeSet(selectedSet);
//	                			return;
//	                		}
//	                		SelectDuplicates.clearSelections();
//		            		repaint();
//		            		revalidate();
//		            		setVisible(true);
//					}
//				}
//			}
//		});
//		allDuplicates.add(deleteButton);
//		this.add(allDuplicates);
	}
	
	//updates the duplicatePanel by removing the set if all images are moved/deleted
	public static void removeSet(JLabel set) {
		JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(set);
		duplicatePanel.remove(set);
		topFrame.revalidate();
		topFrame.repaint();
		topFrame.setVisible(true);
	}
	
	//implements a button to move images
	private class moveButton extends JButton{
		
		moveButton(String arg){
			super(arg);
		}
		
		public void Move(JPanel allDuplicates, ArrayList<Entry> duplicates, JLabel selectedSet) {
			//check for case where nothing is selected
			if(SelectDuplicates.hasSelections()) {
				JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
                //select only folders, not individual files
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int result = fileChooser.showOpenDialog(this);
                if (result == JFileChooser.APPROVE_OPTION) {
                		File selectedFile = fileChooser.getSelectedFile();
                		SelectDuplicates.getSelections().moveAll(selectedFile.getAbsolutePath());
                		for(JLabel duplicate : SelectDuplicates.getImages()) {
                			allDuplicates.remove(duplicate);
                		}
                		//if all images are moved, close frame
                		if(duplicates.size() == SelectDuplicates.getSelections().size()) {
                			JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
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
	}
	
	//implements a button to delete images
	private class deleteButton extends JButton{
		
		deleteButton(String arg){
			super(arg);
		}
		
		//Delete selected images from duplicates
		public void Delete(JPanel allDuplicates, ArrayList<Entry> duplicates, JLabel selectedSet) {
			//check for case where nothing is selected
			if(SelectDuplicates.hasSelections()) {
				String message = "Are you sure you want to permanently delete these images?";
				String title = "Warning";
				int response = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
				//prompt user confirmation to delete
				if(response == JOptionPane.YES_OPTION) {
	            		SelectDuplicates.getSelections().deleteAll();
	            		for(JLabel duplicate : SelectDuplicates.getImages()) {
	            			allDuplicates.remove(duplicate);
	            		}
	            		//close frame if all images are deleted
	            		if(duplicates.size() == SelectDuplicates.getSelections().size()) {
                			JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
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
	}
}
