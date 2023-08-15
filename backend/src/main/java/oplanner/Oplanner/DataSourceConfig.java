package oplanner.Oplanner;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean
    public DataSource dataSource() {
        // Create a DataSource instance using DriverManagerDataSource
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        
        // Set database driver class name
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        
        // Set database URL, username, and password
        dataSource.setUrl("jdbc:mysql://localhost:3306/oplanner");
        dataSource.setUsername("root");
        dataSource.setPassword("Oplanner@2");
        return dataSource;
    }
}
