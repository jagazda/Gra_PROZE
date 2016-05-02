import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Panel extends JPanel implements ActionListener, KeyListener
{
    private static final long serialVersionUID = 2L;
    Parser parser = Parser.getInstance();
	Timer t = new Timer(35, this);
	Integer x , y, dx = 2, dy = -10, x2, y2, dx2 = 10;
	
	public Panel()
	{
		parser.load();
		x = Integer.parseInt(parser.properties.getProperty("xPolozenieGrupy"));
		y = Integer.parseInt(parser.properties.getProperty("yPolozenieGrupy"));
		x2 = Integer.parseInt(parser.properties.getProperty("xPolozenieGracza"));

		this.setVisible(true);
		this.addKeyListener(this);
		this.setFocusable(true);
        this.requestFocusInWindow();
		
	}
	
	public void paintComponent(Graphics g)
	{
		//Graphics2D g = (Graphics2D) k;
		super.paintComponent(g); 
		g.setColor(Color.black);
		g.drawImage(Parser.gracz, x2 , Integer.parseInt(parser.properties.getProperty("yPolozenieGracza")) , this);
		for(int i = 0; i < 6; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				g.drawImage(Parser.obcy, x + i*60, y + j*60, this);
			}
		}
		
		//g.drawImage(Parser.obcy, x, y, this);

		
		//g.drawImage(invader , 10 , 10, this);

		g.drawLine(750, 0, 750, 650); 
		g.setColor(Color.green);
		g.fillRect(150, 500, 100, 20);
		g.fillRect(350, 500, 100, 20);
		g.fillRect(550, 500, 100, 20);
		g.setFont(new Font("default", Font.BOLD, 16));
		g.setColor(Color.MAGENTA);
		g.drawString("imie", Integer.parseInt(parser.properties.getProperty("xImieNapisPolozenie")) , Integer.parseInt(parser.properties.getProperty("yImieNapisPolozenie")));
		g.drawString("numer poziomu", 750 , 140);
		g.drawString("atak obszarowy", 750 , 220);
		g.drawString("atak czasem", 750 , 300);
		g.drawString("zycia", 750 , 380);
		g.drawString("punkty", 750 , 460);
		
		g.setFont(new Font("default", Font.ITALIC, 16));
		g.setColor(Color.BLACK);
		g.drawString("A", 750, 80);
		g.drawString("B", 750, 160);
		g.drawString("C", 750, 240);
		g.drawString("D", 750, 320);
		g.drawString("E", 750, 400);
		g.drawString("D", 750, 480);
		
		t.start();
	
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		if(x < 0 || x > 750 - x)
		{
			dx = -dx;
			y = y - dy;
		}
		
		x = x + dx;
		
		repaint();
	}
	
	

	
	@Override
	public void keyPressed(KeyEvent e)
	{
		if((e.getKeyCode() == KeyEvent.VK_D)&(x2 > 0 & x2 < 750))
		{
			x2 = x2 + dx2;
		}
		else
		{
			if(e.getKeyCode() == KeyEvent.VK_A)
			{
				x2 = x2 - dx2;
			}
		}
		
		if ((e.getKeyCode() == KeyEvent.VK_A)&(x2 > 0 & x2 < 750))
		{
			x2 = x2 - dx2;
		}
		else
		{
			if(e.getKeyCode() == KeyEvent.VK_D)
			{
				x2 = x2 + dx2;
			}
		}
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	
	
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		Panel p = new Panel();
		JFrame frame = new JFrame();
		frame.setTitle("Gra");
		frame.setSize(900, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.add(p);
	}

}
