package lab.aikibo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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
@EnableJpaRepositories(entityManagerFactoryRef = "sismiopEntityManagerFactory",
        transactionManagerRef = "sismiopTransactionManager")
public class SismiopConfig {

    @Resource
    private Environment env;

    @Bean
    @Primary
    PlatformTransactionManager sismiopTransactionManager() {
        return new JpaTransactionManager(sismiopEntityManagerFactory().getObject());
    }

    @Bean
    @Primary
    LocalContainerEntityManagerFactoryBean sismiopEntityManagerFactory() {
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(sismiopDS());
        factoryBean.setJpaVendorAdapter(jpaVendorAdapter);
        factoryBean.setPackagesToScan("lab.aikibo.repo");
        factoryBean.setPersistenceUnitName("sismiop");
        return factoryBean;
    }

    @Bean(name = "sismiopDS")
    @Primary
    public DataSource sismiopDS() {
        DriverManagerDataSource ds = new DriverManagerDataSource();

        ds.setDriverClassName(env.getProperty("db.driverClassName"));
        ds.setUrl(env.getProperty("db.sismiop.url"));
        ds.setUsername(env.getProperty("db.sismiop.username"));
        ds.setPassword(env.getProperty("db.sismiop.password"));

        return ds;
    }

}
