package Pre_Settings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import JDBC.Repository_Login;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
public class SessionListener implements HttpSessionListener{

	@Autowired
	private Repository_Login jdbc;
	
	private static final HashMap<String , HttpSession> sessions = new HashMap<>();
	
	public static SessionListener sessionListener = null;
    
	private String User_Num = null;
	
	public static synchronized SessionListener getInstance() {
        if(sessionListener == null) {
            sessionListener = new SessionListener();
        }
        return sessionListener;
    }
	
	
	
	@SuppressWarnings("unlikely-arg-type")
	public synchronized boolean SessionCheck(HttpSession session , String User_Num) {
		
		if(sessions.get(session).equals(User_Num)) {
			
			System.out.println("Duplicated Session Checked : " + User_Num + "\n");
			sessions.get(User_Num).invalidate();
			sessions.remove(User_Num);
			
			return true;
		}
		else {
			
			return false;
		}
	}
	
	
	
	
	  /**
     * session.setAttribute 실행 되는 순간 같은 sessionId 일경우 1회만 실행
     * @param httpSessionEvent
     */
	@Override
    public void sessionCreated(HttpSessionEvent se) {
		//UC1-1-REQ-1
		User_Num = (String) se.getSession().getAttribute("User_Num");
		
		
		if(se.getSession().getAttribute("User_Num")!=null) {
			
			sessions.put(User_Num, se.getSession());
		}
		
    }

	/**
     * session 이 소멸되는 시점에 실행, 기본 단위는 초(1분 미만은 설정할 수 없음)
     * @param httpSessionEvent
     */
    public void sessionDestroyed(HttpSessionEvent se) {
    	
    	
    	//UC1-1-REQ-2
    	User_Num = (String) se.getSession().getAttribute("User_Num");
    	
    	sessions.remove(User_Num);
    	System.out.println("[SYSTEM] Session Destroyed : " + User_Num);
    	
    	jdbc.set_User_Offline(User_Num);
    	
    }
    

}
