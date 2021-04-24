package Controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import JDBC.Repository_CheckInOut;
import JDBC.Repository_Server;
import Scheduler.CheckInOut_Scheduler;

@Controller
public class CheckInOutController {

	
	@Autowired
	private Repository_CheckInOut jdbc;
	
	@Autowired
	private Repository_Server jdbc2;
	
	@Autowired
	CheckInOut_Scheduler cs;
	
	@RequestMapping(value = {"CheckIn.do"})
	public ModelAndView CheckIn(HttpSession session , HttpServletRequest req ) throws IOException {
		
		ModelAndView mav = new ModelAndView("JSP/OnWork_Employee.jsp");
		
		SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
		Date time = new Date();
		String time_pr = format1.format(time);
		
		jdbc.CheckIn(session.getAttribute("User_Num"), time_pr);
		cs.startScheduler(session.getAttribute("ID"));
		
		mav.addObject("On_Work" ,
				   "<span style=\"color:green\">"
              + "${ID}님은 출근중입니다."
              + "</span>");
		mav.addObject("ID", session.getAttribute("ID"));
		mav.addObject("year", jdbc2.Select_Today_Year());
		mav.addObject("month", jdbc2.Select_Today_Month());
		mav.addObject("today"+jdbc2.Select_Today_Day() , "today");
		
		
		return mav;
	}
	
	@RequestMapping(value = {"CheckOut.do"})
	public ModelAndView CheckOut(HttpSession session , HttpServletRequest req ) throws IOException {
		
		ModelAndView mav = new ModelAndView("JSP/Main_Employee.jsp");
		
		SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
		Date time = new Date();
		String time_pr = format1.format(time);
		
		jdbc.CheckOut(session.getAttribute("User_Num"), time_pr);
		cs.stopScheduler();
		
		mav.addObject("On_Work" ,
				   "<span style=\"color:red\">"
              + "${ID}님은 출근중이 아닙니다"
              + "</span>");
		mav.addObject("ID", session.getAttribute("ID"));
		mav.addObject("year", jdbc2.Select_Today_Year());
		mav.addObject("month", jdbc2.Select_Today_Month());
		mav.addObject("today" + jdbc2.Select_Today_Day() , "today");
		
		jdbc.Record_WorkingHour(session.getAttribute("User_Num"),
				time_pr , cs.getWorkingTime());
		
		return mav;
	}
}
