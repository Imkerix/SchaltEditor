package editor;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Rectangle;

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
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;

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
 * @version 0.2 - 0.3
 */
@SuppressWarnings("serial")
public class EditorGUI extends JFrame
{
	//// Begin : Values needed in several Method that can't invoke each other nicely
		private JList<String> list = new JList<String>();
		private Point startMove;
		private JSVGCanvas canvasleft;
		private JSVGCanvas canvasright;
		private ArrayList<GeometricObject> geomListleft = new ArrayList<GeometricObject>();
		private ArrayList<GeometricObject> geomListright = new ArrayList<GeometricObject>();
		private GeometricObject actObjectleft;
		private GeometricObject actObjectright;
		private JTabbedPane mytabbedpane;
		private int selectedGrabber;
		private String rootDir = "Schaltzeichen";
		private SVGGen svgGenerator = new SVGGen();
		private boolean wasSaved = true;
		private String objectName;
	////end : Values needed in several Method that can't invoke each other nicely
	
	public EditorGUI(String p_objectName, ArrayList<GeometricObject> p_ArrayListWirkSchaltPlan, ArrayList<GeometricObject> p_ArrayListStromFlussPlan)
	{
		this();
		objectName = p_objectName;
		geomListleft = p_ArrayListWirkSchaltPlan;
		geomListright = p_ArrayListStromFlussPlan;
		canvasleft.repaint();
		canvasright.repaint();
	}
		
	/**
	 * Contains the implementation of the graphical user interface.
	 */
	public EditorGUI() 
	{
		//// Begin : Initialize
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			GraphicsDevice[] screens = ge.getScreenDevices();
			Rectangle display = screens[0].getDefaultConfiguration().getBounds();
			setSize((int)display.getWidth()-500, (int)display.getHeight()-500);
			
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			
			setTitle("Schaltzeichen Editor");
			objectName = null;
			
			getContentPane().setLayout(new BorderLayout(0, 0));
			
			JSplitPane splitPane = new JSplitPane();
			splitPane.setDividerSize(5);
			getContentPane().add(splitPane, BorderLayout.CENTER);
			
			JScrollPane scrollPane = new JScrollPane();
			splitPane.setLeftComponent(scrollPane);
			
			scrollPane.setViewportView(list);
		//// End : Initialize
		
		//// Begin : Canvas creation
			 canvasleft = new JSVGCanvas()
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
					// subEnd : Update canvas to actual state
				}
			 };
			 canvasright = new JSVGCanvas()
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
					// subEnd : Update canvas to actual state
				}
			 };
		////End : Canvas creation
		
		//// Begin : Create and init the JTabbedPane	 
			mytabbedpane = new JTabbedPane();
			mytabbedpane.setAutoscrolls(true);
			mytabbedpane.addTab("Wirkschaltzeichen", canvasleft);
			mytabbedpane.addTab("Stromlaufzeichen", canvasright);
			splitPane.setRightComponent(mytabbedpane);
		//// End : Create and init the JTabbedPane	
		
		//// Begin : Add MouseListener to the list with the Geometric Objects and call handler Method	
			list.addMouseListener(new MouseAdapter()
			{
				@Override
				public void mouseClicked(MouseEvent e) 
				{
					listMouseClicked(e);
				}
			});
			addListItems();
		//// End : Add MouseListener to the list with the Geometric Objects	and call handler Method	
			
		//// Begin : Create and implement the JMenuBar 
			
			// subBegin : Create JMenuBar and add it to the EditorGUI
				JMenuBar menuBar = new JMenuBar();
				setJMenuBar(menuBar);
			// subBegin : Create JMenuBar and add it to the EditorGUI
			
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
							System.exit(0);
						}
						else if(!wasSaved)
						{
							closeUnsaved();
						}
					}
					// subsubEnd : React properly on MouseEvent in order to close only a saved switching symbol
				});
			// subEnd : Create the JMenuItem "Schließen"
				
				mnDatei.add(mntmNeu);
				mnDatei.add(mntmSpeichern);
				mnDatei.add(mntmSchliessen);
			
		//// End : Create and implement JMenuBar
		
		//// Begin : Create separator to get a better look in the MenuBar 	
			JSeparator separator = new JSeparator();
			separator.setDoubleBuffered(true);
			menuBar.add(separator);
		//// End : Create separator to get a better look in the MenuBar		
		
		//// Begin : Create and add JButton "Schaltzeichen aufnehmen" to add a switching symbol to the "Schalt Editor"	
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
		//// End : Create and add JButton "Schaltzeichen aufnehmen" to add a switching symbol to the "Schalt Editor"	
		
		//// Begin : Create mouse adapters
			MouseMotionAdapter mma = new MouseMotionAdapter() 
			{
				@Override
				public void mouseDragged(MouseEvent e) 
				{
					canvasMouseDragged(e);
				}
			};
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
			// subBegin : Add mouse adapters
				canvasleft.addMouseMotionListener(mma);
				canvasleft.addMouseListener(ma);
				canvasright.addMouseMotionListener(mma);
				canvasright.addMouseListener(ma);
			// subEnd : Add mouse adapters
		//// Begin : Create mouse adapters
		
		//// Begin : Create window adapter
			addWindowListener(new WindowAdapter() {
				// subBegin : React on closing window for the saving system
		            public void windowClosing(java.awt.event.WindowEvent e) {
		            	if(wasSaved)
						{
							System.exit(0);
						}
						else if(!wasSaved)
						{
							closeUnsaved();
							System.exit(0);
						}
		            }
	            // subEnd : React on closing window for the saving system
	        });
		//// End : Create window adapter
	}
	
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
			case 0: saveSwitchingObject(); System.exit(0); break;
			case 1: System.exit(0); break;
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
					selectedGrabber = go.isInside(e.getX(), e.getY());
				}
			}
		}
		else{
			for(GeometricObject go : geomListright)
			{
				if(go.isInside(e.getX(), e.getY()) != -1)
				{
					actObjectright = go;
					selectedGrabber = go.isInside(e.getX(), e.getY());
				}
			}
		}
		repaint(); // update the canvas
	}
}
