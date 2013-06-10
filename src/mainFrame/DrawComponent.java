package mainFrame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

import shared.Connector;


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


  /**
   * JSVGComponent muss dieses Superkonstruktor implementieren. Es wurde JSVGComponent statt JComponent verwendet
   * um sp�ter eine Funktion zum anzeigen vollst�ndiger SVG-Dateien implementieren zu k�nnen.
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
    if(showGrid)
    {
      drawGrid(g);
    }

    if(! drawObjects.isEmpty())
    {
      for(DrawObject dO : drawObjects)
      {
        dO.draw(g);
      }
    }

    if(actObject != null)
    {
      actObject.drawOutline(g);
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
    g2d.setColor(new Color(210, 210, 210));

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
    g2d.setColor(Color.black);
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


} 