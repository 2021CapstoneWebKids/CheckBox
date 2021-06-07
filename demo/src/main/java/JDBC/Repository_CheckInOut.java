package JDBC;


import java.util.ArrayList;
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
	
	public String Caculate_Salary(Object User_Num) {
		
		String sql = "Select WorkingHours from employee_workinghours where User_Number = '" + User_Num + "'";
		List<String> rs = jdbcTemplate.queryForList(sql, String.class);
		int result = 0;
		for(int i=0; i<rs.size(); i++) {
			
			result += Integer.parseInt(rs.get(i));
		}
		
		result = Integer.parseInt(this.Load_Wage(User_Num)) * result;
		
		return Integer.toString(result);
	}
	
	public String Load_Wage(Object User_Num) {
		
		String sql = "Select Wage from employee_labor_contract where User_Number = '" + User_Num + "'";
		String rs = jdbcTemplate.queryForObject(sql, String.class);
		return rs;
	}
	
	public List<String> Load_WorkTime(Object User_Num){
		
		String sql = "Select Date from employee_workinghours where User_Number = '" + User_Num + "'";
		List<String> rs1 = jdbcTemplate.queryForList(sql, String.class);
		
		String sql2 = "Select WorkingHours from employee_workinghours where User_Number = '" + User_Num + "'";
		List<String> rs2 = jdbcTemplate.queryForList(sql2, String.class);
		
		List<String> rs3 = new ArrayList<String>();
		
		for(int i=0; i<rs1.size(); i++) {
			
			rs3.add("날짜 : " + rs1.get(i) + " 근무시간 : " + rs2.get(i) + "분 <br>");
		}
		
		return rs3;
	}
	
	public List<String> Select_AttendanceDay(Object User_Num , String Month) {
		
		String sql = "Select Day from employee_attendance where User_Number = '"
				+ User_Num + "' AND Month = '" + Month + "'";
		
		List<String> rs = jdbcTemplate.queryForList(sql, String.class);
		
		return rs;
	}
	
}
