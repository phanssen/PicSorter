/*
 * DuplicateSetsPanel: Panel containing all sets of duplicates. 
 * 						User can generate, display, and hide panel.
 * 
 * Author: Jacob Tower
 */

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import java.util.ArrayList;

public class DuplicateSetsPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private static JPanel duplicatePanel;
	
	public void generateDuplicates(ArrayList<ArrayList<Entry>> duplicates) {
		duplicatePanel = new JPanel();
		for(final ArrayList<Entry> set : duplicates){
			ImageIcon smallIcon = scaleImage(set.get(0).getFileLocation());
			DuplicateGroup group = new DuplicateGroup(smallIcon, set);
			group.addMouseListener(new MouseAdapter() {
				  @Override
//				  Call DuplicateGroup.expand() to show all duplicate images
				  public void mouseClicked(MouseEvent e) {
					  hidePanel();
					  JPanel images = group.expand();
					  images.setVisible(true);
				  }
				});
//			System.out.println(duplicatePanel == null);
			duplicatePanel.add(group);
		}
	}
	
	public static ImageIcon scaleImage(String filelocation) {
		ImageIcon smallIcon = new ImageIcon(filelocation); // load the image to a imageIcon
		Image image = smallIcon.getImage(); // transform it 
		Image newimg = image.getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		smallIcon = new ImageIcon(newimg);
		return smallIcon;
	}
	
	public static void hidePanel() {
		duplicatePanel.setVisible(false);
	}
	
	public void ShowDuplicates() {
		duplicatePanel.setVisible(true);
	}
	
	public static void main(String[] args) {
	}
}
