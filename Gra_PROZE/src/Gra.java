
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
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
		setTitle("Gra");
		grupa.add(niski);
		grupa.add(sredni);
		grupa.add(wysoki);
		Panel pGRUPA = new Panel();
		pGRUPA.add(poziomNapis);
		pGRUPA.add(niski);
		pGRUPA.add(sredni);
		pGRUPA.add(wysoki);

		
		Panel pCENTER = new Panel();
		pCENTER.setLayout(new BoxLayout(pCENTER, BoxLayout.LINE_AXIS));
		pCENTER.add(nickNapis);
		pCENTER.add(nick);
		
		Panel pSOUTH = new Panel();
		pSOUTH.add(cofnij);
		pSOUTH.add(graj);
		
		this.setLayout(new BorderLayout());
		this.add(pCENTER, BorderLayout.CENTER);
		this.add(pGRUPA, BorderLayout.NORTH);
		this.add(pSOUTH, BorderLayout.SOUTH);
		this.pack();
		this.setVisible(true);
		
		graj.addActionListener(this);
		cofnij.addActionListener(this);
	}
	
	private static Gra gra = new Gra();
	public static Gra getInstance()
	{
		return gra;
	}
	
	// Coœ tam

	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object zrodlo = e.getSource();
		
		if(zrodlo == cofnij)
		{
			gra.dispose();
		}
	}

	
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub

	}

}
