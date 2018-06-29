package org.openpaas.paasta.portal.common.api.config;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.openpaas.paasta.portal.common.api.config.dataSource.CcConfig;
import org.openpaas.paasta.portal.common.api.config.dataSource.PortalConfig;
import org.openpaas.paasta.portal.common.api.config.dataSource.UaaConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.util.ReflectionTestUtils;


@ActiveProfiles("local")
@TestPropertySource(properties = {"eureka.client.enabled=false"}) //Local용
//@ActiveProfiles("dev")
//@TestPropertySource(properties = {"spring.config.location = classpath:/application.yml","eureka.client.enabled=false"}) // Push 용
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestConfig.class);
}


