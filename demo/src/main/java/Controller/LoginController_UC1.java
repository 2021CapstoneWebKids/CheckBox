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

import JDBC.Repository_CheckInOut;
import JDBC.Repository_Login;
import JDBC.Repository_Server;
import JDBC.Repository_ToDo;
import Pre_Settings.SessionListener;
import Scheduler.CheckInOut_Scheduler;
import Scheduler.Login_Scheduler;
import Security.Bcrypt;

@Controller
public class LoginController_UC1 {

	SessionListener sl = new SessionListener();
	String ID = null;
	String User_Num = null;
	
	
	@Autowired
	Bcrypt Bcry;
	
	@Autowired
	private Repository_Login jdbc;
	
	@Autowired
	private Repository_Server jdbc2;
	
	@Autowired
	private Repository_CheckInOut jdbc3;
	
	@Autowired
	private Repository_ToDo jdbc4;
	
	@Autowired
	Login_Scheduler ls;
	
	
	@RequestMapping(value = {"/login" , "/index" , ""})
	public ModelAndView Main_Login() throws IOException {
		ModelAndView mav = new ModelAndView("JSP/Login.jsp");
		return mav;
	}
	
	@RequestMapping(value = "Create_Account.do")
	public ModelAndView Create_Account() throws IOException {
		ModelAndView mav = new ModelAndView("JSP/Register.jsp");
		return mav;
	}
	
	@RequestMapping(value = "login.do")
	public ModelAndView Login(HttpSession session , HttpServletRequest req , HttpServletResponse httpServletResponse) throws IOException{
		ModelAndView mav = new ModelAndView("JSP/Login.jsp");
		ModelAndView mav2 = new ModelAndView("JSP/Main_Employee.jsp");
		ModelAndView mav3 = new ModelAndView("JSP/Main_CEO.jsp");
		
		ID = req.getParameter("ID");
		String PW = req.getParameter("PW");
		
		String hash_PW = jdbc.select_hashpw(ID);
		User_Num = jdbc.select_usernum(ID);
		String User_ID = jdbc.select_userID(User_Num);
		String Permission = jdbc.select_Permission(User_Num);
		
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
			// Employe 권한 계정 일때
			if(Permission.equals("Employee")){
				session.setAttribute("User_Num" , User_Num);
				session.setAttribute("ID" , ID);
				session.setAttribute("User_ID" , User_ID);
				
				session.setMaxInactiveInterval(30*60);
		
				mav2.addObject("On_Work" ,
						   "<span style=\"color:red\">"
		              + "출근중이 아닙니다"
		              + "</span>");
				
				mav2.addObject("ID", ID);
				
				mav2.addObject("todo_incompleted_EMP" , jdbc4.Make_ToDo_Incompleted_List_EMP());
				mav2.addObject("todo_completed_EMP" , jdbc4.Make_ToDo_Complete());
				mav2.addObject("Attendance_days" , jdbc3.Select_AttendanceDay(User_Num, jdbc2.Select_Today_Month()));
				
				jdbc.set_User_Online(User_Num);
				jdbc.Insert_Login_Track(User_Num, time_pr);
			
			
				return mav2;
			}
			// CEO 권한 계정 일때
			if(Permission.equals("CEO")) {
				session.setAttribute("User_Num" , User_Num);
				session.setAttribute("ID" , ID);
				
				session.setMaxInactiveInterval(30*60);
		
				ls.startScheduler(session.getAttribute("ID"));
			
				mav3.addObject("ID", ID);
				mav3.addObject("year", jdbc2.Select_Today_Year());
				mav3.addObject("month", jdbc2.Select_Today_Month());
				mav3.addObject("day", jdbc2.Select_Today_Day());
				mav3.addObject("todo_incompleted_CEO" , jdbc4.Make_ToDo_Incompleted_List_CEO());
				mav3.addObject("todo_completed_CEO" , jdbc4.Make_ToDo_Complete());
				
				jdbc.set_User_Online(User_Num);
				jdbc.Insert_Login_Track(User_Num, time_pr);
			
			
				return mav3;
			}
			// 권한 NULL 예외처리
			return mav;
		}
		else {
			// UC1-REQ-2
			mav.addObject("fail_message", "ID/PW가 틀렸습니다");
			return mav;
		}
	}
	
	@RequestMapping(value = "logout.do")
	public ModelAndView Logout(HttpSession session , HttpServletRequest req , HttpServletResponse httpServletResponse) throws IOException{
		
		ModelAndView mav = new ModelAndView("JSP/Login.jsp");
		
		SessionListener.getSessionidCheck("User_Num" , User_Num);
		mav.addObject("fail_message", "로그아웃 처리 되었습니다");
		return mav;
		
		}
		
	
	
}
