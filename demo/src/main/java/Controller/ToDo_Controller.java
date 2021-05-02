package Controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import JDBC.Repository_Modify_Info;
import JDBC.Repository_ToDo;

@Controller
public class ToDo_Controller {
	
	@Autowired
	private Repository_ToDo jdbc;
	
	@Autowired
	private Repository_Modify_Info jdbc2;

	@RequestMapping(value = "Make_Todo.do")
	public RedirectView Make_ToDo(HttpSession session , HttpServletRequest req){
		
		SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
		Date time = new Date();
		
		String AUN = session.getAttribute("User_Num").toString();
		String WN = jdbc2.select_MyWorkPlace_Number(AUN);
		String AT = format1.format(time);
		String TT = req.getParameter("TT");
		String FOF = req.getParameter("FixOrFlow");
		String FD = req.getParameter("Fix_Day");
		String L = req.getParameter("Limit");
		String AL = req.getParameter("Alarm");
		String CON = req.getParameter("Context");
		
		jdbc.Make_Todo(WN, AUN, AT, TT, FOF, FD, L, AL, CON);
		System.out.println("TODO 등록 완료\n");
		
		RedirectView rv = new RedirectView();
		rv.setUrl("go_main.do");
		
		return rv;
	}
	
	
}
