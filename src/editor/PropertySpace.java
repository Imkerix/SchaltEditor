package editor;

import java.awt.Color;
import java.awt.Graphics;


import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import shared.Arc;
import shared.GeometricObject;
import shared.Kreis;
import shared.Linie;
import shared.Rechteck;

public class PropertySpace extends JPanel{
	
	
	private GeometricObject geomObj;
	
	public void setup(GeometricObject geomObj) 
	{
	    // subBegin : Fill the JPanel with useful property stuff.
			this.removeAll();
	        this.geomObj = geomObj;
	        this.setDoubleBuffered(true);
	        this.setBackground(Color.lightGray);
	        
	        JLabel lbl_header = new JLabel("Eigenschaften");
	        	lbl_header.setBounds((this.getWidth()/2)-60, 4, 120, 20);
	        	this.add(lbl_header);
	        JSeparator seperator = new JSeparator(JSeparator.HORIZONTAL);
	        	seperator.setBounds(0, 22, this.getWidth(), 1);
	        	this.add(seperator);
	        
	        		JLabel lbl_name = new JLabel("Name : ");
	        			lbl_name.setBounds(10, 34, 70, 30);
	        			this.add(lbl_name);
		        	JTextField geometricObject_Name = new JTextField(geomObj.toString()); // Name of the Geometricobject
			        	geometricObject_Name.setBounds(75, 39, this.getWidth()-75-10, 20);
			        	geometricObject_Name.setFont(lbl_name.getFont());
			        	this.add(geometricObject_Name);
	    // subEnd : Fill the JPanel with useful property stuff.    
	}
	
	@Override
	public void paintComponent(Graphics g) 
	{
        super.paintComponent(g);
        int allowedSpace = this.getWidth()-60-60;

        g.setColor(Color.white);
        g.fillRect(55, this.getHeight()-allowedSpace-105, allowedSpace+10, allowedSpace+10);
        g.setColor(Color.black);
        
		GeometricObject temp = geomObj;
		
	    switch(temp.getClass().getSimpleName())
	    {
	    case "Kreis" : 		 temp = new Kreis	(60, this.getHeight()-allowedSpace-100, allowedSpace, allowedSpace); mkWidthAndHeightLabels(); mkFirstXYLabels();
			break;
	    case "Rechteck" :	 temp = new Rechteck(60, this.getHeight()-allowedSpace-100, allowedSpace, allowedSpace); mkWidthAndHeightLabels(); mkFirstXYLabels();
			break;
	    case "Linie" : 		 temp = new Linie	(60, this.getHeight()-allowedSpace-100, 60+allowedSpace, this.getHeight()-allowedSpace-100+allowedSpace); mkFirstXYLabels(); mkSecondXYLabels();
			break;
	    case "Arc" :		 temp = new Arc		(60, this.getHeight()-allowedSpace-100, allowedSpace, allowedSpace, 90, 90); mkWidthAndHeightLabels(); mkFirstXYLabels(); 
			break;
	    case "Connector" : 	 break;
	    }
		
		temp.draw(g);
		
		
    }
	public void mkFirstXYLabels()
	{
		// Begin : Value Labels
		JLabel lbl_x = new JLabel("X Wert : ");
			lbl_x.setBounds(10, 61, 70, 30);
			this.add(lbl_x);
		JTextField txtF_x = new JTextField(String.valueOf(geomObj.getX()));
			txtF_x.setBounds(75, 66, this.getWidth()-75-10, 20);
			this.add(txtF_x);
	
		JLabel lbl_y = new JLabel("Y Wert : ");
			lbl_y.setBounds(10, 88, 70, 30);
			this.add(lbl_y);
		JTextField txtF_y = new JTextField(String.valueOf(geomObj.getY()));
			txtF_y.setBounds(75, 93, this.getWidth()-75-10, 20);
			this.add(txtF_y);
		// End : Value Labels
			
		// Begin : Thumbnail Labels
		JLabel lbl_PointerX = new JLabel("X");
			lbl_PointerX.setBounds(57, this.getHeight()-(this.getWidth()-60-60)-130, 10, 10);
			this.add(lbl_PointerX);
	
		JLabel lbl_PointerY = new JLabel("Y");
			lbl_PointerY.setBounds(30, this.getHeight()-(this.getWidth()-60-60)-105, 10, 10);
			this.add(lbl_PointerY);
		// End : Thumbnail Labels	
	}
	
	public void mkSecondXYLabels()
	{
		// Begin : Value Labels
		JLabel lbl_x = new JLabel("X2 Wert: ");
			lbl_x.setBounds(10, 115, 70, 30);
			this.add(lbl_x);
		JTextField txtF_x = new JTextField(String.valueOf(geomObj.getX()));
			txtF_x.setBounds(75, 120, this.getWidth()-75-10, 20);
			this.add(txtF_x);
	
		JLabel lbl_y = new JLabel("Y2 Wert: ");
			lbl_y.setBounds(10, 142, 70, 30);
			this.add(lbl_y);
		JTextField txtF_y = new JTextField(String.valueOf(geomObj.getY()));
			txtF_y.setBounds(75, 147, this.getWidth()-75-10, 20);
			this.add(txtF_y);
		// End : Value Labels
			
		// Begin : Thumbnail Labels
		JLabel lbl_PointerX = new JLabel("X2");
			lbl_PointerX.setBounds(60 + (this.getWidth()-60-60)-5 , this.getHeight()-80, 20, 10);
			this.add(lbl_PointerX);
	
		JLabel lbl_PointerY = new JLabel("Y2");
			lbl_PointerY.setBounds(60 + (this.getWidth()-60-60)+20, this.getHeight()-105, 20, 10);
			this.add(lbl_PointerY);
		// End : Thumbnail Labels	
	}
	
	public void mkWidthAndHeightLabels()
	{
		// Begin : Value Labels
		JLabel lbl_width = new JLabel("Breite : ");
			lbl_width.setBounds(10, 115, 70, 30);
			this.add(lbl_width);
		JTextField txtF_width = new JTextField(String.valueOf(geomObj.getWidth()));
			txtF_width.setBounds(75, 120, this.getWidth()-75-10, 20);
			this.add(txtF_width);
	
		JLabel lbl_height = new JLabel("Höhe : ");
			lbl_height.setBounds(10, 142, 70, 30);
			this.add(lbl_height);
		JTextField txtF_height = new JTextField(String.valueOf(geomObj.getHeight()));
			txtF_height.setBounds(75, 147, this.getWidth()-75-10, 20);
			this.add(txtF_height);
		// End : Value Labels
		
		// Begin : Thumbnail Labels
		JLabel lbl_PointerWidth = new JLabel("Höhe");
			lbl_PointerWidth.setBounds(15, this.getHeight()-100-((this.getWidth()-60-60)/2)-20, 40, 20);
			this.add(lbl_PointerWidth);
		
		JLabel lbl_PointerHeight = new JLabel("Breite");
			lbl_PointerHeight.setBounds(60 + ((this.getWidth()-60-60)/2)-20, this.getHeight()-(this.getWidth()-60-60)-130, 60, 20);
			this.add(lbl_PointerHeight);
		// End : Thumbnail Labels	
	}
}
