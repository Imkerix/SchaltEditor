package shared;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.io.Serializable;

public class Arc extends GeometricObject implements Serializable{
	
	private static final long serialVersionUID = -5441707071346189549L;
	
	//// Variables only Arc has a use for ////
	double startAngle, arcAngle;
	
	// Surrounding points
		double dx;
		double dy;
		double dwidth;
		double dheight;
	// Surrounding points

	
	public Arc(double p_x, double p_y, double p_width, double p_height, double p_startAngle, double p_arcAngle)
	{
		this.x = p_x;
		this.y = p_y;
		this.width = p_width;
		this.height = p_height;
		this.startAngle = p_startAngle;
		this.arcAngle = p_arcAngle;
	}
	
	public void draw(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		if(line != null)
		{
			g2d.setStroke(line);
			g2d.drawArc((int)x, (int)y, (int)(width), (int)(height), (int)(startAngle), (int)(arcAngle));
			g2d.setStroke(new BasicStroke());
		}
		else
		{
			g2d.drawArc((int)x, (int)y, (int)(width), (int)(height), (int)(startAngle), (int)(arcAngle));
		}
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
			rectList.add(new Rectangle((int)x-5, (int)y-5, 10, 10));

		//Grabber1
		g.setColor(Color.white);
		g.fillRect((int)(x+width)-5, (int)y-5, 10, 10);
		g.setColor(Color.black);
		g.drawRect((int)(x+width)-5, (int)y-5, 10, 10);
			rectList.add(new Rectangle((int)(x+width)-5, (int)y-5, 10, 10));

		//Grabber2
		g.setColor(Color.white);
		g.fillRect((int)x-5, (int)(y+height)-5, 10, 10);
		g.setColor(Color.black);
		g.drawRect((int)x-5, (int)(y+height)-5, 10, 10);
			rectList.add(new Rectangle((int)x-5, (int)(y+height)-5, 10, 10));

		//Grabber3
		g.setColor(Color.white);
		g.fillRect((int)(x+width)-5, (int)(y+height)-5, 10, 10);
		g.setColor(Color.black);
		g.drawRect((int)(x+width)-5, (int)(y+height)-5, 10, 10);
			rectList.add(new Rectangle((int)(x+width)-5, (int)(y+height)-5, 10, 10));
	}
	
	@Override
	public void expand(int grabber, Point endMove, int canvasWidth, int canvasHeight)
	{
		if(((endMove.getX()) > 0)   // for X
				&& ((endMove.getY()) > 0) // for Y
				&& ((endMove.getX()) < canvasWidth) 
				&& ((endMove.getY()) < canvasHeight)	
				)
		{
			Point startMove = new Point();
			
			switch (grabber)
			{
				case 0:
							
						startMove = new Point((int) x,(int) y);
						width += (startMove.getX()-endMove.getX());
						height += (startMove.getY()-endMove.getY());
						x = endMove.getX();
						y = endMove.getY();
						break;
				
				case 1:
						startMove = new Point((int) (x + width),(int) y);	
						width += (endMove.getX()-startMove.getX());
						height += (startMove.getY()-endMove.getY());
						x = endMove.getX()-width;
						y = endMove.getY();
						break;
				
				case 2:	
	
						startMove = new Point((int) x,(int) (y + height));
						width += (startMove.getX()-endMove.getX());
						height += (endMove.getY()-startMove.getY());
						x = endMove.getX();
						y = endMove.getY()-height;
						break;
				
				case 3:
				{	
						startMove = new Point((int) (x + width),(int) (y + height));
						width += (endMove.getX()-startMove.getX());
						height += (endMove.getY()-startMove.getY());
						x = endMove.getX()-width;
						y = endMove.getY()-height;
						break;
				}
			}
		}
	}
	
}
