package mainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
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
  private JTextField jTextField2;
  private JLabel jLabel2;
  private JCheckBox jCheckBox1;
  private JButton jButton2;
  private JLabel jLabel1;
  
  private FrmMain frmMain;

  public FrmOptions(FrmMain frmMain) 
  {
    super();
    this.frmMain = frmMain;
    initGUI();
  }
  
  private void initGUI() {
    try {
      setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
      getContentPane().setLayout(null);
      {
        jLabel1 = new JLabel();
        getContentPane().add(jLabel1, "Center");
        jLabel1.setText("Zoom-Faktor:");
        jLabel1.setBounds(20, 21, 92, 18);
      }
      {
        jTextField1 = new JTextField();
        getContentPane().add(jTextField1);
        jTextField1.setBounds(142, 19, 51, 23);
        jTextField1.setText(Double.toString(frmMain.getZoomFactor()));
      }
      {
        jButton1 = new JButton();
        getContentPane().add(jButton1);
        jButton1.setText("Speichern");
        jButton1.setBounds(552, 348, 112, 23);
        jButton1.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent evt) {
            jButton1ActionPerformed(evt);
          }
        });
      }
      {
        jButton2 = new JButton();
        getContentPane().add(jButton2);
        jButton2.setText("Abbrechen");
        jButton2.setBounds(419, 348, 116, 23);
        jButton2.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent evt) {
            jButton2ActionPerformed(evt);
          }
        });
      }
      {
        jCheckBox1 = new JCheckBox();
        getContentPane().add(jCheckBox1);
        jCheckBox1.setText("Gitter anzeigen");
        jCheckBox1.setBounds(20, 51, 173, 20);
      }
      {
        jLabel2 = new JLabel();
        getContentPane().add(jLabel2);
        jLabel2.setText("Gitter-Interval:");
        jLabel2.setBounds(20, 85, 108, 16);
      }
      {
        jTextField2 = new JTextField();
        getContentPane().add(jTextField2);
        jTextField2.setBounds(140, 82, 53, 23);
        jTextField2.setText(Integer.toString(frmMain.getGridInterval()));
      }
      {
        jCheckBox2 = new JCheckBox();
        getContentPane().add(jCheckBox2);
        jCheckBox2.setText("Synchronisiertes Verschieben");
        jCheckBox2.setBounds(20, 113, 210, 20);
      }
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