// Sterowanie A,D 
//Strzal glowny SPACJA
// Atak obszarowy F 
//Do zniszczenia celu potrzeba dwoch zderzen 

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Panel extends JPanel implements ActionListener, KeyListener {
	private static final long serialVersionUID = 2L;
	Parser parser = Parser.getInstance();
	Timer t = new Timer(20, this);
	Integer dx = 2, dy = -10, x2, y2, dx2 = 10; // x , y,
	ArrayList<Pocisk> lista = new ArrayList<Pocisk>();
	ArrayList<Pocisk> pociskiObcych = new ArrayList<Pocisk>();
	ArrayList<ArrayList<Pocisk>> atakObszarowyLista = new ArrayList<ArrayList<Pocisk>>();
	ArrayList<Przeszkoda> przeszkody = new ArrayList<Przeszkoda>();
	InvaderGroup ig;
	Integer punkty;
	boolean b, isAlive;
	int atakObcych;
	Random random = new Random();
	Gracz gracz = new Gracz();
	
	BufferedImage[] grafikaObcego = {Parser.obcy, Parser.obcy2};
	int klatkaAnimacji;
	
	long cykl;

	// Konstruktor
	public Panel() {
		parser.load();
		Integer x = Integer.parseInt(parser.properties.getProperty("xPolozenieGrupy"));
		Integer y = Integer.parseInt(parser.properties.getProperty("yPolozenieGrupy"));
		x2 = Integer.parseInt(parser.properties.getProperty("xPolozenieGracza"));
		y2 = Integer.parseInt(parser.properties.getProperty("yPolozenieGracza"));
		ig = new InvaderGroup(x, y);
		punkty = 0;
		atakObcych = 0;
		b = false;
		cykl = 0;
		klatkaAnimacji = 0;
		isAlive = true;
		
		 przeszkody.add(new Przeszkoda(40, 500));
		 przeszkody.add(new Przeszkoda(325, 500));
		 przeszkody.add(new Przeszkoda(575, 500));

		this.setVisible(true);
		this.addKeyListener(this);
		this.setFocusable(true);
		this.requestFocusInWindow();
		//t.start();
		
	}
	
	public void start() {
		if (!t.isRunning()) {
			t.start();
		}
	}
	
	public void stop() {
		t.stop();
	}

	public void paintComponent(Graphics g) {
		// BufferStrategy bs = new getBufferStrategy();
		// Graphics2D g = (Graphics2D) k;
		super.paintComponent(g);
		g.setColor(Color.black);
		g.drawImage(Parser.gracz, x2, y2, this);

		// Rysowanie obcych
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 3; j++) {
				if (ig.grupa[i][j].widoczny == true)
					g.drawImage(grafikaObcego[klatkaAnimacji], ig.grupa[i][j].x, ig.grupa[i][j].y, this);
			}
		}

		// Rysowanie pociskow
		for (int j = 0; j < lista.size(); j++) {
			g.drawImage(Parser.pocisk, lista.get(j).x, lista.get(j).y, this);
		}
		
		//Rysowanie pociskow obcych
		for (Pocisk pocisk : pociskiObcych) {
			g.drawImage(Parser.pocisk2, pocisk.x, pocisk.y, this);
		}

		// Rysowanie ataku obszarowego
		for (int j = 0; j < atakObszarowyLista.size(); j++) {
			for (int i = 0; i < atakObszarowyLista.get(j).size(); i++) {
				if (atakObszarowyLista.get(j).get(i) != null){
						if((atakObszarowyLista.get(j).get(i).x > 0)&(atakObszarowyLista.get(j).get(i).x < 750))
					g.drawImage(Parser.pocisk2, atakObszarowyLista.get(j).get(i).x, atakObszarowyLista.get(j).get(i).y, this);
				}
			}
		}
		
		//Rysowanie przeszkod
		for(int i = 0; i < przeszkody.size(); i++)
		{
			if(przeszkody.get(i).zycie == 3)
			g.drawImage(Parser.przeszkoda0, przeszkody.get(i).x, przeszkody.get(i).y, this);
					
			if(przeszkody.get(i).zycie == 2)
			g.drawImage(Parser.przeszkoda1, przeszkody.get(i).x, przeszkody.get(i).y, this);
					
			if(przeszkody.get(i).zycie == 1)
			g.drawImage(Parser.przeszkoda2, przeszkody.get(i).x, przeszkody.get(i).y, this);
				
		} 


		g.drawLine(750, 0, 750, 650);
		g.setColor(Color.green);
		g.setFont(new Font("default", Font.BOLD, 16));
		g.setColor(Color.MAGENTA);
		g.drawString("imie", Integer.parseInt(parser.properties.getProperty("xImieNapisPolozenie")),
		Integer.parseInt(parser.properties.getProperty("yImieNapisPolozenie")));
		g.drawString("numer poziomu", 750, 140);
		g.drawString("atak obszarowy", 750, 220);
		g.drawString("atak czasem", 750, 300);
		g.drawString("zycia", 750, 380);
		g.drawString("punkty", 750, 460);

		g.setFont(new Font("default", Font.ITALIC, 16));
		g.setColor(Color.BLACK);
		g.drawString("A", 750, 80);
		g.drawString("B", 750, 160);
		g.drawString("C", 750, 240);
		g.drawString("D", 750, 320);
		g.drawString(Integer.toString(gracz.zycia), 750, 400);
		g.drawString(Integer.toString(punkty), 750, 480);
	}

	void kolizja() {
		// Sprawdzanie kolizji obiektu z grup z pociskiem
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 3; j++) {
				for (int k = 0; k < lista.size(); k++) {
					if (ig.grupa[i][j].x > lista.get(k).x + Parser.pocisk.getWidth()
							|| ig.grupa[i][j].x + Parser.obcy.getWidth() < lista.get(k).x
							|| ig.grupa[i][j].y > lista.get(k).y + Parser.pocisk.getHeight()
							|| ig.grupa[i][j].y + Parser.obcy.getHeight() < lista.get(k).y) {
						repaint();
					} else {
						lista.remove(k);
						ig.grupa[i][j].zycie--;
						repaint();
					}
				}
			}
		}
		
		//Wykrycie kolizji z graczem
		for (int i = 0; i < pociskiObcych.size(); i++) {
			if (x2 > pociskiObcych.get(i).x + Parser.pocisk2.getWidth()
			|| x2 + Parser.gracz.getWidth() < pociskiObcych.get(i).x
			|| y2 > pociskiObcych.get(i).y + Parser.pocisk2.getHeight()
			|| y2 + Parser.gracz.getHeight() <pociskiObcych.get(i).y) {
				repaint();

			}
			else
			{
				pociskiObcych.remove(i);
				gracz.zycia --;
			}
		}

		//Wykrywanie kolizji obiektu z grupy z pociskiem z ataku obszarowego
		for (int j = 0; j < 3; j++) 
		{
			for (int i = 0; i < 6; i++) {
				for (int m = 0; m < atakObszarowyLista.size(); m++) {
					for (int n = 0; n < atakObszarowyLista.get(m).size(); n++) {
						if (atakObszarowyLista.get(m).get(n) == null) {
							continue;
						}
						if (ig.grupa[i][j].x > atakObszarowyLista.get(m).get(n).x + Parser.pocisk2.getWidth()
								|| ig.grupa[i][j].x + Parser.obcy2.getWidth() < atakObszarowyLista.get(m).get(n).x
								|| ig.grupa[i][j].y > atakObszarowyLista.get(m).get(n).y + Parser.pocisk.getHeight()
								|| ig.grupa[i][j].y + Parser.obcy2.getHeight() < atakObszarowyLista.get(m).get(n).y)
							repaint();
						else {
							ig.grupa[i][j].zycie--;
							atakObszarowyLista.get(m).remove(n);
							atakObszarowyLista.get(m).add(n, null);
							repaint();
						}

					}
				}
			}
		}
		
		//Wykrywanie kolizji glownych pociskow z przeskodami	
		for(int i = 0; i < przeszkody.size(); i++ )
		{
			for(int j = 0; j < lista.size() ; j++)
			{
				if(przeszkody.get(i).x > lista.get(j).x + Parser.pocisk.getWidth() || przeszkody.get(i).x + Parser.przeszkoda0.getWidth() < lista.get(j).x || przeszkody.get(i).y > lista.get(j).y + Parser.pocisk.getHeight() || przeszkody.get(i).y + Parser.przeszkoda0.getHeight() < lista.get(j).y)
				{
					repaint();
				}
				else
				{
					lista.remove(j);
					przeszkody.get(i).zycie -- ;
				}
			} 
		}
	}

	// Sprawdza czy zycie obiektu = 0 i nalicza punkty
	void zniszcz() 
	{
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 3; j++) {
				if (ig.grupa[i][j].zycie == 0) {
					ig.grupa[i][j].isVisible(false);
					ig.grupa[i][j].x = 0;
					ig.grupa[i][j].y = 0;
					ig.grupa[i][j].zycie--;
					punkty += 100;
				}
			}
		}
		
		//Sprawdzanie czy zycie przeszkody = 0 i ewentualne usuwanie
		for(int i = 0; i < przeszkody.size(); i++)
		{
			if(przeszkody.get(i).zycie == 0)
				przeszkody.remove(i);
		}
		
		//Wykrywanie kolizji ataku dodatkowego i przeszkody oraz ewentualne usuwanie pociskow
		for(int i = 0; i < przeszkody.size(); i++ )
		{
			for(int j = 0; j < atakObszarowyLista.size() ; j++)
			{
				for(int k = 0; k < atakObszarowyLista.get(j).size() ; k++)
				{	
					if (atakObszarowyLista.get(j).get(k) == null) {
						continue;
					}
					if(przeszkody.get(i).x > atakObszarowyLista.get(j).get(k).x + Parser.pocisk2.getWidth()
					|| przeszkody.get(i).x + Parser.przeszkoda0.getWidth() < atakObszarowyLista.get(j).get(k).x
					|| przeszkody.get(i).y > atakObszarowyLista.get(j).get(k).y + Parser.pocisk.getHeight()
					|| przeszkody.get(i).y + Parser.przeszkoda0.getHeight() < atakObszarowyLista.get(j).get(k).y)
					{
						repaint();
					}
					else
					{
						przeszkody.get(i).zycie --;
						atakObszarowyLista.get(j).remove(i);
						atakObszarowyLista.get(j).add(i, null);
						repaint();
					}
				}
			}
		}
		
		// Wykrywanie kolizji pociskow obcych z przeszkodami oraz ewentualne usuwanie pociskow
		for(int i = 0; i < pociskiObcych.size(); i++)
		{
			for(int j = 0; j < przeszkody.size(); j++)
			{
				if(przeszkody.get(j).x > pociskiObcych.get(i).x + Parser.pocisk2.getWidth()
					|| przeszkody.get(j).x + Parser.przeszkoda0.getWidth() <  pociskiObcych.get(i).x
					|| przeszkody.get(j).y >  pociskiObcych.get(i).y + Parser.pocisk.getHeight()
					|| przeszkody.get(j).y + Parser.przeszkoda0.getHeight() <  pociskiObcych.get(i).y)
				{
					repaint();
				}
				else
				{
					pociskiObcych.remove(i);
					przeszkody.get(j).zycie--;
				}
			}
		}
		
		//Sprawdzanie stanu zycia gracza
				
		if(gracz.zycia == 0)
		{
			isAlive = false;
		}
				
	}
		
		
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		// Przesuniecie grupy przeciwnikow
		if (ig.x < 0 || ig.x > 750 - ig.x) {
			dx = -dx;
			ig.przesun(ig.x, ig.y - dy);
		}
		ig.przesun(ig.x + dx, ig.y);

		// Przesuniecie pociskow
		for (int j = 0; j < lista.size(); j++) {
			lista.get(j).y = lista.get(j).y - lista.get(j).dy;
		}
		for (Pocisk pocisk : pociskiObcych) {
			pocisk.y += pocisk.dy;
		}

		// Przesuniecie pociskow z ataku obaszarowego
		for (int i = 0; i < atakObszarowyLista.size(); i++) {

			if (atakObszarowyLista.get(i).get(0) != null)
				atakObszarowyLista.get(i).get(0).y = atakObszarowyLista.get(i).get(0).y
						- atakObszarowyLista.get(i).get(0).dy;

			if (atakObszarowyLista.get(i).get(1) != null)
				atakObszarowyLista.get(i).get(1).y = atakObszarowyLista.get(i).get(1).y
						+ atakObszarowyLista.get(i).get(1).dy;

			if (atakObszarowyLista.get(i).get(2) != null)
				atakObszarowyLista.get(i).get(2).x = atakObszarowyLista.get(i).get(2).x
						- atakObszarowyLista.get(i).get(2).dx;

			if (atakObszarowyLista.get(i).get(3) != null)
				atakObszarowyLista.get(i).get(3).x = atakObszarowyLista.get(i).get(3).x
						+ atakObszarowyLista.get(i).get(3).dy;

			if (atakObszarowyLista.get(i).get(4) != null) {
				atakObszarowyLista.get(i).get(4).x = atakObszarowyLista.get(i).get(4).x
						- atakObszarowyLista.get(i).get(4).dx;
				atakObszarowyLista.get(i).get(4).y = atakObszarowyLista.get(i).get(4).y
						- atakObszarowyLista.get(i).get(4).dy;
			}

			if (atakObszarowyLista.get(i).get(5) != null) {
				atakObszarowyLista.get(i).get(5).x = atakObszarowyLista.get(i).get(5).x
						+ atakObszarowyLista.get(i).get(5).dx;
				atakObszarowyLista.get(i).get(5).y = atakObszarowyLista.get(i).get(5).y
						+ atakObszarowyLista.get(i).get(5).dy;
			}

			if (atakObszarowyLista.get(i).get(6) != null) {
				atakObszarowyLista.get(i).get(6).x = atakObszarowyLista.get(i).get(6).x
						+ atakObszarowyLista.get(i).get(6).dx;
				atakObszarowyLista.get(i).get(6).y = atakObszarowyLista.get(i).get(6).y
						- atakObszarowyLista.get(i).get(6).dy;
			}

			if (atakObszarowyLista.get(i).get(7) != null) {
				atakObszarowyLista.get(i).get(7).x = atakObszarowyLista.get(i).get(7).x
						- atakObszarowyLista.get(i).get(7).dx;
				atakObszarowyLista.get(i).get(7).y = atakObszarowyLista.get(i).get(7).y
						+ atakObszarowyLista.get(i).get(7).dy;
			}

		}
		
		// Atak obcych.
		atakObcych = (atakObcych + 1) % 35;
		if (atakObcych == 0) {
			int rx = random.nextInt(6);
			int ry = random.nextInt(3);
			if (ig.grupa[rx][ry].widoczny) {
				pociskiObcych.add(new Pocisk(ig.grupa[rx][ry].x, ig.grupa[rx][ry].y));
			}
		}
		
		//Przelaczanie klatek animacji
		if (cykl % 30 == 0) {
			klatkaAnimacji = 1 - klatkaAnimacji;
		}

		kolizja();
		zniszcz();
		repaint();

		cykl++;
		t.start();
	}

	
	@Override
	public void keyPressed(KeyEvent e) {
		// this.setFocusable(true);

		if (e.getKeyCode() == KeyEvent.VK_D) // &(x2 > 0 & x2 < 750)
		{
			if(x2 + Parser.gracz.getWidth() < 750)
			x2 = x2 + dx2;
		}
		
		if (e.getKeyCode() == KeyEvent.VK_A) {
			if(x2 > 0)
			x2 = x2 - dx2;
		}

		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			Pocisk pocisk = new Pocisk(x2, y2);
			lista.add(pocisk);
		}

		if (e.getKeyCode() == KeyEvent.VK_F) {
			b = true;
			ArrayList<Pocisk> atakObszarowy = new ArrayList<Pocisk>();
			for (int i = 0; i < 8; i++) {
				atakObszarowy.add(new Pocisk(x2, y2));
			}

			atakObszarowyLista.add(atakObszarowy);
		}

		repaint();
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Panel p = new Panel();
		JFrame frame = new JFrame();
		frame.setTitle("Gra");
		frame.setSize(900, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.add(p);
		p.start();
		
		if(p.isAlive == false)
		{
			p.stop();
			JFrame komunikat = new JFrame();
			JLabel napis = new JLabel("PRZEGRAŁEŚ");
			komunikat.add(napis);
			komunikat.setVisible(true);
			komunikat.setSize(150, 100);
			//frame.pack();
		}
		
		// frame.revalidate();
	}

}
