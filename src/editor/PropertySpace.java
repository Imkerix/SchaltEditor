package editor;

import java.awt.Component;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import mainFrame.DrawComponent;
import shared.GeometricObject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

@SuppressWarnings("serial")
public class PropertySpace extends JPanel 
{
	private JTextField textField_Name;
	private GeometricObject geomObject;
	private JPanel innerPanel = new JPanel();
	private JPanel canvasPanel;
	private int canvasWidth = 0;
	private int canvasHeight = 0;
	private final JPanel surroundingpanel = new JPanel();
	private DrawComponent canvas;
	
	
	public PropertySpace(GeometricObject p_geomObj, DrawComponent p_canvas) 
	{
		this.geomObject = p_geomObj;
		this.canvas = p_canvas;
		this.canvasWidth = canvas.getWidth();
		this.canvasHeight = canvas.getHeight();
		this.setBackground(Color.gray);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JLabel lblEigenschaften = new JLabel("Eigenschaften");
		lblEigenschaften.setAlignmentX(CENTER_ALIGNMENT);
		add(lblEigenschaften);
		
		JLabel lblName = new JLabel("Name : ");
		lblName.setAlignmentX(Component.CENTER_ALIGNMENT);
		innerPanel.add(lblName);
		
		textField_Name = new JTextField(p_geomObj.toString());
		textField_Name.addFocusListener(new FocusListener() 
		{
			String beforeFocus;
			
			@Override
			public void focusLost(FocusEvent e) 
			{
				if(!(beforeFocus.equals(textField_Name.getText()))) // if text was changed
				{
					geomObject.setObjectName(textField_Name.getText());
				}
			}
			
			@Override
			public void focusGained(FocusEvent e) 
			{
				beforeFocus = textField_Name.getText(); 
			}
		});
		textField_Name.addPropertyChangeListener(new PropertyChangeListener() 
		{
			@Override
			public void propertyChange(PropertyChangeEvent evt) 
			{
				geomObject.setObjectName(textField_Name.getText());
			}
		});
		textField_Name.setColumns(10);
		innerPanel.add(textField_Name);
		
	 	switch(p_geomObj.getClass().getSimpleName())
	    {
		    case "Kreis" : 	
		    	mkFirstXYLabels(); 
		    	mkCircleLabel();
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
				
			    switch(geomObject.getClass().getSimpleName())
			    {
				    case "Kreis" : 		 g.drawOval(60, this.getHeight()-allowedSpace-100, allowedSpace, allowedSpace); 
				    					 g.drawString("Durchmesser", (this.getWidth()/2)-43, this.getHeight()-(allowedSpace/2)-100);
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
				    case "Connector" : 	 
				    	break;
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
				JLabel lbl_x = new JLabel("X Wert :");
					innerPanel.add(lbl_x);
				final JTextField txtF_x = new JTextField(String.valueOf((int)(geomObject.getX())));
				    txtF_x.addFocusListener(new FocusListener() 
					{
						String beforeFocus;
						
						@Override
						public void focusLost(FocusEvent e) 
						{
							if(!(beforeFocus.equals(txtF_x.getText()))) // if text was changed
							{
								Point startMove = new Point((int)geomObject.getX(), (int)geomObject.getY());
								Point endMove = new Point(Integer.parseInt(txtF_x.getText()), (int)geomObject.getY());
								geomObject.move(startMove, endMove, canvasWidth, canvasHeight);
								canvas.repaint();
							}
						}
						
						@Override
						public void focusGained(FocusEvent e) 
						{
							beforeFocus = txtF_x.getText(); 
						}
					});
				 	txtF_x.addActionListener(new ActionListener() 
				 	{
						@Override
						public void actionPerformed(ActionEvent e) 
						{
							Point startMove = new Point((int)geomObject.getX(), (int)geomObject.getY());
							Point endMove = new Point(Integer.parseInt(txtF_x.getText()), (int)geomObject.getY());
							geomObject.move(startMove, endMove, canvasWidth, canvasHeight);
							canvas.repaint();
						}
					});
					innerPanel.add(txtF_x);
			
				JLabel lbl_y = new JLabel("Y Wert : ");
					innerPanel.add(lbl_y);
				final JTextField txtF_y = new JTextField(String.valueOf((int)(geomObject.getY())));
					txtF_y.addFocusListener(new FocusListener() 
					{
						String beforeFocus;
						
						@Override
						public void focusLost(FocusEvent e) 
						{
							if(!(beforeFocus.equals(txtF_y.getText()))) // if text was changed
							{
								Point startMove = new Point((int)geomObject.getX(), (int)geomObject.getY());
								Point endMove = new Point((int)geomObject.getX(), Integer.parseInt(txtF_y.getText()));
								geomObject.move(startMove, endMove, canvasWidth, canvasHeight);
								canvas.repaint();
							}
						}
						
						@Override
						public void focusGained(FocusEvent e) 
						{
							beforeFocus = txtF_y.getText(); 
						}
					});
					txtF_y.addActionListener(new ActionListener() 
				 	{
						@Override
						public void actionPerformed(ActionEvent e) 
						{
							Point startMove = new Point((int)geomObject.getX(), (int)geomObject.getY());
							Point endMove = new Point((int)geomObject.getX(), Integer.parseInt(txtF_y.getText()));
							geomObject.move(startMove, endMove, canvasWidth, canvasHeight);
							canvas.repaint();
						}
					});
					innerPanel.add(txtF_y);
			// End : Value Labels
		}
		public void mkSecondXYLabels()
		{
			// Begin : Value Labels
				JLabel lbl_x = new JLabel("X2 Wert: ");
					innerPanel.add(lbl_x);
				final JTextField txtF2_x = new JTextField(String.valueOf((int)(geomObject.getX()+geomObject.getWidth())));
					txtF2_x.addFocusListener(new FocusListener() 
					{
						String beforeFocus;
						
						@Override
						public void focusLost(FocusEvent e) 
						{
							if(!(beforeFocus.equals(txtF2_x.getText()))) // if text was changed
							{
								Point endMove = new Point(Integer.parseInt(txtF2_x.getText()), (int)(geomObject.getY()+geomObject.getHeight()));
								geomObject.expand(1, endMove, canvasWidth, canvasHeight);
								canvas.repaint();
							}
						}
						
						@Override
						public void focusGained(FocusEvent e) 
						{
							beforeFocus = txtF2_x.getText(); 
						}
					});
					txtF2_x.addActionListener(new ActionListener() 
					{
						@Override
						public void actionPerformed(ActionEvent e) 
						{
							Point endMove = new Point(Integer.parseInt(txtF2_x.getText()), (int)(geomObject.getY()+geomObject.getHeight()));
							geomObject.expand(1, endMove, canvasWidth, canvasHeight);
							canvas.repaint();
						}
					});
					innerPanel.add(txtF2_x);
			
				JLabel lbl_y = new JLabel("Y2 Wert: ");
					innerPanel.add(lbl_y);
				final JTextField txtF2_y = new JTextField(String.valueOf((int)(geomObject.getY()+geomObject.getHeight())));
					txtF2_y.addFocusListener(new FocusListener() 
					{
						String beforeFocus;
						
						@Override
						public void focusLost(FocusEvent e) 
						{
							if(!(beforeFocus.equals(txtF2_y.getText()))) // if text was changed
							{
								Point endMove = new Point((int)(geomObject.getX()+geomObject.getWidth()), Integer.parseInt(txtF2_y.getText()));
								geomObject.expand(1, endMove, canvasWidth, canvasHeight);
								canvas.repaint();
							}
						}
						
						@Override
						public void focusGained(FocusEvent e) 
						{
							beforeFocus = txtF2_y.getText(); 
						}
					});
					txtF2_y.addActionListener(new ActionListener() 
					{
						@Override
						public void actionPerformed(ActionEvent e) 
						{
							Point endMove = new Point((int)(geomObject.getX()+geomObject.getWidth()), Integer.parseInt(txtF2_y.getText()));
							geomObject.expand(1, endMove, canvasWidth, canvasHeight);
							canvas.repaint();
						}
					});
					innerPanel.add(txtF2_y);
			// End : Value Labels
		}
		public void mkWidthAndHeightLabels()
		{
			// Begin : Value Labels
				JLabel lbl_width = new JLabel("Breite : ");
					innerPanel.add(lbl_width);
				final JTextField txtF_width = new JTextField(String.valueOf((int)(geomObject.getWidth())));
					txtF_width.addFocusListener(new FocusListener() 
					{
						String beforeFocus;
						
						@Override
						public void focusLost(FocusEvent e) 
						{
							if(!(beforeFocus.equals(txtF_width.getText()))) // if text was changed
							{
								Point endMove = new Point((int)(Integer.parseInt(txtF_width.getText())+geomObject.getX()) ,(int)geomObject.getY());
								geomObject.expand(1, endMove, canvasWidth, canvasHeight);
								canvas.repaint();
							}
						}
						
						@Override
						public void focusGained(FocusEvent e) 
						{
							beforeFocus = txtF_width.getText(); 
						}
					});
					txtF_width.addActionListener(new ActionListener() 
					{
						@Override
						public void actionPerformed(ActionEvent e) 
						{
							Point endMove = new Point((int)(Integer.parseInt(txtF_width.getText())+geomObject.getX()) ,(int)geomObject.getY());
							geomObject.expand(1, endMove, canvasWidth, canvasHeight);
							canvas.repaint();
						}
					});
					innerPanel.add(txtF_width);
			
				JLabel lbl_height = new JLabel("Höhe : ");
					innerPanel.add(lbl_height);
				final JTextField txtF_height = new JTextField(String.valueOf((int)(geomObject.getHeight())));
					txtF_height.addFocusListener(new FocusListener() 
					{
						String beforeFocus;
						
						@Override
						public void focusLost(FocusEvent e) 
						{
							if(!(beforeFocus.equals(txtF_height.getText()))) // if text was changed
							{
								Point endMove = new Point((int)geomObject.getX(), (int)(Integer.parseInt(txtF_height.getText())+geomObject.getY()));
								geomObject.expand(2, endMove, canvasWidth, canvasHeight);
								canvas.repaint();
							}
						}
						
						@Override
						public void focusGained(FocusEvent e) 
						{
							beforeFocus = txtF_height.getText(); 
						}
					});
					txtF_height.addActionListener(new ActionListener() 
					{
						@Override
						public void actionPerformed(ActionEvent e) 
						{
							Point endMove = new Point((int)geomObject.getX(), (int)(Integer.parseInt(txtF_height.getText())+geomObject.getY()));
							geomObject.expand(2, endMove, canvasWidth, canvasHeight);
							canvas.repaint();
						}
					});
					innerPanel.add(txtF_height);
			// End : Value Labels
		}
		public void mkCircleLabel()
		{
			// Begin : Value Labels
				JLabel lbl_x = new JLabel("Durchmesser :");
					innerPanel.add(lbl_x);
				final JTextField txtF_diameter = new JTextField(String.valueOf((int)(geomObject.getWidth())));
				txtF_diameter.addFocusListener(new FocusListener() 
				{
					String beforeFocus;
					
					@Override
					public void focusLost(FocusEvent e) 
					{
						if(!(beforeFocus.equals(txtF_diameter.getText()))) // if text was changed
						{
							Point endMove = new Point((int)(Integer.parseInt(txtF_diameter.getText())+geomObject.getX()) ,(int)(Integer.parseInt(txtF_diameter.getText())+geomObject.getY()));
							geomObject.expand(0, endMove, canvasWidth, canvasHeight);
							canvas.repaint();
						}
					}
					
					@Override
					public void focusGained(FocusEvent e) 
					{
						beforeFocus = txtF_diameter.getText(); 
					}
				});
				txtF_diameter.addActionListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent e)
					{
						Point endMove = new Point((int)(Integer.parseInt(txtF_diameter.getText())+geomObject.getX()) ,(int)(Integer.parseInt(txtF_diameter.getText())+geomObject.getY()));
						geomObject.expand(0, endMove, canvasWidth, canvasHeight);
						canvas.repaint();
					}
				});
				innerPanel.add(txtF_diameter);
			// End : Value Labels
		}
	////Begin : Value Lables
}
