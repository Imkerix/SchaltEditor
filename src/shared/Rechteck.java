package shared;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

public class Rechteck extends GeometricObject
{
	public Rechteck(double x, double y, double width, double height)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public void draw(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawRect((int) x, (int) y, (int) width, (int) height);
		
		 if (width < 0)
		 {
			 width = Math.abs(width);
			 x = x - width;
			 g2d.drawRect((int) x , (int) y, (int) width, (int) height);
		 }
		 if (height < 0)
		 {
			 height = Math.abs(height);
			 y = y - height;
			 g2d.drawRect((int) x , (int) y, (int) width, (int) height);
		 }
		 if (width < 0 && height < 0)
		 {
			 width = Math.abs(width);
			 x = x - width;
			 height = Math.abs(height);
			 y = y - height;
			 g2d.drawRect((int) x , (int) y, (int) width, (int) height);
		 }
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

