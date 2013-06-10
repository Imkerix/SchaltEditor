package mainFrame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.swing.JScrollPane;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;

import javax.swing.WindowConstants;
import javax.swing.filechooser.FileFilter;

import org.apache.batik.swing.svg.JSVGComponent;

import editor.EditorGUI;

/**
 * This code was edited or generated using CloudGarden's Jigloo

 * SWT/Swing GUI Builder, which is free for non-commercial
 * use. If Jigloo is being used commercially (ie, by a corporation,
 * company or business for any purpose whatever) then you
 * should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details.
 * Use of Jigloo implies acceptance of these licensing terms.
 * A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
 * THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
 * LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
 */

public class FrmMain extends javax.swing.JFrame 
{
	private static final long serialVersionUID = 1L;

	//GUI Components

	//MainPanel
	private JSplitPane jSplitPane1;

	//Left side of the SplitPane
	private JPanel jPanel1 = new JPanel();
	private JScrollPane jScrollPane1 = new JScrollPane();
	private JList<String> jList1 = new JList<String>();

	//Right side of the SplitPane
	private JTabbedPane jTabbedPane2 = new JTabbedPane();
	private JScrollPane jScrollPane2 = new JScrollPane();
	private JScrollPane jScrollPane3 = new JScrollPane();
	private DrawComponent drawComponent1 = new DrawComponent(null, rootPaneCheckingEnabled, rootPaneCheckingEnabled);
	private DrawComponent drawComponent2 = new DrawComponent(null, rootPaneCheckingEnabled, rootPaneCheckingEnabled);

	//MenuBar
	private JMenuBar jMenuBar1;

	//Menu "Tools"
	private JMenu jMenu1;  
	private JMenuItem jMenuItem1;
	private JMenuItem jMenuItem2;

	//Menu "Editor"
	private JMenu jMenu2;
	private JMenuItem jMenuItem3;


	//Settings
	private double zoomFactor = 1.1;
	private boolean synchronizedMoving = false;

	//Other attributes 
	private ArrayList<ElectricObject> electricObjects= new ArrayList<ElectricObject>();  
	private JMenuItem jMenuItem6;
	private JMenuItem jMenuItem5;
	private JMenu jMenu4;
	private JMenuItem jMenuItem4;
	private JMenu jMenu3;
	private Point startMove;
	private int zoomCount = 0;
	private FrmOptions frmOptions = new FrmOptions(this, drawComponent1, drawComponent2);



	public FrmMain() {
		super();
		initGUI();
		createElectricObjects();
		updateListItems();
	}
	@SuppressWarnings("serial")
	private void initGUI() 
	{
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			this.setSize(1003, 532);
			setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
			{
				jMenuBar1 = new JMenuBar();
				setJMenuBar(jMenuBar1);
				{
					jMenu4 = new JMenu();
					jMenuBar1.add(jMenu4);
					jMenu4.setText("Datei");
					{
						jMenuItem6 = new JMenuItem();
						jMenu4.add(jMenuItem6);
						jMenuItem6.setText("�ffnen");
						jMenuItem6.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								jMenuItem6ActionPerformed(evt);
							}
						});
					}
					{
						jMenuItem5 = new JMenuItem();
						jMenu4.add(jMenuItem5);
						jMenuItem5.setText("Speichern unter...");
						jMenuItem5.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								jMenuItem5ActionPerformed(evt);
							}
						});
					}
				}
				{
					jMenu1 = new JMenu();
					jMenuBar1.add(jMenu1);
					jMenu1.setText("Tools");
					{
						jMenuItem1 = new JMenuItem();
						jMenu1.add(jMenuItem1);
						jMenuItem1.setText("Zeichenfl�che vergr��ern");
						jMenuItem1.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								jMenuItem1ActionPerformed(evt);
							}
						});
					}
					{
						jMenuItem2 = new JMenuItem();
						jMenu1.add(jMenuItem2);
						jMenuItem2.setText("Zeichenfl�che verkleiner");
						jMenuItem2.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								jMenuItem2ActionPerformed(evt);
							}
						});
					}
				}
				{
					jMenu2 = new JMenu();
					jMenuBar1.add(jMenu2);
					jMenu2.setText("Editor");
					{
						jMenuItem3 = new JMenuItem();
						jMenu2.add(jMenuItem3);
						jMenuItem3.setText("Neues Schaltzeichen");
						jMenuItem3.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								jMenuItem3ActionPerformed(evt);
							}
						});
					}
				}
				{
					jMenu3 = new JMenu();
					jMenuBar1.add(jMenu3);
					jMenu3.setText("Extras");
					{
						jMenuItem4 = new JMenuItem();
						jMenu3.add(jMenuItem4);
						jMenuItem4.setText("Benutzervorgaben");
						jMenuItem4.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								jMenuItem4ActionPerformed(evt);
							}
						});
					}
				}
			}
			{
				jSplitPane1 = new JSplitPane();
				getContentPane().add(jSplitPane1, BorderLayout.CENTER);
				//Left side of the SplitPane
				{  
					BorderLayout jPanel1Layout = new BorderLayout();
					jPanel1.setLayout(jPanel1Layout);
					jPanel1.add(jScrollPane1);

					jScrollPane1.setViewportView(jList1);

					jSplitPane1.add(jPanel1, JSplitPane.LEFT);
					jPanel1.setMinimumSize(new Dimension(300, 50));
					jSplitPane1.setDividerLocation(300);
					jSplitPane1.setEnabled(false);
				}
				//Right side of the SplitPane
				{
					jScrollPane2.setViewportView(drawComponent1);
					jScrollPane3.setViewportView(drawComponent2);
					jTabbedPane2.addTab("Wirkschaltplan", jScrollPane2);
					jTabbedPane2.addTab("Stromlaufplan", jScrollPane3);
					jSplitPane1.add(jTabbedPane2, JSplitPane.RIGHT);

					
					
					//MOUSELISTENER

					//create mouselisteners
					MouseMotionAdapter mma = new MouseMotionAdapter() {
						public void mouseDragged(MouseEvent evt) {
							svgCanvasMouseDragged(evt);
						}
					};

					MouseAdapter ma = new MouseAdapter() {
						public void mouseReleased(MouseEvent evt) {
							svgCanvasMouseReleased(evt);
						}
						public void mousePressed(MouseEvent evt) {
							svgCanvasMousePressed(evt);
						}
					};

					//add mouselisteners to components
					drawComponent1.addMouseMotionListener(mma);
					drawComponent2.addMouseMotionListener(mma);
					drawComponent1.addMouseListener(ma);
					drawComponent2.addMouseListener(ma);


					//KEYBINDINGS

					//create keybindings
					AbstractAction delete = new AbstractAction() {
						public void actionPerformed(ActionEvent e) {
							svgCanvasKeyDELETEPressed();
						}
					};

					AbstractAction plus = new AbstractAction() {
						public void actionPerformed(ActionEvent e) {
							svgCanvasKeyPLUSPressed();
						}
					};

					AbstractAction minus = new AbstractAction() {
						public void actionPerformed(ActionEvent e) {
							svgCanvasKeyMINUSPressed();
						}
					};


					//add keybindings
					//Del
					drawComponent1.getInputMap(JSVGComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0), "deleteAction");
					drawComponent1.getActionMap().put("deleteAction", delete);

					//+
					drawComponent1.getInputMap(JSVGComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ADD, 0), "plusAction");
					drawComponent1.getActionMap().put("plusAction", plus);

					//-
					drawComponent1.getInputMap(JSVGComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_SUBTRACT, 0), "minusAction");
					drawComponent1.getActionMap().put("minusAction", minus);

					//Del
					drawComponent2.getInputMap(JSVGComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0), "deleteAction");
					drawComponent2.getActionMap().put("deleteAction", delete);

					//+
					drawComponent2.getInputMap(JSVGComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ADD, 0), "plusAction");
					drawComponent2.getActionMap().put("plusAction", plus);

					//-
					drawComponent2.getInputMap(JSVGComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_SUBTRACT, 0), "minusAction");
					drawComponent2.getActionMap().put("minusAction", minus);
				}
			}
			pack();      
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	//METHODS

	//creates an electricObject out of the svg-files.
	private void createElectricObjects()
	{
		//Die Files der beiden Ordner auflisten
		File file = new File(System.getProperty("user.dir") + "/Wirkschaltplan");
		File[] wirkstromFiles = file.listFiles();

		file = new File(System.getProperty("user.dir") + "/Stromlaufplan");
		File[] stromlaufFiles = file.listFiles();

		if(stromlaufFiles != null)
		{
			//ElectricObject erzeugen und der Liste hinzuf�gen
			for (int i = 0; i < stromlaufFiles.length; i++) 
			{
				File file1 = wirkstromFiles[i];
				File file2 = stromlaufFiles[i];
				String name = file1.getName();

				ElectricObject eo = new ElectricObject(name, file1, file2);
				electricObjects.add(eo);
			}
		}
	}

	//F�llt die Liste mit den Objekten aus der ArrayList<ElectricObject>
	public void updateListItems()
	{
		String[] filenames = new String[electricObjects.size()];

		for (int i = 0; i < electricObjects.size(); i++) 
		{
			filenames[i] = electricObjects.get(i).getName();
		}
		jList1.setListData(filenames);
		jList1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				jList1MouseClicked(evt);
			}
		});
	}

	/**
	 * Ruft die repaint-Methode von beiden DrawComponents auf.
	 */
	private void repaintAll()
	{
		drawComponent1.repaint();
		drawComponent2.repaint();
	}


	//EVENTS  

	/**
	 * MouseClickedListener der Liste
	 * Erzeugt aus dem angew�hlten ElectricObject f�r die beiden DrawComponents die jeweiligen DrawObjecte und f�gt sie an die Listen der DrawComponents an.
	 * NOTE: DURCH DOPPELKLICK ERSETZEN!
	 * @param evt
	 */  
	private void jList1MouseClicked(MouseEvent evt) 
	{
		if(evt.getClickCount() == 2)
		{
			String item = jList1.getSelectedValue().toString();
			ElectricObject eo = null;

			for(ElectricObject e : electricObjects)
			{
				if((e.getName()).equals(item))
				{
					eo = e;
					break;
				}
			}  

			drawComponent1.addToList(eo.getDrawObject(0, drawComponent1.getNextIndex(), zoomCount, zoomFactor));
			drawComponent2.addToList(eo.getDrawObject(1, drawComponent2.getNextIndex(), zoomCount, zoomFactor));
			repaintAll();
		}
	}

	/**
	 * MouseReleasedListener:
	 * Beim loslassen der Maus wird gepr�ft, ob ein Objekt angew�hlt wurde und der Index dieses Objektes ausgelesen. Anschlie�end wir
	 * f�r die beiden DrawComponents das aktuelle Objekt anhand des ausgelesenen Index gesetzt.
	 * @param evt
	 */
	private void svgCanvasMouseReleased(MouseEvent evt) 
	{
		int objectIndex = -1;

		if(jTabbedPane2.getSelectedIndex() == 0)
		{
			objectIndex = drawComponent1.checkSelection(evt.getX(), evt.getY());
			drawComponent1.setActObject(objectIndex);
		}
		else if(jTabbedPane2.getSelectedIndex() == 1)
		{
			objectIndex = drawComponent2.checkSelection(evt.getX(), evt.getY());
			drawComponent2.setActObject(objectIndex);
		}

		if(synchronizedMoving)
		{
			drawComponent1.setActObject(objectIndex);
			drawComponent2.setActObject(objectIndex);
		}
	}

	/**
	 * MousePressedListener: Beim Dr�cken der Maus wird der gedr�ckte Punkt gespeichert. Dient zum Verschieben des Objektes.
	 * @param evt
	 */
	private void svgCanvasMousePressed(MouseEvent evt) 
	{
		startMove = evt.getPoint();
	}

	/**
	 * MouseDraggedListener: Beim draggen der Maus wird die move-Methode der DrawComponents aufgerufen. 
	 * @param evt
	 */
	private void svgCanvasMouseDragged(MouseEvent evt) 
	{
		drawComponent1.move(startMove, evt.getPoint());
		drawComponent2.move(startMove, evt.getPoint());
		startMove = evt.getPoint();
	}

	/**
	 * Men� Tools, Men�punkt "Zeichenfl�che vergr��ern": Dazu wird zun�chst die aktuelle gr��e des eines DrawComponents ausgelesen, da diese, je nach Bildschirmgr��e
	 * variieren kann. Dann wird die selbe Gr��e  100 Pixel als neue gr��e gesetzt. Die Gr��e wird f�r beide DrawComponents gleichzeitig erh�ht.
	 * @param evt
	 */
	private void jMenuItem1ActionPerformed(ActionEvent evt) 
	{
		int width = drawComponent1.getSize().width;
		int height = drawComponent1.getSize().height;

		drawComponent1.setPreferredSize(new Dimension(width+100, height+100));
		drawComponent1.setSize(width+100, height+100);
		drawComponent2.setPreferredSize(new Dimension(width+100, height+100));
		drawComponent2.setSize(width+100, height+100);
	}

	/**
	 * Men� Tools, Men�punkt "Zeichenfl�che vergkleinern": Dazu wird zun�chst die aktuelle gr��e des eines DrawComponents ausgelesen, da diese, je nach Bildschirmgr��e
	 * variieren kann. Dann wird die selbe Gr��e - 100 Pixel als neue Gr��e gesetzt. Die Gr��e wird f�r beide DrawComponents gleichzeitig verkleinert.
	 * @param evt
	 */
	private void jMenuItem2ActionPerformed(ActionEvent evt) 
	{
		int width = drawComponent1.getSize().width;
		int height = drawComponent1.getSize().height;

		drawComponent1.setPreferredSize(new Dimension(width-100, height-100));
		drawComponent1.setSize(width-100, height-100);
		drawComponent2.setPreferredSize(new Dimension(width-100, height-100));
		drawComponent2.setSize(width-100, height-100);
	}

	/**
	 * Men� Editor, Men�punkt "Neues Schaltzeichen": �ffnet ein neues JFrame mit dem leeren Editor.
	 * @param evt
	 */
	private void jMenuItem3ActionPerformed(ActionEvent evt) 
	{
		EditorGUI editorGui = new EditorGUI();
		editorGui.setVisible(true);
	}

	/**
	 * Men� Extras, Men�punkt "Benutzervorgaben": �ffnet das Einstellungsfenster
	 * @param evt
	 */
	private void jMenuItem4ActionPerformed(ActionEvent evt) 
	{
		frmOptions.setVisible(true);
	}

	/**
	 * Men� Datei, Men�punkt "Speichern unter...": �ffnet einen Filechooser und speichert den aktuellen Schaltplan.
	 * @param evt
	 */
	private void jMenuItem5ActionPerformed(ActionEvent evt) 
	{
		JFileChooser fc = new JFileChooser();
		fc.setAcceptAllFileFilterUsed(false);
		fc.setFileFilter(new FileFilter()
		{
			public boolean accept( File f ) 
			{
				return f.isDirectory() || f.getName().toLowerCase().endsWith(".ser");
			}
			public String getDescription() 
			{
				return "Datei" + " (*.ser)";
			}          
		});
		int result = fc.showSaveDialog(drawComponent1);


		if(result == JFileChooser.APPROVE_OPTION)
		{
			File file = fc.getSelectedFile();
			String parentpath = file.getParentFile().getAbsolutePath() + "/";

			if(file.exists())
			{
				result = JOptionPane.showOptionDialog(drawComponent1, "Datei �berschreiben?", null,
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE,
						null, null, null);

				switch(result)
				{
				case 0: saveAs((parentpath + file.getName() + ".ser"));
				break;
				case 1: saveAs((parentpath + file.getName() + "2.ser"));
				break;
				case 2: break;
				}

			}
			else
			{
				System.out.println(parentpath + file.getName() +  ".ser");
				saveAs((parentpath + file.getName()  + ".ser"));
			}
		}
	}

	/**
	 * Men� Datei, Men�punkt "�ffnen": Per FileChooser wird die zu �ffnede Datei gew�hlt. 
	 * Anschlie�end werden die DrawObjects aus der Datei ausgelesen
	 * @param evt
	 */
	@SuppressWarnings("unchecked")
	private void jMenuItem6ActionPerformed(ActionEvent evt) 
	{
		JFileChooser fc = new JFileChooser();
		fc.setAcceptAllFileFilterUsed(false);
		fc.setFileFilter(new FileFilter()
		{
			public boolean accept( File f ) 
			{
				return f.isDirectory() || f.getName().toLowerCase().endsWith(".ser");
			}
			public String getDescription() 
			{
				return "Datei" + " (*.ser)";
			}          
		});
		int result = fc.showOpenDialog(drawComponent1);

		if(result == JFileChooser.APPROVE_OPTION)
		{
			File file = fc.getSelectedFile();
			InputStream fis = null;
			ObjectInputStream oip = null;

			//Nur wenn eine Datei vorhanden ist
			if(file.exists())
			{
				try
				{
					fis = new FileInputStream(file);

					oip = new ObjectInputStream( fis );
					drawComponent1.setObjectList((ArrayList<DrawObject>) oip.readObject());
					drawComponent2.setObjectList((ArrayList<DrawObject>) oip.readObject());
				}
				catch (IOException e) 
				{
					e.printStackTrace();
				}
				catch (ClassNotFoundException e) 
				{
					e.printStackTrace();
				}
			}
		}
		repaintAll();

	}


	/**
	 * Wenn die Taste "Entfernen" gedr�ckt wird, wird die delete-Methode der beiden DrawComponents aufgerufen.
	 */
	private void svgCanvasKeyDELETEPressed()
	{
		drawComponent1.delete();
		drawComponent2.delete();
	}

	/**
	 * Wenn die Taste "+" gedr�ckt wird zun�chst der Zoom-Count erh�ht, anschlie�end die zoom-Methode der beiden DrawComponents aufgerufen und
	 * zu letzt, die Zeichenfl�che um den ZoomFaktor vergr��ert.
	 */
	private void svgCanvasKeyPLUSPressed()
	{
		zoomCount++;
		drawComponent1.zoom(zoomFactor);
		drawComponent2.zoom(zoomFactor);
		zoomDrawComponent(zoomFactor);
		repaint();
	}

	/**
	 * Wenn die Taste "-" gedr�ckt wird zun�chst der Zoom-Count verringert, anschlie�end die zoom-Methode der beiden DrawComponents aufgerufen und
	 * zu letzt, die Zeichenfl�che um den ZoomFaktor verkleinert.
	 */
	private void svgCanvasKeyMINUSPressed()
	{
		zoomCount--;
		drawComponent1.zoom(1/zoomFactor);
		drawComponent2.zoom(1/zoomFactor);
		zoomDrawComponent(1/zoomFactor);
		repaint();
	}

	/**
	 * Vergr��ert oder Verkleinert die Zeichenfl�che um einen Zoom-Faktor.
	 * @param zoom
	 */
	private void zoomDrawComponent(double zoom)
	{
		Dimension canvasSize = drawComponent1.getSize();
		int width = canvasSize.width;
		int height = canvasSize.height;

		drawComponent1.setPreferredSize(new Dimension((int) (width*zoom), (int) (height*zoom)));
		drawComponent1.setSize((int) (width*zoom), (int) (height*zoom));
		drawComponent2.setPreferredSize(new Dimension((int) (width*zoom), (int) (height*zoom)));
		drawComponent2.setSize((int) (width*zoom), (int) (height*zoom));
	}

	public void saveAs(String filename)
	{
		OutputStream fos;
		ObjectOutputStream oos = null;
		try
		{
			fos = new FileOutputStream(filename);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(drawComponent1.getObjectList());
			oos.writeObject(drawComponent2.getObjectList());
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	public double getZoomFactor()
	{
		return zoomFactor;
	}

	public void setZoomFactor(double zoomFactor)
	{
		this.zoomFactor = zoomFactor;
	}

	public void showGrid(boolean b)
	{
		drawComponent1.showGrid(b);
		drawComponent2.showGrid(b);
		repaintAll();
	}

	public int getGridInterval()
	{
		return drawComponent1.getGridInterval();
	}

	public void setGridInterval(int interval)
	{
		drawComponent1.setGridInterval(interval);
		drawComponent2.setGridInterval(interval);
	}

	public void setSynchronizedMoving(boolean b)
	{
		this.synchronizedMoving = b;
	}


} 