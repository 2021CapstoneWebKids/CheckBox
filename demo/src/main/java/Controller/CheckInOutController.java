package Controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import JDBC.Repository_CheckInOut;
import JDBC.Repository_Login;

@Controller
public class CheckInOutController {

	
	@Autowired
	private Repository_CheckInOut jdbc;
	
	@RequestMapping(value = {"CheckIn.do"})
	public ModelAndView CheckIn(HttpSession session , HttpServletRequest req ) throws IOException {
		
	}
	
	@RequestMapping(value = {"CheckOut.do"})
	public ModelAndView CheckIn(HttpSession session , HttpServletRequest req ) throws IOException {
		
	}
}
