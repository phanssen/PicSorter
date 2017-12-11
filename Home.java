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
	public Home() {
		setTitle("PicSorter");
		setSize(1000, 800);
		setLocation(100, 200);

		HomePanel home = new HomePanel();
		add(home);
	}

	public static void main(String [] args) {
		Home frame = new Home();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
}