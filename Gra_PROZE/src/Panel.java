// Sterowanie A,D 
//Strzal glowny SPACJA
// Atak obszarowy F 
//Do zniszczenia celu potrzeba dwoch zderzen 
/*
 * Klasa z implementacja ekranu gry
 */
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
	
	Gracz gracz = Gracz.getInstance();
	/*
	 * nowy obiekt klasy Parser
	 */
	Parser parser = new Parser();
	/*
	 * timer
	 */
	Timer t = new Timer(20, this);
	/*
	 * wspolrzedne i przesuniecia gracza
	 */
	/*
	 * nowy obiekt z lista pociskow
	 */
	ArrayList<Pocisk> lista = new ArrayList<Pocisk>();
	/*
	 * nowy obiek z lista pociskow obcych
	 */
	ArrayList<Pocisk> pociskiObcych = new ArrayList<Pocisk>();
	/*
	 * nowy obiekt z lista lista pociskow obszarowych
	 */
	ArrayList<ArrayList<Pocisk>> atakObszarowyLista = new ArrayList<ArrayList<Pocisk>>();
	
	ArrayList<Pocisk> pociskiBossa = new ArrayList<Pocisk>();
	/*
	 * nowy obiekt z lista przeszkod
	 */
	ArrayList<Przeszkoda> przeszkody = new ArrayList<Przeszkoda>();
	/*
	 * grupa obcych
	 */
	InvaderGroup ig;
	/*
	 * funkcja rondom
	 */
	Random random = new Random();
	/*
	 * obiekt gracza
	 */
	//Gracz Gracz = new Gracz();
	
	BufferedImage[] grafikaObcego = {Parser.obcy, Parser.obcy2};
	/*
	 * ilosc animacji
	 */
	int klatkaAnimacji;
	/*
	 * ilosc cykli
	 */
	long cykl;
	
	Bonusy zamrozenie = new Bonusy();
	Bonusy tarcza = new Bonusy();

		
	Boss boss = new Boss();

	/*
	 * Konstuktor klasy Praser
	 */
	public Panel() {
		/*
		 * obiekt z wspolrzednymi grupy obcych
		 */
		
		parser.load();
		ig = new InvaderGroup();
		cykl = 0;
		klatkaAnimacji = 0;
		/*
		 * dodanie przeszkod
		 */
		 przeszkody.add(new Przeszkoda(40, 500));
		 przeszkody.add(new Przeszkoda(325, 500));
		 przeszkody.add(new Przeszkoda(575, 500));
		 tarcza.x = random.nextInt(750 - Parser.atakObszarowy.getWidth());

		this.setVisible(true);
		this.addKeyListener(this);
		this.setFocusable(true);
		this.requestFocusInWindow();		
	}
	/*
	 * metoda startujaca timer
	 */
	public void start() {
		if (!t.isRunning()) {
			t.start();
		}
	}
	/*
	 * metoda zatrzymujaca timer
	 */
	public void stop() {
		t.stop();
	}
/*
 * metoda rysujaca obraz
 * (non-Javadoc)
 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
 */
	public void paintComponent(Graphics g) {
		// BufferStrategy bs = new getBufferStrategy();
		// Graphics2D g = (Graphics2D) k;

		super.paintComponent(g);
		g.setColor(Color.black);
		g.drawImage(Parser.gracz, gracz.x, gracz.y, this);

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
		
		//Rysowanie bossa
	    if(boss.isVisible)
		{
			boss.przesuniecie();
			boss.paintComponent(g);
		}
	    
	    //Rysowanie pociskow bossa
	    if(boss.isVisible)
	    {
	    	for(int i = 0; i < pociskiBossa.size(); i++)
	    	{
		    	g.drawImage(Parser.pocisk2, pociskiBossa.get(i).x, pociskiBossa.get(i).y , this);
	    	}
	    }
	    
	    //Rysowanie bonusu ataku obszarowego
	    if(tarcza.isVisible)
	    {
	    	g.drawImage(Parser.atakObszarowy, tarcza.x ,Integer.parseInt(parser.properties.getProperty("yPolozenieGracza")), this);
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
		g.drawString(Integer.toString(tarcza.ilosc), 750, 240);
		g.drawString("D", 750, 320);
		g.drawString(Integer.toString(gracz.zycia), 750, 400);
		g.drawString(Integer.toString(gracz.punkty), 750, 480);
	}
/*
 * metoda wykrywajaca kolizje
 */
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
			if (gracz.x > pociskiObcych.get(i).x + Parser.pocisk2.getWidth()
			|| gracz.x + Parser.gracz.getWidth() < pociskiObcych.get(i).x
			|| gracz.y > pociskiObcych.get(i).y + Parser.pocisk2.getHeight()
			|| gracz.y + Parser.gracz.getHeight() <pociskiObcych.get(i).y) {
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
							/*ig.grupa[i][j].zycie--;
							atakObszarowyLista.get(m).remove(n);
							atakObszarowyLista.get(m).add(n, null);*/
							ig.grupa[i][j].zycie = 0;
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
				if(przeszkody.get(i).x > lista.get(j).x + Parser.pocisk.getWidth() || przeszkody.get(i).x + Parser.przeszkoda0.getWidth() < lista.get(j).x
						|| przeszkody.get(i).y > lista.get(j).y + Parser.pocisk.getHeight() || przeszkody.get(i).y + Parser.przeszkoda0.getHeight() < lista.get(j).y)
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
		
		
		
		//Wykrywanie kolizji pocisku z bossem
		if(boss.isVisible){
		for(int i = 0; i < lista.size(); i++)
		{
			if(boss.x > lista.get(i).x + Parser.pocisk.getWidth() || boss.x + Parser.obcy2.getWidth() < lista.get(i).x || boss.y > lista.get(i).y + Parser.pocisk.getHeight() 
			|| boss.y + Parser.obcy2.getHeight() < lista.get(i).y)
			{
				repaint();
			}
			else
			{
				lista.remove(i);
				boss.zycie -- ;
			}
		}
	}
		
		//Wykrywanie kolizji pociskow z ataku obszarowego z bossem 
		if(boss.isVisible)
		{
			for(int i =0; i < atakObszarowyLista.size(); i++)
			{
				for(int j = 0; j < atakObszarowyLista.get(i).size(); j++)
				{
					if (atakObszarowyLista.get(i).get(j) == null) {
						continue;
					}
					if (boss.x > atakObszarowyLista.get(i).get(j).x + Parser.pocisk2.getWidth()
							|| boss.x + Parser.obcy2.getWidth() < atakObszarowyLista.get(i).get(j).x
							|| boss.y > atakObszarowyLista.get(i).get(j).y + Parser.pocisk.getHeight()
							|| boss.y + Parser.obcy2.getHeight() < atakObszarowyLista.get(i).get(j).y)
						repaint();
					else {
						boss.zycie --;
						atakObszarowyLista.get(i).remove(j);
						atakObszarowyLista.get(i).add(j, null);
						repaint();
					}
				}
					
			}
		}
		
		
	//Wykrywanie kolizji ataku bossa z przeszkodami i ewentuale usuwanie obiektow
	if(boss.isVisible)
	{
		for(int i = 0; i < przeszkody.size(); i++ )
		{
			for(int j = 0; j < pociskiBossa.size() ; j++)
			{
				if(przeszkody.get(i).x > pociskiBossa.get(j).x + Parser.pocisk2.getWidth() || przeszkody.get(i).x + Parser.przeszkoda0.getWidth() < pociskiBossa.get(j).x
						|| przeszkody.get(i).y > pociskiBossa.get(j).y + Parser.pocisk2.getHeight() || przeszkody.get(i).y + Parser.przeszkoda0.getHeight() < pociskiBossa.get(j).y)
				{
					repaint();
				}
				else
				{	
					pociskiBossa.remove(j);
					przeszkody.get(i).zycie -- ;
					repaint();
				}
			} 
		}
	}
	
	//Wykrywanie kolizji pociskow bossa z graczem
	if(boss.isVisible)
	{
		for(int i = 0; i < pociskiBossa.size(); i++ )
		{
				if(gracz.x > pociskiBossa.get(i).x + Parser.pocisk2.getWidth() || gracz.x + Parser.gracz.getWidth() < pociskiBossa.get(i).x
						||gracz.y > pociskiBossa.get(i).y + Parser.pocisk2.getHeight() || gracz.y + Parser.gracz.getHeight() < pociskiBossa.get(i).y)
				{
					repaint();
				}
				else
				{	
					pociskiBossa.remove(i);
					gracz.zycia -- ;
					repaint();
				}
		}
	}
		
	}
/*
 * metoda sprawdzajaca czy zycie obiektu = 0 i nalicza punkty
 */
	void zniszcz() 
	{
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 3; j++) {
				if (ig.grupa[i][j].zycie == 0) {
					ig.grupa[i][j].isVisible(false);
					ig.grupa[i][j].x = 0;
					ig.grupa[i][j].y = 0;
					ig.grupa[i][j].zycie--;
					gracz.punkty += 100;
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
					repaint();
				}
			}
		}
		
		//Sprawdzanie stanu zycia bossa
		if(boss.zycie == 0)
		{
			boss.isVisible = false;
			boss.zycie --;
			gracz.punkty += 600;
		}
		
		//Sprawdzanie stanu zycia gracza	
		if(gracz.zycia == 0)
		{
			gracz.alive = false;
		}
		
				
	}
		
		
	/*
	 * metoda obslugujaca zdarzenia 
	 * (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		// Przesuniecie grupy przeciwnikow
		if (ig.x < 0 || ig.x > 750 - ig.x) {
			ig.dx = -ig.dx;
			ig.przesun(ig.x, ig.y - ig.dy);
		}
		ig.przesun(ig.x + ig.dx, ig.y);

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
		
		//Przesuniecie pociskow Bossa
		for(int i = 0; i < pociskiBossa.size(); i++)
		{
			pociskiBossa.get(i).y += pociskiBossa.get(i).dy;
		}
		
		//Atak obcych.
		ig.atakObcych = (ig.atakObcych + 1) % 35;
		if (ig.atakObcych == 0) {
			int rx = random.nextInt(6);
			int ry = random.nextInt(3);
			if (ig.grupa[rx][ry].widoczny) {
				pociskiObcych.add(new Pocisk(ig.grupa[rx][ry].x, ig.grupa[rx][ry].y));
			}
		}
		
		//Atak bossa
		boss.czasBossa = (boss.czasBossa + 1) % 25;
		if(boss.czasBossa == 0)
		{
			if(boss.isVisible)
			{
				pociskiBossa.add(new Pocisk(boss.x, boss.y));
			}
		}
		
		//Przelaczanie klatek animacji
		if (cykl % 30 == 0) {
			klatkaAnimacji = 1 - klatkaAnimacji;
		}
		
		//Sprawdzanie warunku na bossa
		if(gracz.punkty == 700)
		{
			boss.isVisible = true;
		}
		
		//Generowanie bonusu ataku obszarowego
		if(gracz.punkty == 800)
		{
			tarcza.isVisible = true;
		}
		
		tarcza.czasWidocznosci = (tarcza.czasWidocznosci + 1) % 500;
		
		//Sprawdzanie czasu zycia bonusu ataku obszarowego
		if(tarcza.isVisible)
		{
			if(tarcza.czasWidocznosci == 0)
			tarcza.isVisible = false;
		}
		
		//Sprawdzenie czy bonus ataku obszarowego został zebrany 
		if(tarcza.isVisible)
		{
		if(gracz.x > tarcza.x + Parser.atakObszarowy.getWidth() || gracz.x + Parser.gracz.getWidth() < tarcza.x)
		{
			repaint();
		}
		else
		{
			tarcza.isVisible = false;
			tarcza.ilosc ++;
		}
		
		}

		kolizja();
		zniszcz();
		repaint();

		cykl++;
		t.start();
	}

	/*
	 * metoda obslugujaca zdarzenia wcisniecia klawiszy
	 * (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		// this.setFocusable(true);

		if (e.getKeyCode() == KeyEvent.VK_D) // &(x2 > 0 & x2 < 750)
		{
			if(gracz.x + Parser.gracz.getWidth() < 750)
			gracz.x += gracz.dx;
		}
		
		if (e.getKeyCode() == KeyEvent.VK_A) {
			if(gracz.x > 0)
			gracz.x -= gracz.dx;
		}

		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			Pocisk pocisk = new Pocisk(gracz.x, gracz.y);
			lista.add(pocisk);
		}

		if ((e.getKeyCode() == KeyEvent.VK_F)&&(tarcza.ilosc > 0)) {
			ArrayList<Pocisk> atakObszarowy = new ArrayList<Pocisk>();
			for (int i = 0; i < 8; i++) {
				atakObszarowy.add(new Pocisk(gracz.x, gracz.y));
				tarcza.ilosc --;
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
/*
 * funckja main zawierajaca dzialanie klasy
 * @param args nie uzywane
 */
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
		
		if(p.gracz.alive == false)
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
