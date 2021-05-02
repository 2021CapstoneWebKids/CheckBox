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
	
	public String select_MyWorkPlace_Number(Object object) {
		
		String sql = "Select User_WorkPlace_Number from user_workplace where "
				+ "User_Number = '" + object + "'";
		
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
	
	public void delete_user(Object object) {
	
		String sql = "Delete t1 ,t2 ,t3 ,t4 ,t5 ,t6 "
				+ " From users_identi AS t1"
				+ " INNER JOIN users_name as t2 on t1.User_Number = t2.User_Number"
				+ " INNER JOIN users_permission as t3 on t1.User_Number = t3.User_Number"
				+ " INNER JOIN user_email as t4 on t1.User_Number = t4.User_Number"
				+ " INNER JOIN user_workplace as t5 on t1.User_Number = t5.User_Number"
				+ " INNER JOIN users_isonline as t6 on t1.User_Number = t6.User_Number"
				+ " where t1.User_Number = '" + object
				+ "'";
		
		jdbcTemplate.execute(sql);
		
		
		
	}
}
