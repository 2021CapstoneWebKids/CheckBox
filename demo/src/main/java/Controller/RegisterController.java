package Controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import Email.Email_Send_Gmail;
import JDBC.Repository_Register;
import Security.Bcrypt;

@Controller
public class RegisterController {
	
	@Autowired
	Bcrypt Bcry;

	@Autowired
	private Repository_Register jdbc;
	
	
	@RequestMapping(value = "register.do")
	public ModelAndView Register(HttpServletRequest req , HttpServletResponse httpServletResponse) throws IOException, AddressException, MessagingException{
		
		ModelAndView mav = new ModelAndView("JSP/Register.jsp");
		ModelAndView mav2 = new ModelAndView("JSP/Register_Wait.jsp");
		
		String ID = req.getParameter("ID");
		String PW = req.getParameter("PW");
		String NAME = req.getParameter("NAME");
		String W_NUMBER = req.getParameter("W_NUMBER");
		
		
		// ID중복검사 TC3-3
		if(jdbc.is_Exist_ID(ID)) {
			// 중복이 있을시
			mav.addObject("fail_message", "중복 ID입니다 다른 ID를 입력해주세요");
			return mav;
		}
		// ID특수기호 검사 TC3-2
		if(ID.contains("@") || ID.contains("#") || ID.contains("$")){
			// 특수기호가 포함되어있을시
			mav.addObject("fail_message", "ID에 사용불가 특수문자가 포함되어있습니다. 다른 ID를 써주세요");
			return mav;
		}
		
		if(!jdbc.is_Exist_WorkPlace_Number(W_NUMBER)) {
			// 없는 사업장 번호일시
			mav.addObject("fail_message", "없는 사업장 번호입니다. 관리자에게 문의해주세요 ");
			return mav;
		}
		
		SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
		Date time = new Date();
		String time_pr = format1.format(time);
		
		String sendto = jdbc.Select_Manager_Email(W_NUMBER);
		
		Email_Send_Gmail es_g = new Email_Send_Gmail();
		String context = "계정생성 요청 접수됨\n" +
				"요청 ID : " + ID + " \n" +
				"요청 Password : " + PW + " \n" +
				"요청자 이름 : " + NAME + " \n" +
				"요청 시각 : " + time_pr + " \n" +
				"담당자 이메일: " + sendto + " \n";
		
		switch(jdbc.Select_Manger_Email_SMTP(W_NUMBER)){
			
		case "gmail":
			es_g.Send(NAME, ID, context , sendto);
			System.out.println(jdbc.Select_Manger_Email_SMTP(W_NUMBER));
			System.out.println(context);
			return mav2;
			
		default:
			mav.addObject("fail_message", "없는 사업장 번호입니다. 관리자에게 문의해주세요 ");
			break;
		}
				
		System.out.println(context);
		
		return mav2;
		
	}
	
	
	
}
