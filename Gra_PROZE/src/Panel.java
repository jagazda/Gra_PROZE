import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class Panel extends JPanel
{

	
	public void paint(Graphics g)
	{	
		new Parser();
		//Graphics2D g = (Graphics2D) k;
		super.paintComponent(g);
		g.setColor(Color.black);
		new Parser();
		g.drawImage(Parser.gracz, Parser.lista5[8], Parser.lista5[9], this);
		for(int i = 0; i < 6; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				g.drawImage(Parser.obcy, Parser.lista5[10]+i*60, Parser.lista5[11]+j*60, this);
			}
		}

		g.drawLine(750, 0, 750, 650); 
		g.setColor(Color.green);
		g.fillRect(150, 500, 100, 20);
		g.fillRect(350, 500, 100, 20);
		g.fillRect(550, 500, 100, 20);
		g.setFont(new Font("default", Font.BOLD, 16));
		g.setColor(Color.MAGENTA);
		g.drawString("imie", Parser.lista5[12] , Parser.lista5[13]);
		g.drawString("numer poziomu", 750 , 140);
		g.drawString("atak obszarowy", 750 , 220);
		g.drawString("atak czasem", 750 , 300);
		g.drawString("zycia", 750 , 380);
		g.drawString("punkty", 750 , 460);
		
		g.setFont(new Font("default", Font.ITALIC, 16));
		g.setColor(Color.BLACK);
		g.drawString(Parser.lista6[0], 750, 80);
		g.drawString("B", 750, 160);
		g.drawString("C", 750, 240);
		g.drawString("D", 750, 320);
		g.drawString("E", 750, 400);
		g.drawString("D", 750, 480);
	


	}
	
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		Panel p = new Panel();

	}

}
