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


@ActiveProfiles("def")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@TestPropertySource(properties = {"eureka.client.enabled=false"}) //Local용
@TestPropertySource(value = {"/bootstrap.yml"}, properties = {"eureka.client.enabled=false"}) // Push 용
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestConfig.class);

    public static final String AUTHORIZATION_HEADER_KEY = "cf-Authorization";

    public final static String TOKEN = "eyJhbGciOiJSUzI1NiIsImtpZCI6ImtleS0xIiwidHlwIjoiSldUIn0.eyJqdGkiOiIxZTFkOWJjNTBlN2U0ZDZlODE4MjU0MDZmODEwN2U0OCIsInN1YiI6ImUwNjFkOTk3LTZmOWQtNGE0My05ZmI2LTg4ZDU3OTlmZjllMCIsInNjb3BlIjpbImNsb3VkX2NvbnRyb2xsZXIucmVhZCIsImNsb3VkX2NvbnRyb2xsZXIud3JpdGUiLCJjbG91ZF9jb250cm9sbGVyX3NlcnZpY2VfcGVybWlzc2lvbnMucmVhZCIsIm9wZW5pZCJdLCJjbGllbnRfaWQiOiJsb2NhbHBvcnRhbGNsaWVudCIsImNpZCI6ImxvY2FscG9ydGFsY2xpZW50IiwiYXpwIjoibG9jYWxwb3J0YWxjbGllbnQiLCJncmFudF90eXBlIjoiYXV0aG9yaXphdGlvbl9jb2RlIiwidXNlcl9pZCI6ImUwNjFkOTk3LTZmOWQtNGE0My05ZmI2LTg4ZDU3OTlmZjllMCIsIm9yaWdpbiI6InVhYSIsInVzZXJfbmFtZSI6InN3bW9vbiIsImVtYWlsIjoic3dtb29uIiwiYXV0aF90aW1lIjoxNTI3NTYxNjM3LCJyZXZfc2lnIjoiNTJlZDFjMjAiLCJpYXQiOjE1Mjc1NjE2NDUsImV4cCI6MTUyNzYwNDg0NSwiaXNzIjoiaHR0cHM6Ly91YWEuMTE1LjY4LjQ2LjE4Ny54aXAuaW8vb2F1dGgvdG9rZW4iLCJ6aWQiOiJ1YWEiLCJhdWQiOlsiY2xvdWRfY29udHJvbGxlciIsImxvY2FscG9ydGFsY2xpZW50Iiwib3BlbmlkIiwiY2xvdWRfY29udHJvbGxlcl9zZXJ2aWNlX3Blcm1pc3Npb25zIl19.j02QrO7X9G4zwo7RbUn_VCxNnQo9AfjexU67ZUo22wlQcXE_1HdqCosm9AgTfbgN3FZfbkn7EbC529Um9ZIgwfnEu2MR_xTO7vRS22m--W8o6gWluVvge6wuhu-4LlPaUYXgovTwEYrqBCo1nsDBD7nbXfft4I1EBw_-FpPbg1bBLZa09qv3i6An0xrjXsw1dCSR138xctx6m0dptoo_BL0MLQ7Nsj5KW2nx8Q9AFAEf-ZPGgPyxU3R9L_hEFsF5SUEHF6jtSmNy_WKvNvuX5zldXfxY8w77nJgvFxJXh-p7y15CszA-Z4n65rUxTS1jhkaURKM9pJYT76j4eRh82RrK6j9cxj5nqjPiIe1ArPRiTWkUv6GB80vD3nxeKSdVeynkP6_ZtJZgChxzeoEgIpoDwBUii09sDRilpAzS3H3-L--aOBL16QwEk64sLlmosMeNyl6XfdyC3RbVykJKIyd0RnHoYmx36YSsxCUctsQTYmVFXWdL9SeMZoxs0Zu91-V-CUVL5XGGO3JOi5NTnSWbY4PWR59X0AJI05qcNf65DOmn7eoCUevRcgQ3u6_p1Ae-mGppf7FxDO9QCBavDj5nCGCrptEn1Ek77DWurN7o8VEZnsmVWlRQTRE96Owc1DyZIezOrHCZu12Gw9dB5oPoCPpJ8IV-o0v7VFqKSLs";
    public final static String GUID = "42a50bfe-f186-48dc-8e5c-ad2a73bc4a8a";
    public final static String APPNAME = "test-app";


//    @Value("${datasource.cc.driver-class-name}")
//    String ccDriverClassName;
//    @Value("${datasource.cc.url}")
//    String ccUrl;
//    @Value("${datasource.cc.username}")
//    String ccUsername;
//    @Value("${datasource.cc.password}")
//    String ccPassword;
//    @Value("${jpa.hibernate.ddl-auto}")
//    String ccddlAuto;
//    @Value("${jpa.hibernate.naming.strategy}")
//    String ccdialect;
//
//    @Value("${datasource.portal.driver-class-name}")
//    String portalDriverClassName;
//    @Value("${datasource.portal.url}")
//    String portalUrl;
//    @Value("${datasource.portal.username}")
//    String portalUsername;
//    @Value("${datasource.portal.password}")
//    String portalPassword;
//    @Value("${jpa.hibernate.ddl-auto}")
//    String portalddlAuto;
//    @Value("${jpa.hibernate.naming.strategy}")
//    String portaldialect;
//
//    @Value("${datasource.uaa.driver-class-name}")
//    String uaaDriverClassName;
//    @Value("${datasource.uaa.url}")
//    String uaaUrl;
//    @Value("${datasource.uaa.username}")
//    String uaaUsername;
//    @Value("${datasource.uaa.password}")
//    String uaaPassword;
//    @Value("${jpa.hibernate.ddl-auto}")
//    String uaaddlAuto;
//    @Value("${jpa.hibernate.naming.strategy}")
//    String uaadialect;


//    @Autowired
//    PortalConfig portalConfig;
//
//    @Autowired
//    UaaConfig uaaConfig;
//
//    @Autowired
//    CcConfig ccConfig;

//    @Before
//    public void setUp() throws Exception {
//        reflectionTestUtils();
//    }
//
//
//    public void reflectionTestUtils() throws Exception {
//        ReflectionTestUtils.setField(ccConfig, "ccDriverClassName", ccDriverClassName);
//        ReflectionTestUtils.setField(ccConfig, "ccUrl", ccUrl);
//        ReflectionTestUtils.setField(ccConfig, "ccUsername", ccUsername);
//        ReflectionTestUtils.setField(ccConfig, "ccPassword", ccPassword);
//        ReflectionTestUtils.setField(ccConfig, "ddlAuto", ccddlAuto);
//        ReflectionTestUtils.setField(ccConfig, "dialect", ccdialect);
//
//        ReflectionTestUtils.setField(uaaConfig, "uaaDriverClassName", uaaDriverClassName);
//        ReflectionTestUtils.setField(uaaConfig, "uaaUrl", uaaUrl);
//        ReflectionTestUtils.setField(uaaConfig, "uaaUsername", uaaUsername);
//        ReflectionTestUtils.setField(uaaConfig, "uaaPassword", uaaPassword);
//        ReflectionTestUtils.setField(uaaConfig, "ddlAuto", uaaddlAuto);
//        ReflectionTestUtils.setField(uaaConfig, "dialect", uaadialect);
//
//        ReflectionTestUtils.setField(portalConfig, "portalDriverClassName", portalDriverClassName);
//        ReflectionTestUtils.setField(portalConfig, "portalUrl", portalUrl);
//        ReflectionTestUtils.setField(portalConfig, "portalUsername", portalUsername);
//        ReflectionTestUtils.setField(portalConfig, "portalPassword", portalPassword);
//        ReflectionTestUtils.setField(portalConfig, "ddlAuto", portalddlAuto);
//        ReflectionTestUtils.setField(portalConfig, "dialect", portaldialect);
//
//
//    }


}


