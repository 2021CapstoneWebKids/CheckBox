package JDBC;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class Repository_Register {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public boolean is_Exist_ID(String id) {
		
		String sql = "Select EXISTS (select * from users_identi where User_ID = '"
				+ id + "') as success";
		
		boolean rs = jdbcTemplate.queryForObject(sql, boolean.class);
		
		return rs;
	}
	
	public boolean is_Exist_WorkPlace_Number(String WorkPlace_Number) {
		
		String sql = "Select EXISTS (select * from workplace_list where WorkPlace_Number = '"
				+ WorkPlace_Number + "') as success";
		
		boolean rs = jdbcTemplate.queryForObject(sql, boolean.class);
		
		return rs;
	}
	
	public String Select_Manager_Email(String WorkPlace_Number) {
		
		String sql = "Select Manager_Email from workplace_list where WorkPlace_Number = '"
				+ WorkPlace_Number +"'";
		
		String rs = jdbcTemplate.queryForObject(sql, String.class);
		
		return rs;
	}
	
	public String Select_Manger_Email_SMTP(String WorkPlace_Number) {
		
		String sql = "Select SMTP from workplace_list where WorkPlace_Number = '"
				+ WorkPlace_Number + "'";
		
		String rs = jdbcTemplate.queryForObject(sql, String.class);
		
		return rs;
	}
}
