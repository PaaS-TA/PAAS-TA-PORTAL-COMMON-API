package org.openpaas.paasta.portal.common.api.config;

import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;


/**
 * Created by indra on 2018-06-27.
 */
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestConfig {


    String adminUserName = Constants.adminUserName;
    String BUILD_PACK_CATALOG_ID = Constants.BUILD_PACK_CATALOG_ID;
    String PROC_NAME_COMMON_CODE_DETAIL = Constants.PROC_NAME_COMMON_CODE_DETAIL;
    String PROC_NAME_COMMON_CODE_GROUP = Constants.PROC_NAME_COMMON_CODE_GROUP;
    String CATALOG_EGOV_BUILD_PACK_CHECK_STRING = Constants.CATALOG_EGOV_BUILD_PACK_CHECK_STRING;
    String CATALOG_EGOV_BUILD_PACK_ENVIRONMENT_KEY = Constants.CATALOG_EGOV_BUILD_PACK_ENVIRONMENT_KEY;
    String CATALOG_EGOV_BUILD_PACK_ENVIRONMENT_VALUE = Constants.CATALOG_EGOV_BUILD_PACK_ENVIRONMENT_VALUE;
    String CATALOG_TYPE_BUILD_PACK = Constants.CATALOG_TYPE_BUILD_PACK;
    String CATALOG_TYPE_STARTER = Constants.CATALOG_TYPE_STARTER;
    String CREATE_APPLICATION_STAGING_COMMAND = Constants.CREATE_APPLICATION_STAGING_COMMAND;
    String CUD_U = Constants.CUD_U;
    String CUD_C = Constants.CUD_C;
    int CREATE_APPLICATION_MEMORY_SIZE = Constants.CREATE_APPLICATION_MEMORY_SIZE;
    int CREATE_APPLICATION_DISK_SIZE = Constants.CREATE_APPLICATION_DISK_SIZE;
    String DUPLICATION_SEPARATOR = Constants.DUPLICATION_SEPARATOR;
    String MY_QUESTION_STATUS_WAITING = Constants.MY_QUESTION_STATUS_WAITING;
    String NONE_VALUE = Constants.NONE_VALUE;
    String ORGMANAGER = Constants.ORGMANAGER;
    int PAGE_NO = Constants.PAGE_NO;
    int PAGE_SIZE = Constants.PAGE_SIZE;
    String REQUEST_DOMAIN_STATUS_SHARED = Constants.REQUEST_DOMAIN_STATUS_SHARED;
    String RESULT_STATUS_FAIL = Constants.RESULT_STATUS_FAIL;
    String RESULT_STATUS_FAIL_DUPLICATED = Constants.RESULT_STATUS_FAIL_DUPLICATED;
    String RESULT_STATUS_SUCCESS = Constants.RESULT_STATUS_SUCCESS;
    String SERVICE_PACK_CATALOG_ID = Constants.SERVICE_PACK_CATALOG_ID;
    String SPACEMANAGER = Constants.SPACEMANAGER;
    String STARTER_CATALOG_ID = Constants.STARTER_CATALOG_ID;
    String STRING_DATE_TYPE = Constants.STRING_DATE_TYPE;
    String USE_YN_N = Constants.USE_YN_N;
    String USE_YN_Y = Constants.USE_YN_Y;
    String USERS = Constants.USERS;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    
    

    @Test
    public void testGetParameter(){

        Assert.assertEquals(adminUserName,Constants.adminUserName);
        Assert.assertEquals( BUILD_PACK_CATALOG_ID , Constants.BUILD_PACK_CATALOG_ID);
        Assert.assertEquals( PROC_NAME_COMMON_CODE_DETAIL , Constants.PROC_NAME_COMMON_CODE_DETAIL);
        Assert.assertEquals( PROC_NAME_COMMON_CODE_GROUP , Constants.PROC_NAME_COMMON_CODE_GROUP);
        Assert.assertEquals( CATALOG_EGOV_BUILD_PACK_CHECK_STRING , Constants.CATALOG_EGOV_BUILD_PACK_CHECK_STRING);
        Assert.assertEquals( CATALOG_EGOV_BUILD_PACK_ENVIRONMENT_KEY , Constants.CATALOG_EGOV_BUILD_PACK_ENVIRONMENT_KEY);
        Assert.assertEquals( CATALOG_EGOV_BUILD_PACK_ENVIRONMENT_VALUE , Constants.CATALOG_EGOV_BUILD_PACK_ENVIRONMENT_VALUE);
        Assert.assertEquals( CATALOG_TYPE_BUILD_PACK , Constants.CATALOG_TYPE_BUILD_PACK);
        Assert.assertEquals( CATALOG_TYPE_STARTER , Constants.CATALOG_TYPE_STARTER);
        Assert.assertEquals( CREATE_APPLICATION_STAGING_COMMAND , Constants.CREATE_APPLICATION_STAGING_COMMAND);
        Assert.assertEquals( CUD_U , Constants.CUD_U);
        Assert.assertEquals( CUD_C , Constants.CUD_C);
        Assert.assertEquals( CREATE_APPLICATION_MEMORY_SIZE , Constants.CREATE_APPLICATION_MEMORY_SIZE);
        Assert.assertEquals( CREATE_APPLICATION_DISK_SIZE , Constants.CREATE_APPLICATION_DISK_SIZE);
        Assert.assertEquals( DUPLICATION_SEPARATOR , Constants.DUPLICATION_SEPARATOR);
        Assert.assertEquals( MY_QUESTION_STATUS_WAITING , Constants.MY_QUESTION_STATUS_WAITING);
        Assert.assertEquals( NONE_VALUE , Constants.NONE_VALUE);
        Assert.assertEquals( ORGMANAGER , Constants.ORGMANAGER);
        Assert.assertEquals( PAGE_NO , Constants.PAGE_NO);
        Assert.assertEquals( PAGE_SIZE , Constants.PAGE_SIZE);
        Assert.assertEquals( REQUEST_DOMAIN_STATUS_SHARED , Constants.REQUEST_DOMAIN_STATUS_SHARED);
        Assert.assertEquals( RESULT_STATUS_FAIL , Constants.RESULT_STATUS_FAIL);
        Assert.assertEquals( RESULT_STATUS_FAIL_DUPLICATED , Constants.RESULT_STATUS_FAIL_DUPLICATED);
        Assert.assertEquals( RESULT_STATUS_SUCCESS , Constants.RESULT_STATUS_SUCCESS);
        Assert.assertEquals( SERVICE_PACK_CATALOG_ID , Constants.SERVICE_PACK_CATALOG_ID);
        Assert.assertEquals( SPACEMANAGER , Constants.SPACEMANAGER);
        Assert.assertEquals( STARTER_CATALOG_ID , Constants.STARTER_CATALOG_ID);
        Assert.assertEquals( STRING_DATE_TYPE , Constants.STRING_DATE_TYPE);
        Assert.assertEquals( USE_YN_N , Constants.USE_YN_N);
        Assert.assertEquals( USE_YN_Y , Constants.USE_YN_Y);
        Assert.assertEquals( USERS , Constants.USERS);

    }


}
