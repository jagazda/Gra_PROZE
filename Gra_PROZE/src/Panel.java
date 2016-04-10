import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Panel extends JPanel
{

	
	public void paint(Graphics k)
	{
		
		Graphics2D g = (Graphics2D) k;
		super.paintComponent(g);
		g.setColor(Color.black);
		new Parser();
		g.drawImage(Parser.gracz, 450, 550, this);
		for(int i = 0; i < 6; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				g.drawImage(Parser.obcy, 300+i*60, 250+j*60, this);
			}
		}
		g.drawString("test", 800 , 75);
		g.drawLine(750, 0, 750, 650); 
		g.fillRect(150, 500, 100, 20);
		g.fillRect(350, 500, 100, 20);
		g.fillRect(550, 500, 100, 20);
		
	}
	
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub

	}

}
