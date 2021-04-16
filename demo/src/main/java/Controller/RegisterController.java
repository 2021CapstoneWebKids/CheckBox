package Controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import JDBC.Repository_Login;
import JDBC.Repository_Modify_Info;
import JDBC.Repository_Register;
import Security.Bcrypt;

@Controller
public class RegisterController {
	
	@Autowired
	Bcrypt Bcry;

	@Autowired
	private Repository_Register jdbc;
	
	@RequestMapping(value = "register.do")
	public ModelAndView Register(HttpServletRequest req , HttpServletResponse httpServletResponse) throws IOException{
		
		ModelAndView mav = new ModelAndView("JSP/Register.jsp");
		
		String ID = req.getParameter("ID");
		String PW = req.getParameter("PW");
		String NAME = req.getParameter("NAME");
		String W_NUMBER = req.getParameter("W_NUMBER");
		
		
		// ID중복검사 TC3-3
		if(jdbc.is_Exist_ID(ID)) {
			// 중복이 있을시
			mav.addObject("fail_message", "중복 ID입니다 다른 ID를 입력해주세요");
			mav.addObject("PW", PW);
			mav.addObject("NAME", NAME);
			
			return mav;
		}
		
		return mav;
		
	}
	
	
	
}
