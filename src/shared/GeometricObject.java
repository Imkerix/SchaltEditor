package shared;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JToolBar;

public abstract class GeometricObject 
{	
	double x;
	double y;
	double width;
	double height;
	ArrayList<Rectangle> rectList = new ArrayList<Rectangle>();
	JToolBar optionsBar;
	
	public void draw(Graphics g){};

	/**
	 * Changes the x and y coordinates to paint the {@link GeometricObject} somewhere else.
	 * @param startMove the Point where the movement starts.
	 * @param endMove the Point where the movement ends.
	 */
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
		}
	}
	
	public abstract void expand(int grabber, Point endMove, int canvasWidth, int canvasHeight); //could be abstract
	
	/**
	 * Returns the x value of the {@link GeometricObject}. <br>
	 * @return x a double representing the x value of the {@link GeometricObject}.
	 */
	public double getX()
	{
		return x;
	}
	
	
	/**
	 * Returns the y value of the {@link GeometricObject}. <br>
	 * @return y a double representing the y value of the {@link GeometricObject}.
	 */
	public double getY()
	{
		return y;
	}
	
	
	/**
	 * Returns the width. <br>
	 * @return width a double representing the width of the {@link GeometricObject}.
	 */
	public double getWidth()
	{
		return width;
	}
	
	/**
	 * Returns the height. <br>
	 * @return height a double representing the height of the {@link GeometricObject}.
	 */
	public double getHeight()
	{
		return height;
	}
	
	/**
	 * Checks if the coordinates p_x and p_y have hit the {@link GeometricObject}.
	 * 
	 * @param p_x the x coordinate to check.
	 * @param p_y the y coordinate to check.
	 * @return An int that represents the area where the coordinates p_x and p_y hit the {@link GeometricObject}.
	 * 
	 * <br>
	 * 10 = hit inside the Object<br>
	 * 0  = hit in grabber 0<br>
	 * 1  = hit in grabber 1<br>
	 * 2  = hit in grabber 2<br>
	 * 3  = hit in grabber 3<br>
	 * <br>
	 */
	public int isInside(int p_x, int p_y)
	{
		for (Rectangle r : rectList) 
		{
			if((r.getX()<p_x && p_x<(r.getX()+r.getWidth())) && (r.getY()<p_y && p_y<(r.getY()+r.getHeight()))) 
			{
				return rectList.indexOf(r);		
			}
		}
		if((getX()<p_x && p_x<(getX()+getWidth())) && (getY()<p_y && p_y<(getY()+getHeight())))
		{
			return 10; //is in canvas
		}
		else
		{
			return -1; //not inside of anything
		}
		
	}
	
	/**
	 *  Draw&lsquo;s the outline for an {@link GeometricObject}
	 * @param g the Graphics Object used to draw the outline
	 */
	public void drawOutline(Graphics g)
	{
		Graphics2D g2d = (Graphics2D)g; // we want to use strokes
		g2d.setStroke(new BasicStroke(1.0f, BasicStroke.CAP_BUTT,BasicStroke.JOIN_BEVEL,1.0f,new float[]{5.0f},0.0f)); // we need a dashed line
		g2d.drawRect((int)x, (int)y, (int)width, (int)height);		
		g2d.setStroke(new BasicStroke());
	}
	
	/**
	 * Resizes the object by the value of the parameter.
	 * @param zoom
	 */
	public void zoom(double zoom)
	{
		    x = (x*zoom);
		    y = (y*zoom);
		    width = (width*zoom);
		    height = (height*zoom);   
	}
	
	
	/**
	 *  Draw&lsquo;s the grabbers for an {@link GeometricObject}
	 * @param g the Graphics Object used to draw the grabbers
	 */
	public void drawGrabbers(Graphics g)
	{
				rectList.clear();
				
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
	public void setOptionsBar()
	{
		optionsBar = new JToolBar();
		JComboBox<String> dotted = new JComboBox<String>();
		optionsBar.add(dotted);
	}

}

