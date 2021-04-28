package JDBC;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class Repository_CheckInOut {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	String CheckIn_Time;
	String CheckOut_Time;
	
	public void CheckIn(Object object , String time) {
		
		String sql = "INSERT INTO employee_checkintime values('"
				+ object + "' , '" + time + "') "
				+ "ON DUPLICATE KEY UPDATE Checkin_Time = '"
				+ time + "'";
		
		jdbcTemplate.execute(sql);
		
	}
	
	public void CheckOut(Object object , String time) {
		
		String sql = "INSERT INTO employee_checkouttime values('"
				+ object + "' , '" + time + "') "
				+ "ON DUPLICATE KEY UPDATE Checkout_Time = '"
				+ time + "'";
		
		jdbcTemplate.execute(sql);
		
	}
	
	public void Record_WorkingHour(Object User_Num , String Date , int WorkingTime) {
		
		String sql = "INSERT INTO employee_workinghours values('"
				+ User_Num + "' , '" + Date + "' , '" 
				+ WorkingTime + "')";
		
		jdbcTemplate.execute(sql);
		
	}
	
	public void Record_AttendanceDay(Object User_Num , String Month , String Day) {
		
		String sql = "INSERT INTO employee_attendance values('"
				+ User_Num + "' , '" + Month + "' , '" + Day 
				+"')";
		
		jdbcTemplate.execute(sql);
	}
	
	public List<String> Select_AttendanceDay(Object User_Num , String Month) {
		
		String sql = "Select Day from employee_attendance where User_Number = '"
				+ User_Num + "' AND Month = '" + Month + "'";
		
		List<String> rs = jdbcTemplate.queryForList(sql, String.class);
		
		return rs;
	}
	
}
