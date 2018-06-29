package org.openpaas.paasta.portal.common.api.domain.configInfo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.openpaas.paasta.portal.common.api.config.Constants;
import org.openpaas.paasta.portal.common.api.config.TestConfig;
import org.openpaas.paasta.portal.common.api.entity.portal.ConfigInfo;
import org.openpaas.paasta.portal.common.api.repository.portal.ConfigInfoRepository;
import org.slf4j.Logger;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;

/**
 * Created by indra on 2018-06-29.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ConfigInfoServiceTest extends TestConfig {
//    @Mock
//    Logger logger;
//    @Mock
//    ConfigInfoRepository configInfoRepository;
    @MockBean
    ConfigInfoService configInfoService;

    HashMap getValueResultMap;

    ConfigInfo configInfoParam;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        //testGetValue
        getValueResultMap = new HashMap();
        getValueResultMap.put("RESULT", Constants.RESULT_STATUS_SUCCESS);

        //testUpdateValue
        configInfoParam = new ConfigInfo();
        configInfoParam.setName("email_auth_yn");
        configInfoParam.setValue("Y");
        configInfoParam.setCreatedAt(null);
        configInfoParam.setUpdatedAt(null);
    }

    @Test
    public void testGetValue() throws Exception {
        when(configInfoService.getValue("name")).thenReturn(getValueResultMap);

        HashMap<String, Object> result = configInfoService.getValue("name");
        Assert.assertEquals(getValueResultMap, result);
    }

    @Test
    public void testUpdateValue() throws Exception {
        when(configInfoService.updateValue("name", configInfoParam)).thenReturn(Constants.RESULT_STATUS_SUCCESS);

        String result = configInfoService.updateValue("name", configInfoParam);
        Assert.assertEquals(Constants.RESULT_STATUS_SUCCESS, result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme