package org.openpaas.paasta.portal.common.api.config;


import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by SEJI on 2018-03-07.
 */
@Configuration
public class PrintConfig {
    private static final Logger logger = getLogger(PrintConfig.class);

    //CcConfig
    @Value("${spring.datasource.cc.driver-class-name}") String ccDriverClassName;
    @Value("${spring.datasource.cc.url}") String ccUrl;
    @Value("${spring.datasource.cc.username}") String ccUsername;
    @Value("${spring.datasource.cc.password}") String ccPassword;
    @Value("${spring.jpa.hibernate.ddl-auto}") String CcddlAuto;
    @Value("${spring.jpa.hibernate.naming.strategy}") String Ccdialect;

    //PotalConfig
    @Value("${spring.datasource.portal.driver-class-name}") String portalDriverClassName;
    @Value("${spring.datasource.portal.url}") String portalUrl;
    @Value("${spring.datasource.portal.username}") String portalUsername;
    @Value("${spring.datasource.portal.password}") String portalPassword;
    @Value("${spring.jpa.hibernate.ddl-auto}") String PotalddlAuto;
    @Value("${spring.jpa.hibernate.naming.strategy}") String Potaldialect;

    //UaaConfig
    @Value("${spring.datasource.uaa.driver-class-name}") String uaaDriverClassName;
    @Value("${spring.datasource.uaa.url}") String uaaUrl;
    @Value("${spring.datasource.uaa.username}") String uaaUsername;
    @Value("${spring.datasource.uaa.password}") String uaaPassword;
    @Value("${spring.jpa.hibernate.ddl-auto}") String UaaAuto;
    @Value("${spring.jpa.hibernate.naming.strategy}") String Uaadialect;


    @Bean
    public boolean loggerPrintConfig() {

        logger.info("[CcConfig]=======================================================================");
        logger.info(ccDriverClassName+"ccDriverClassName");
        logger.info(ccUrl+"ccUrl");
        logger.info(ccUsername+"ccUsername");
        logger.info(ccPassword+"ccPassword");
        logger.info(CcddlAuto+"CcddlAuto");
        logger.info(Ccdialect+"Ccdialect");
        logger.info("==================================================================================");

        logger.info("[PotalConfig]=====================================================================");
        logger.info(portalDriverClassName+"portalDriverClassName");
        logger.info(portalUrl+"portalUrl");
        logger.info(portalUsername+"portalUsername");
        logger.info(portalPassword+"portalPassword");
        logger.info(PotalddlAuto+"PotalddlAuto");
        logger.info(Potaldialect+"Potaldialect");
        logger.info("==================================================================================");

        logger.info("[UaaConfig]=======================================================================");
        logger.info(uaaDriverClassName+"uaaDriverClassName");
        logger.info(uaaUrl+"uaaUrl");
        logger.info(uaaUsername+"uaaUsername");
        logger.info(uaaPassword+"uaaPassword");
        logger.info(UaaAuto+"UaaAuto");
        logger.info(Uaadialect+"Uaadialect");
        logger.info("==================================================================================");

        return true;
    }

}
