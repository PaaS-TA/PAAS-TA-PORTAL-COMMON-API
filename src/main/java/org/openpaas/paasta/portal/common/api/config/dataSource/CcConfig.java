package org.openpaas.paasta.portal.common.api.config.dataSource;

import org.slf4j.Logger;
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

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by indra on 2018-02-07.
 */
@Configuration
@EnableJpaRepositories(
        basePackages = "org.openpaas.paasta.portal.common.api.repository.cc",
        entityManagerFactoryRef = "ccEntityManager",
        transactionManagerRef = "ccTransactionManager"
)
public class CcConfig {

    private static final Logger logger = getLogger(CcConfig.class);

    @Value("${datasource.cc.driver-class-name}") String ccDriverClassName;
    @Value("${datasource.cc.url}") String ccUrl;
    @Value("${datasource.cc.username}") String ccUsername;
    @Value("${datasource.cc.password}") String ccPassword;
    @Value("${jpa.hibernate.ddl-auto}") String ddlAuto;
    @Value("${jpa.hibernate.naming.strategy}") String dialect;


    @Bean
    public boolean loggerPrintConfig() {

        logger.info("[CcConfig]=======================================================================");
        logger.info(ccDriverClassName+"ccDriverClassName");
        logger.info(ccUrl+"ccUrl");
        logger.info(ccUsername+"ccUsername");
        logger.info(ccPassword+"ccPassword");
        logger.info(ddlAuto+"CcddlAuto");
        logger.info(dialect+"Ccdialect");
        logger.info("==================================================================================");
        return true;
    }


    @Bean
    public LocalContainerEntityManagerFactoryBean ccEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(ccDataSource());
        em.setPackagesToScan(new String[] { "org.openpaas.paasta.portal.common.api.entity.cc" });

        HibernateJpaVendorAdapter vendorAdapter= new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto",ddlAuto);
        properties.put("hibernate.dialect",dialect);
        em.setJpaPropertyMap(properties);

        return em;
    }

    @Bean
    public DataSource ccDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(ccDriverClassName);
        dataSource.setUrl(ccUrl);
        dataSource.setUsername(ccUsername);
        dataSource.setPassword(ccPassword);

        return dataSource;
    }

    @Bean
    public PlatformTransactionManager ccTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(ccEntityManager().getObject());
        return transactionManager;
    }
}
