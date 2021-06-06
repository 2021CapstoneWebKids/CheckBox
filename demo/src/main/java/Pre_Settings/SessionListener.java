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
import Scheduler.Login_Scheduler;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
public class SessionListener implements HttpSessionListener{

	@Autowired
	private Repository_Login jdbc;
	
	@Autowired
	Login_Scheduler ls;
	
	private static final Map<String , HttpSession> sessions = new ConcurrentHashMap<>();
	
	public static SessionListener sessionListener = null;
    
	private String User_Num = null;
	
	public static synchronized SessionListener getInstance() {
        if(sessionListener == null) {
            sessionListener = new SessionListener();
        }
        return sessionListener;
    }
	
	
	 //중복로그인 지우기
	 // 중복로그인 감지시 True , 없을시 False 반환
    public synchronized static boolean getSessionidCheck(String type,String compareId){
        String result = "";
        for( String key : sessions.keySet() ){
            HttpSession value = sessions.get(key);
            if(value != null &&  value.getAttribute(type) != null && value.getAttribute(type).toString().equals(compareId) ){
                //System.out.println(value.getAttribute(type).toString());
                result =  key.toString();
            }
        }
        removeSessionForDoubleLogin(result);
        
        if(result.equals("")) {
        	return false;
        }
        else {
        	return true;
        }
    }
    
    private static void removeSessionForDoubleLogin(String userId){    	
        
    	
        if(userId != null && userId.length() > 0){
            sessions.get(userId).invalidate();
            sessions.remove(userId);    	
            System.out.println("\nremove userId : " + userId);
        }
    }
    
	
	  /**
     * session.setAttribute 실행 되는 순간 같은 sessionId 일경우 1회만 실행
     * @param httpSessionEvent
     */
	@Override
    public void sessionCreated(HttpSessionEvent se) {
		//UC1-1-REQ-1
		sessions.put(se.getSession().getId(), se.getSession());
		System.out.println("[SYSTEM] Session Created : " + se.getSession().getAttribute("User_Num"));
    }

	
	
	
	
	/**
     * session 이 소멸되는 시점에 실행, 기본 단위는 초(1분 미만은 설정할 수 없음)
     * @param httpSessionEvent
     */
    public void sessionDestroyed(HttpSessionEvent se) {
    	
    	ls.stopScheduler();
    	
    	//UC1-1-REQ-2
    	User_Num = (String) se.getSession().getAttribute("User_Num");
    	if(sessions.get(se.getSession().getId()) != null) {
    		sessions.get(se.getSession().getId()).invalidate();
    		sessions.remove(se.getSession().getId());
    		
    		System.out.println("[SYSTEM] Session Destroyed : " + se.getSession().getAttribute("User_Num"));
        	
        	jdbc.set_User_Offline(User_Num);
    	}
    	
    }
    

}
