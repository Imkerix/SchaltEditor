package shared;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

public class Linie extends GeometricObject
{
	double punkt2X;
	double punkt2Y;
	double dx;
	double dy;
	double dwidth;
	double dheight;
	
	public Linie(double x1, double y1, double x2, double y2)
	{
		this.x = x1;
		this.y = y1;
		punkt2X = x2;
		punkt2Y = y2;
		this.width = punkt2X-x;
		this.height = punkt2Y-y;
		dx = x;
		dy = y;
		dwidth = width;
		dheight = height;
	}
	
	public void draw(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawLine((int)x, (int)y, (int)(punkt2X), (int)(punkt2Y));
	}
	
	@Override
	public void move(Point startMove, Point endMove, int canvasWidth, int canvasHeight)
	{
		if(((x + endMove.getX()-startMove.getX()) > 0) 
				&& ((y + endMove.getY()-startMove.getY()) > 0)
				&& ((x + width + endMove.getX()-startMove.getX()) < canvasWidth) 
				&& ((y + height + endMove.getY()-startMove.getY()) < canvasHeight)	
				)
		{
			x += endMove.getX()-startMove.getX();
			y += endMove.getY()-startMove.getY();
			punkt2X += endMove.getX()-startMove.getX();
			punkt2Y += endMove.getY()-startMove.getY();	
		}
	}
	
	@Override
	public double getX()
	{
		return dx;
	}
	@Override
	public double getY()
	{
		return dy;
	}
	@Override
	public double getWidth()
	{
		return dwidth;
	}
	@Override
	public double getHeight()
	{
		return dheight;
	}
	
	public void drawOutline(Graphics g)
	{
		Graphics2D g2d = (Graphics2D)g;
		g2d.setStroke(new BasicStroke(1.0f, BasicStroke.CAP_BUTT,BasicStroke.JOIN_BEVEL,1.0f,new float[]{5.0f},0.0f));
		
		if(x < punkt2X)
		{
			dx = x;
			dwidth = punkt2X - x;
		}
		if(x > punkt2X)
		{
			dx = punkt2X;
			dwidth = x - punkt2X;
		}
		
		if(y < punkt2Y)
		{
			dy = y;
			dheight = punkt2Y - y;
		}
		if(y > punkt2Y)
		{
			dy = punkt2Y;
			dheight = y - punkt2Y;
		}
		
		g2d.drawRect((int)dx, (int)dy, (int)(dwidth), (int)(dheight));	
		g2d.setStroke(new BasicStroke());
	}
	
	@Override
	public void drawGrabbers(Graphics g)
	{
		super.rectList.clear();
		//Grabber0
		g.setColor(Color.white);
		g.fillRect((int)x-5, (int)y-5, 10, 10);
		g.setColor(Color.black);
		g.drawRect((int)x-5, (int)y-5, 10, 10);
			super.rectList.add(new Rectangle((int)x-5, (int)y-5, 10, 10));

		//Grabber1
		g.setColor(Color.white);
		g.fillRect((int)(punkt2X)-5,(int)(punkt2Y)-5 ,10,10);
		g.setColor(Color.black);
		g.drawRect((int)(punkt2X)-5, (int)(punkt2Y)-5 ,10,10);
			super.rectList.add(new Rectangle((int)(punkt2X)-5, (int)(punkt2Y)-5 ,10,10));
	}
	
	@Override
	public void expand(int grabber, Point endMove, int canvasWidth, int canvasHeight)
	{
		if((endMove.getX() > 0)
		&& (endMove.getY() > 0)
		&& (endMove.getX() < canvasWidth)
		&& (endMove.getY() < canvasHeight))
		{
			switch (grabber)
			{
			case 0:
				x = endMove.getX();
				y = endMove.getY();
				break;
				
			case 1:
				punkt2X = endMove.getX();
				punkt2Y = endMove.getY();
				break;
			}
		}
	}

}
