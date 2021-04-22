package JDBC;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class Repository_CheckInOut {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
}
