package org.openpaas.paasta.portal.common.api.config.dataSource;

import org.springframework.beans.factory.annotation.Autowired;
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

import javax.sql.DataSource;
import java.util.HashMap;

/**
 * Created by indra on 2018-02-07.
 */
@Configuration
@EnableJpaRepositories(
        basePackages = "org.openpaas.paasta.portal.common.api.repository.portal",
        entityManagerFactoryRef = "portalEntityManager",
        transactionManagerRef = "portalTransactionManager"
)
public class PortalConfig {

    @Autowired
    private Environment env;

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean portalEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(portalDataSource());
        em.setPackagesToScan(new String[] { "org.openpaas.paasta.portal.common.api.entity.portal" });

        HibernateJpaVendorAdapter vendorAdapter= new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto","update");//create-drop
//        properties.put("hibernate.dialect","org.hibernate.dialect.PostgreSQLDialect");
        properties.put("hibernate.dialect","org.hibernate.dialect.MySQL5Dialect");
        em.setJpaPropertyMap(properties);

        return em;
    }

    @Primary
    @Bean
    public DataSource portalDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName("org.postgresql.Driver");
//        dataSource.setUrl("jdbc:postgresql://localhost:5524/portaldb");
//        dataSource.setUsername("portaladmin");
//        dataSource.setPassword("admin");
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://115.68.46.219:3306/portaldb");
        dataSource.setUsername("root");
        dataSource.setPassword("!paas_ta202");

        return dataSource;
    }

    @Primary
    @Bean
    public PlatformTransactionManager portalTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(portalEntityManager().getObject());
        return transactionManager;
    }

}
