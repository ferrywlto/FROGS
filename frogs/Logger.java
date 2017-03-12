package frogs;
/**
 * <<Singleton>>
 * @author Ferry To
 *
 */
public class Logger
{
	protected static MessagePrinter printer = null;
	
	public static boolean isPrinterSet()
	{
		return (printer != null);
	}
	
	public static boolean setPrinter(MessagePrinter msgPrinter)
	{
		if(isPrinterSet())
			return false;
		else
		{
			printer = msgPrinter;
			return true;
		}
	}
	
	public static void printMsg(Object obj, String msg)
	{	
		try
		{
			if(!isPrinterSet())
				throw new Exception("Printer not set.");
			else
				printer.printMsg(obj, msg);
		}
		catch (Exception e){e.printStackTrace();}
	}	
}