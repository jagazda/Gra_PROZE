import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.imageio.ImageIO;

public class Parser
{
	
	public static BufferedImage obcy, gracz, pocisk;
	File f = new File("conf.properties");
    Properties properties = new Properties();

    public void load()
    {
        InputStream is;
        try 
        {
                is = new FileInputStream(f);
                properties.load(is);
        } 
        catch (FileNotFoundException e)
        {
                e.printStackTrace();
        } 
        catch (IOException e) 
        {
                e.printStackTrace();
        }
    }
    
    public void saveProperties(String key, String value)
    {
        OutputStream os;
        
        try 
        {
                os = new FileOutputStream(f);
                properties.setProperty(key, value);
                properties.store(os, null);
        } 
        
        catch (FileNotFoundException e)
        {
                e.printStackTrace();
        }
        
        catch (IOException e)
        {
                e.printStackTrace();
        }
}
    
	private Parser()
	{		
			try
			{
				obcy  = ImageIO.read(new File("Sources/space-invaders-md.png"));
				gracz = ImageIO.read(new File("Sources/ship_up.png"));
				pocisk = ImageIO.read(new File("Sources/bullet1.png"));
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
	}
	
	private static Parser parser = new Parser();
	public static Parser getInstance()
	{
		return parser;
	}
		
		
	public static void main(String[] args)
	{
		  //Parser parser = new Parser();
		  //parser.load();
		  //parser.saveProperties("zycie", "100");
		  //parser.saveProperties("atak", "25");
		  //System.out.println(parser.properties.getProperty("yPolozenieGrupy"));
	}

}
