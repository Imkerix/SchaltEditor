package mainFrame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import shared.Connector;


import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;
import org.apache.batik.svggen.SVGGraphics2DIOException;
import org.apache.batik.swing.svg.JSVGComponent;
import org.apache.batik.swing.svg.SVGUserAgent;

//Eigener JComponent zum Zeichnen erstellt, da Canvas Heavyweight ist, der Rest aber Lightweight. JComponent ist Lightweight
public class DrawComponent extends JSVGComponent
{
	private static final long serialVersionUID = 1L;

	private ArrayList<DrawObject> drawObjects = new ArrayList<DrawObject>();
	private DrawObject actObject;
	private Connector actConnector1;
	private Connector actConnector2;

	private boolean showGrid = false;
	private int gridInterval = 30;
	private Color gridColor = new Color(210, 210, 210);
	private Color symbolColor = Color.black;

	private boolean showConnectors = true;


	/**
	 * JSVGComponent muss dieses Superkonstruktor implementieren. Es wurde JSVGComponent statt JComponent verwendet
	 * um spaeter eine Funktion zum anzeigen vollstaendiger SVG-Dateien implementieren zu koennen.
	 * @param ua
	 * @param eventsEnabled
	 * @param selectableText
	 */
	public DrawComponent(SVGUserAgent ua, boolean eventsEnabled, boolean selectableText) 
	{
		super(ua, eventsEnabled, selectableText);  
	}

	@Override
	/**
	 * Wenn bereits Objekte in der DrawObject-Liste vorhanden sind, wird die draw-Methoder dieser Objekte aufgerufen. Ist aktuell ein Objekt angew�hlt, wird die Au�enlinie dieses Objektes gezeichnet.
	 */
	public void paintComponent(Graphics g)
	{  
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);


		if(showGrid)
		{
			drawGrid(g2d);
		}

		g2d.setColor(symbolColor);
		if(! drawObjects.isEmpty())
		{
			for(DrawObject dO : drawObjects)
			{
				dO.draw(g2d, showConnectors, actObject);
			}
		}

		if(actObject != null)
		{
			actObject.drawOutline(g2d);
		}




	}

	/**
	 * F�gt der Liste ein neues DrawObject hinzu.
	 * @param drawObject
	 */
	public void addToList(DrawObject drawObject)
	{
		drawObjects.add(drawObject);
	}

	/**
	 * Pr�ft, ob der geklickte Punkt in einem der Objekte, oder einem der Connector liegt.
	 * Wurde es Connector angeklickt und es wurde davor bereits schon ein anderer angew�hlt, werden die beiden Connector verbunden.
	 * @param x
	 * @param y
	 * @return
	 */
	public int checkSelection(int x, int y)
	{  
		//Wenn ein Objekt angew�hlt ist...
		if(actObject != null)
		{
			//...und noch kein Connector ausgew�hlt wurde..
			if(actConnector1 == null)
			{
				//..wird gepr�ft ob ein Connector geklickt wurde und dieser in actConnector1 gespeichert.
				actConnector1 = actObject.checkConnectors(x, y);

				//Wenn ein Connector geklickt wurde...
				if(actConnector1 != null)
				{
					//...wird dieser auf "geklickt" gesetzt.
					actConnector1.setKlicked(true);

					//Wenn dieser Connector allerdings schon verbunden ist...
					if(actConnector1.isConnected())
					{
						//...wird die Verbindung gel�st und der Connector abgew�hlt.
						actConnector1.getConnectedTo().connectTo(null);
						actConnector1.connectTo(null);
						actConnector1.setKlicked(false);
						actConnector1 = null;
					}
				}
			}
			//...und der erste Connector bereits gew�hlt wurde...
			else if(actConnector2 == null)
			{
				//..wird gepr�ft ob ein Connector geklickt wurde und dieser in actConnector2 gespeichert.
				actConnector2 = actObject.checkConnectors(x, y);

				//Wenn ein Connector geklickt wurde...
				if(actConnector2 != null)
				{
					//werden actConnector1 und actConnector2 miteinander verbunden.
					actConnector1.connectTo(actConnector2);
					actConnector2.connectTo(actConnector1);

				}

				//Wenn allerdings der selbe Connector zwei mal gew�hlt wurde...
				if(actConnector1 == actConnector2)
				{
					//...wird die Verbindung wieder gel�st und der Connector abgew�hlt.
					actConnector1.setKlicked(false);
					actConnector2.setKlicked(false);
					actConnector1.connectTo(null);
					actConnector2.connectTo(null);
					actConnector1 = null;
					actConnector2 = null;
				}
			}
		}


		//Pr�fen ob ein Objekt angew�hlt wurde
		actObject = null;
		for(DrawObject dO : drawObjects)
		{
			if(dO.isInside(x, y))
			{
				return dO.getIndex();
			}
		}
		//Wenn actConnector1 nicht null ist, aber in kein Objekt geklickt wurde...
		if(actConnector1 != null)
		{
			//wird der Connector abgew�hlt.
			actConnector1.setKlicked(false);
			actConnector1 = null;
		}
		//Dasselbe gilt f�r actConnector2.
		if(actConnector2 != null)
		{
			actConnector2.setKlicked(false);
			actConnector2 = null;
		}

		//Wenn kein Objekt angeklickt wurde
		return -1;
	}

	public void move(Point startMove, Point endMove)
	{
		if(actObject != null)
		{
			actObject.move(startMove, endMove, this.getWidth(), this.getHeight());
		}
		repaint();
	}

	public void delete()
	{
		if(actObject != null)
		{
			for (int i = 0; i < drawObjects.size(); i++) 
			{
				if(actObject == drawObjects.get(i))
				{
					drawObjects.remove(i);
					for(; i < drawObjects.size(); i++)
					{
						drawObjects.get(i).setIndex(-1);
					}
				}
			}
			actObject = null;
		}
		repaint();
	}

	public int getNextIndex()
	{
		return drawObjects.size();
	}

	public void setActObject(int index)
	{
		if(index >= 0)
		{
			actObject = drawObjects.get(index);
		}
		else if(index == -1)
		{
			actObject = null;
		}    
		this.repaint();
	}

	public void zoom(double zoom)
	{
		for(DrawObject dO : drawObjects)
		{
			dO.zoom(zoom);
		}
	}  

	public ArrayList<DrawObject> getObjectList()
	{
		return drawObjects;
	}

	public void setObjectList(ArrayList<DrawObject> list)
	{
		this.drawObjects = list;
	}

	public void drawGrid(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(gridColor);

		for(int i = 1; i < (this.getWidth()/gridInterval); i++)
		{
			int x = i*gridInterval;
			int y = gridInterval;

			for(int k = 1; k < (this.getHeight()/gridInterval); k++)
			{
				g2d.fillOval(x, y, 3, 3);
				y += gridInterval;
			}
		}
	}

	public void showGrid(boolean b)
	{
		this.showGrid = b;
	}

	public int getGridInterval()
	{
		return gridInterval;
	}

	public void setGridInterval(int interval)
	{
		this.gridInterval = interval;
	}

	public void setGridColor(Color c)
	{
		gridColor = c;
	}

	public void showConnectors(boolean b)
	{
		this.showConnectors = b;
	}

	/**
	 * 
	 * @param file
	 * @param datatype: 0 == SVG, 1 == JPEG
	 * @throws IOException 
	 */
	public void export(String path) throws IOException
	{
		actObject = null;
		if(path.endsWith(".svg"))
		{
			SVGGraphics2D g2d = new SVGGraphics2D(GenericDOMImplementation.getDOMImplementation().createDocument("http://www.w3.org/2000/svg", "svg", null)); 
			paintComponent(g2d);
			g2d.setSVGCanvasSize(this.getSize());

			try 
			{
				Writer out = new OutputStreamWriter(new FileOutputStream(new File(path)), "UTF-8");
				g2d.stream(out, true); //true means that svgGenerator  uses CSS
				out.close();
			}
			catch (UnsupportedEncodingException | FileNotFoundException | SVGGraphics2DIOException e) 
			{
				e.printStackTrace();
			}
		}
		else if(path.endsWith(".jpeg"))
		{
			BufferedImage bufferedImage = new BufferedImage(this.getWidth(),
					this.getHeight(), BufferedImage.TYPE_INT_RGB);
			Graphics2D g2d = bufferedImage.createGraphics();
			g2d.setBackground(Color.white);
			g2d.setColor(Color.white);
			g2d.fillRect(0, 0, getWidth(), getHeight());
			g2d.setColor(Color.black);
			paintComponent(g2d);

			try {
				ImageIO.write(bufferedImage, "jpeg", new File(path));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void setSymbolColor(Color c)
	{
		this.symbolColor = c;
	}
}

