package mainFrame;

/**
 * Die Main Klasse enthaelt nur die Main-Methode und dient zum Starten des Programms.
 * @author Clemens Krug
 *
 */
public class Main 
{
	/**
	 * Diese Methode wird beim Starten des Programms aufgerufen.
	 * @param args
	 */
  public static void main(String[] args) 
  {
    FrmMain frmMain = new FrmMain();
    frmMain.setVisible(true);
  }
} 
