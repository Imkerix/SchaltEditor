package editor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map.Entry;

import org.apache.batik.svggen.SVGGraphics2D;
import org.apache.batik.svggen.SVGGraphics2DIOException;

/**
 * A modified version of the Class found at 
 * http://xmlgraphics.apache.org/batik/using/svg-generator.html
 * @author erik heinisch
 *
 */

public class SVGGen {
	
	/**
	 * Creates the SVG files, and the directories they need to be structured in a tidy way.
	 * 
	 * @param p_recentPaintingObjects a {@link HashMap} <String, SVGGraphics2D> that provides all SVGGraphics2D objects and path 
	 * details needed to create and stream the SVG Files, and their directory structure.
	 */
	public void mkFile(HashMap<String, SVGGraphics2D> p_recentPaintingObjects)  
	{ 
	    for (Entry<String, SVGGraphics2D> pair : p_recentPaintingObjects.entrySet()) 
	    { 
	    	// Begin : How are the directory paths this SVG objects will need
		    	String objectDir = pair.getKey().substring(0, pair.getKey().lastIndexOf(File.separator));
		    	String rootDir = objectDir.substring(0, objectDir.lastIndexOf(File.separator));
	    	// End : How are the directory paths this SVG objects will need
		    
	    	// Begin : if Directory does not exist create one
		    	File root = new File(rootDir); // root directory
		    	if(!root.exists())
		    	{
		    		root.mkdir();
		    	}
		    	File object = new File(objectDir); // directory for the specific object
		    	if(!object.exists())
		    	{
		    		object.mkdir();
		    	}
	    	// End : if Directory does not exist create one
		    	
		    // Begin : Create new file and stream the SVG into
		    	try 
		    	{
		    		Writer out = new OutputStreamWriter(new FileOutputStream(new File(pair.getKey())), "UTF-8");
		    		pair.getValue().stream(out, true); //true means that svgGenerator  uses CSS
		    	}
		    	catch (UnsupportedEncodingException | FileNotFoundException | SVGGraphics2DIOException e) 
		    	{
		    		e.printStackTrace();
		    	}
	    	// End : Create new file and stream the SVG into
	    	
	    }
	}
	
}
