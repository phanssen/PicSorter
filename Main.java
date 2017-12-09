import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.File; 
import java.util.ArrayList;

//main class to run everything
public Main extends JFrame{
    public static void main(String [] args) {
        JPanel panelOne = Home.createPanel("Open folder", true);
		JPanel panelTwo = Home.createPanel("viewDuplicates", false);
		JPanel panelThree = Home.createPanel("Pick up where you left off", false);
		
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