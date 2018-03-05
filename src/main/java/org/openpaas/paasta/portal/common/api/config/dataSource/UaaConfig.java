package org.openpaas.paasta.portal.common.api.config.dataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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

    @Value("${spring.datasource.uaa.driver-class-name}") String uaaDriverClassName;
    @Value("${spring.datasource.uaa.url}") String uaaUrl;
    @Value("${spring.datasource.uaa.username}") String uaaUsername;
    @Value("${spring.datasource.uaa.password}") String uaaPassword;
    @Value("${spring.jpa.hibernate.ddl-auto}") String ddlAuto;
    @Value("${spring.jpa.hibernate.naming.strategy}") String dialect;


    @Bean
    public LocalContainerEntityManagerFactoryBean uaaEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(uaaDataSource());
        em.setPackagesToScan(new String[] { "org.openpaas.paasta.portal.common.api.entity.uaa" });

        HibernateJpaVendorAdapter vendorAdapter= new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto",ddlAuto);
        properties.put("hibernate.dialect",dialect);
        em.setJpaPropertyMap(properties);

        return em;
    }

    @Bean
    public DataSource uaaDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(uaaDriverClassName);
        dataSource.setUrl(uaaUrl);
        dataSource.setUsername(uaaUsername);
        dataSource.setPassword(uaaPassword);

        return dataSource;
    }

    @Bean
    public PlatformTransactionManager uaaTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(uaaEntityManager().getObject());
        return transactionManager;
    }
}
