package Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import JDBC.Repository_Modify_Info;
import Security.Bcrypt;

@Controller
public class InfoController_UC1 {

	public static final ModelAndView ERROR = new ModelAndView("JSP/Error.jsp");
	//중복 로그인 등으로 인해 소멸된 세션의 유저가 
	// ~~.do 등의 데이터를 변경하려는 시도가 감지될때
	// 유도되는 웹페이지
	@Autowired
	Bcrypt Bcry;
	
	@Autowired
	private Repository_Modify_Info jdbc;
	
	@RequestMapping(value = "modify_info.do")
	public ModelAndView Modify_info(HttpSession session) {
		
		if(session.getAttribute("User_Num") == null) {
			return ERROR;
		}
		// 소멸된 세션의 유저인지 검사 
		// 첫 로그인때 무조건 Session 의 "User_Num" Attribute에 값이 등록되기 때문에 비교대상으로 적합
		// 세션이 소멸되면 "User_Num" Attribute 값은 자동으로 Null 상태가 됌.
		
		ModelAndView mav = new ModelAndView("JSP/Modify_Info.jsp");
		
		mav.addObject("MyName", jdbc.select_MyName(session.getAttribute("User_Num")));
		mav.addObject("MyID", jdbc.select_MyID(session.getAttribute("User_Num")));
		
		return mav;
	}
	
	@RequestMapping(value = "change_name.do")
	public ModelAndView change_name(HttpSession session , HttpServletRequest req , HttpServletResponse httpServletResponse) {
		
		if(session.getAttribute("User_Num") == null) {
			return ERROR;
		}
		
		ModelAndView mav = new ModelAndView("JSP/Modify_Info.jsp");
		
		
		String ch_name = req.getParameter("ch_name");
		jdbc.update_MyName((String) session.getAttribute("User_Num") , ch_name);
		mav.addObject("MyName", jdbc.select_MyName(session.getAttribute("User_Num")));
		mav.addObject("MyID", jdbc.select_MyID(session.getAttribute("User_Num")));
		mav.addObject("message", "이름변경이 완료되었습니다.");
		
		
		return mav;
	}
	
	@RequestMapping(value = "change_ID.do")
	public ModelAndView change_ID(HttpSession session , HttpServletRequest req , HttpServletResponse httpServletResponse) {
		
		if(session.getAttribute("User_Num") == null) {
			return ERROR;
		}
		
		ModelAndView mav = new ModelAndView("JSP/Modify_Info.jsp");
		
		String ch_ID = req.getParameter("ch_ID");
		jdbc.update_MyID((String) session.getAttribute("User_Num") , ch_ID);
		mav.addObject("MyName", jdbc.select_MyName(session.getAttribute("User_Num")));
		mav.addObject("MyID", jdbc.select_MyID(session.getAttribute("User_Num")));
		mav.addObject("message", "ID변경이 완료되었습니다.");
		
		return mav;
	}
	
	@RequestMapping(value = "change_PW.do")
	public ModelAndView change_PW(HttpSession session , HttpServletRequest req , HttpServletResponse httpServletResponse) {
		
		if(session.getAttribute("User_Num") == null) {
			return ERROR;
		}
		
		ModelAndView mav = new ModelAndView("JSP/Modify_Info.jsp");
		
		String ch_PW = req.getParameter("ch_PW");
		jdbc.update_MyPW((String) session.getAttribute("User_Num") , ch_PW);
		mav.addObject("MyName", jdbc.select_MyName(session.getAttribute("User_Num")));
		mav.addObject("MyID", jdbc.select_MyID(session.getAttribute("User_Num")));
		mav.addObject("message", "PW변경이 완료되었습니다.");
		
		return mav;
	}
	
}
