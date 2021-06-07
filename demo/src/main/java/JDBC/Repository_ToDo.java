package JDBC;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import Scheduler.CheckInOut_Scheduler;
import Scheduler.Login_Scheduler;

@Repository
public class Repository_ToDo {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	Login_Scheduler ls;
	
	@Autowired
	private Repository_CheckInOut jdbc_chk;
	
	SimpleDateFormat format2 = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
	Date time = new Date();
	String time_pr2 = format2.format(time);
	
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
		
		switch(FixOrFlow){
			
		case "Fix" : Limit = "X";
		
		case "Flow" : Fix_Day = "X";
		}
		
		
		
		String time_code = Integer.toString(ls.getLoginTime());
		
		String Task_Number = WorkPlace_Number + Assign_User_Number
							+ time_code;
		
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
		
		String sql3 = "INSERT INTO todo_incomplete values('"
				+ Task_Number + "' , '"
				+ Assign_Time + "')";
		
		jdbcTemplate.execute(sql3);
						
		
	}
	
	public void Delete_ToDo(String Task_Number) {
		
		String sql2 = "DELETE from todo_daily where Task_Number ='" + Task_Number + "'";
		
		jdbcTemplate.execute(sql2);
		
		String sql3 = "DELETE from todo_incomplete where Task_Number ='" + Task_Number + "'";
		
		jdbcTemplate.execute(sql3);
		
		String sql4 = "DELETE from todo_complete where Task_Number ='" + Task_Number + "'";
		
		jdbcTemplate.execute(sql4);
		
	}
	
	public void Complete_ToDo(String Task_Number) {
		
		String sql = "DELETE from todo_incomplete where Task_Number ='" + Task_Number + "'";
		
		jdbcTemplate.execute(sql);
		
		String sql2 = "INSERT INTO todo_complete values('" + Task_Number + "' , '" + time_pr2 + "')";
		
		jdbcTemplate.execute(sql2);
		
	}
	
	public List<String> Load_Todos_Incomplete_Task_Numbers() {
		
		String sql = "SELECT Task_Number from todo_incomplete";
		List<String> rs = jdbcTemplate.queryForList(sql, String.class);
		return rs;
		
	}
	
	public List<String> Load_Todos_complete_Task_Numbers() {
		
		String sql = "SELECT Task_Number from todo_complete";
		List<String> rs = jdbcTemplate.queryForList(sql, String.class);
		return rs;
		
	}
	
	public List<String> Load_Todos_Assign_Time(String Task_Number) {
		
		String sql = "SELECT Assign_Time from todo_daily where Task_Number = '" + Task_Number +"'";
		List<String> rs = jdbcTemplate.queryForList(sql, String.class);
		return rs;
		
	}
	
	public List<String> Load_Limit_Day(String Task_Number) {
		
		String sql = "SELECT Limit_Day from todo_daily where Task_Number = '" + Task_Number +"'";
		List<String> rs = jdbcTemplate.queryForList(sql, String.class);
		return rs;
		
	}
	
	public List<String> Load_Task_Implementer_Number(String Task_Number) {
		
		String sql = "SELECT Task_Implementer_Number from todo_daily where Task_Number = '" + Task_Number +"'";
		List<String> rs = jdbcTemplate.queryForList(sql, String.class);
		return rs;
		
	}
	
	public List<String> Load_Fix_Or_Flow(String Task_Number) {
		
		String sql = "SELECT Fix_Or_Flow from todo_daily where Task_Number = '" + Task_Number +"'";
		List<String> rs = jdbcTemplate.queryForList(sql, String.class);
		return rs;
		
	}
	
	public List<String> Load_Fix_Day(String Task_Number) {
		
		String sql = "SELECT Fix_Day from todo_daily where Task_Number = '" + Task_Number +"'";
		List<String> rs = jdbcTemplate.queryForList(sql, String.class);
		return rs;
		
	}
	
	public List<String> Load_Task_Type(String Task_Number) {
		
		String sql = "SELECT Task_Type from todo_daily where Task_Number = '" + Task_Number +"'";
		List<String> rs = jdbcTemplate.queryForList(sql, String.class);
		return rs;
		
	}
	
	public String Load_Todo_Context(String Task_Number) {
		
		String sql = "SELECT Context from todo_daily where Task_Number = '" + Task_Number +"'";
		String rs = jdbcTemplate.queryForObject(sql, String.class);
		return rs;
		
	}
	
	public List<String> Load_Todos_Complete_Times(){
		
		String sql = "SELECT Complete_Time from todo_complete";
		List<String> rs = jdbcTemplate.queryForList(sql , String.class);
		return rs;
	}
	
	public String Load_Employees(){
		
		String sql = "Select User_Name from users_name";
		List<String> rs1 = jdbcTemplate.queryForList(sql , String.class);
		
		String sql2 = "Select User_Number from users_name";
		List<String> rs2 = jdbcTemplate.queryForList(sql2 , String.class);
		
		String sql3 = "Select User_Permission from users_permission";
		List<String> rs3 = jdbcTemplate.queryForList(sql3 , String.class);
		
		String sql4 = "Select User_Email from user_email";
		List<String> rs4 = jdbcTemplate.queryForList(sql4 , String.class);
		
		String rsf = "";
		
		for(int i=0; i<rs1.size(); i++) {
			
			rsf += "   <details><summary>" + rs1.get(i) + " </summary>"
					+ " User_Number : " + rs2.get(i) + "<br>"
					+ " User_Permission : " + rs3.get(i) + "<br>"
					+ " User_Email : " + rs4.get(i) + "<br>"
					+ "</details>";
		}
		
		return rsf;
	}
	
	public String Load_Salarys(){
		
		String sql = "Select User_Name from users_name";
		List<String> rs1 = jdbcTemplate.queryForList(sql , String.class);
		
		String sql2 = "Select User_Permission from users_permission";
		List<String> rs2 = jdbcTemplate.queryForList(sql2 , String.class);
		
		String sql3 = "Select User_Number from users_name";
		List<String> rs3 = jdbcTemplate.queryForList(sql3 , String.class);
		
		String rsf = "";
		
		for(int i=0; i<rs3.size(); i++) {
			
			if(rs2.get(i).equals("CEO")) {
				continue;
			}
			
			rsf += "   <details><summary>" + rs1.get(i) + " </summary>"
					+ " 직책 : " + rs2.get(i) + "<br>"
					+ " 이번달 급여 : " + jdbc_chk.Caculate_Salary(rs3.get(i)) + "원 <br>"
					+ " 현재 시급 : " + jdbc_chk.Load_Wage(rs3.get(i)) + "원<br>"
					+ "</details>";
		}
		
		return rsf;
	}
	
	public String Load_Recent_CheckinTime(Object User_Num) {
		
		String sql = "Select Checkin_Time from employee_checkintime where Checkin_Time = '" + User_Num + "'";
		String rs = jdbcTemplate.queryForObject(sql, String.class);
		
		return rs;
	}
	
	
	public String Make_ToDo_Incompleted_List_CEO() {
		
		List<String> TNs = this.Load_Todos_Incomplete_Task_Numbers();
		
		String rs = "";
		for(int i=0; i<TNs.size(); i++) {
			
			/*rs += "<p id=\"p" + i+1 + "\" style=\"color:red\"> "
					+ CONs.get(i) + " <button onclick=\"location.href=''\"> 삭제 </button> "
					+ " </p>";
			*/
			
			rs += "<form method=\"post\">"
					+ "   <input type=\"text\" class=\"incom\" name=\"tns\" id=\"tns\" value=\"" + TNs.get(i) + "\" readonly/>"
					+ "   <input type=\"text\" class=\"incom\"  name=\"cons\" id=\"cons\" value=\"" + this.Load_Todo_Context(TNs.get(i)) + "\" readonly/>"
					+ "   <button name=\"submit\" type=\"submit\"  onclick=\"javascript: form.action='Delete_Todo.do';\"> 삭제 </button>"
					+ "   <details><summary>정보 </summary>" 
					+ "업무 등록 시간 : " + this.Load_Todos_Assign_Time(TNs.get(i)) + "<br>"
					+ "업무 발령자 유저번호 : " + this.Load_Task_Implementer_Number(TNs.get(i)) + "<br>"
					+ "고정/유동업무 : " + this.Load_Fix_Or_Flow(TNs.get(i)) + "<br>"
					+ "고정업무 요일 : " + this.Load_Fix_Day(TNs.get(i)) + "<br>"
					+ "유동업무 제한기간 : " + this.Load_Limit_Day(TNs.get(i)) + "<br>"
					+ "업무 항목 : " + this.Load_Task_Type(TNs.get(i)) + "<br>"
					+ "</details>"
					+ "</form>";
			
		}
		
		return rs;
	}
	
	public String Make_ToDo_Incompleted_List_EMP() {
		
		List<String> TNs = this.Load_Todos_Incomplete_Task_Numbers();
		
		String rs = "";
		for(int i=0; i<TNs.size(); i++) {
			
			/*rs += "<p id=\"p" + i+1 + "\" style=\"color:red\"> "
					+ CONs.get(i) + " <button onclick=\"location.href=''\"> 삭제 </button> "
					+ " </p>";
			*/
			
			rs += "<form method=\"post\">"
					+ "   <input type=\"text\" class=\"incom\" name=\"tns\" id=\"tns\" value=\"" + TNs.get(i) + "\" readonly/>"
					+ "   <input type=\"text\" class=\"incom\"  name=\"cons\" id=\"cons\" value=\"" + this.Load_Todo_Context(TNs.get(i)) + "\" readonly/>"
					+ "   <button name=\"submit\" type=\"submit\"  onclick=\"javascript: form.action='Complete_Todo.do';\"> 완료 </button>"
					+ "   <button name=\"submit\" type=\"submit\"  onclick=\"javascript: form.action='Info_Todo.do';\"> 정보 </button>"
					+ "</form>";
			
		}
		
		return rs;
	}
	
	public String Make_ToDo_Complete() {
		
		List<String> TNs = this.Load_Todos_complete_Task_Numbers();
		List<String> CTs = this.Load_Todos_Complete_Times();
		
		String rs = "";
		for(int i=0; i<TNs.size(); i++) {
			
			rs += "<form method=\"post\">"
					+ "   <input type=\"text\" class=\"com\" name=\"tns\" id=\"tns\" value=\"" + TNs.get(i) + "\" readonly/>"
					+ "   <input type=\"text\" class=\"com\"  name=\"cons\" id=\"cons\" value=\"" + CTs.get(i) + "\" readonly/>"
					+ "   <button name=\"submit\" type=\"submit\"  onclick=\"javascript: form.action='Info_Todo.do';\"> 정보 </button>"
					+ "</form>";
		}
		
		return rs;
	}
	

}
