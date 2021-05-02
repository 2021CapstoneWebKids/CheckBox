package JDBC;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class Repository_ToDo {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private int Task_Number_Count = 1;
	
	public void Make_Todo(
			String WorkPlace_Number ,
			String Assign_User_Number ,
			String Assign_Time ,
			String Task_Type ,
			String FixOrFlow ,
			String Fix_Day ,
			String Limit ,
			String Alarm ,
			String Context){
		
		String Task_Number = WorkPlace_Number + Assign_User_Number
							+ Task_Number_Count;
		
		
		
		String sql = "INSERT INTO todo_main values('"
				+ Task_Number + "' , '"
				+ WorkPlace_Number + "' , '"
				+ Assign_User_Number + "')";
		
		jdbcTemplate.execute(sql);
		
		String sql2 = "INSERT INTO todo_daily values('"
				+ Task_Number + "' , '"
				+ WorkPlace_Number + "' , '"
				+ Assign_User_Number + "' , '"
				+ Assign_Time + "' , '"
				+ FixOrFlow + "' , '"
				+ Fix_Day + "' , '"
				+ Limit + "' , '"
				+ Task_Type + "' , '"
				+ Alarm + "' , '"
				+ Context + "')";
		
		jdbcTemplate.execute(sql2);
				
				
				
		Task_Number_Count++;		
		
	}
}
