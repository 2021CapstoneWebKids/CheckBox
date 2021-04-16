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
}
