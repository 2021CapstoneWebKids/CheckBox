package Controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import JDBC.Repository_Login;
import Pre_Settings.SessionListener;
import Security.Bcrypt;

@Controller
public class LoginController_UC1 {

	SessionListener sl = new SessionListener();
	String ID = null;
	
	
	@Autowired
	Bcrypt Bcry;
	
	@Autowired
	private Repository_Login jdbc;
	
	@RequestMapping(value = {"/login" , "/index" , ""})
	public ModelAndView Main_Login(HttpServletRequest req , HttpServletResponse httpServletResponse) throws IOException {
		ModelAndView mav = new ModelAndView("JSP/Login.jsp");
		return mav;
	}
	
	@RequestMapping(value = "login.do")
	public ModelAndView Login(HttpSession session , HttpServletRequest req , HttpServletResponse httpServletResponse) throws IOException{
		ModelAndView mav = new ModelAndView("JSP/Login.jsp");
		ModelAndView mav2 = new ModelAndView("JSP/Main.jsp");
		
		ID = req.getParameter("ID");
		String PW = req.getParameter("PW");
		
		String hash_PW = jdbc.select_hashpw(ID);
		String User_Num = jdbc.select_usernum(ID);
		
		if(Bcry.isMatch(PW , hash_PW)){
			// UC1-REQ-1
			SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
			Date time = new Date();
			String time_pr = format1.format(time);
			
			System.out.println("\n" + "[SYSTEM] 아이디 : " + ID + "님이 " + time_pr + " 에 로그인 하였습니다.");
		
			//UC1-1-REQ-1
			if(SessionListener.getSessionidCheck("User_Num" , User_Num)) {
				mav.addObject("fail_message", "중복로그인이 감지되어 해당 모든 세션을 제거했습니다 다시 로그인해주세요");
				return mav;
			}
			else {
				session.setAttribute("User_Num" , User_Num);
				session.setMaxInactiveInterval(1*60);
		
			
				mav2.addObject("ID", ID);
				jdbc.set_User_Online(User_Num);
				jdbc.Insert_Login_Track(User_Num, time_pr);
			
			
				return mav2;
			}
		}
		else {
			// UC1-REQ-2
			mav.addObject("fail_message", "ID/PW가 틀렸습니다");
			return mav;
		}
	}
	
	@RequestMapping(value = "logout.do")
	public ModelAndView Logout(HttpServletRequest req , HttpServletResponse httpServletResponse) throws IOException{
		
		ModelAndView mav = new ModelAndView("JSP/Login.jsp");
		String User_Num = jdbc.select_usernum(ID);
		
		if(SessionListener.getSessionidCheck("User_Num" , User_Num)) {
			mav.addObject("fail_message", "중복로그인이 감지되어 세션에서 제거되었습니다.");
			HttpSession session = req.getSession();
			session.invalidate();
			return mav;
		}
		else {
			mav.addObject("fail_message", "로그아웃 처리 되었습니다");
			return mav;
		}
		
	}
	
	
	
	
	
	
	
	
}
