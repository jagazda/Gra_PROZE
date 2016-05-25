/*
 * klasa implementujaca przeszkode
 */
public class Przeszkoda
{
	/*
	 * wspolrzedne przeszkody oraz zycie przeszkody
	 */
	Integer x, y, zycie;
	/*
	 * widocznosc
	 */
	boolean b;
	/*
	 * konstruktor przeszkody
	 * @param xx wspolrzedna x przeszkody
	 * @param yy wspolrzedna y przeszkody
	 */
	Przeszkoda(Integer xx, Integer yy)
	{
		this.x = xx;
		this.y = yy;
		zycie = 3;
		b = true;
	}
	
	
	/*
	 * pusta funkcja main
	 * @param args nie uzywane
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub

	}

}
