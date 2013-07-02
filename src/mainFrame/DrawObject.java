package mainFrame;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import shared.Connector;
import shared.GeometricObject;
public class DrawObject implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3389787842642755586L;
	private double x1;
	private double y1;
	private double x2;
	private double y2;
	private int index;
	private ArrayList<GeometricObject> geometricObjects = new ArrayList<GeometricObject>();
	private ArrayList<Connector> connectors = new ArrayList<Connector>();
	public DrawObject(int index)
	{
		this.index = index;
	}
	public void draw(Graphics g, boolean b, DrawObject d)
	{
		for(GeometricObject go : geometricObjects)
		{
			go.draw(g);
		}

		for(Connector c : connectors)
		{
			c.draw(g,b,d);
		}
	}
	public void addGeometricObject(GeometricObject go)
	{
		geometricObjects.add(go);
	}
	public void setOuterPoints(double x1, double y1, double x2, double y2)
	{
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}
	public void updateOuterPoints(Point startMove, Point endMove)
	{
		x1 += endMove.getX() - startMove.getX();
		y1 += endMove.getY() - startMove.getY();
		x2 += endMove.getX() - startMove.getX();
		y2 += endMove.getY() - startMove.getY();
	}
	public void drawOutline(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g; 
		g2d.setColor(Color.white);
		g2d.drawRect((int) x1, (int) y1, (int) (x2-x1), (int) (y2-y1));
		g2d.setColor(Color.black);
		g2d.setStroke(new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1.0f, new float[] {5.0f}, 0.0f)); 
		g2d.drawRect((int) x1, (int) y1, (int) (x2-x1), (int) (y2-y1)); 
		g2d.setStroke(new BasicStroke());
	}
	public boolean isInside(int x, int y)
	{
		if((x1 < x & x < x2) & (y1 < y & y < y2))
		{
			return true; 
		}
		return false;
	}
	public void move(Point startMove, Point endMove, int canvasWidth, int canvasHeight)
	{
		if(((x1 + endMove.getX()-startMove.getX()) >= 0) 
				&& ((y1 + endMove.getY()-startMove.getY()) >= 0)
				&& ((x2 + endMove.getX()-startMove.getX()) < canvasWidth) 
				&& ((y2 + endMove.getY()-startMove.getY()) < canvasHeight))
		{ 

			updateOuterPoints(startMove, endMove);
			for(GeometricObject go : geometricObjects)
			{
				go.move(startMove, endMove, canvasWidth, canvasHeight);
			}
			for(Connector c : connectors)
			{
				c.move(startMove, endMove, canvasWidth, canvasHeight);
			}
		}
	}
	public int getIndex()
	{
		return index;
	}
	public void setIndex(int value)
	{
		index += value;
	}
	public void zoom(double zoom)
	{
		x1 = x1*zoom;
		y1 = y1*zoom;
		x2 = x2*zoom;
		y2 = y2*zoom;
		for(GeometricObject go : geometricObjects)
		{
			go.zoom(zoom);
		}
		for(Connector c : connectors)
		{
			c.zoom(zoom);
		}
	}
	public void addConnector(Connector c)
	{
		connectors.add(c);
	}
	public Connector checkConnectors(int p_x, int p_y)
	{
		for(Connector c : connectors)
		{
			if(c.isInside(p_x, p_y) == 10)
			{
				return c;
			}
		}
		return null;
	}
}