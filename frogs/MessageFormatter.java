package frogs;

public class MessageFormatter {
	//Just a handy function to construct an error response.
	public static String responseMsg(String type, String result)
	{
		return "<wbcgdp_response type='"+type+"'><result>"+result+"</result></wbcgdp_response>";
	}
	
	public static String errorMsg(String msg)
	{
		return "<wbcgdp_response type='error'><msg>"+msg+"</msg></wbcgdp_response>";
	}
	
	public static String infoMsg(String msg)
	{
		return "<wbcgdp_response type='info'><msg>"+msg+"</msg></wbcgdp_response>";
	}
}
