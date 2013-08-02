package mainFrame;
import java.awt.BorderLayout;
import java.awt.Color;
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
import shared.Connector;
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
	EditorGUI editorGui = new EditorGUI();

	//GUI
	//Hauptpanel
	private JSplitPane jSplitPane1;
	//Linke Seite der SplitPane
	private JPanel jPanel1 = new JPanel();
	private JScrollPane jScrollPane1 = new JScrollPane();
	private JList<String> jList1 = new JList<String>();
	//Rechte Seite der SplitPane
	private JTabbedPane jTabbedPane2 = new JTabbedPane();
	private JScrollPane jScrollPane2 = new JScrollPane();
	private JScrollPane jScrollPane3 = new JScrollPane();
	private DrawComponent drawComponent1 = new DrawComponent(null, rootPaneCheckingEnabled, rootPaneCheckingEnabled, true);
	private DrawComponent drawComponent2 = new DrawComponent(null, rootPaneCheckingEnabled, rootPaneCheckingEnabled, false);
	//MenuBar
	private JMenuBar jMenuBar1;
	//Menu "Datei"
	private JMenu menuFile;
	private JMenuItem menuItemOpen;	
	private JMenuItem menuItemSave;	
	private JMenuItem menuItemSaveAs;
	//Untermenu "Exportieren"
	private JMenu menuExport;
	private JMenuItem menuItemExportCurrent;
	private JMenuItem menuItemExportBoth;


	//Menu "Tools"
	private JMenu menuTools; 
	private JMenuItem menuItemExtendWorkBench;
	private JMenuItem menuItemReduceWorkBench;
	//Menu "Editor"
	private JMenu menuEditor;
	private JMenuItem menuItemNewSymbol;

	//Menu "Extras"
	private JMenu menuExtras;
	private JMenuItem menuItemPreferences;
	//Settings
	private double zoomFactor = 1.1;
	private boolean synchronizeWorkBench = false;
	//Other attributes 
	private ArrayList<ElectricObject> electricObjects= new ArrayList<ElectricObject>(); 
	private Point startMove;
	private int zoomCount = 0;
	private FrmOptions frmOptions = new FrmOptions(this, editorGui, drawComponent1, drawComponent2, editorGui.getLeftDrawComponent(), editorGui.getRightDrawComponent());
	private JFileChooser jfc = new JFileChooser();
	private String path = null;
	public FrmMain() {
		super();
		initGUI();
		Connector.setDiameter(8);
		createElectricObjects();
		updateListItems();
	}
	@SuppressWarnings("serial")
	private void initGUI() 
	{
		try {
			setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			this.setSize(1003, 532);
			setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
			{
				jMenuBar1 = new JMenuBar();
				setJMenuBar(jMenuBar1);
				{
					menuFile = new JMenu();
					jMenuBar1.add(menuFile);
					menuFile.setText("Datei");
					{
						menuItemOpen = new JMenuItem();
						menuFile.add(menuItemOpen);
						menuItemOpen.setText("Oeffnen");
						menuItemOpen.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								menuItemOpenActionPerformed(evt);
							}
						});
					}
					{
						menuItemSave = new JMenuItem();
						menuFile.add(menuItemSave);
						menuItemSave.setText("Speichern");
						menuItemSave.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								menuItemSaveActionPerformed(evt);
							}
						});
					}
					{
						menuItemSaveAs = new JMenuItem();
						menuFile.add(menuItemSaveAs);
						menuItemSaveAs.setText("Speichern unter...");
						menuItemSaveAs.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								menuItemSaveAsActionPerformed(evt);
							}
						});
					}
					{
						menuExport = new JMenu();
						menuFile.add(menuExport);
						menuExport.setText("Exportieren...");
						{
							menuItemExportCurrent = new JMenuItem();
							menuExport.add(menuItemExportCurrent);
							menuItemExportCurrent.setText("Aktuellen Schaltplan");
							menuItemExportCurrent.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent evt) {
									try {
										menuItemExportCurrentActionPerformed(evt);
									} catch (IOException e) {
										e.printStackTrace();
									}
								}
							});
						}
						{
							menuItemExportBoth = new JMenuItem();
							menuExport.add(menuItemExportBoth);
							menuItemExportBoth.setText("Alle Schaltplaene");
							menuItemExportBoth.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent evt) {
									try {
										menuItemExportBothActionPerformed(evt);
									} catch (IOException e) {
										e.printStackTrace();
									}
								}
							});
						}
					}
				}
				{
					menuTools = new JMenu();
					jMenuBar1.add(menuTools);
					menuTools.setText("Tools");
					{
						menuItemExtendWorkBench = new JMenuItem();
						menuTools.add(menuItemExtendWorkBench);
						menuItemExtendWorkBench.setText("Arbeitsflaeche vergroeßern");
						menuItemExtendWorkBench.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								menuItemExtendWorkBenchActionPerformed(evt);
							}
						});
					}
					{
						menuItemReduceWorkBench = new JMenuItem();
						menuTools.add(menuItemReduceWorkBench);
						menuItemReduceWorkBench.setText("Arbeitsflaeche verkleiner");
						menuItemReduceWorkBench.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								menuItemReduceWorkBenchActionPerformed(evt);
							}
						});
					}
				}
				{
					menuEditor = new JMenu();
					jMenuBar1.add(menuEditor);
					menuEditor.setText("Editor");
					{
						menuItemNewSymbol = new JMenuItem();
						menuEditor.add(menuItemNewSymbol);
						menuItemNewSymbol.setText("Neues Schaltzeichen");
						menuItemNewSymbol.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								menuItemNewSymbolActionPerformed(evt);
							}
						});
					}
				}
				{
					menuExtras = new JMenu();
					jMenuBar1.add(menuExtras);
					menuExtras.setText("Extras");
					{
						menuItemPreferences = new JMenuItem();
						menuExtras.add(menuItemPreferences);
						menuItemPreferences.setText("Benutzervorgaben");
						menuItemPreferences.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								menuItemPreferencesActionPerformed(evt);
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
					jScrollPane2.getViewport().setBackground(Color.white);
					jScrollPane3.setViewportView(drawComponent2);
					jScrollPane3.getViewport().setBackground(Color.white);
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
					drawComponent1.getInputMap(JSVGComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_PLUS, 0), "plusAction");
					drawComponent1.getActionMap().put("plusAction", plus);
					//-
					drawComponent1.getInputMap(JSVGComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_SUBTRACT, 0), "minusAction");
					drawComponent1.getInputMap(JSVGComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, 0), "minusAction");
					drawComponent1.getActionMap().put("minusAction", minus);
					//Del
					drawComponent2.getInputMap(JSVGComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0), "deleteAction");
					drawComponent2.getActionMap().put("deleteAction", delete);
					//+
					drawComponent2.getInputMap(JSVGComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ADD, 0), "plusAction");
					drawComponent2.getInputMap(JSVGComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_PLUS, 0), "plusAction");
					drawComponent2.getActionMap().put("plusAction", plus);
					//-
					drawComponent2.getInputMap(JSVGComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_SUBTRACT, 0), "minusAction");
					drawComponent2.getInputMap(JSVGComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, 0), "minusAction");
					drawComponent2.getActionMap().put("minusAction", minus);
				}
			}
			//FileChooser Filters
			{
				jfc.setAcceptAllFileFilterUsed(false);
				jfc.addChoosableFileFilter(new FileFilter()
				{
					public boolean accept( File f ) 
					{
						return f.isDirectory() || f.getName().toLowerCase().endsWith(".svg");
					}
					public String getDescription() 
					{
						return "SVG-Dateien";
					} 
				});
				jfc.addChoosableFileFilter(new FileFilter()
				{
					public boolean accept( File f ) 
					{
						return f.isDirectory() || f.getName().toLowerCase().endsWith(".jpg");
					}
					public String getDescription() 
					{
						return "JPG-Dateien";
					} 
				});
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
		File file = new File(System.getProperty("user.dir") + "/Schaltzeichen/Wirkschaltplaene");
		File[] wirkstromFiles = file.listFiles();
		file = new File(System.getProperty("user.dir") + "/Schaltzeichen/Stromlaufplaene");
		File[] stromlaufFiles = file.listFiles();
		if(stromlaufFiles != null)
		{
			//ElectricObject erzeugen und der Liste hinzufï¿½gen
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
	//Fuellt die Liste mit den Objekten aus der ArrayList<ElectricObject>
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
	/**
	 * Vergroessert oder verkleinert die Zeichenflaeche um einen Zoom-Faktor.
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
	/**
	 * 
	 * @param b: true bedeutet, dass nur der aktuelle Schaltplan exportiert wird. False bedeutet, dass beide Schaltpläne exportiert werden.
	 * @throws IOException
	 */
	private void export(boolean b) throws IOException
	{
		int result = jfc.showSaveDialog(drawComponent1);
		if(result == JFileChooser.APPROVE_OPTION)
		{
			File file = jfc.getSelectedFile();	
			String datatype = "";
			if(jfc.getFileFilter().getDescription().equals("SVG-Dateien"))
			{
				datatype = ".svg";
			}
			else if(jfc.getFileFilter().getDescription().equals("JPG-Dateien"))
			{
				datatype = ".jpeg";
			}
			if(file.exists())
			{
				result = JOptionPane.showOptionDialog(drawComponent1, "Datei ueberschreiben?", null,
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE,
						null, null, null);
				switch(result)
				{
				case 0:
					if(b)
					{
						((DrawComponent) ((JScrollPane) jTabbedPane2.getSelectedComponent()).getViewport().getView()).export(file.getPath());
					}
					else
					{
						drawComponent1.export(file.getPath());
						drawComponent2.export(file.getPath());
					}
					break;
				case 1: menuItemExportCurrentActionPerformed(null);
				break;
				case 2: break;
				}
			}
			else
			{
				if(b)
				{
					((DrawComponent) ((JScrollPane) jTabbedPane2.getSelectedComponent()).getViewport().getView()).export(file.getPath() + datatype);
				}
				else
				{
					drawComponent1.export(file.getPath() + "_Wirkschaltplan" + datatype);
					drawComponent2.export(file.getPath() + "_Stromlaufplan" + datatype);
				}
			}
		}
	}
	//MOUSE-EVENTS
	/**
	 * MouseClickedListener der Liste
	 * Erzeugt aus dem angewï¿½hlten ElectricObject fï¿½r die beiden DrawComponents die jeweiligen DrawObjecte und fï¿½gt sie an die Listen der DrawComponents an.
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

			if(synchronizeWorkBench)
			{
				drawComponent1.addToList(eo.getDrawObject(true, drawComponent1.getNextIndex(), zoomCount, zoomFactor));
				drawComponent2.addToList(eo.getDrawObject(false, drawComponent2.getNextIndex(), zoomCount, zoomFactor));
			}
			else
			{
				DrawComponent actDrawComponent = (DrawComponent) ((JScrollPane) jTabbedPane2.getSelectedComponent()).getViewport().getView();
				actDrawComponent.addToList(eo.getDrawObject(actDrawComponent.getSchaltplan(), 
						actDrawComponent.getNextIndex() , 
						zoomCount, zoomFactor));
			}
			repaintAll();
		}
	}
	/**
	 * MouseReleasedListener:
	 * Beim loslassen der Maus wird geprï¿½ft, ob ein Objekt angewï¿½hlt wurde und der Index dieses Objektes ausgelesen. Anschlieï¿½end wir
	 * fï¿½r die beiden DrawComponents das aktuelle Objekt anhand des ausgelesenen Index gesetzt.
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
		if(synchronizeWorkBench)
		{
			drawComponent1.setActObject(objectIndex);
			drawComponent2.setActObject(objectIndex);
		}
	}
	/**
	 * MousePressedListener: Beim Drï¿½cken der Maus wird der gedrï¿½ckte Punkt gespeichert. Dient zum Verschieben des Objektes.
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
	//KEY-EVENTS
	/**
	 * Wenn die Taste "Entfernen" gedrï¿½ckt wird, wird die delete-Methode der beiden DrawComponents aufgerufen.
	 */
	private void svgCanvasKeyDELETEPressed()
	{
		drawComponent1.delete();
		drawComponent2.delete();
	}
	/**
	 * Wenn die Taste "+" gedrï¿½ckt wird zunï¿½chst der Zoom-Count erhï¿½ht, anschlieï¿½end die zoom-Methode der beiden DrawComponents aufgerufen und
	 * zu letzt, die Zeichenflï¿½che um den ZoomFaktor vergrï¿½ï¿½ert.
	 */
	private void svgCanvasKeyPLUSPressed()
	{
		zoomCount++;
		drawComponent1.zoom(zoomFactor);
		drawComponent2.zoom(zoomFactor);
		Connector.staticZoom(zoomFactor);
		zoomDrawComponent(zoomFactor);
		repaint();
	}
	/**
	 * Wenn die Taste "-" gedrï¿½ckt wird zunï¿½chst der Zoom-Count verringert, anschlieï¿½end die zoom-Methode der beiden DrawComponents aufgerufen und
	 * zu letzt, die Zeichenflï¿½che um den ZoomFaktor verkleinert.
	 */
	private void svgCanvasKeyMINUSPressed()
	{
		zoomCount--;
		drawComponent1.zoom(1/zoomFactor);
		drawComponent2.zoom(1/zoomFactor);
		Connector.staticZoom(1/zoomFactor);
		zoomDrawComponent(1/zoomFactor);
		repaint();
	}
	//Menu-Listener
	/**
	 * Menue Tools, Menuepunkt "Zeichenflaeche vergroessern": Dazu wird zunï¿½chst die aktuelle grï¿½ï¿½e des eines DrawComponents ausgelesen, da diese, je nach Bildschirmgrï¿½ï¿½e
	 * variieren kann. Dann wird die selbe Groesse 100 Pixel als neue Groesse gesetzt. Die Groessee wird fuer beide DrawComponents gleichzeitig erhoeht.
	 * @param evt
	 */
	private void menuItemExtendWorkBenchActionPerformed(ActionEvent evt) 
	{
		int width = drawComponent1.getSize().width;
		int height = drawComponent1.getSize().height;
		drawComponent1.setPreferredSize(new Dimension(width+100, height+100));
		drawComponent1.setSize(width+100, height+100);
		drawComponent2.setPreferredSize(new Dimension(width+100, height+100));
		drawComponent2.setSize(width+100, height+100);
	}
	/**
	 * Menï¿½ Tools, Menï¿½punkt "Zeichenflï¿½che vergkleinern": Dazu wird zunï¿½chst die aktuelle grï¿½ï¿½e des eines DrawComponents ausgelesen, da diese, je nach Bildschirmgrï¿½ï¿½e
	 * variieren kann. Dann wird die selbe Grï¿½ï¿½e - 100 Pixel als neue Grï¿½ï¿½e gesetzt. Die Grï¿½ï¿½e wird fï¿½r beide DrawComponents gleichzeitig verkleinert.
	 * @param evt
	 */
	private void menuItemReduceWorkBenchActionPerformed(ActionEvent evt) 
	{
		int width = drawComponent1.getSize().width;
		int height = drawComponent1.getSize().height;
		drawComponent1.setPreferredSize(new Dimension(width-100, height-100));
		drawComponent1.setSize(width-100, height-100);
		drawComponent2.setPreferredSize(new Dimension(width-100, height-100));
		drawComponent2.setSize(width-100, height-100);
	}
	/**
	 * Menue Editor, Menuepunkt "Neues Schaltzeichen": Oeffnet ein neues JFrame mit dem leeren Editor.
	 * @param evt
	 */
	private void menuItemNewSymbolActionPerformed(ActionEvent evt) 
	{
		editorGui.setVisible(true);
	}
	/**
	 * Menue Extras, Menuepunkt "Benutzervorgaben": Oeffnet das Einstellungsfenster
	 * @param evt
	 */
	private void menuItemPreferencesActionPerformed(ActionEvent evt) 
	{
		frmOptions.setVisible(true);
	}
	/**
	 * Menï¿½ Datei, Menï¿½punkt "Speichern unter...": ï¿½ffnet einen Filechooser und speichert den aktuellen Schaltplan.
	 * @param evt
	 */
	private void menuItemSaveAsActionPerformed(ActionEvent evt) 
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
			path = file.getPath();
			if(file.exists())
			{
				result = JOptionPane.showOptionDialog(drawComponent1, "Datei ueberschreiben?", null,
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE,
						null, null, null);
				switch(result)
				{
				case 0: saveAs((path));
				break;
				case 1: menuItemSaveAsActionPerformed(evt);
				break;
				case 2: break;
				}
			}
			else
			{
				saveAs((path + ".ser"));
				path = path + ".ser";
			}
		}
	}
	/**
	 * Menu Datei, Menupunkt "Oeffnen": Per FileChooser wird die zu oeffnede Datei gewaehlt. 
	 * Anschliessend werden die DrawObjects aus der Datei ausgelesen
	 * @param evt
	 */
	@SuppressWarnings("unchecked")
	private void menuItemOpenActionPerformed(ActionEvent evt) 
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
			path = file.getPath();
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
	 * Datei --> Exportieren --> Aktuellen Schaltplan
	 * Ruft die Export-Methode mit Parameter "True" auf, da nur der aktuelle Schaltplan exportiert werden soll.
	 * @param evt
	 * @throws IOException
	 */
	private void menuItemExportCurrentActionPerformed(ActionEvent evt) throws IOException 
	{
		export(true);
	}
	/**
	 * Datei --> Exportieren --> Alle Schaltplaene
	 * Ruft die Export-Methode mit Parameter "False" auf, da beide Schaltplaene exportiert werden sollen.
	 * @param evt
	 * @throws IOException
	 */
	private void menuItemExportBothActionPerformed(ActionEvent evt) throws IOException 
	{
		export(false);
	}
	private void menuItemSaveActionPerformed(ActionEvent evt) 
	{
		if(path == null)
		{
			menuItemSaveAsActionPerformed(evt);
		}
		else
		{
			saveAs(path);
		}
	}
	//GETTER UND SETTER
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
		editorGui.getRightDrawComponent().showGrid(b);
		editorGui.getLeftDrawComponent().showGrid(b);
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
	public void synchronizeWorkBench(boolean b)
	{
		this.synchronizeWorkBench = b;
	}
	public void setWorkBenchBackground(Color c1, Color c2)
	{
		jScrollPane2.getViewport().setBackground(c1);
		jScrollPane3.getViewport().setBackground(c2);
	}
}