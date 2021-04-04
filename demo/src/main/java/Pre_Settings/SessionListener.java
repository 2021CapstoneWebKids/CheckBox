package Pre_Settings;

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
	
	public static SessionListener sessionListener = null;
    
	private String User_Num = null;
	
	public static synchronized SessionListener getInstance() {
        if(sessionListener == null) {
            sessionListener = new SessionListener();
        }
        return sessionListener;
    }
	
	  /**
     * session.setAttribute 실행 되는 순간 같은 sessionId 일경우 1회만 실행
     * @param httpSessionEvent
     */
	@Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
		User_Num = (String) httpSessionEvent.getSession().getAttribute("User_Num");
		System.out.println("[SYSTEM] Session Created : " + User_Num);
    }

	/**
     * session 이 소멸되는 시점에 실행, 기본 단위는 초(1분 미만은 설정할 수 없음)
     * @param httpSessionEvent
     */
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
    	
    	User_Num = (String) httpSessionEvent.getSession().getAttribute("User_Num");
    	
    	System.out.println("[SYSTEM] Session Destroyed : " + User_Num);
    	
    	jdbc.set_User_Offline(User_Num);
    }
    

}
