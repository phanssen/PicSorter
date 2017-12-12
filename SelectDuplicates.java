/*
 * SelectDuplicates class: used to select duplicate images from GUI to be moved or deleted
 * Author: Jacob Tower
 */

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;
public class SelectDuplicates {
	private static SelectionArray selections = new SelectionArray();
	private static ArrayList<JLabel> selectedImages = new ArrayList<JLabel>();
	
	public static void Select(Entry selection, JLabel image) {
		if(selections.contains(selection)) {
			image.setBorder(null);
			selections.remove(selection);
			selectedImages.remove(image);
		}
		else {
		image.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		selections.add(selection);
		selectedImages.add(image);
		}
	}
	
	public static SelectionArray getSelections() {
		return selections;
	}
	
	public static ArrayList<JLabel> getImages(){
		return selectedImages;
	}
	
	public static boolean hasSelections() {
		if (selections.size() > 0){
			return true;
		}
		else return false;
	}
	
	public static void clearSelections() {
		selections.clear();
		selectedImages.clear();
	}
	
}
