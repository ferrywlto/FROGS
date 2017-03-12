package frogs;
import java.util.Calendar;
import java.util.Date;
/**
 * <<Singleton>>
 * @author Ferry To
 *
 */
public class ConsoleMessagePrinter implements MessagePrinter
{
	
	protected static final Calendar cal = Calendar.getInstance();
	protected static ConsoleMessagePrinter logger = null;
	
	public static String getDateString()
	{	
		cal.setTime(new Date());

		String dateStr = cal.get(Calendar.YEAR)+":"+(cal.get(Calendar.MONTH)+1)+":"+cal.get(Calendar.DAY_OF_MONTH);
		String timeStr = cal.get(Calendar.HOUR_OF_DAY)+":"+cal.get(Calendar.MINUTE)+":"+cal.get(Calendar.SECOND);

		return "["+dateStr+"|"+timeStr+"]";
	}

	public static String getContentString(Object obj, String msg)
	{
		return ("["+obj.getClass().getSimpleName()+"]: "+msg);
	}
	
	public static String getCompleteString(Object obj, String msg)
	{
		return (getDateString()+getContentString(obj, msg));
	}
	
	protected ConsoleMessagePrinter(){}
	
	/**
	 *<<Factory Method>> 
	 */
	/**
	protected static ConsoleMessagePrinter createInstance()
	{
		return new ConsoleMessagePrinter();
	}
	*/
	public static ConsoleMessagePrinter getInstance()
	{
		if(logger == null)
			logger = new ConsoleMessagePrinter();
		
		return logger;
	}
	public void printMsg(Object obj, String msg)
	{	
		System.out.println(getCompleteString(obj, msg));
	}

}
