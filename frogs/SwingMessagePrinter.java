package frogs;
import javax.swing.text.JTextComponent;

public class SwingMessagePrinter implements MessagePrinter
{
	protected JTextComponent outputComponent;
	protected String message;
	protected static SwingMessagePrinter logger = null;
	
	protected SwingMessagePrinter(){}
	
	public void printMsg(Object obj, String msg)
	{
		if(outputComponent == null)
		{
			ConsoleMessagePrinter.getInstance().printMsg(obj, msg);
		}
		else
		{
			message = ConsoleMessagePrinter.getDateString()+ConsoleMessagePrinter.getContentString(obj, msg);
			outputComponent.setText(outputComponent.getText()+(message+"\n"));
			outputComponent.setCaretPosition(outputComponent.getText().length());
		}
	}
	
	public static SwingMessagePrinter getInstance()
	{
		if(logger == null)
			logger = new SwingMessagePrinter();
		
		return logger;
	}
	
	public void setOutputComponent(JTextComponent component)
	{
		outputComponent = component;
		new SwingMessagePrinter();
	}
}
