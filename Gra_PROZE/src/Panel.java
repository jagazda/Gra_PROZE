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
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Panel extends JPanel implements ActionListener, KeyListener
{
    private static final long serialVersionUID = 2L;
    Parser parser = Parser.getInstance();
	Timer t = new Timer(35, this);
	Integer  dx = 2, dy = -10, x2, y2, dx2 = 10; //x , y,
	ArrayList<Pocisk> lista = new ArrayList<Pocisk>();
	//List<Invader> lista2 = new ArrayList<Invader>();
	InvaderGroup ig;
	Integer punkty;
	
	public Panel()
	{
		parser.load();
		Integer x = Integer.parseInt(parser.properties.getProperty("xPolozenieGrupy"));
		Integer y = Integer.parseInt(parser.properties.getProperty("yPolozenieGrupy"));
		x2 = Integer.parseInt(parser.properties.getProperty("xPolozenieGracza"));
		y2 = Integer.parseInt(parser.properties.getProperty("yPolozenieGracza"));
	    ig = new InvaderGroup(x, y);
	    punkty = 0;

		
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
		g.drawImage(Parser.gracz, x2 , y2 , this);
		
		for(int i = 0; i < 6; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				if(ig.grupa[i][j].widoczny == true)
					//System.out.println(ig.grupa[i][j].widoczny);
				//g.drawImage(Parser.obcy, x + i*60, y + j*60, this);
				g.drawImage(Parser.obcy, ig.grupa[i][j].x, ig.grupa[i][j].y, this);
			}
		}
		
		for(int j = 0; j < lista.size() ; j++)
		{
			//if(lista.get(j).widoczna == true)
			g.drawImage(Parser.pocisk, lista.get(j).x ,lista.get(j).y , this);
		}

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
		g.drawString("F", 750, 400);
		g.drawString(Integer.toString(punkty), 750, 480);
		
		t.start();
	
	}
	
	void kolizja()
	{
		/*Vector2 p1 = b1.position;
		Vector2 p2 = b2.position;
	 
		if (p1.x > p2.x + b2.width || p1.x + b1.width < p2.x || p1.y > p2.y + b2.height || p1.y + b1.height < p2.y)
			
			return false;
		else
			return true;*/
		//boolean flaga = false;
		
		for(int i = 0; i < 6; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				for(int k = 0; k < lista.size() ; k++)
				{
					
				if(ig.grupa[i][j].x > lista.get(k).x + Parser.pocisk.getWidth() || ig.grupa[i][j].x + Parser.obcy.getWidth() < lista.get(k).x || ig.grupa[i][j].y > lista.get(k).y + Parser.pocisk.getHeight() || ig.grupa[i][j].y + Parser.obcy.getHeight() < lista.get(k).y)
				{
					//flaga =  false;
				
					/*System.out.println(ig.grupa[i][j].x - lista.get(k).x - Parser.pocisk.getWidth()); 
					System.out.println(lista.get(k).x - ig.grupa[i][j].x - Parser.obcy.getWidth() );
					System.out.println(ig.grupa[i][j].y - lista.get(k).y - Parser.pocisk.getHeight() );
					System.out.println(lista.get(k).y - ig.grupa[i][j].y - Parser.obcy.getHeight());*/
					
					repaint();
				}
				
				else
				{
					/*ig.grupa[i][j].x = -10;
					ig.grupa[i][j].y = -10;
					lista.get(k).x = -10;
					lista.get(k).y = -10;*/
					/*ig.grupa[i][j] = null;
					lista.remove(k);*/
					//lista.get(k).isVisible(false);
					
					lista.remove(k);
					ig.grupa[i][j].zycie --;
					
					//System.out.println(ig.grupa[i][j].widoczny);

					repaint();
				}
				/*else 
					flaga = true;*/
				
				}
			}
		}	
		//return flaga;
	}
	
	void zniszcz()
	{
		int flaga = 0;
		for(int i = 0; i < 6; i++)
		{
			for(int j = 0; j < 3; j++)
			{	
				if(ig.grupa[i][j].zycie == 0)
				{
					//System.out.println(ig.grupa[i][j].zycie);
					ig.grupa[i][j].isVisible(false);
					ig.grupa[i][j].x = 0;
					ig.grupa[i][j].y = 0;
					ig.grupa[i][j].zycie--;
					punkty += 100;
					
				}
			}
		}	
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		if(ig.x < 0 || ig.x > 750 - ig.x)
		{
			dx = -dx;
			ig.przesun(ig.x, ig.y - dy);
		}		
		ig.przesun(ig.x + dx, ig.y);
		
		for(int j = 0; j < lista.size() ; j++)
		{
			lista.get(j).y = lista.get(j).y - lista.get(j).dy;
		}
		
		kolizja();
		zniszcz();
		repaint();
	}
	
	

	
	@Override
	public void keyPressed(KeyEvent e)
	{
		//this.setFocusable(true);

		if(e.getKeyCode() == KeyEvent.VK_D) //&(x2 > 0 & x2 < 750)
		{
			x2 = x2 + dx2;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_A)
		{
			x2 = x2 - dx2;
			//System.out.println("ruch");
		}
		
		if(e.getKeyCode() == KeyEvent.VK_SPACE)
		{	
			Pocisk pocisk = new Pocisk(x2,y2);
			lista.add(pocisk);
			repaint();
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
		//frame.revalidate();
	}

}
