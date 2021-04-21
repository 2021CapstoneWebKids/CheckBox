package Controller;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import Email.Email_Send_Gmail;
import JDBC.Repository_Login;
import JDBC.Repository_Modify_Info;
import Scheduler.WithDrawal_Code_Make;
import Security.Bcrypt;

@Controller
public class Forgot_Controller {

	@Autowired
	Bcrypt Bcry;
	
	@Autowired
	private Repository_Login jdbc;
	
	@Autowired
	private Repository_Modify_Info jdbc2;
	
	@Autowired
	WithDrawal_Code_Make wcm;
	
	private String code;
	private String ID;
	private String Name;
	
	@RequestMapping(value = "Forgot_try.do")
	public ModelAndView Forgot_try(HttpServletRequest req) throws IOException, AddressException, MessagingException {
		ModelAndView mav = new ModelAndView("JSP/ForgotAccount_Waiting.jsp");
		ModelAndView mav2 = new ModelAndView("JSP/ForgotAccount.jsp");
		
		ID = req.getParameter("ID");
		Name = req.getParameter("Name");
		
		if(Name.equals(jdbc2.select_MyName(jdbc.select_usernum(ID)))) {
			
			wcm.startScheduler(ID);
			this.code = wcm.getCode();
			Email_Send_Gmail es_g = new Email_Send_Gmail();
			
			mav.addObject("fail_message" , "이메일" + 
							jdbc.select_userEmail(jdbc.select_usernum(ID))
							+ "로 복구코드를 전송했습니다.");
		
			es_g.Send(jdbc.select_usernum(ID)
					, ID
					, "복구 코드는 : " + code + " 입니다." 
					, jdbc.select_userEmail(jdbc.select_usernum(ID)));
		
			return mav;
		}
		
		mav2.addObject("fail_message", "해당 ID의 Name이 맞지 않습니다.");
		return mav2;
	}
	
	@RequestMapping(value ="Forgot_input_code.do")
	public ModelAndView Forgot_Input_Code(HttpSession session , HttpServletRequest req){
		
		ModelAndView mav = new ModelAndView("JSP/Success_Forgot.jsp");
		ModelAndView mav2 = new ModelAndView("JSP/ForgotAccount.jsp");
		String Input_Code = req.getParameter("CODE");
		
		if(Input_Code.equals(code)) {
			wcm.stopScheduler();
			jdbc2.delete_user(session.getAttribute("User_Num"));
			return mav;
		}
		else {
			mav2.addObject("fail_message", "코드가 틀렸습니다.");
			wcm.stopScheduler();
			return mav2;
		}
		
	}
	
	@RequestMapping(value ="Forgot_changePW.do")
	public ModelAndView Forgot_changePW(HttpServletRequest req){
		
		ModelAndView mav = new ModelAndView("JSP/Success_Forgot_Change.jsp");
		
		String change_PW = req.getParameter("PW");
		
		jdbc2.update_MyPW(jdbc.select_usernum(ID), change_PW);
		
		return mav;
	}
	
	
}
