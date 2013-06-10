package shared;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

public class Kreis extends GeometricObject
{
	public Kreis(double x, double y, double width, double height)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public void draw(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawOval((int) x, (int) y, (int) width, (int) height);
	}
	
	
	
	@Override
	public void expand(int grabber, Point endMove, int canvasWidth, int canvasHeight)
	{
		Point middle = new Point((int) (x + (width / 2)), (int) (y + (height / 2)));

		int distanceToEndPoint = (int) Math.sqrt(Math.pow((middle.getY()-endMove.getY()), 2)+Math.pow((middle.getX()-endMove.getX()), 2));
		int distanceToStartPoint = (int) Math.sqrt(Math.pow((middle.getY()-getY()), 2)+Math.pow((middle.getX()-getX()), 2));
		
		int diff = distanceToEndPoint - distanceToStartPoint;
			
		if(((x - diff) > 0) 
			&& ((y - diff) > 0)
			&& ((x + (width + (diff * 2))) < canvasWidth) 
			&& ((y + (height + (diff * 2))) < canvasHeight)	
			)
		{	
			switch (grabber)
			{
			case 0:
					width += (diff * 2);
					height += (diff * 2);
					x -= diff;
					y -= diff;
					break;
			case 1: 
					y -= diff;
					x -= diff;
					width += (diff * 2);
					height += (diff * 2);
					break;
			case 2: 
					x -= diff;
					y -= diff;
					width += (diff * 2);
					height += (diff * 2);
					break;
			case 3: 
					x -= diff;
					y -= diff;		
					width += (diff * 2);
					height += (diff * 2); 
					break;
			}
		}	
	}
}
