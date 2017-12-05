import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.File; 

public class Home extends JFrame {
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
	public static JPanel createPanel(String labelName, boolean clickable) {
		JPanel panel = new JPanel(new FlowLayout());
		JLabel label = new JLabel(labelName);
		if(clickable == true) {
			label.addMouseListener(new MouseAdapter() {  
				public void mouseClicked(MouseEvent e) {
					//open the "browse directory" functionality
					JFileChooser fileChooser = new JFileChooser();
					fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
					//select only folders, not individual files
					fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					int result = fileChooser.showOpenDialog(label);
					if (result == JFileChooser.APPROVE_OPTION) {
						File selectedFile = fileChooser.getSelectedFile();
						System.out.println("Selected file: " + selectedFile.getAbsolutePath());
						//implement file search and sort here - connect to Jacob's UI(?)
					}
				}
			});
		}
		panel.add(label);
		return panel;
	}

	public static void main(String [] args) {
		JPanel panelOne = createPanel("Open folder", true);
		JPanel panelTwo = createPanel("viewDuplicates", false);
		JPanel panelThree = createPanel("Pick up where you left off", false);
		
		//add small panels to a main panel
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		mainPanel.add(panelOne);
		mainPanel.add(panelTwo);
		mainPanel.add(panelThree);
		
		//add main panel to frame, display
		JFrame frame = new Home("PicSorter");
		frame.add(mainPanel);
		frame.setVisible(true);
	}
}