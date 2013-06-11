package shared;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import mainFrame.DrawObject;

public class Connector extends GeometricObject
{
	private Connector connectedTo = null;
	private boolean isKlicked = false;


	public Connector(double x, double y, double width, double height)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
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
		// TODO Automatisch generierter Methodenstub

	}

}


