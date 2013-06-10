package mainFrame;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import org.apache.batik.dom.svg.SAXSVGDocumentFactory;
import org.apache.batik.util.XMLResourceDescriptor;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import shared.Connector;
import shared.Kreis;
import shared.Linie;
import shared.Rechteck;

public class ElectricObject 
{
  private String name;
  private File wirkschaltplan;
  private File stromlaufplan;

  public ElectricObject(String name, File wirkschaltplan, File stromlaufplan)
  {
    this.name = name;
    this.wirkschaltplan = wirkschaltplan;
    this.stromlaufplan = stromlaufplan;  
  }

  public DrawObject getDrawObject(int state, int index, int zoomCount, double zoomFactor)
  {
    DrawObject drawObject = new DrawObject(index);
    ArrayList<Double> xList= new ArrayList<Double>();
    ArrayList<Double> yList= new ArrayList<Double>();
    File svgFile;
    
    if(state == 0)
    {
      svgFile = wirkschaltplan;
    }
    else
    {
      svgFile = stromlaufplan;
    }



    Document doc = null;
    try 
    {
      SAXSVGDocumentFactory f = new SAXSVGDocumentFactory(XMLResourceDescriptor.getXMLParserClassName());
      doc = f.createDocument(svgFile.toURI().toString());
    } 
    catch (IOException e) 
    {
      e.printStackTrace();
    }

    Element root = doc.getDocumentElement();        
    NodeList list = root.getChildNodes();        

    NodeList objectList = null;


    for (int i = 0; i < list.getLength(); i++) 
    {
      if(list.item(i).getNodeName().equals("g"))
      {            
        objectList = list.item(i).getFirstChild().getChildNodes();
      }          
    }

    for (int i = 0; i < objectList.getLength(); i++) 
    {
      Node actObject = objectList.item(i);
      NamedNodeMap attributes = actObject.getAttributes();
      boolean objectFound = false;
      boolean isLine = false;

      double x = 0;
      double y = 0;
      double width = 0;
      double height = 0;

      switch(actObject.getNodeName())
      {
      case "rect": 
        for (int j = 0; j < attributes.getLength(); j++) 
        {
          Node actAttribute = attributes.item(j);
          switch(actAttribute.getNodeName())
          {
          case "x":
            x = Integer.parseInt(actAttribute.getNodeValue());
            break;
          case "y":
            y = Integer.parseInt(actAttribute.getNodeValue());
            break;
          case "width":
            width = Integer.parseInt(actAttribute.getNodeValue());
            break;            
          case "height":
            height = Integer.parseInt(actAttribute.getNodeValue());
            break;
          }          
        }  
        drawObject.addGeometricObject(new Rechteck(x, y, width, height));
        objectFound = true;
        break;        

      case "circle":
        for (int j = 0; j < attributes.getLength(); j++) 
        {
          Node actAttribute = attributes.item(j);
          switch(actAttribute.getNodeName())
          {
          case "r":
            width = Double.parseDouble(actAttribute.getNodeValue())*2;
            height = width;
            break;
          case "cx":
            x = Double.parseDouble(actAttribute.getNodeValue())-width/2;
            break;
          case "cy":
            y = Double.parseDouble(actAttribute.getNodeValue())-height/2;
            break;
          }          
        }
        if(width == 8 && height == 8)
        {
          drawObject.addConnector(new Connector(x, y, width, height));
        }
        else
        {
        drawObject.addGeometricObject(new Kreis(x, y, width, height));
        }
        objectFound = true;
        break;

      case "line":
        for (int j = 0; j < attributes.getLength(); j++) 
        {
          Node actAttribute = attributes.item(j);
          switch(actAttribute.getNodeName())
          {
          case "x1":
            x = Integer.parseInt(actAttribute.getNodeValue());
            break;
          case "x2":
            width = Integer.parseInt(actAttribute.getNodeValue());
            break;
          case "y1":
            y = Integer.parseInt(actAttribute.getNodeValue());
            break;
          case "y2":
            height = Integer.parseInt(actAttribute.getNodeValue());
            break;
          }          
        }
        drawObject.addGeometricObject(new Linie(x, y, width, height));
        objectFound = true;
        isLine = true;
        break;
      }

      if(objectFound)
      {
        xList.add(x);
        yList.add(y);
        
        if(isLine)
        {          
          xList.add(width);          
          yList.add(height);          
        }
        else
        {
          xList.add(x+width);
          yList.add(y+height);
        }
      }
    }

    Collections.sort(xList);
    Collections.sort(yList);

    drawObject.setOuterPoints(xList.get(0), yList.get(0), xList.get(xList.size()-1), yList.get(yList.size()-1));  
    
    drawObject.zoom(Math.pow(zoomFactor, zoomCount));

    return drawObject;
  }


  public String getName()
  {
    return name;
  }

} 