package JDBC;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class Repository_Server {

	// 서버관련 쿼리 Repository
	@Autowired
	private JdbcTemplate jdbcTemplate;
		
	
	public void Update_Server_Clock() {
		
		SimpleDateFormat format_year = new SimpleDateFormat ("yyyy");
		SimpleDateFormat format_month = new SimpleDateFormat ("MM");
		SimpleDateFormat format_day = new SimpleDateFormat ("dd");
		
		Date time = new Date();
		
		String year = format_year.format(time);
		String month = format_month.format(time);
		String day = format_day.format(time);
		
		String sql = "UPDATE server_time SET"
				+ " Today_Year = '" + year + "' ,"
				+ " Today_Month = '" + month + "' ,"
				+ " Today_Day = '" + day + "'";
		
		jdbcTemplate.execute(sql);
				
	}
	
	public String Select_Today_Year() {
		
		String sql = "SELECT Today_Year From server_time";
		String rs = jdbcTemplate.queryForObject(sql, String.class);
	
		return rs;
	}
	
	public String Select_Today_Month() {
		
		String sql = "SELECT Today_Month From server_time";
		String rs = jdbcTemplate.queryForObject(sql, String.class);
	
		return rs;
	}
	
	public String Select_Today_Day() {
		
		String sql = "SELECT Today_Day From server_time";
		String rs = jdbcTemplate.queryForObject(sql, String.class);
	
		return rs;
	}
	
}
