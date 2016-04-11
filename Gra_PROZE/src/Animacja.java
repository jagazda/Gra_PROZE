import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Animacja extends JFrame implements ActionListener
{
	//static final int WIDTH = 600, HEIGHT = 900;
	
	private static final long serialVersionUID = 1L;
	
	Image obrazek;

	Animacja()
	{
		this.setTitle("Gra");
		this.setSize(900, 650);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		 JPanel panel = new Panel();
		 this.add(panel);
		//JButton button = new JButton("fds");
		//button.setPreferredSize(new Dimension(10,30));
		//panel.add(button, BorderLayout.CENTER);
		//this.add(button);
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}




	public static void main(String[] args)
	{
		Animacja anim = new Animacja();
		Parser parser = new Parser();
	   // System.out.println( Arrays.toString(Parser.lista5));
		
		for(int i = 0; i < (Parser.lista5).length; i++)
				{
					System.out.println("Nr elementu " + i + " " + Parser.lista5[i]);
				}
	}
	
	

}
