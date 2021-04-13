package JDBC;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import Security.Bcrypt;

@Repository
public class Repository_Modify_Info {

	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	Bcrypt Bcry;
	
	
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
	
	public void update_MyName(String User_Num , String ch_name) {
		
		String sql = "Update users_name SET User_Name = '"
				+ ch_name + "' WHERE User_Number = '"
						+ User_Num + "'";
		
		jdbcTemplate.execute(sql);
		
	}
	
	public void update_MyID(String User_Num , String ch_ID) {
		
		String sql = "Update users_identi SET User_ID = '"
				+ ch_ID + "' WHERE User_Number = '"
						+ User_Num + "'";
		
		jdbcTemplate.execute(sql);
		
	}
	
	public void update_MyPW(String User_Num , String ch_PW) {
		
		String encry_PW = Bcry.encrypt(ch_PW);
		
		String sql = "Update users_identi SET User_PW = '"
				+ encry_PW + "' WHERE User_Number = '"
						+ User_Num + "'";
		
		jdbcTemplate.execute(sql);
		
	}
}
