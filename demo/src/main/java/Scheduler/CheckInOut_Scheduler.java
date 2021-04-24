package Scheduler;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.PeriodicTrigger;
import org.springframework.stereotype.Component;

@Component
public class CheckInOut_Scheduler {

	private ThreadPoolTaskScheduler scheduler;
	
	private int WorkingTime = 0;
	private String ID;
	
	public void stopScheduler() {
		
		WorkingTime = 0;
		scheduler.shutdown();
		System.out.println(new Date() + " " + ID + "님이 퇴근함"
				+ "총 근무시간 : " + WorkingTime + "분\n");
		
	}
	
	public void startScheduler(Object ID) {
		scheduler = new ThreadPoolTaskScheduler();
		scheduler.initialize();
		// scheduler.destroy();
		scheduler.schedule(getRunnable() , getTrigger());
		this.ID = (String) ID;
		System.out.println(new Date() + " " + ID + "님이 출근함\n");
	}
	
	private Runnable getRunnable() {
		return () -> {
			// 동적스케줄러 기능
			// 트리거 주기마다 실행되는 기능코드
			WorkingTime++;
			System.out.println(ID + " 님의 근무시간 : " + WorkingTime + "분 경과");
		};
	}
	
	private Trigger getTrigger() {
		// 동적스케줄러 작업 주기 설정 트리거
		
		return new PeriodicTrigger(60 , TimeUnit.SECONDS);
	}
	
	public int getWorkingTime() {
		return WorkingTime;
	}
}
