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
	private Repository_CheckInOut jdbc3;
	
	@Autowired
	CheckInOut_Scheduler cs;
	
	
	@RequestMapping(value = {"CheckIn.do"})
	public ModelAndView CheckIn(HttpSession session , HttpServletRequest req ) throws IOException {
		
		ModelAndView mav = new ModelAndView("JSP/OnWork_Employee.jsp");
		
		SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
		
		Date time = new Date();
		String time_pr = format1.format(time);
		String time_m = jdbc2.Select_Today_Month();
		String time_d = jdbc2.Select_Today_Day();
		
		jdbc.CheckIn(session.getAttribute("User_Num"), time_pr);
		jdbc.Record_AttendanceDay(session.getAttribute("User_Num"), time_m, time_d);
		
		cs.startScheduler(session.getAttribute("ID"));
		
		
		mav.addObject("On_Work" ,
				   "<div><span style=\"color:green\">"
              + "출근중입니다."
              + "</span></div>");
		mav.addObject("ID", session.getAttribute("ID"));
		mav.addObject("year", jdbc2.Select_Today_Year());
		mav.addObject("month", jdbc2.Select_Today_Month());
		mav.addObject("day", jdbc2.Select_Today_Day());
		
		
		return mav;
	}
	
	@RequestMapping(value = {"CheckOut.do"})
	public ModelAndView CheckOut(HttpSession session , HttpServletRequest req ) throws IOException {
		
		ModelAndView mav = new ModelAndView("JSP/Main_Employee.jsp");
		
		SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
		Date time = new Date();
		String time_pr = format1.format(time);
		
		jdbc.CheckOut(session.getAttribute("User_Num"), time_pr);
		jdbc.Record_WorkingHour(session.getAttribute("User_Num"),
				time_pr , cs.getWorkingTime());
		
		cs.stopScheduler();
		cs.setWorkingTime(0);
		
		
		mav.addObject("On_Work" ,
				   "<div><span style=\"color:red\">"
              + "출근중이 아닙니다"
              + "</span></div>");
		mav.addObject("ID", session.getAttribute("ID"));
		mav.addObject("year", jdbc2.Select_Today_Year());
		mav.addObject("month", jdbc2.Select_Today_Month());
		
		
		for(int i=1; i< Integer.parseInt(jdbc2.Select_Today_Day()); i++) {
			
			mav.addObject("today"+i , "style=\"background-color:gray\"");
		}
		
		for(String str : jdbc3.Select_AttendanceDay(session.getAttribute("User_Num"), jdbc2.Select_Today_Month())) {
			
			mav.addObject("today"+str , "style=\"background-color:blue\"");
			
		}
		
		mav.addObject("today"+jdbc2.Select_Today_Day() , "style=\"background-color:green\"");
		
		
		
		
		
		return mav;
	}
}
