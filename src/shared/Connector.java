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
	private static double width = 8;
	private static double height = 8;


	public Connector(double x, double y)
	{
		this.x = x;
		this.y = y;
	}	

	public void draw(Graphics g, boolean b, DrawObject d) 
	{
		Graphics2D g2d = (Graphics2D) g;
		if(b || (!b && (d != null)))
		{
			g2d.drawOval((int) x, (int) y, (int) width, (int) height);
			if(isKlicked || connectedTo != null)
			{
				g2d.fillOval((int) x, (int) y, (int) width, (int) height);
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

	public static void setDiameter(double diameter)
	{
		height = diameter;
		width = diameter;
	}

	public static double getDiameter()
	{
		return height;
	}

	@Override
	public int isInside(int p_x, int p_y)
	{
		for (Rectangle r : rectList) 
		{
			if((r.getX()<p_x && p_x<(r.getX()+width)) && (r.getY()<p_y && p_y<(r.getY()+height))) 
			{
				return rectList.indexOf(r);		
			}
		}
		if((getX()<p_x && p_x<(getX()+width)) && (getY()<p_y && p_y<(getY()+height)))
		{
			return 10; //is in canvas
		}
		else
		{
			return -1; //not inside of anything
		}

	}
	
	public static void staticZoom(double zoomFactor)
	{
		width = (width*zoomFactor);
		height = (height*zoomFactor);
	}




}


