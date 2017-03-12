package frogs;


public enum ConnectionState 
{
	// GENERAL
	INIT, 
	LOGGED, 
	CLOSED,
	
	// SPECIFIC
	WAITING, 
	PLAY_CMD, 
	PLAY_WAIT 
}
