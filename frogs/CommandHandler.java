package frogs;


/**
 * @author  Ferry To
 */
public abstract class CommandHandler 
{
	// All concrete subclass of action need these
	// These cannot be changed after object creation
	//protected final GeneralResourceManager manager;
	protected final String[] params;
	
	public CommandHandler(GeneralResourceManager grm, String[] args)
	{
		manager = grm;
		params = args;
	}
	// Use this for the new solution approach
	// Concrete class that extend this abstract class need
	// to override this method and have the method pass the
	// result msg to the result_manager. Therefore,
	// concrete class of action also have to hold a reference to
	// its result_manager. In-addition, the reference_manager for
	// #current_player#, #current_tile#, #current_tile#, rule type, etc.
	public abstract void execute();
}
