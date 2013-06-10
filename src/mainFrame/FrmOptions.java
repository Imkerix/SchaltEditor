package mainFrame;
import java.awt.BorderLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

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
	private JTabbedPane jTabbedPane1;
	private JTextField jTextField2;
	private JLabel jLabel2;
	private JCheckBox jCheckBox1;
	private JButton jButton2;
	private JLabel jLabel1;
	private JPanel jPanel1;

	private FrmMain frmMain;

	public FrmOptions(FrmMain frmMain) 
	{
		super();
		this.frmMain = frmMain;
		initGUI();
	}

	private void initGUI() {
		try {
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
				jButton2.setBounds(402, 320, 101, 23);
				jButton2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						jButton2ActionPerformed(evt);
					}
				});
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
			}
			{
				jTabbedPane1 = new JTabbedPane();
				jTabbedPane1.addTab("Allgemein", jPanel1);
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

		dispose();
	}

	private void jButton2ActionPerformed(ActionEvent evt) 
	{
		dispose();
	}

} 