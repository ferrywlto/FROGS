package frogs;
import javax.xml.validation.Validator;

import org.w3c.dom.Document;


public class CommandHandlerMappingValidator extends XMLValidator
{
	
	public static final boolean validateCommandHandlerMapping(Document document)
	{
		return validateXMLDocument(getMapperValidator(),document);
	}
	
	protected static final Validator getMapperValidator()
	{
		String fPath = ServerConfiguration.getStringParameter("HandlerMappingSchemaPath");
		String fName = ServerConfiguration.getStringParameter("HandlerMappingSchemaFile");
		return getXMLValidatorFromFile(fPath+fName);
	}
}
