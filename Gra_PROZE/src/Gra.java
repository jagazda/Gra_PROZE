
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;



public class Gra extends JFrame implements ActionListener
{

	JButton cofnij = new JButton("Cofnij");
	JButton graj = new JButton("Graj");
	JLabel nickNapis = new JLabel("Podaj nick");
	JTextField nick = new JTextField();
	JLabel poziomNapis =  new JLabel("Wybierz poziom trudnosci");
	ButtonGroup grupa = new ButtonGroup();
	JRadioButton niski = new JRadioButton("Niski", true);
	JRadioButton sredni = new JRadioButton("Sredni");
	JRadioButton wysoki = new JRadioButton("Wysoki");
	
	private Gra()
	{
		setSize(900,600);	 
		setTitle("Gra");
		this.setLayout(new GridLayout(6,1));
		
		grupa.add(niski);
		grupa.add(sredni);
		grupa.add(wysoki);
		this.add(niski);
		this.add(sredni);
		this.add(wysoki);
		
		this.add(cofnij);
		
		this.add(nickNapis);
		this.add(nick);
		
		this.add(poziomNapis);
		
		this.add(cofnij);
		this.add(graj);
		
		this.setVisible(true);
		
		
	}
	
	private static Gra gra = new Gra();
	public static Gra getInstance()
	{
		return gra;
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object zrodlo = e.getSource();
		
		if(zrodlo == cofnij)
		{
			dispose();
		}
	}

	
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub

	}

}
