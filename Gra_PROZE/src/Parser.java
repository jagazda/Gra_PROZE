import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.imageio.ImageIO;

public class Parser
{
	
	public static BufferedImage obcy, gracz;
	ArrayList<String> lista1 = new ArrayList<String>(); 
	ArrayList<String> lista2 = new ArrayList<String>(); 
    ArrayList<String> lista3 = new ArrayList<String>();
	ArrayList<String> lista4 = new ArrayList<String>();
	static int [] lista5 = new int[36];
	static String [] lista6 = new String[1];
	
	Parser()
	{		
			try
			{
				obcy  = ImageIO.read(new File("Sources/space-invaders-md.png"));
				gracz = ImageIO.read(new File("Sources/ship_up.png"));
			}
			
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
			
			/*try{
				  FileInputStream fstream = new FileInputStream("plikKonfiguracyjny.txt");
				  DataInputStream in = new DataInputStream(fstream);
				  BufferedReader br = new BufferedReader(new InputStreamReader(in));
				  String strLine;
				 
				  while ((strLine = br.readLine()) != null)   {
				     
					  String[] splitted = strLine.split(" "); 
					  System.out.println(splitted.toString());
				  }
				  
				  in.close();
				    }catch (Exception e){//Catch exception if any
				  System.err.println("Error: " + e.getMessage());
				  }*/
			
			
			File plik = new File("plikKonfiguracyjny.txt");
			try
			{
				Scanner odczyt = new Scanner(plik);
				StringTokenizer token;
				while(odczyt.hasNextLine())
				{
					token = new StringTokenizer(odczyt.nextLine(), " ");

					while(token.hasMoreElements())
					{
						//System.out.print("Token = " + token.nextToken());
						lista1.add(token.nextToken());
					}
				}
			} catch (FileNotFoundException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//System.out.print(lista1.toString());

						
			for(int i = 0; i < lista1.size(); i++)
			{
				if(i%2 == 1)
				{
					
					lista2.add(lista1.get(i));
				
				}
			}			

			for(int i = 0; i < lista2.size() - 15; i++)
			{
				lista3.add(lista2.get(i));
			}
						
			//System.out.println(lista2.toString());
			StringTokenizer token2;

			for(int i = lista2.size() - 15; i < lista2.size(); i++)
			{
				token2 = new StringTokenizer(lista2.get(i), "x");
				while(token2.hasMoreElements())
				{
					lista4.add(token2.nextToken());
				}
			}
			
			//System.out.println(lista4.size());
			for(int i = 1; i <  lista3.size(); i++ )
			{
				lista5[i - 1] =	Integer.parseInt(lista3.get(i));
			}
			
			for(int i = 0; i < lista4.size(); i++ )
			{
				lista5[i+6] = Integer.parseInt(lista4.get(i));
			}
			
			//System.out.println(lista3.toString());
			//System.out.println(lista2.toString());
			lista6[0] = lista3.get(0);
			
	}
		
		
	public static void main(String[] args)
	{
		  Parser parser = new Parser();
		  //System.out.println(lista2.toString());

	}

}
