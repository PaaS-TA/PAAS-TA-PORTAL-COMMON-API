package org.openpaas.paasta.portal.common.api.domain.configInfo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.openpaas.paasta.portal.common.api.config.Constants;
import org.openpaas.paasta.portal.common.api.entity.portal.ConfigInfo;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

import static org.mockito.Mockito.when;

/**
 * Created by indra on 2018-06-29.
 */
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ConfigInfoServiceTest {

    @Mock
    ConfigInfoService configInfoService;

    HashMap getValueResultMap;

    ConfigInfo configInfoParam;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        setTestData();
    }

    private void setTestData() {

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

