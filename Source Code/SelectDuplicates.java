/*
 * SelectDuplicates class: used to select duplicate images from GUI to be moved or deleted
 * Author: Jacob Tower
 */

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class SelectDuplicates {
	//These track selected images and their JLabels
	private static SelectionArray selections = new SelectionArray();
	private static ArrayList<JLabel> selectedImages = new ArrayList<JLabel>();
	
	//selects or deselects a duplicate when it is clicked on
	public static void Select(Entry selection, JLabel image) {
		//deselect case
		if(selections.contains(selection)) {
			image.setBorder(null);
			selections.remove(selection);
			selectedImages.remove(image);
		}
		//select case
		else {
		image.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		selections.add(selection);
		selectedImages.add(image);
		}
	}
	
	//return selected files
	public static SelectionArray getSelections() {
		return selections;
	}
	
	//return selected images
	public static ArrayList<JLabel> getImages(){
		return selectedImages;
	}
	
	//return whether anything is currently selected
	public static boolean hasSelections() {
		if (selections.size() > 0){
			return true;
		}
		else return false;
	}
	
	//clear all selections
	public static void clearSelections() {
		selections.clear();
		selectedImages.clear();
	}
}
