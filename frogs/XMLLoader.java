package frogs;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.validation.SchemaFactory;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * @author    Ferry To
 * @uml.dependency   supplier="webBaseChessGameDesigner.system.Messager.printMsg"
 */
public class XMLLoader 
{
	protected static SchemaFactory sFactory;
	protected static DocumentBuilderFactory dFactory;
	protected static DocumentBuilder builder;

	/**
	 * @uml.property   name="loader"
	 */
	protected static XMLLoader loader = getInstance();
	
	/**
	 * @return  the loader
	 * @uml.property  name="loader"
	 */
	protected static XMLLoader getInstance()
	{
		if (loader != null)
			return loader;
		else
			return new XMLLoader();
	}
	
	/*
	 * static String schemaFileName;
			static String schemaFilePath;
			trace("Load Chess Game Design Specification Schema: "+schemaFilePath+schemaFileName);
	 */
	
	// Singleton Pattern used to ensure only one instance created.
	private XMLLoader()
	{
		try
		{
			sFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			dFactory = DocumentBuilderFactory.newInstance();
			builder = dFactory.newDocumentBuilder();
		}
		catch(ParserConfigurationException pce){ pce.printStackTrace(); System.exit(0);}
	}
	
	
	
	// IMPORTANT! This is the way how Flash communicate with Servlet by XML!
	// Especially for Servlet that getting a pure XML document form a client.
	// Usage: getXMLDocumentFromHttpRequest(reg.getReader());
	public static Document getXMLDocumentFromHttpRequest(BufferedReader br)
	{
		try { return builder.parse(new InputSource(br)); }
		catch (Exception e) 
		{
			e.printStackTrace(); 
			return null;
		}
	}

	// Convert a long XML string into an XML Document Object.
	// Usage: getXMLDocumentFromString("<xml><root><foo name="bar"/></root></xml>");
	// P.S. also can use it to parse a HTTP Request parameter.
	public static Document getXMLDocumentFromString(String xmlStr)
	{	
		try { return builder.parse(new InputSource(new StringReader(xmlStr))); }
		catch (SAXParseException e)
		{
			e.printStackTrace();
			//Messager.printMsg(this,"[XMLLoader]: Document is not well-formed.");
			return null;
		}
		catch (SAXException e)
		{
			e.printStackTrace();
			//Messager.printMsg(this,"Document is invaild.");
			return null;
		}
		catch (IOException e)
		{
			e.printStackTrace();
			//Messager.printMsg(this,"Cannot get document.");
			return null;
		}
	}

	// Get an XML Document Object by parsing an XML file on the server.
	public static Document getXMLDocumentFromFile(String fileName)
	{	
		try { return builder.parse(new InputSource(new FileReader(new File(fileName)))); }
		catch (Exception e) 
		{
			e.printStackTrace(); 
			return null;
		}
	}
	
	// This version is to grab XML document from an InputStream, 
	// if all three versions above does not suit for you, 
	// this should be the one you need. 
	public static Document getXMLDocumentFromStream(InputStream is)
	{
		try { return builder.parse(new InputSource(is)); }
		catch (Exception e) 
		{
			e.printStackTrace(); 
			return null;
		}
	}
	

	

}
