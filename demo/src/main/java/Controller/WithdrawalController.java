package Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import JDBC.Repository_Login;
import Scheduler.WithDrawal_Code_Make;
import Security.Bcrypt;

@Controller
public class WithdrawalController {

	@Autowired
	Bcrypt Bcry;
	
	@Autowired
	private Repository_Login jdbc;
	
	@Autowired
	WithDrawal_Code_Make wcm;
	
	private String code;
	
	@RequestMapping(value = "withdrawal.do")
	public ModelAndView WithDrawal(){
		
		ModelAndView mav = new ModelAndView("JSP/Withdrawal.jsp");
		return mav;
	}
	
	@RequestMapping(value = "withdrawal_try.do")
	public ModelAndView WithDrawal_try(HttpSession session , HttpServletRequest req) {
		
		ModelAndView mav = new ModelAndView("JSP/Withdrawal.jsp");
		ModelAndView mav2 = new ModelAndView("JSP/Withdrawal_Waiting.jsp");
		String ID = req.getParameter("ID");
		String PW = req.getParameter("PW");
		
		String hash_PW = jdbc.select_hashpw(ID);
		
		if(!ID.equals(jdbc.select_userID(session.getAttribute("User_Num")))){
			mav.addObject("fail_message", "본인계정의 정보를 입력해주세요");
			return mav;
		}
		
		if(Bcry.isMatch(PW , hash_PW)){
			
			wcm.startScheduler(ID);
			this.code = wcm.getCode();
			return mav2;
			
		}
		else {
			mav.addObject("fail_message", "ID/PW가 틀렸습니다");
			return mav;
		}
	}
	
	@RequestMapping(value = "withdrawal_input_code.do")
	public ModelAndView WithDrawal(HttpServletRequest req) {
		
		ModelAndView mav = new ModelAndView("JSP/Success_WithDrawl.jsp");
		ModelAndView mav2 = new ModelAndView("JSP/Withdrawal.jsp");
		String Input_Code = req.getParameter("CODE");
		
		if(Input_Code.equals(code)) {
			return mav;
		}
		else {
			mav2.addObject("fail_message", "코드가 틀렸습니다.");
			return mav2;
		}
	}
	
}
