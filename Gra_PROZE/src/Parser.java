import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Properties;

import javax.imageio.ImageIO;

public class Parser extends Applet
{
	//Icon obcy;
	public static BufferedImage  obcy, obcy2, gracz, pocisk, pocisk2, przeszkoda0, przeszkoda1, przeszkoda2;
	//Image obcy;
	File f = new File("conf.properties");
    Properties properties = new Properties();

    /*public void init(){};   
    public void paint(Graphics g){};*/
    
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
				//obcy  = getImage(new URL(getDocumentBase(), "C:\\SPB_Data\\git\\Gra_PROZE\\Gra_PROZE\\Sources\\invader.gif"));
				obcy = ImageIO.read(new File("C:\\SPB_Data\\git\\Gra_PROZE\\Gra_PROZE\\Sources\\invader.gif"));
				obcy2 = ImageIO.read(new File("C:\\SPB_Data\\git\\Gra_PROZE\\Gra_PROZE\\Sources\\space-invaders-md.png"));
				gracz = ImageIO.read(new File("C:\\SPB_Data\\git\\Gra_PROZE\\Gra_PROZE\\Sources\\ship_up.png"));
				pocisk = ImageIO.read(new File("C:\\SPB_Data\\git\\Gra_PROZE\\Gra_PROZE\\Sources\\bullet1.png"));
				pocisk2 = ImageIO.read(new File("C:\\SPB_Data\\git\\Gra_PROZE\\Gra_PROZE\\Sources\\kolo.png"));
				przeszkoda0 = ImageIO.read(new File("C:\\SPB_Data\\git\\Gra_PROZE\\Gra_PROZE\\Sources\\przeszkoda0.png"));
				przeszkoda1 = ImageIO.read(new File("C:\\SPB_Data\\git\\Gra_PROZE\\Gra_PROZE\\Sources\\przeszkoda1.png"));
				przeszkoda2 = ImageIO.read(new File("C:\\SPB_Data\\git\\Gra_PROZE\\Gra_PROZE\\Sources\\przeszkoda2.png"));

			}
			
			catch(Exception e)
			{
				e.printStackTrace();
			}	
	}
	
	private static Parser parser = new Parser();
	public static Parser getInstance()
	{
		return parser;
	}
		
		
	public static void main(String[] args)
	{
		  
	}

}
