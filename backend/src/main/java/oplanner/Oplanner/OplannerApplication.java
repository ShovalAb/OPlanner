package oplanner.Oplanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.CrossOrigin;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.core.io.ClassPathResource;

@CrossOrigin(allowedHeaders = "*", origins = "*")
@SpringBootApplication
@RestController
public class OplannerApplication {

    @Autowired
    private DataSource dataSource;

    public static void main(String[] args) {
        SpringApplication.run(OplannerApplication.class, args);
    }

    @PostConstruct
    public void initializeDatabase() {
        // Create a ResourceDatabasePopulator to populate the database schema and data
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();

        // Add the schema.sql script to create the database tables
        populator.addScript(new ClassPathResource("schema.sql"));

        // Add the data.sql script to insert records into the tables
        populator.addScript(new ClassPathResource("data.sql"));

        // Create a DataSourceInitializer to apply the database populator
        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
        dataSourceInitializer.setDataSource(dataSource);
        dataSourceInitializer.setDatabasePopulator(populator);

        // Apply the database populator to initialize the database schema and data
        dataSourceInitializer.afterPropertiesSet();
    }
}
