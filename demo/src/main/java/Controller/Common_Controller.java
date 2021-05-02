package Controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import JDBC.Repository_Login;
import JDBC.Repository_Server;
import JDBC.Repository_ToDo;

@Controller
public class Common_Controller {
	
	@Autowired
	private Repository_Login jdbc_Login;
	
	@Autowired
	private Repository_Server jdbc2;
	
	@Autowired
	private Repository_ToDo jdbc4;

	@RequestMapping(value = {"go_main.do"})
	public ModelAndView Go_Main(HttpSession session) throws IOException {
		
		ModelAndView mav = new ModelAndView("JSP/Error.jsp");
		ModelAndView mav1 = new ModelAndView("JSP/Main_Employee.jsp");
		ModelAndView mav2 = new ModelAndView("JSP/Main_CEO.jsp");
		
		mav1.addObject("ID", session.getAttribute("ID"));
		mav2.addObject("ID", session.getAttribute("ID"));
		
		String Permission = jdbc_Login.select_Permission((String) session.getAttribute("User_Num"));
		
		if(Permission.equals("Employee")) {
			
			return mav1;
		}
		
		if(Permission.equals("CEO")) {
			
			String ID = (String) session.getAttribute("ID");
			mav2.addObject("ID", ID);
			mav2.addObject("year", jdbc2.Select_Today_Year());
			mav2.addObject("month", jdbc2.Select_Today_Month());
			mav2.addObject("day", jdbc2.Select_Today_Day());
			mav2.addObject("todo_incompleted" , jdbc4.Make_ToDo_Incompleted_List());
			
			return mav2;
		}
		
		// 권한 NULL 예외처리
		return mav;
	}
	
	
	
}
