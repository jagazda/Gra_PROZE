
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;



public class Menu extends JFrame implements ActionListener
{
	JButton gra = new JButton("Gra");
	JButton statystyki = new JButton("Statystyki");
	JButton wyjscie = new JButton("Wyjscie");
	
	public Menu()
	{
		setSize(600,600);	 
		setTitle("Space Invaders");
		setVisible(true);
		Panel pCENTER = new Panel();
		
		JPanel pWEST = new JPanel();
		pWEST.setOpaque(true);
		pWEST.setLayout(new BoxLayout(pWEST, BoxLayout.X_AXIS));
		Dimension minSize1 = new Dimension(30, 50);
		Dimension prefSize1 = new Dimension(100, 500);
		Dimension maxSize1 = new Dimension(Short.MAX_VALUE, 1000);
		
		pWEST.add(new Box.Filler(minSize1, prefSize1, maxSize1));
		Panel pEAST = new Panel();
		pEAST.setLayout(new BoxLayout(pEAST, BoxLayout.X_AXIS));
		pEAST.add(new Box.Filler(minSize1, prefSize1, maxSize1));

		pWEST.setOpaque(true);
		
		gra.setPreferredSize(new Dimension(100, 30));
		statystyki.setPreferredSize(new Dimension(100, 30));
		wyjscie.setPreferredSize(new Dimension(100, 30));

		pCENTER.setLayout(new BoxLayout(pCENTER, BoxLayout.Y_AXIS));
		Dimension minSize2 = new Dimension(0, 50);
		Dimension prefSize2 = new Dimension(200, 70);
		Dimension maxSize2 = new Dimension(Short.MAX_VALUE, 1000);

		pCENTER.add(new Box.Filler(minSize2, prefSize2, maxSize2));
		Panel pGRA = new Panel();
		pGRA.setLayout(new BorderLayout());
		pGRA.add(gra, BorderLayout.CENTER);
		pCENTER.add(pGRA);
		pCENTER.add(new Box.Filler(minSize2, prefSize2, maxSize2));
		Panel pSTATYSTYKI = new Panel();
		pSTATYSTYKI.setLayout(new BorderLayout());
		pSTATYSTYKI.add(statystyki, BorderLayout.CENTER);
		pCENTER.add(pSTATYSTYKI);
		pCENTER.add(new Box.Filler(minSize2, prefSize2, maxSize2));
		Panel pWYJSCIE = new Panel();
		pWYJSCIE.setLayout(new BorderLayout());
		pWYJSCIE.add(wyjscie, BorderLayout.CENTER);
		pCENTER.add(pWYJSCIE);
		pCENTER.add(new Box.Filler(minSize2, prefSize2, maxSize2));

		this.setLayout(new BorderLayout());
		this.add(pCENTER, BorderLayout.CENTER);
		this.add(pWEST, BorderLayout.WEST);
		this.add(pEAST, BorderLayout.EAST);
		this.pack();
		
		gra.addActionListener(this);
		statystyki.addActionListener(this);
		wyjscie.addActionListener(this);
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object zrodlo = e.getSource();
		if(zrodlo == gra)
		{
			Gra.getInstance();
		}
		
		else if(zrodlo == statystyki)
		{
			
		}

		else if(zrodlo == wyjscie)
		{
			dispose(); // vs EXIT_ON_CLOSE
		}
		
	}




	public static void main(String[] args)
	{
		Menu menu = new Menu();
		menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menu.setVisible(true);
	}

}


