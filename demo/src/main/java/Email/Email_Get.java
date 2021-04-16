package Email;

import javax.servlet.http.HttpServletRequest;

public class Email_Get {
	
	private String name, email, msg;
	
	public void GetEmail(HttpServletRequest req)
	{
		name = req.getParameter("name");
		email = req.getParameter("email");
		msg = req.getParameter("message");
		
		
		System.out.println("\nRegister Request : " + name+"  "+email+"  "+msg);
	}
	
	public String getEmail() {
		return email;
	}
	public String getName() {
		return name;
	}
	public String getMsg() {
		return msg;
	}

}