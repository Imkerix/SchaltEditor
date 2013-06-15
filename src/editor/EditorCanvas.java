package editor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import org.apache.batik.swing.JSVGCanvas;

public class EditorCanvas extends JSVGCanvas{

	
	private Color gridColor = new Color(210, 210, 210);
	private int gridInterval = 30;


	////Begin : Grid
		public void drawGrid(Graphics g)
		{
		    Graphics2D g2d = (Graphics2D) g;
		    g2d.setColor(gridColor);
	
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
		
		public void setGridInterval(int interval)
		{
		    this.gridInterval = interval;
		}
		public void setGridColor(Color c)
		{
			gridColor = c;
		}
	
}

