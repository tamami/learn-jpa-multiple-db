package lab.aikibo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * Created by tamami on 09/04/17.
 */
@Configuration
@EnableJpaRepositories(entityManagerFactoryRef = "dummyEntityManagerFactory",
        transactionManagerRef = "dummyTransactionManager")
public class DummyConfig {

    @Resource
    private Environment env;

    @Bean
    PlatformTransactionManager dummyTransactionManager() {
        return new JpaTransactionManager(dummyEntityManagerFactory().getObject());
    }

    @Bean
    LocalContainerEntityManagerFactoryBean dummyEntityManagerFactory() {
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dummyDS());
        factoryBean.setJpaVendorAdapter(jpaVendorAdapter);
        factoryBean.setPersistenceUnitName("dummy");
        factoryBean.setPackagesToScan("lab.aikibo.repo");
        return factoryBean;
    }

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
