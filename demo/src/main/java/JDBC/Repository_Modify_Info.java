package JDBC;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class Repository_Modify_Info {

	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	public String select_MyName(Object object) {
		
		String sql = "Select User_Name from users_name where "
				+ "User_Number = '" + object + "'";
		
		String rs = jdbcTemplate.queryForObject(sql, String.class);
		
		return rs;
		
	}
	
	public String select_MyID(Object object) {
		
		String sql = "Select User_ID from users_identi where "
				+ "User_Number = '" + object + "'";
		
		String rs = jdbcTemplate.queryForObject(sql, String.class);
		
		return rs;
		
	}

	public String select_MyPW(String User_Num) {
	
		String sql = "Select User_PW from users_identi where "
				+ "User_Number = '" + User_Num + "'";
	
		String rs = jdbcTemplate.queryForObject(sql, String.class);
	
		return rs;
	
	}
}
