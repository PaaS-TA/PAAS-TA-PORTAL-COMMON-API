package org.openpaas.paasta.portal.common.api.config.dataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;

/**
 * Created by indra on 2018-02-07.
 */
@Configuration
@EnableJpaRepositories(
        basePackages = "org.openpaas.paasta.portal.common.api.repository.uaa",
        entityManagerFactoryRef = "uaaEntityManager",
        transactionManagerRef = "uaaTransactionManager"
)
public class UaaConfig {

    @Autowired
    private Environment env;

    @Bean
    public LocalContainerEntityManagerFactoryBean uaaEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(uaaDataSource());
        em.setPackagesToScan(new String[] { "org.openpaas.paasta.portal.common.api.entity.uaa" });

        HibernateJpaVendorAdapter vendorAdapter= new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto","update");//create-drop
//        properties.put("hibernate.dialect","org.hibernate.dialect.PostgreSQLDialect");
        properties.put("hibernate.dialect","org.hibernate.dialect.MySQL5Dialect");
        em.setJpaPropertyMap(properties);

        return em;
    }

    @Bean
    public DataSource uaaDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://115.68.46.219:3306/ccdb");
        dataSource.setUsername("root");
        dataSource.setPassword("!paas_ta202");
//        dataSource.setDriverClassName("org.postgresql.Driver");
//        dataSource.setUrl("jdbc:postgresql://localhost:5524/uaadb");
//        dataSource.setUsername("uaaadmin");
//        dataSource.setPassword("admin");

        return dataSource;
    }

    @Bean
    public PlatformTransactionManager uaaTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(uaaEntityManager().getObject());
        return transactionManager;
    }
}
