import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Display {
	private Canvas canvas;
	private ButtonObservable observable;
	private Display display;
	
	public Display(){
		display = this;
		observable = new ButtonObservable();
		canvas = new Canvas();
	}
	
	public void test(){
		System.out.println("hi");
	}
	
	
	private class Canvas extends JFrame{
		private JPanel canvas;
		
		public Canvas(){
			super("PicSorter");
			setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);

			pack();
			setVisible( true );
			
			Button1 findDirect = new Button1("Find Directory");
			JPanel panel = new JPanel( new FlowLayout(FlowLayout.RIGHT));
			panel.add(findDirect);
			
			add( panel, BorderLayout.SOUTH);
			pack();
			
			observable.addObserver(findDirect);
			
			Control control = new Control();
			addKeyListener(control);
			setFocusable(true);
			requestFocus();
			
			findDirect.addActionListener(control);
			
			
	        // create a panel in which to display the Landscape
	        canvas = new JPanel();
	        canvas.setPreferredSize(new Dimension(600, 600));
            canvas.setBackground(Color.white);

	        // add the panel to the window, layout, and display
	        this.add(this.canvas, BorderLayout.CENTER);
	        this.pack();
	        this.setVisible(true);
		}
	}
	
	private class Control extends KeyAdapter implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			observable.notifyObserver(display);
			
		}
		
	}

	public static void main(String[] args) {
		Display n = new Display();

	}

}
