package Scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import JDBC.Repository_Server;

@EnableScheduling
@Component
public class Server_Time_Clock {

	@Autowired
	private Repository_Server jdbc;
	
	@Scheduled(cron = "0 0 0 * * *")
	public void autoUpdate() {
		//매일 00:00:00에 호출 , 고정 시각 스케줄러
		// cron ex
		// https://madplay.github.io/post/a-guide-to-cron-expression
		jdbc.Update_Server_Clock();
		System.out.println("서버 시간이 업데이트 되었습니다.\n");
	}
	
}
