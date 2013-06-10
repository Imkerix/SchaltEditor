package mainFrame;
import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import javax.swing.WindowConstants;


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
public class FrmOptions extends javax.swing.JFrame 
{
	private static final long serialVersionUID = 1L;
	private JTextField jTextField1;
	private JButton jButton1;
	private JCheckBox jCheckBox2;
	private JButton jButton6;
	private JButton jButton5;
	private JButton jButton4;
	private JButton jButton3;
	private JEditorPane jEditorPane2;
	private JLabel jLabel6;
	private JSeparator jSeparator2;
	private JLabel jLabel5;
	private JSeparator jSeparator1;
	private JLabel jLabel4;
	private JEditorPane jEditorPane1;
	private JLabel jLabel3;
	private JTabbedPane jTabbedPane1;
	private JTextField jTextField2;
	private JLabel jLabel2;
	private JCheckBox jCheckBox1;
	private JButton jButton2;
	private JLabel jLabel1;
	private JPanel jPanel1;
	private JPanel jPanel2;

	private FrmMain frmMain;
	private DrawComponent drawComponent1;
	private DrawComponent drawComponent2;

	public FrmOptions(FrmMain frmMain, DrawComponent dc1, DrawComponent dc2) 
	{
		super();
		this.frmMain = frmMain;
		this.drawComponent1 = dc1;
		this.drawComponent2 = dc2;
		initGUI();
	}

	private void initGUI() {
		try {
			ActionListener alABORT = new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					jButton2ActionPerformed(evt);
				}
			};	
			ActionListener alSAVE = new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					jButton1ActionPerformed(evt);
				}
			};		
			ActionListener alAPPLY = new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					jButton5ActionPerformed(evt);
				}
			};		
			


			{
				jLabel1 = new JLabel();
				jLabel1.setText("Zoom-Faktor:");
				jLabel1.setBounds(28, 20, 111, 16);
			}
			{
				jTextField1 = new JTextField();
				jTextField1.setBounds(151, 17, 56, 23);
				jTextField1.setText(Double.toString(frmMain.getZoomFactor()));
			}
			{
				jButton2 = new JButton();
				jButton2.setText("Abbrechen");
				jButton2.setBounds(412, 320, 101, 23);
				jButton2.addActionListener(alABORT);
			}
			{
				jCheckBox1 = new JCheckBox();
				jCheckBox1.setText("Gitter anzeigen");
				jCheckBox1.setBounds(22, 108, 129, 20);
			}
			{
				jLabel2 = new JLabel();
				jLabel2.setText("Gitter-Interval:");
				jLabel2.setBounds(22, 137, 97, 16);
			}
			{
				jTextField2 = new JTextField();
				jTextField2.setBounds(151, 134, 54, 23);
				jTextField2.setText(Integer.toString(frmMain.getGridInterval()));
			}
			{
				jCheckBox2 = new JCheckBox();
				jCheckBox2.setText("Synchronisiertes Verschieben");
				jCheckBox2.setBounds(22, 195, 255, 20);
			}
			{
				jPanel1 = new JPanel();
				jPanel1.add(jButton2);
				jPanel1.add(jCheckBox1);
				jPanel1.add(jCheckBox2);
				jPanel1.add(jLabel1);
				jPanel1.add(jLabel2);
				jPanel1.add(jTextField1);
				jPanel1.add(jTextField2);
				{
					jButton1 = new JButton();
					jPanel1.add(jButton1);
					jButton1.setText("Speichern");
					jButton1.setBounds(530, 320, 116, 23);
					jButton1.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							jButton1ActionPerformed(evt);
						}
					});
				}
				{
					jButton5 = new JButton();
					jPanel1.add(jButton5);
					jButton5.setText("Uebernehmen");
					jButton5.setBounds(283, 320, 108, 23);
					jButton5.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							jButton5ActionPerformed(evt);
						}
					});
				}
			}
			jPanel2 = new JPanel();

			{
				jTabbedPane1 = new JTabbedPane();
				jTabbedPane1.addTab("Allgemein", jPanel1);
				jTabbedPane1.addTab("Farben", jPanel2);
				jPanel2.setLayout(null);
				{
					jLabel3 = new JLabel();
					jPanel2.add(jLabel3);
					jLabel3.setText("Rasterfarbe:");
					jLabel3.setBounds(12, 34, 111, 16);
				}
				{
					jEditorPane1 = new JEditorPane();
					jPanel2.add(jEditorPane1);
					jEditorPane1.setBounds(146, 34, 27, 20);
					jEditorPane1.setEditable(false);
					jEditorPane1.addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent evt) {
							jEditorPane1MouseClicked(evt);
						}
					});
				}
				{
					jLabel4 = new JLabel();
					jPanel2.add(jLabel4);
					jLabel4.setText("Zeichenflaeche Wirkschaltplan");
					jLabel4.setBounds(12, 12, 259, 16);
				}
				{
					jLabel5 = new JLabel();
					jPanel2.add(jLabel5);
					jLabel5.setText("Zeichenflaeche Stromlaufplan");
					jLabel5.setBounds(328, 12, 210, 16);
				}
				{
					jSeparator1 = new JSeparator(SwingConstants.VERTICAL);
					jPanel2.add(jSeparator1);
					jSeparator1.setBounds(306, 12, 10, 130);
				}
				{
					jSeparator2 = new JSeparator();
					jPanel2.add(jSeparator2);
					jSeparator2.setBounds(24, 165, 610, 10);
				}
				{
					jLabel6 = new JLabel();
					jPanel2.add(jLabel6);
					jLabel6.setText("Rasterfarbe:");
					jLabel6.setBounds(328, 34, 81, 16);
				}
				{
					jEditorPane2 = new JEditorPane();
					jPanel2.add(jEditorPane2);
					jEditorPane2.setBounds(453, 37, 35, 20);
					jEditorPane2.setEditable(false);
					jEditorPane2.addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent evt) {
							jEditorPane2MouseClicked(evt);
						}
					});
				}
				{
					jButton3 = new JButton();
					jPanel2.add(jButton3);
					jButton3.setText("Speichern");
					jButton3.setBounds(538, 315, 103, 23);
					jButton3.addActionListener(alSAVE);
				}
				{
					jButton4 = new JButton();
					jPanel2.add(jButton4);
					jButton4.setText("Abbrechen");
					jButton4.setBounds(432, 315, 88, 23);
					jButton4.addActionListener(alABORT);
				}
				{
					jButton6 = new JButton();
					jPanel2.add(jButton6);
					jButton6.setText("Uebernehmen");
					jButton6.setBounds(298, 315, 123, 23);
					jButton6.addActionListener(alAPPLY);
				}
				jPanel1.setLayout(null);
				getContentPane().add(jTabbedPane1, BorderLayout.CENTER);
			}
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			pack();
			this.setSize(684, 420);
		} catch (Exception e) {
			//add your error handling code here
			e.printStackTrace();
		}
	}

	private void jButton1ActionPerformed(ActionEvent evt) 
	{
		applyOptions();
		dispose();
	}

	private void jButton2ActionPerformed(ActionEvent evt) 
	{
		dispose();
	}

	private void jEditorPane1MouseClicked(MouseEvent evt) 
	{
		Color c = JColorChooser.showDialog(this, "Rasterfarbe Zeichenflaeche Wirkschaltplan:", new Color(210, 210, 210));
		jEditorPane1.setBackground(c);
	}

	private void jEditorPane2MouseClicked(MouseEvent evt) 
	{
		Color c = JColorChooser.showDialog(this, "Rasterfarbe Zeichenflaeche Stromlaufplan:", new Color(210, 210, 210));
		jEditorPane2.setBackground(c);
	}

	private void jButton5ActionPerformed(ActionEvent evt) 
	{
		applyOptions();
	}

	public void applyOptions()
	{
		try
		{
			frmMain.setZoomFactor(Double.parseDouble(jTextField1.getText()));    
			frmMain.setGridInterval(Integer.parseInt(jTextField2.getText()));
		}
		catch(NumberFormatException nfe)
		{

		}

		frmMain.showGrid(jCheckBox1.isSelected());
		frmMain.setSynchronizedMoving(jCheckBox2.isSelected());
		drawComponent1.setGridColor(jEditorPane1.getBackground());
		drawComponent2.setGridColor(jEditorPane2.getBackground());
	}

} 