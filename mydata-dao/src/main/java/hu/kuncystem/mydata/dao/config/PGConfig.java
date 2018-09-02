package hu.kuncystem.mydata.dao.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Class Comment
 *
 * @author Csaba Kun <kuncy88@gmail.com>
 * @date Sep 2, 2018
 * 
 * @version 1.0
 */
@Configuration
@ComponentScan(basePackages = { "hu.kuncystem.mydata.dao" })
@EnableTransactionManagement
public class PGConfig {
    
    private static final String DB_USER = "kuncy88";
    private static final String DB_PASSWORD = "semmi12";
    private static final String DB_URL_TEST = "localhost:5432/kuncystemTest";
    private static final String DB_URL = "localhost:5432/kuncystem";

    @Bean
    @Profile("test")
    public DataSource dataSourceTest() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName("org.postgresql.Driver");
        driverManagerDataSource.setUrl("jdbc:postgresql://" + DB_URL_TEST);
        driverManagerDataSource.setUsername(DB_USER);
        driverManagerDataSource.setPassword(DB_PASSWORD);
        
        return driverManagerDataSource;
    }
    
    @Bean
    @Profile("live")
    public DataSource dataSourceLive() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName("org.postgresql.Driver");
        driverManagerDataSource.setUrl("jdbc:postgresql://" + DB_URL);
        driverManagerDataSource.setUsername(DB_USER);
        driverManagerDataSource.setPassword(DB_PASSWORD);

        return driverManagerDataSource;
    }

    @Bean
    public JdbcOperations jdbcTemplates(DataSource ds) {
        return new JdbcTemplate(ds);
    }
}
