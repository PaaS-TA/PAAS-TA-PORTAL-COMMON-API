package org.openpaas.paasta.portal.common.api.domain.userManagement;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.openpaas.paasta.portal.common.api.entity.portal.UserDetail;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;

public class UserManagementServiceTest {


    @Mock
    UserManagementService userManagementService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        setTestData();
    }

    Map thenReturnMap;
    List<Map> thenReturnMapList;

    private void setTestData() {
        thenReturnMap = new HashMap();
        thenReturnMap.put("result", true);
        thenReturnMap.put("msg", "You have successfully completed the task.");

        thenReturnMapList = Arrays.<Map<String, Object>>asList(thenReturnMap);
    }

    @Test
    public void testGetUserInfoList() throws Exception {
        when(userManagementService.getUserInfoList(any())).thenReturn(thenReturnMap);

        Map<String, Object> result = userManagementService.getUserInfoList(new UserDetail());
        Assert.assertEquals(thenReturnMap, result);
    }

    @Test
    public void testGetUserInfo() throws Exception {
        when(userManagementService.getUserInfo(anyString())).thenReturn(thenReturnMap);

        Map<String, Object> result = userManagementService.getUserInfo("userid");
        Assert.assertEquals(thenReturnMap, result);
    }

    @Test
    public void testUpdateOperatingAuthority() throws Exception {

        when(userManagementService.updateOperatingAuthority("userId")).thenReturn(thenReturnMap);
        Map<String, Object> result = userManagementService.updateOperatingAuthority("userId");
        Assert.assertEquals(thenReturnMap, result);
    }

    @Test
    public void testDeleteUserAccount() throws Exception {
//        when(userDetailRepository.deleteByUserId(any())).thenReturn(0);
        when(userManagementService.deleteUserAccount(anyString())).thenReturn(thenReturnMap);
        Map<String, Object> result = userManagementService.deleteUserAccount("userId");
        Assert.assertEquals(thenReturnMap, result);
    }

    @Test
    public void testUpdateUserActive() throws Exception {
        when(userManagementService.UpdateUserActive(anyString(), any())).thenReturn(thenReturnMap);

        Map<String, Object> result = userManagementService.UpdateUserActive("userId", new UserDetail());
        Assert.assertEquals(thenReturnMap, result);
    }
}

