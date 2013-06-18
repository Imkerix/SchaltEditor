package mainFrame;

import java.util.ArrayList;

import com.sun.org.apache.bcel.internal.generic.GETFIELD;

import editor.EditorGUI;

import shared.GeometricObject;
import shared.Kreis;
import shared.Linie;
import shared.Rechteck;

/**
 * The main-class only includes the main-method.
 * @author Clemens Krug
 *
 */
public class Main 
{
	/**
	 * This method is called at the very start of the program.
	 * @param args
	 */
  public static void main(String[] args) 
  {
//    FrmMain frmMain = new FrmMain();
//    frmMain.setVisible(true);
	  
	  ArrayList<GeometricObject> left = new ArrayList<GeometricObject>();
	  left.add(new Kreis(0, 0, 44, 44));
	  left.add(new Linie(34, 0, 44, 44));
	  left.add(new Rechteck(0, 45, 44, 44));
	  
	  ArrayList<GeometricObject> right = new ArrayList<GeometricObject>();
	  right.add(new Kreis(0, 0, 44, 44));
	  right.add(new Linie(34, 0, 44, 44));
	  right.add(new Rechteck(0, 45, 44, 44));
	  
	  
	  EditorGUI test = new EditorGUI();
	  test.setVisible(true);
	  test.setEditMode("RobotsFTW", left, right);
  }
} 
