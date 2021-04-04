package Pre_Settings;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import Security.Bcrypt;

@Configuration
public class WebConfig {
	
	@Bean // 스프링 IoC 컨테이너가 관리하는 자바 객체
	public Bcrypt encryptConfigure() {
		// Bcrypt 해시암호화 Config 설정
		return new Bcrypt();
	}
}
