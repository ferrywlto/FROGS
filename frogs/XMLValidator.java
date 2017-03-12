package frogs;
import java.io.File;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.transform.dom.DOMSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * 
 * @author Ferry To
 *
 */
public class XMLValidator 
{
	// Get a validator object which created by the specified XML schema
	// for validating against an XML Document.
	public static final Validator getXMLValidatorFromFile(String fileName)
	{
		try
		{
			File validatorFile = new File(fileName);
			if(!(validatorFile.exists() && validatorFile.isFile()))
			{
				fileName = (ServerConfiguration.getStringParameter("WebappsLocation")+fileName);
				validatorFile = new File(fileName);
				// If still not found after concated the webapp location, means path have problem
				if(!(validatorFile.exists() && validatorFile.isFile()))
				{
					throw new Exception("Validator File Not Found.");
				}
			}
			Schema schema = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI).newSchema(validatorFile);
			return schema.newValidator();
		}
		catch (Exception e) 
		{
			e.printStackTrace(); 
			return null;
		}
	}
	
	// Just a handy function. One can get the Validator by getXMLValidatorFromFile()
	// and validate an XML by themselves.
	public static final boolean validateXMLDocument(Validator validator, Document doc)
	{
		try
		{
			validator.validate(new DOMSource(doc));
			return true;
		}
		catch(SAXParseException spe)
		{
			//Messager.printMsg(this,"Document is invalid.");
			spe.printStackTrace();
			return false;			
		}
		catch(SAXException se)
		{
			//Messager.printMsg(this,"Document is invalid.");
			se.printStackTrace();
			return false;
		}
		catch(IOException ioe)
		{
			//Messager.printMsg(this,"I/O Problem.");
			ioe.printStackTrace();
			return false;
		}
	}
	
	/**
	 * @return
	 */
	public static final boolean validateXMLDocument(Document document, String validatorFilename)
	{
		return validateXMLDocument(getXMLValidatorFromFile(validatorFilename),document);
	}
	
	/**
	 * @deprecated this method should be renamed to reflect the new responsibility
	 * @see getMapperValidator()
	 * @return
	 */
	public Validator getRuleMapperValidator()
	{
		String fPath = ServerConfiguration.getStringParameter("RuleMapperSchemaFileLocation");
		String fName = ServerConfiguration.getStringParameter("RuleMapperSchemaFileName");
		return getXMLValidatorFromFile(fPath+fName);
	}
	
	/**
	 * @deprecated this method is legacy of WBCGD and no longer use in FROGS
	 * @return
	 */
	public static Validator getSpecificationValidator()
	{
		String fPath = ServerConfiguration.getStringParameter("SpecificationSchemaFileLocation");
		String fName = ServerConfiguration.getStringParameter("SpecificationSchemaFileName");
		return getXMLValidatorFromFile(fPath+fName);
		
	}
	
	/**
	 * @deprecated this method is legacy of WBCGD and no longer use in FROGS
	 * @return
	 */	
	public Validator getCommandValidator()
	{
		String fPath = ServerConfiguration.getStringParameter("CommandSchemaFileLocation");
		String fName = ServerConfiguration.getStringParameter("CommandSchemaFileName");
		return getXMLValidatorFromFile(fPath+fName);
	}	
}
