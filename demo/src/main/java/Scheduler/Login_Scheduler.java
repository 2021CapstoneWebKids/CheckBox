package Scheduler;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.PeriodicTrigger;
import org.springframework.stereotype.Component;

@Component
public class Login_Scheduler {

	private ThreadPoolTaskScheduler scheduler;
	
	private int LoginTime = 0;
	private String ID;
	
	public void stopScheduler() {
		
		scheduler.shutdown();
		scheduler.destroy();
		
	}
	
	public void startScheduler(Object ID) {
		scheduler = new ThreadPoolTaskScheduler();
		scheduler.initialize();
		// scheduler.destroy();
		scheduler.schedule(getRunnable() , getTrigger());
		this.ID = (String) ID;
		System.out.println(new Date() + " " + ID + "님이 로그인함\n");
	}
	
	private Runnable getRunnable() {
		return () -> {
			// 동적스케줄러 기능
			// 트리거 주기마다 실행되는 기능코드
			LoginTime++;
			System.out.println(ID + " 님의 접속시간 : " + LoginTime + "분 경과");
		};
	}
	
	private Trigger getTrigger() {
		// 동적스케줄러 작업 주기 설정 트리거
		
		return new PeriodicTrigger(60 , TimeUnit.SECONDS);
	}
	
	public int getLoginTime() {
		return LoginTime;
	}
	
	public void setLoginTime(int lt) {
		this.LoginTime = lt;
	}
}
