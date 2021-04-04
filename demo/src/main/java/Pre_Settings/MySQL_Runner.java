package Pre_Settings;

import java.sql.Connection;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class MySQL_Runner implements ApplicationRunner{

	@Autowired
    DataSource dataSource;

    @Autowired
    JdbcTemplate jdbcTemplate;
    Statement statement;
    
    
    @Override
    public void run(ApplicationArguments args) throws Exception {

        try(Connection connection = dataSource.getConnection()){
            System.out.println(connection);
            String URL = connection.getMetaData().getURL();
            System.out.println(URL);
            String User = connection.getMetaData().getUserName();
            System.out.println(User);

            statement = connection.createStatement();
            System.out.println("\n\n SQL Connection complete \n\n");
        }
        
    }
    
    public JdbcTemplate getJdbcTemplate() {
    	return this.jdbcTemplate;
    }
}
