package frogs;


import java.util.HashMap;
import java.util.Hashtable;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class CommandHandlerMapper 
{
	// This class works like a dynamic class loader using Java Reflection
	// by using this class, both client and server can refer to single XML
	// file for action/condition ID mapping.
	
	static boolean commandMapped = false;
	//static final HashMap<Integer,String> conditionMap = new HashMap<Integer,String>(0);
	//static final HashMap<Integer,String> actionMap = new HashMap<Integer,String>(0);
	static final HashMap<Integer,String> commandMap = new HashMap<Integer,String>(0);
	
	protected static final CommandHandlerMapper mapper = new CommandHandlerMapper();
	
	public static void startRuleMapping()
	{
		if(!commandMapped)
		{
			String filePath = ServerConfiguration.getStringParameter("HandlerMappingFileLocation");
			String fileName = ServerConfiguration.getStringParameter("HandlerMappingFileName");

			Document cmdMappingDoc = XMLLoader.getXMLDocumentFromFile(filePath+fileName);
			
			boolean isCommandMappingValid = CommandHandlerMappingValidator.validateCommandHandlerMapping(cmdMappingDoc); 
				
			if(isCommandMappingValid)
			{
				Element root = cmdMappingDoc.getDocumentElement();
				NodeList handlerNodes = root.getElementsByTagName("handler");
				
				Logger.printMsg(mapper,"Registering Command Handlers...");
				for(int i=0; i<handlerNodes.getLength(); i++)
				{
					Element node = (Element)handlerNodes.item(i);
					//String id = node.getElementsByTagName("ID").item(0).getFirstChild().getTextContent();
					String id = node.getAttribute("ID");
					//String name = node.getElementsByTagName("name").item(0).getFirstChild().getTextContent();
					String name = node.getAttribute("name");
					
					commandMap.put(Integer.parseInt(id), name);
					Logger.printMsg(mapper,"Handler[ID:"+id+"|name:"+name+"]");
				}
				
				commandMapped = true;
				Logger.printMsg(mapper,"Command Handlers Registration Completed.\n");
			}
			else
			{
				Logger.printMsg(mapper,"Command Handlers Mapping Failed.");
				System.exit(0);
			}
		}
	}

	public static String getCommandHandlerNameByID(int ID)
	{
		if(commandMapped)
			return commandMap.get(ID);
		else
			return null;
	}
}
