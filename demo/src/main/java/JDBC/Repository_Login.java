package JDBC;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class Repository_Login {

	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	public String select_hashpw(String ID) {
		
		String sql = "Select User_PW from users_identi where User_ID ='"
				+ ID + "'";
		
		String rs = jdbcTemplate.queryForObject(sql, String.class);
		
		return rs;
		
	}
	
	public String select_usernum(String ID) {
		
		String sql = "Select User_Number from users_identi where User_ID ='"
				+ ID + "'";
		
		String rs = jdbcTemplate.queryForObject(sql, String.class);
		
		return rs;
		
	}
	
	public void set_User_Online(String User_Num) {
		
		String sql = "Update users_isonline set inWorking = 1 where "
				+ "User_Number = '" + User_Num + "'";
		
		jdbcTemplate.execute(sql);
	}
	
	public void set_User_Offline(String User_Num) {
		
		String sql = "Update users_isonline set inWorking = 0 where "
				+ "User_Number = '" + User_Num + "'";
		
		jdbcTemplate.execute(sql);
	}
	
	public void Insert_Login_Track(String User_Num , String Time) {
		
		String sql = "Insert into login_track VALUES ('" + User_Num +"' , ' " +
		Time + "')";
		
		jdbcTemplate.execute(sql);
	}
}
