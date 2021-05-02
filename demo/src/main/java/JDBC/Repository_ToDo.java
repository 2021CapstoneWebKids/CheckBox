package JDBC;

import java.util.List;

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
	
	public String Make_ToDo_Incompleted_List() {
		
		List<String> TNs = this.Load_Todos_Task_Numbers();
		List<String> CONs = this.Load_Todos_Contexts();
		
		String rs = "";
		for(int i=0; i<TNs.size(); i++) {
			
			rs += "<p id=\"p" + i+1 + "\" style=\"color:red\"> "
					+ CONs.get(i) + " <button onclick=\"location.href=''\"> 삭제 </button> "
					+ " </p>";
		}
		
		return rs;
	}
}
