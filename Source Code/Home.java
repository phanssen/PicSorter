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
	//constructor
	public Home() {
		//set up the frame
		setTitle("PicSorter");
		setSize(960, 800);
		setLocation(100, 200);
		
		//create instance of HomePanel
		HomePanel home = new HomePanel(this);
		add(home);
	}

	//main function
	public static void main(String [] args) {
		Home frame = new Home();
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}