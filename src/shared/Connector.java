package shared;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

import mainFrame.DrawObject;

public class Connector extends GeometricObject
{
	private Connector connectedTo = null;
	private boolean isKlicked = false;
	private static double diameter = 8;


	public Connector(double x, double y)
	{
		this.x = x;
		this.y = y;
		this.width = diameter;
		this.height = diameter;
	}	

	public void draw(Graphics g, boolean b, DrawObject d) 
	{
		Graphics2D g2d = (Graphics2D) g;
		if(b || (!b && (d != null)))
		{
			g2d.drawOval((int) x, (int) y, (int) diameter, (int) diameter);
			if(isKlicked || connectedTo != null)
			{
				g2d.fillOval((int) x, (int) y, (int) diameter, (int) diameter);
			}
			
		}

		if(connectedTo != null)
		{
			g.drawLine((int) (x+width/2), (int) (y+height/2), (int) connectedTo.getConnectX(), (int) connectedTo.getConnectY());
		}



	}

	@Override
	public void drawOutline(Graphics g)
	{

	}

	@Override
	public void drawGrabbers(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		g2d.fillOval((int) x, (int) y, (int) width, (int) height);

	}

	public void connectTo(Connector c)
	{
		this.connectedTo = c;
	}

	public double getConnectX()
	{
		return (x+width/2);		
	}

	public double getConnectY()
	{
		return (y+height/2);
	}

	public void setKlicked(boolean b)
	{
		this.isKlicked = b;
	}

	public boolean isConnected()
	{
		if(connectedTo != null)
		{
			return true;
		}
		return false;
	}

	public Connector getConnectedTo()
	{
		return connectedTo;
	}

	@Override
	public void expand(int grabber, Point endMove, int canvasWidth, int canvasHeight) {

	}
	
	public static void setDiameter(double pdiameter)
	{
		diameter = pdiameter;
	}
	
	public static double getDiameter()
	{
		return diameter;
	}
	
	@Override
	public int isInside(int p_x, int p_y)
	{
		for (Rectangle r : rectList) 
		{
			if((r.getX()<p_x && p_x<(r.getX()+diameter)) && (r.getY()<p_y && p_y<(r.getY()+diameter))) 
			{
				return rectList.indexOf(r);		
			}
		}
		if((getX()<p_x && p_x<(getX()+diameter)) && (getY()<p_y && p_y<(getY()+diameter)))
		{
			return 10; //is in canvas
		}
		else
		{
			return -1; //not inside of anything
		}
		
	}
	

}


