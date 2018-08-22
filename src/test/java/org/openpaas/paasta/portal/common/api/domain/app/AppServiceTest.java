package org.openpaas.paasta.portal.common.api.domain.app;

import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Created by indra on 2018-06-27.
 */
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AppServiceTest {

    @Mock
    AppService appService;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAppImageUrl() throws Exception {
        when(appService.getAppImageUrl(anyString())).thenReturn("thumbImgPath");
        String result = appService.getAppImageUrl("thumbImgPath");
        Assert.assertEquals("thumbImgPath", result);
    }
}

