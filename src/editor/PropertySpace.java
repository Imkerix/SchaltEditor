package editor;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Component;

import shared.GeometricObject;

import javax.swing.BoxLayout;

@SuppressWarnings("serial")
public class PropertySpace extends JPanel 
{
	
	private JTextField textField_Name;
	private String geomObjType;
	private GeometricObject geomObject;
	private JPanel innerPanel = new JPanel();
	private JPanel canvasPanel;
	
	private final JPanel surroundingpanel = new JPanel();
	
	public PropertySpace(GeometricObject p_geomObj) 
	{
		this.geomObjType = p_geomObj.getClass().getSimpleName();
		this.geomObject = p_geomObj;
		this.setBackground(Color.gray);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JLabel lblEigenschaften = new JLabel("Eigenschaften");
		lblEigenschaften.setAlignmentX(CENTER_ALIGNMENT);
		add(lblEigenschaften);
		
		JLabel lblName = new JLabel("Name : ");
		lblName.setAlignmentX(Component.CENTER_ALIGNMENT);
		innerPanel.add(lblName);
		
		textField_Name = new JTextField(geomObjType);
		textField_Name.setColumns(10);
		innerPanel.add(textField_Name);
		
	 	switch(geomObjType)
	    {
		    case "Kreis" : 	
		    	mkFirstXYLabels(); 
		    	mkWidthAndHeightLabels();
				break;
		    case "Rechteck" :
		    	mkFirstXYLabels(); 
		    	mkWidthAndHeightLabels();
				break;
		    case "Linie" : 	
		    	mkFirstXYLabels(); 
		    	mkSecondXYLabels();
				break;
		    case "Arc" :
		    	mkFirstXYLabels(); 
		    	mkWidthAndHeightLabels();
				break;
		    case "Connector" : 	 break;
		}
		
		
		
		
		innerPanel.setLayout(new GridLayout(0, 2, 0, 0));
		innerPanel.setBackground(Color.lightGray);
		surroundingpanel.setBackground(Color.lightGray);
		surroundingpanel.add(innerPanel);
		add(surroundingpanel);
		
		canvasPanel = new JPanel()
		{
			@Override
			public void paintComponent(Graphics g) 
			{
		        super.paintComponent(g);
		        
		        this.removeAll(); // without the JLabels would stay and cause trouble
		        
		        int allowedSpace = this.getWidth()-60-60;
		        
		        g.setColor(Color.white);
		        g.fillRect(55, this.getHeight()-allowedSpace-105, allowedSpace+10, allowedSpace+10);
		        g.setColor(Color.black);
				
			    switch(geomObjType)
			    {
			    case "Kreis" : 		 g.drawOval(60, this.getHeight()-allowedSpace-100, allowedSpace, allowedSpace); 
			    	mkWidthAndHeightThumbnailLabels();
			    	mkFirstXYThumbnailLabels(); 
					break;
			    case "Rechteck" :	 g.drawRect(60, this.getHeight()-allowedSpace-100, allowedSpace, allowedSpace);
			    	mkWidthAndHeightThumbnailLabels();
			    	mkFirstXYThumbnailLabels();
					break;
			    case "Linie" : 		 g.drawLine(60, this.getHeight()-allowedSpace-100, 60+allowedSpace, this.getHeight()-allowedSpace-100+allowedSpace); 
			    	mkFirstXYThumbnailLabels();
			    	mkSecondXYThumbnailLabels();
					break;
			    case "Arc" :		 g.drawArc(60, this.getHeight()-allowedSpace-100, allowedSpace, allowedSpace, 90, 90); 
			    	mkWidthAndHeightThumbnailLabels();
			    	mkFirstXYThumbnailLabels(); 
					break;
			    case "Connector" : 	 break;
			    }
		    }
				//// Begin : Thumbnail Lables
					public void mkFirstXYThumbnailLabels()
					{
							// Begin : Thumbnail Labels
								JLabel lbl_PointerX = new JLabel("X");
									lbl_PointerX.setBounds(57, this.getHeight()-(this.getWidth()-60-60)-130, 10, 10);
									this.add(lbl_PointerX);
							
								JLabel lbl_PointerY = new JLabel("Y");
									lbl_PointerY.setBounds(30, this.getHeight()-(this.getWidth()-60-60)-105, 10, 10);
									this.add(lbl_PointerY);
							// End : Thumbnail Labels	
					}
					public void mkSecondXYThumbnailLabels()
					{
							// Begin : Thumbnail Labels
								JLabel lbl_PointerX = new JLabel("X2");
									lbl_PointerX.setBounds(60 + (this.getWidth()-60-60)-5 , this.getHeight()-80, 20, 10);
									this.add(lbl_PointerX);
							
								JLabel lbl_PointerY = new JLabel("Y2");
									lbl_PointerY.setBounds(60 + (this.getWidth()-60-60)+20, this.getHeight()-105, 20, 10);
									this.add(lbl_PointerY);
							// End : Thumbnail Labels	
					}
					public void mkWidthAndHeightThumbnailLabels()
					{
							// Begin : Thumbnail Labels
								JLabel lbl_PointerWidth = new JLabel("Höhe");
									lbl_PointerWidth.setBounds(15, this.getHeight()-100-((this.getWidth()-60-60)/2)-20, 40, 20);
									this.add(lbl_PointerWidth);
								
								JLabel lbl_PointerHeight = new JLabel("Breite");
									lbl_PointerHeight.setBounds(60 + ((this.getWidth()-60-60)/2)-20, this.getHeight()-(this.getWidth()-60-60)-130, 60, 20);
									this.add(lbl_PointerHeight);
							// End : Thumbnail Labels	
					}
				//// End : Thumbnail Lables
		};
		canvasPanel.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		canvasPanel.setBackground(Color.lightGray);
		canvasPanel.setDoubleBuffered(true);
		canvasPanel.setLayout(null);
		add(canvasPanel);
	}
	
	//// Begin : Value Lables
		public void mkFirstXYLabels()
		{
			// Begin : Value Labels
				JLabel lbl_x = new JLabel("X Wert : ");
					innerPanel.add(lbl_x);
				JTextField txtF_x = new JTextField(String.valueOf(geomObject.getX()));
					innerPanel.add(txtF_x);
			
				JLabel lbl_y = new JLabel("Y Wert : ");
					innerPanel.add(lbl_y);
				JTextField txtF_y = new JTextField(String.valueOf(geomObject.getY()));
					innerPanel.add(txtF_y);
			// End : Value Labels
		}
		public void mkSecondXYLabels()
		{
			// Begin : Value Labels
				JLabel lbl_x = new JLabel("X2 Wert: ");
					innerPanel.add(lbl_x);
				JTextField txtF_x = new JTextField(String.valueOf(geomObject.getX()));
					innerPanel.add(txtF_x);
			
				JLabel lbl_y = new JLabel("Y2 Wert: ");
					innerPanel.add(lbl_y);
				JTextField txtF_y = new JTextField(String.valueOf(geomObject.getY()));
					innerPanel.add(txtF_y);
			// End : Value Labels
		}
		public void mkWidthAndHeightLabels()
		{
			// Begin : Value Labels
				JLabel lbl_width = new JLabel("Breite : ");
					innerPanel.add(lbl_width);
				JTextField txtF_width = new JTextField(String.valueOf(geomObject.getWidth()));
					innerPanel.add(txtF_width);
			
				JLabel lbl_height = new JLabel("Höhe : ");
					innerPanel.add(lbl_height);
				JTextField txtF_height = new JTextField(String.valueOf(geomObject.getHeight()));
					innerPanel.add(txtF_height);
			// End : Value Labels
		}
	////Begin : Value Lables
}
