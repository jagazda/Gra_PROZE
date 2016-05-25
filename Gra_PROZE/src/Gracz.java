/*
 * klasa implementujaca obiekt gracza
 */

public class Gracz  
{
	/*
	 * wspolrzedna poczatkowa x
	 */
    int x;
	/*
	 * wspolrzedna poczatkowa y
	 */
     int y;
	/*
	 * zycie poczatkowe
	 */
    int zycia;
	/*
	 * konstruktor gracza
	 */
	
    int punkty;
	
    int oslona;
    
    int atakCzasowy;
	
	int dx;
	
	boolean alive = true;
	
	Parser parser = new Parser();
	
	private Gracz()
	{
		zycia = 3;
		dx = 10;
		punkty = 0;
		oslona = 0;
		atakCzasowy = 0;
		parser.load();
        x = Integer.parseInt(parser.properties.getProperty("xPolozenieGracza"));
	 	y = Integer.parseInt(parser.properties.getProperty("yPolozenieGracza"));
		
		//setBounds(Animacja.WIDTH / 2 - Parser.obcy.getWidth() / 2, Animacja.HEIGHT - 10, Parser.obcy.getWidth(), Parser.obcy.getHeight());
	}
	
	private static Gracz gracz = new Gracz();
	public static Gracz getInstance()
	{
		return gracz;
	}
	
/*
 * pusta funkcja main
 * @param args nie uzywane
 */
	public static void main(String[] args)
	{
		
	}

}
