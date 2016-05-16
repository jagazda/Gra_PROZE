import java.awt.Graphics;
import java.awt.image.ImageObserver;

public class Gracz  
{

	int x = 20;
	int y = 20;
	int  zycia;
	
	public Gracz()
	{
		zycia = 3;
		//setBounds(Animacja.WIDTH / 2 - Parser.obcy.getWidth() / 2, Animacja.HEIGHT - 10, Parser.obcy.getWidth(), Parser.obcy.getHeight());
	}
	
	/*public void draw(Graphics g)
	{
		g.drawImage(Parser.gracz, x, y,  (ImageObserver) this);
	}
	*/

	public static void main(String[] args)
	{
		
	}

}
