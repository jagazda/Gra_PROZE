import java.awt.Graphics;

import javax.swing.JPanel;

public class Boss extends JPanel
{
	Integer x, y, dx, dy, zycie, czasBossa;
	boolean isVisible = false;
	
	Parser parser = new Parser();

	public Boss()
	{
		parser.load();
		x = 10;
		y = 10;
		dx = 5;
		dy = 30;
		zycie = 6;
		czasBossa = 0;
	}
	
	void przesuniecie()
	{
		if (this.x < 0 || this.x > 750 - Parser.obcy2.getWidth()) {
			this.dx = -this.dx;
			this.y += this.dy;
		}
		
		this.x += this.dx;
		}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(Parser.obcy2, this.x, this.y, null);
	}
	
	
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub

	}

}
