package mainFrame;

import java.util.ArrayList;

import editor.EditorGUI;

import shared.GeometricObject;
import shared.Kreis;
import shared.Linie;
import shared.Rechteck;

public class Main 
{
  public static void main(String[] args) 
  {
    FrmMain frmMain = new FrmMain();
    frmMain.setVisible(true);
    //Force Commit 2
	
	  
	  


			// Usage example for the reworking of an object		
//				ArrayList<GeometricObject> left = new ArrayList<GeometricObject>();
//				left.add(new Kreis(0, 0, 34, 67));
//				left.add(new Rechteck(45, 34, 374, 7));
//				left.add(new Linie(3, 78, 64, 68));
//				
//				
//				ArrayList<GeometricObject> right = new ArrayList<GeometricObject>();
//				right.add(new Kreis(66, 66, 34, 67));
//				right.add(new Rechteck(33, 34, 34, 7));
//				right.add(new Linie(77, 78, 64, 68));
//				
//				EditorGUI eGui = new EditorGUI("robotsFTW", left,right);
//				eGui.setVisible(true);

			// normal usage for the editor
//				EditorGUI eGui = new EditorGUI();
//				eGui.setVisible(true);
	  
  }
} 