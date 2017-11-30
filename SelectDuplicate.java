import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import java.io.File;
import java.util.ArrayList;

//	currently extends JFrame to create its own window - 
//	once integrated it will have to be able to run in window it is called from
public class SelectDuplicate extends JFrame{

	private static final long serialVersionUID = -4983971561262660902L;
	//automatically computed serialVersionUID
	
	public static void main(String[] args) {
		test();
	}

//	class variable storing the panel that holds all the duplicate sets once displayDuplicates has been called the first time
private Panel duplicatePanel;


	//This method displays one image for each set of duplicates in a JPanel within the window.
	//Each image is a JLabel that acts as a button so the user can click on it to view all of the duplicates
	//in that set.
	public void displayDuplicates(ArrayList<ArrayList<Entry>> duplicates){
//		initializes size of window - once integrated should be called within existing UI window from home screen, 
//		once the folder containing photos has been selected
		this.setSize(800, 600);
		
		Panel holder = new Panel();
		
//		create one JLabel for each set of duplicates, add it to the panel
		for(final ArrayList<Entry> duplicateGroup : duplicates){
//		Resizing image to fit within grid
			ImageIcon smallIcon = new ImageIcon(duplicateGroup.get(0).getFileLocation()); // load the image to a imageIcon
			Image image = smallIcon.getImage(); // transform it 
			Image newimg = image.getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
			smallIcon = new ImageIcon(newimg);
			
//		Add image to JLabel, enable expansion on click
			JLabel button = new JLabel(smallIcon);
			button.addMouseListener(new MouseAdapter() {
				  @Override
//				  on click the JLabel should call expand to display entire set of duplicates
				  public void mouseClicked(MouseEvent e) {
					 clear();
				     expand(duplicateGroup);
				     validate();
				     repaint();
				     setVisible(true);
				  }
				});
			holder.add(button);
		}
		
		this.add(holder);
		duplicatePanel = holder;
		this.validate();
		this.repaint();
		this.setVisible(true);
	}
	
	//clears all containers within the window
	public void clear(){
		this.getContentPane().removeAll();
	}
	
//	creates a new panel containing each duplicate image from the selected duplicate set
//	this panel will be created each time a duplicate set is selected - 
//	takes slightly longer each time but saves room instead of storing a large number of sets of images
	public void expand(ArrayList<Entry> duplicates){
		Panel allDuplicates = new Panel();
		
//		adds the "back button" JLabel as the first image of the set
//		maybe could create back arrow class to save time implementing each time its called?
//			issue is passing window or panel of duplicate sets into the class so that it can be called from the mouseListener
		ImageIcon arrow = new ImageIcon("/Users/jacobtower/Pictures/backArrow.png");
		Image pic = arrow.getImage(); 
		Image smallPic = pic.getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH); 
		arrow = new ImageIcon(smallPic);
		final JLabel back = new JLabel(arrow);
		back.addMouseListener(new MouseAdapter() {
			  @Override
			  public void mouseClicked(MouseEvent e) {
				  showDuplicates();
			  }
			});
		allDuplicates.add(back);
		
//		load each individual duplicate image, display in grid
		for (Entry duplicateFile : duplicates){
			ImageIcon smallIcon = new ImageIcon(duplicateFile.getFileLocation()); // load the image to a imageIcon
			Image image = smallIcon.getImage(); // transform it 
			Image newimg = image.getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
			smallIcon = new ImageIcon(newimg);
			JLabel picture = new JLabel(smallIcon);
			allDuplicates.add(picture);
		}
		this.add(allDuplicates);
	}
	
//	displays one image from each set of duplicates so the user can select the duplicate group to expand
//	clears the frame and makes stored Panel of duplicate sets visible
	public void showDuplicates(){
		this.clear();
		this.add(duplicatePanel);
		this.validate();
		this.setVisible(true);
	}
	
// Test function for the SelectDuplicate class. Currently requires hard coded file path for the folder selection - 
// add a file path argument to main methods?
	private static void test(){
//		create all classes needed to parse specified folder for duplicates
		SelectDuplicate testWindow = new SelectDuplicate();
		EntryFactory factory = new EntryFactory();
		HMap<Long, Entry> sizeMap = new HMap<Long, Entry>();
		ArrayList<ArrayList<Entry>> duplicates = new ArrayList<ArrayList<Entry>>();
		File folder = new File("/Users/jacobtower/Photos/");
		ArrayList<String> fileLocations = scanfolder.listFilesForFolder(folder);
		
//		Create an entry for each duplicate, build file size hashmap of all entries by file size
		for(String name : fileLocations){
			Entry e = factory.buildEntry(name);
			sizeMap.insert(e.getFileSize(), e);
		}
		
		//	fill arrayList of arrayLists with each set of duplicates 
		for(Long key : sizeMap.getKey()){
			if(sizeMap.get(key).size() > 1){
				duplicates.add(sizeMap.get(key));
			}
		}
//		pass arrayList<arrayList<Entry>> containing all sets of duplicates into displayDuplicates to create grid of
//		duplicate images for user selection
		testWindow.displayDuplicates(duplicates);
	}
}
