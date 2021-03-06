package Scheduler;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.PeriodicTrigger;
import org.springframework.stereotype.Component;

@Component
public class WithDrawal_Code_Make {

	private ThreadPoolTaskScheduler scheduler;
	private String ID;
	private int count = 0;
	private String code;
	
	
	
	public void stopScheduler() {
		scheduler.shutdown();
	}
	
	public void startScheduler(String ID) {
		scheduler = new ThreadPoolTaskScheduler();
		scheduler.initialize();
		this.ID = ID;
		// 동적 스케줄러 시작 부분
		scheduler.schedule(getRunnable() , getTrigger());
		code = getRandomStr(5);
		System.out.println("코드 : " + code + "\n");
		System.out.println(new Date() + ",  " + ID + "계정이 코드를 요청함");
		
		if(count >= 6) {
			stopScheduler();
			scheduler.destroy();
			System.out.println("Scheduler Destroyed\n");
		}
		// scheduler.destroy();
	}
	
	private Runnable getRunnable() {
		return () -> {
			// 동적스케줄러 기능
			// 트리거 주기마다 실행되는 기능코드
			count++;
		};
	}
	
	private Trigger getTrigger() {
		// 동적스케줄러 작업 주기 설정 트리거
		
		return new PeriodicTrigger(10, TimeUnit.SECONDS);
	}
	
	private String getRandomStr(int size) {
		if(size > 0) {
			char[] tmp = new char[size];
			for(int i=0; i<tmp.length; i++) {
				int div = (int) Math.floor( Math.random() * 2 );
				
				if(div == 0) { // 0이면 숫자로
					tmp[i] = (char) (Math.random() * 10 + '0') ;
				}else { //1이면 알파벳
					tmp[i] = (char) (Math.random() * 26 + 'A') ;
				}
			}
			return new String(tmp);
		}
		return "ERROR : Size is required."; 
	}
	
	public String getCode() {
		return code;
	}
	
}
