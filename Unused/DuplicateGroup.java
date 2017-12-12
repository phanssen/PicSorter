/*
 * DuplicateGroup: JLabel representation of group of duplicates
 * 					expand method is called onClick from a DisplayDuplicateSets Panel
 * 
 * Author: Jacob Tower
 */

import javax.swing.*;
import java.util.ArrayList;

public class DuplicateGroup extends JLabel{

	private static final long serialVersionUID = 1L;
	private ArrayList<Entry> group;
	
	public DuplicateGroup(ImageIcon image, ArrayList<Entry> set) {
		this.group = set;
	}

	public JPanel expand(){
		JPanel duplicates = new JPanel();
		for (Entry duplicate : group) {
			JLabel duplicateImage = new JLabel(DuplicateSetsPanel.scaleImage(duplicate.getFileLocation()));
			duplicates.add(duplicateImage);
		}
		return duplicates;
	}
	
	public static void main(String[] args) {	
	}
}
