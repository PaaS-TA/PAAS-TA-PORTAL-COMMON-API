package org.openpaas.paasta.portal.common.api.domain.webIdeUser;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.openpaas.paasta.portal.common.api.config.dataSource.PortalConfig;
import org.openpaas.paasta.portal.common.api.entity.portal.WebIdeUser;
import org.openpaas.paasta.portal.common.api.repository.portal.WebIdeUserRepository;
import org.slf4j.Logger;

import java.util.*;

import static org.mockito.Mockito.*;

public class WebIdeUserServiceTest {
    @Mock
    WebIdeUserService webIdeUserService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    Map thenReturnMap;
    List<Map> thenReturnMapList;
    WebIdeUser webIdeUser;
    List<WebIdeUser> webIdeUserList;
    private void setTestData() {
        thenReturnMap = new HashMap();
        thenReturnMap.put("result", true);
        thenReturnMap.put("msg", "You have successfully completed the task.");

        thenReturnMapList = Arrays.<Map<String, Object>>asList(thenReturnMap);

        webIdeUser = new WebIdeUser();
        webIdeUser.setOrgName("orgName");
        webIdeUser.setUrl("url");
        webIdeUser.setUseYn("Y");
        webIdeUser.setUserId("userId");
        webIdeUser.setUpdatedAt(new Date());
        webIdeUser.setCreatedAt(new Date());


        webIdeUserList = Arrays.<WebIdeUser>asList(webIdeUser);
    }



    @Test
    public void testGetUser() throws Exception {
        when(webIdeUserService.getUser(any())).thenReturn(webIdeUser);

        WebIdeUser result = webIdeUserService.getUser(new WebIdeUser());
        Assert.assertEquals(webIdeUser, result);
    }

    @Test
    public void testGetList() throws Exception {
        when(webIdeUserService.getList(any())).thenReturn(null);

        HashMap<String, Object> result = webIdeUserService.getList(new WebIdeUser());
        Assert.assertEquals(null, result);
    }

    @Test
    public void testInsertUser() throws Exception {
        when(webIdeUserService.insertUser(any())).thenReturn("msg");

        String result = webIdeUserService.insertUser(new WebIdeUser());
        Assert.assertEquals("msg", result);
    }

    @Test
    public void testUpdateUser() throws Exception {
        when(webIdeUserService.updateUser(any())).thenReturn("msg");

        String result = webIdeUserService.updateUser(new WebIdeUser());
        Assert.assertEquals("msg", result);
    }

    @Test
    public void testDeleteUser() throws Exception {
        when(webIdeUserService.deleteUser(any())).thenReturn("msg");

        String result = webIdeUserService.deleteUser(new WebIdeUser());
        Assert.assertEquals("msg", result);
    }
}

