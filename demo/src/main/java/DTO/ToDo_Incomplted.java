package DTO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ToDo_Incomplted {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<String> Load_Todos_Task_Numbers() {
		
		String sql = "SELECT Task_Number from todo_daily";
		List<String> rs = jdbcTemplate.queryForList(sql, String.class);
		return rs;
		
	}
	
	public List<String> Load_Todos_Contexts() {
		
		String sql = "SELECT Context from todo_daily";
		List<String> rs = jdbcTemplate.queryForList(sql, String.class);
		return rs;
		
	}
}
