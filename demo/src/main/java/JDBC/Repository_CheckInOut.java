package JDBC;


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
				+ "ON DUPLICATE KEY UPDATE User_Number = '"
				+ object + "'";
		
		jdbcTemplate.execute(sql);
		
	}
	
	public void CheckOut(Object object , String time) {
		
		String sql = "INSERT INTO employee_checkouttime values('"
				+ object + "' , '" + time + "') "
				+ "ON DUPLICATE KEY UPDATE User_Number = '"
				+ object + "'";
		
		jdbcTemplate.execute(sql);
		
	}
	
	public void Record_WorkingHour(Object User_Num , String Date , int WorkingTime) {
		
		String sql = "INSERT INTO employee_workinghours values('"
				+ User_Num + "' , '" + Date + "' , '" 
				+ WorkingTime + "')";
		
		jdbcTemplate.execute(sql);
		
	}
	
}
