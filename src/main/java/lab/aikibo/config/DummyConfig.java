package lab.aikibo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * Created by tamami on 09/04/17.
 */
@Configuration
@EnableJpaRepositories("lab.aikibo.repo")
public class DummyConfig {

    @Resource
    private Environment env;

    @Bean(name = "dummyDS")
    public DataSource dummyDS() {
        DriverManagerDataSource ds = new DriverManagerDataSource();

        ds.setDriverClassName(env.getProperty("db.driverClassName"));
        ds.setUrl(env.getProperty("db.dummy.url"));
        ds.setUsername(env.getProperty("db.dummy.username"));
        ds.setPassword(env.getProperty("db.dummy.password"));

        return ds;
    }

}
