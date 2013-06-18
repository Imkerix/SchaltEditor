package editor;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Point;
import shared.Connector;
import shared.GeometricObject;
import shared.Kreis;
import shared.Linie;
import shared.Rechteck;

import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;
import org.apache.batik.swing.JSVGCanvas;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JList;
import javax.swing.JSplitPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.JSeparator;

/**
 * 
 * @author erik heinisch
 * @version 0.3 - 0.4
 */
@SuppressWarnings("serial")
public class EditorGUI extends JFrame 
{
	//// Begin : Stuff needed in several Method that can't invoke each other nicely
	private JList<String> list;
	private Point startMove;
	private EditorCanvas canvasleft;
	private EditorCanvas canvasright;
	private ArrayList<GeometricObject> geomListleft = new ArrayList<GeometricObject>();
	private ArrayList<GeometricObject> geomListright = new ArrayList<GeometricObject>();
	private GeometricObject actObjectleft;
	private GeometricObject actObjectright;
	private JTabbedPane mytabbedpane = new JTabbedPane();
	private JSplitPane splitPane = new JSplitPane();
	private JScrollPane scrollPane = new JScrollPane();
	private JScrollPane scrollPaneleft = new JScrollPane();
	private JScrollPane scrollPaneright = new JScrollPane();
	private int selectedGrabber;
	private String rootDir = "Schaltzeichen";
	private SVGGen svgGenerator = new SVGGen();
	private boolean wasSaved = true;
	private String objectName;
	private JMenuBar menuBar;
	private boolean isActive = false;
	//// End : Stuff needed in several Method that can't invoke each other nicely

		
	
		
	/**
	 * Contains the implementation of the graphical user interface.
	 */
	public EditorGUI() 
	{
		//// Begin : Initialize
			setExtendedState(Frame.MAXIMIZED_BOTH);
			
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			
			setTitle("Schaltzeichen Editor");
			objectName = null;
			
		//// End : Initialize
		
		//// Begin : Canvas	
						 
				// subBegin : MouseMotionAdapter for Canvas
						MouseMotionAdapter mma = new MouseMotionAdapter() 
						{
							@Override
							public void mouseDragged(MouseEvent e) 
							{
								canvasMouseDragged(e);
							}
						};
				// subEnd : MouseMotionAdapter for Canvas	
						
				
				// subBegin : MouseAdapter for Canvas	
							MouseAdapter ma = new MouseAdapter() 
						{
							@Override
							public void mousePressed(MouseEvent e) 
							{
								canvasMousePressed(e);
							}
							@Override
							public void mouseReleased(MouseEvent e) 
							{
								canvasMouseReleased(e);
							}
						};
				// subEnd : MouseAdapter for Canvas	
							
				// subBegin : Canvas creation
						// subBegin : Left side
							 canvasleft = new EditorCanvas()
							 {
								
								public void paint(Graphics g) 
								{
									// subBegin : Update canvas to actual state
										for (GeometricObject go : geomListleft) 
										{
											go.draw(g);
										}
										if(actObjectleft!=null)
										{
											actObjectleft.drawOutline(g);
											actObjectleft.drawGrabbers(g);
										}
										drawGrid(g);
									// subEnd : Update canvas to actual state
								}
							 };
							 // subBegin : Add mouse adapters
							 	canvasleft.addMouseListener(ma);
								canvasleft.addMouseMotionListener(mma);
							 // subEnd : Add mouse adapters
						// subEnd : Left side	
								
						// subBegin : Right side		
								
							 canvasright = new EditorCanvas()
							 {
								@Override
								public void paint(Graphics g) 
								{
									// subBegin : Update canvas to actual state
										for (GeometricObject go : geomListright) 
										{
											go.draw(g);
										}
										if(actObjectright!=null)
										{
											actObjectright.drawOutline(g);
											actObjectright.drawGrabbers(g);
										}
										drawGrid(g);
									// subEnd : Update canvas to actual state
								}
							 };
							// subBegin : Add mouse adapters
							 	canvasright.addMouseListener(ma);
								canvasright.addMouseMotionListener(mma);
							 // subEnd : Add mouse adapters
						
						// subBegin : Right side	
								
				// subEnd : Canvas creation
						 
				// subBegin : KeyListener for Canvas	 
							 canvasleft.addKeyListener(new KeyAdapter() {
								@Override
								public void keyPressed(KeyEvent arg0) {
									switch (arg0.getKeyCode())
									{
									case KeyEvent.VK_DELETE : 
										if(actObjectleft != null)
										{
											geomListleft.remove(actObjectleft);
											actObjectleft = null;
											canvasleft.repaint();
										}
										break;
									case KeyEvent.VK_PLUS : 
											actObjectleft.zoom(2); 
											canvasleft.repaint();
										break;
									case KeyEvent.VK_MINUS :
											actObjectleft.zoom(0.5);
											canvasleft.repaint();
										break;
									}
								}
							});
							 canvasright.addKeyListener(new KeyAdapter() {
								@Override
								public void keyPressed(KeyEvent arg0) {
									switch (arg0.getKeyCode())
									{
									case KeyEvent.VK_DELETE : 
										if(actObjectright != null)
										{
											geomListright.remove(actObjectright);
											actObjectright = null;
											canvasright.repaint();
										}
										break;
									case KeyEvent.VK_PLUS : 
											actObjectright.zoom(2);
											canvasright.repaint();
										break;
									case KeyEvent.VK_MINUS :
											actObjectright.zoom(0.5);
											canvasright.repaint();
										break;
									}
								}
							});
			    // subEnd : KeyListener for Canvas
							
		//// End : Canvas	
			
		//// Begin : List with GeometricObjects	
			
				// subBegin : Creation of the list
						list = new JList<String>();
				// subEnd : Creation of the list
				
					// subBegin : Add MouseListener to the list with the Geometric Objects and call handler Method	
							list.addMouseListener(new MouseAdapter()
							{
								@Override
								public void mouseClicked(MouseEvent e) 
								{
									listMouseClicked(e);
								}
							});
					// subEnd : Add MouseListener to the list with the Geometric Objects and call handler Method
					
					// subBegin : Add items to the list
							addListItems();
					// subEnd : Add items to the list
					
					// subBegin : Add the List to the ScrollPane
							scrollPane.setViewportView(list);
					// subEnd : Add the List to the ScrollPane
				
		//// End : List with GeometricObjects	
			
		//// Begin : Create and implement the JMenuBar 
				
			// subBegin : Creation		
					menuBar = new JMenuBar();
			// subEnd : Creation
				
				// subBegin : Init JMenuBar and add it to the EditorGUI
						menuBar.setDoubleBuffered(true);
						setJMenuBar(menuBar);
				// subBegin : Init JMenuBar and add it to the EditorGUI
				
				// subBegin : Create JMenu and add it to the JMenuBar
						JMenu mnDatei = new JMenu("Datei");
						menuBar.add(mnDatei);
				// subEnd : Create JMenu and add it to the JMenuBar
				
				// subBegin : Create the JMenuItem "Neu"
						JMenuItem mntmNeu = new JMenuItem("Neu");
						mntmNeu.addActionListener(new ActionListener() 
						{
							// subsubBegin : React properly on MouseEvent in order to save before clearing the canvas 
								public void actionPerformed(ActionEvent arg0) 
								{
									if(!wasSaved)
									{
										saveSwitchingObject();
									}
									actObjectleft = null;
									actObjectright = null;
									geomListleft.clear();
									geomListright.clear();
									canvasleft.repaint();
									canvasright.repaint();
								}
							// subsubEnd : React properly on MouseEvent in order to save before clearing the canvas
						});
						mnDatei.add(mntmNeu);
				// subEnd : Create the JMenuItem "Neu"
				
				// subBegin : Create the JMenuItem "Schließen"
						JMenuItem mntmSpeichern = new JMenuItem("Speichern");
						mntmSpeichern.addActionListener(new ActionListener() 
						{
							// subsubBegin : Call saving Handler method "saveSwitchingObject()" if unsaved
								public void actionPerformed(ActionEvent arg0) 
								{
									if(!wasSaved)
									{
										saveSwitchingObject();
									}
								}
							// subsubEnd : Call saving Handler method "saveSwitchingObject()" if unsaved
						});
						mnDatei.add(mntmSpeichern);
				// subEnd : Create the JMenuItem "Speichern"
					
				// subBegin : Create the JMenuItem "Schließen"
						JMenuItem mntmSchliessen = new JMenuItem("Schließen");
						mntmSchliessen.addActionListener(new ActionListener() 
						{
							// subsubBegin : React properly on MouseEvent in order to close only a saved switching symbol 
								public void actionPerformed(ActionEvent arg0) 
								{
									if(wasSaved)
									{
										dispose();
									}
									else if(!wasSaved)
									{
										closeUnsaved();
									}
								}
							// subsubEnd : React properly on MouseEvent in order to close only a saved switching symbol
						});
						mnDatei.add(mntmSchliessen);
				// subEnd : Create the JMenuItem "Schließen"
						
						menuBar.add(new JSeparator()); // For the look of the menubar
					
				// subBegin : Create and add JButton "Schaltzeichen aufnehmen" to add a switching symbol to the "Schalt Editor"	
						JButton btnSchaltzeichenAufnehmen = new JButton("Schaltzeichen aufnehmen");
						btnSchaltzeichenAufnehmen.addActionListener(new ActionListener() 
						{
							// subBegin : Call saving Handler method "saveSwitchingObject()" 
								public void actionPerformed(ActionEvent arg0) 
								{
									saveSwitchingObject();
								}
							// subEnd : Call saving Handler method "saveSwitchingObject()"
						});
						menuBar.add(btnSchaltzeichenAufnehmen);
				// subEnd : Create and add JButton "Schaltzeichen aufnehmen" to add a switching symbol to the "Schalt Editor"	
					
		//// End : Create and implement JMenuBar
		
		
		
		
		
		//// Begin : Create window adapter
				this.addWindowListener(new WindowAdapter() {
					// subBegin : React on closing window for the saving system
			            public void windowClosing(WindowEvent e) 
			            {
			            	if(wasSaved)
							{
								dispose();
							}
							else if(!wasSaved)
							{
								closeUnsaved();
							}
			            }
		            // subEnd : React on closing window for the saving system
		        });
		//// End : Create window adapter
			
		//// Begin : JSplitPane
			
				// subBegin : Left side
						splitPane.setLeftComponent(scrollPane);
				// subEnd : Left side
					
				
				// subBegin : Right side
						// subBegin : Left side tabbed pane
							
							// subBegin : Set object to scroll 
								scrollPaneleft.setViewportView(canvasleft);
							// subEnd : Set object to scroll 
								
							// subBegin : Add to mytabbedpane
								mytabbedpane.addTab("Wirkschaltzeichen", scrollPaneleft);
							// subEnd : Add to mytabbedpane
								
						// subEnd : Left side tabbed pane
					
						// subBegin : Right side tabbed pane
							
							// subBegin : Set object to scroll  
								scrollPaneright.setViewportView(canvasright);
							// subEnd : Set object to scroll 
								
							// subBegin : Add to mytabbedpane
								mytabbedpane.addTab("Stromlaufzeichen", scrollPaneright);
							// subEnd : Add to mytabbedpane
								
						// subEnd : Right side tabbed pane		
						
						splitPane.setRightComponent(mytabbedpane);
						
				// subEnd : Right side
				
				splitPane.setDividerSize(5);
				this.getContentPane().add(splitPane, BorderLayout.CENTER);

		//// End : JSplitPane
	}
	
	////Begin : Listener methods
	
	/**
			 * Invokes the +expand(int grabber, Point endMove) or the +move(Point startMove, Point endMove) method 
			 * in {@link GeometricObject}, as required by the int selectedGrabber.<br>
			 * @param e the Mouse Event that gives the Position of the mouse released action.   
			 */
			public void canvasMouseDragged(MouseEvent e)
			{
				//// Begin : Which is the active canvas
					if(mytabbedpane.getSelectedIndex()==0 && actObjectleft!=null)
					{	
						
						// subBegin : Where has the user clicked and how to react to		
							switch (selectedGrabber)
							{
							case 0:case 1:case 2:case 3: // case 0,1,2,3 have the same handling 
								actObjectleft.expand(selectedGrabber, e.getPoint(), canvasleft.getWidth(), canvasleft.getHeight()); wasSaved = false; break;
							case 10: actObjectleft.move(startMove, e.getPoint(), canvasleft.getWidth(), canvasleft.getHeight()); wasSaved = false; break;
							}
						// subEnd : Where has the user clicked and how to react to
					}
					else if(actObjectright!=null)
					{				
						// subBegin : Where has the user clicked and how to react to	
							switch (selectedGrabber)
							{
							case 0:case 1:case 2:case 3: // case 0,1,2,3 have the same handling
								actObjectright.expand(selectedGrabber, e.getPoint(), canvasright.getWidth(), canvasright.getHeight()); wasSaved = false; break;
							case 10: actObjectright.move(startMove, e.getPoint(), canvasright.getWidth(), canvasright.getHeight()); wasSaved = false; break;
							}
						// subEnd : Where has the user clicked and how to react to	
					}
				//// End : Which is the active canvas
				
				startMove = e.getPoint(); 	// set StartMove to actual event (mouse) position
				repaint(); 					// update the canvas
			}
			
			/**
			 * Set&lsquo;s new values to the Point startMove <br>
			 * @param e
			 */
			public void canvasMousePressed(MouseEvent e)
			{
				startMove = e.getPoint();
			}
			
			/**
			 * Tests where a {@link GeometricObject} was hit my the mouse event and set&lsquo;s <br>
			 * selectedGrabber to a value representing the area where the Object was hit.<br>
			 * <br>
			 * 10 = hit inside the Object<br>
			 * 0  = hit in grabber 0<br>
			 * 1  = hit in grabber 1<br>
			 * 2  = hit in grabber 2<br>
			 * 3  = hit in grabber 3<br>
			 * <br>
			 * 
			 * @param e the MouseEvent where the {@link GeometricObject} was hit by the mouse.
			 */
			public void canvasMouseReleased(MouseEvent e)
			{
				actObjectleft = null;  
				actObjectright = null;
				
				if(mytabbedpane.getSelectedIndex()==0)
				{
					for(GeometricObject go : geomListleft)
					{
						if(go.isInside(e.getX(), e.getY()) != -1)
						{
							actObjectleft = go;
							if(isActive == false)
							{
								menuBar.add(go.setOptionsBar(), 1);
								isActive = true;
								menuBar.validate();
							}
							
							selectedGrabber = go.isInside(e.getX(), e.getY());
						}
						else
						{
							if(isActive == true)
							{
								menuBar.remove(1);
								isActive = false;
								menuBar.validate();
							}
						}
					}
				}
				else
				{
					for(GeometricObject go : geomListright)
					{
						if(go.isInside(e.getX(), e.getY()) != -1)
						{
							actObjectright = go;
								
							if(isActive == false)
							{
								menuBar.add(go.setOptionsBar(), 1);
								isActive = true;
								menuBar.validate();
							}
							
							selectedGrabber = go.isInside(e.getX(), e.getY());
						}
						else
						{
							if(isActive == true)
							{
								menuBar.remove(1);
								isActive = false;
								menuBar.validate();
							}
						}
					}
				}
				repaint(); // update the canvas
			}
			
	//// End : Listener methods
	
	/**
	 * Add&lsquo;s Strings for the available standard forms to the list in the graphical user interface.
	 */
	public void addListItems()
	{
		String[] itemsToAdd = new String[4];
		itemsToAdd[0] = "Kreis";
		itemsToAdd[1] = "Rechteck";
		itemsToAdd[2] = "Linie";
		itemsToAdd[3] = "Connector";
		
		list.setListData(itemsToAdd);
	}
	
	/**
	 * Creates and prepares the {@link SVGGraphics2D} objects for the saving process in {@link SVGGen}. <br>
	 * Finally it calls the +mkFile(HashMap<String, SVGGraphics2D> p_recentPaintingObjects) method in {@link SVGGen}
	 * to create the SVG&lsquo;s.<br>
	 * 
	 */
	public void saveSwitchingObject()
	{
		actObjectleft = null; //If actObjectleft would be != null grabbers and outline would be saved also.
		actObjectright = null; //If actObjectright would be != null grabbers and outline would be saved also.
				
		SVGGraphics2D left = new SVGGraphics2D(GenericDOMImplementation.getDOMImplementation().createDocument("http://www.w3.org/2000/svg", "svg", null));  
		SVGGraphics2D right = new SVGGraphics2D(GenericDOMImplementation.getDOMImplementation().createDocument("http://www.w3.org/2000/svg", "svg", null));  

		canvasleft.paint(left);
		canvasright.paint(right);
		
		left.setSVGCanvasSize(canvasleft.getSize());
		right.setSVGCanvasSize(canvasright.getSize());
		
		if(objectName == null)
		{
			objectName = JOptionPane.showInputDialog("Geben sie dem Schaltzeichen einen Namen:");
		}
		
		HashMap<String, SVGGraphics2D> itemsToSave = new HashMap<String, SVGGraphics2D>();
		itemsToSave.put(rootDir+File.separator+"Wirkschaltplaene"+File.separator+objectName+".svg", left);	
		itemsToSave.put(rootDir+File.separator+"Stromlaufplaene"+File.separator+objectName+".svg", right);
		svgGenerator.mkFile(itemsToSave);
		wasSaved = true;
	}
	
	/**
	 * Handles the saving, if the user tries to close the Editor without having saved. <br>
	 * Provides a yes no option to the user so the switching symbol still can be saved. <br>
	 */
	public void closeUnsaved()
	{
		Object[] options = {"Ja", "Nein"};
		int wannasave =  JOptionPane.showOptionDialog
		(
			null, //Parent component if any
		    "Vor dem Schließen das Schaltzeichen speichern ?", //Message to the user
		    "Speichern", //Frame title
		    JOptionPane.YES_NO_OPTION, //Answer possibility type
		    JOptionPane.QUESTION_MESSAGE, //Message type
		    null,     //No custom Icon
		    options,  //Button titles
		    options[0] //Default option
		); 
		
		switch (wannasave)
		{
			case 0: saveSwitchingObject(); dispose(); break;
			case 1: dispose(); break;
		}
	}
	
	/**
	 * Add&lsquo;s "Kreis" "Linie" or "Rechteck" objects to the {@link ArrayList} of the active canvas that handles its switching symbols.<br>
	 * @param e a MouseEvent that is given by the MouseAdapter of the standard switching symbols list
	 */
	public void listMouseClicked(MouseEvent e) 
	{
		if(e.getClickCount() == 2)
		{
			ArrayList<GeometricObject> temp;
			
			if(mytabbedpane.getSelectedIndex()==0)
			{
				temp=geomListleft;
			}
			else
			{
				temp=geomListright;
			}
			switch (list.getSelectedValue())
			{
				case "Kreis" : temp.add(new Kreis(0, 0, 50, 50));
					break;
				case "Rechteck" : temp.add(new Rechteck(0, 0, 50, 50));
					break;
				case "Linie" : temp.add(new Linie(0, 0, 50, 50));
					break;
				case "Connector" : temp.add(new Connector(0, 0, 8, 8));
					break;
			}
			wasSaved = false;
			repaint();		
		}
	}
	
	/**
	 * Prepares the graphical user interface for the usage to edit an existing switching symbol.<br>
	 * @param p_objectName the name of the switching object to edit.
	 * @param p_ArrayListWirkSchaltPlan the {@link ArrayList} that contains the {@link GeometricObject}&lsquo;s for the WirkSchaltPlan.
	 * @param p_ArrayListStromFlussPlan the {@link ArrayList} that contains the {@link GeometricObject}&lsquo;s for the StromFlussPlan.
	 */	
	public void setEditMode(String p_objectName, ArrayList<GeometricObject> p_ArrayListWirkSchaltPlan, ArrayList<GeometricObject> p_ArrayListStromFlussPlan)
	{
		objectName = p_objectName;
		geomListleft = p_ArrayListWirkSchaltPlan;
		geomListright = p_ArrayListStromFlussPlan;
		canvasleft.repaint();
		canvasright.repaint();
	}
	
}
