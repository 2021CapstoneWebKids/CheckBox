package Controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import JDBC.Repository_Modify_Info;
import Security.Bcrypt;

@Controller
public class InfoController_UC1 {

	@Autowired
	Bcrypt Bcry;
	
	@Autowired
	private Repository_Modify_Info jdbc;
	
	@RequestMapping(value = "modify_info.do")
	public ModelAndView Modify_info(HttpSession session) {
		
		ModelAndView mav = new ModelAndView("JSP/Modify_Info.jsp");
		
		mav.addObject("MyName", jdbc.select_MyName(session.getAttribute("User_Num")));
		mav.addObject("MyID", jdbc.select_MyID(session.getAttribute("User_Num")));
		
		return mav;
	}
}
