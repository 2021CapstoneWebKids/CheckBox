package Controller;

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
public class WithdrawalController {

	@Autowired
	Bcrypt Bcry;
	
	@Autowired
	private Repository_Login jdbc;
	
	@Autowired
	private Repository_Modify_Info jdbc2;
	
	@Autowired
	WithDrawal_Code_Make wcm;
	
	private String code;
	
	@RequestMapping(value = "withdrawal.do")
	public ModelAndView WithDrawal(){
		
		ModelAndView mav = new ModelAndView("JSP/Withdrawal.jsp");
		return mav;
	}
	
	@RequestMapping(value = "withdrawal_try.do")
	public ModelAndView WithDrawal_try(HttpSession session , HttpServletRequest req) throws AddressException, MessagingException {
		
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
			Email_Send_Gmail es_g = new Email_Send_Gmail();
			mav2.addObject("fail_message" , "이메일" + 
							jdbc.select_userEmail(session.getAttribute("User_Num"))
							+ "로 코드를 전송했습니다.");
			
			es_g.Send(jdbc.select_usernum(session.getAttribute("ID"))
					, jdbc.select_userID(session.getAttribute("User_Num")),
					"삭제 코드는 : " + code + " 입니다." 
					, jdbc.select_userEmail(session.getAttribute("User_Num")));
			
			return mav2;
			
		}
		else {
			mav.addObject("fail_message", "ID/PW가 틀렸습니다");
			return mav;
		}
	}
	
	@RequestMapping(value = "withdrawal_input_code.do")
	public ModelAndView WithDrawal(HttpSession session , HttpServletRequest req){
		
		ModelAndView mav = new ModelAndView("JSP/Success_WithDrawal.jsp");
		ModelAndView mav2 = new ModelAndView("JSP/Withdrawal.jsp");
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
	
}
