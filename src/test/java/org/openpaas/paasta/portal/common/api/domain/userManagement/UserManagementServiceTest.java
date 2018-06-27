package org.openpaas.paasta.portal.common.api.domain.userManagement;

import org.apache.commons.collections.map.HashedMap;
import org.jinq.jpa.JinqJPAStreamProvider;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.openpaas.paasta.portal.common.api.CommonApiApplication;
import org.openpaas.paasta.portal.common.api.config.Constants;
import org.openpaas.paasta.portal.common.api.config.JinqSource;
import org.openpaas.paasta.portal.common.api.config.dataSource.PortalConfig;
import org.openpaas.paasta.portal.common.api.domain.common.CommonService;
import org.openpaas.paasta.portal.common.api.domain.user.UserService;
import org.openpaas.paasta.portal.common.api.entity.portal.UserDetail;
import org.openpaas.paasta.portal.common.api.repository.portal.UserDetailRepository;
import org.openpaas.paasta.portal.common.api.repository.uaa.UsersRepository;
import org.openpaas.portal.common.api.config.TestConfig;
import org.slf4j.Logger;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.mockito.Mockito.*;

/**
 * Created by indra on 2018-06-26.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = CommonApiApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserManagementServiceTest extends TestConfig {
    @Mock
    Logger logger;
    @Mock
    UserService userService;
    @Mock
    PortalConfig portalConfig;
    @Mock
    UserDetailRepository userDetailRepository;
    @Mock
    UsersRepository usersRepository;
    @Mock
    JinqSource jinqSource;
    @MockBean
    CommonService commonService;
    @MockBean
    UserManagementService userManagementService;

//    @Mock
    UserDetail userDetail;

    @Mock
    JinqJPAStreamProvider streams;

    List<UserDetail> userDetailList;

    Map<String, Object> getUserInfoResult;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        Date d = new Date();

        getUserInfoResult = new HashMap<String, Object>();
        userDetailList = new ArrayList<UserDetail>();
        userDetail = new UserDetail();
        userDetail.setUserId("test@test.com");
        userDetail.setStatus("1");
        userDetail.setTellPhone("02-1111-2222");
        userDetail.setZipCode("123456");
        userDetail.setAddress("서울 마포구");
        userDetail.setAddressDetail("빌딩");
        userDetail.setUserName("테스터");
        userDetail.setAdminYn("Y");
        userDetail.setRefreshToken("UDMQHWNALKF69YTCHL0");
        userDetail.setAuthAccessCnt(0);
        userDetail.setAuthAccessTime(d);
        userDetail.setImgPath("");
        userDetailList.add(userDetail);
        getUserInfoResult.put("list", userDetailList);
    }

    @Test
    public void testGetUserInfoList() throws Exception {
//        when(jinqSource.streamAllPortal(any())).thenReturn(null);
        when(userManagementService.getUserInfo(anyString())).thenReturn(new HashMap<String, Object>() {{
            put("String", null);
        }});
        Map<String, Object> result = userManagementService.getUserInfoList(new UserDetail());
        Assert.assertEquals(new HashMap<String, Object>() {{
            put("String", null);
        }}, result);
    }

    @Test
    public void testGetUserInfo() throws Exception {
        when(userManagementService.getUserInfo("test@test.com")).thenReturn(getUserInfoResult);
        Map<String, Object> result = userManagementService.getUserInfo("test@test.com");
        Assert.assertEquals(new HashMap<String, Object>() {{
            put("list", userDetailList);
        }}, result);
    }

    @Test
    public void testSetResetPassword() throws Exception {
        Map<String, Object> result = userManagementService.setResetPassword("userId");
        Assert.assertEquals(new HashMap<String, Object>() {{
            put("String", null);
        }}, result);
    }

    @Test
    public void testUpdateOperatingAuthority() throws Exception {
        when(userDetailRepository.findByUserId(any())).thenReturn(new UserDetail());

        Map<String, Object> result = userManagementService.updateOperatingAuthority("userId");
        Assert.assertEquals(new HashMap<String, Object>() {{
            put("String", null);
        }}, result);
    }

    @Test
    public void testDeleteUserAccount() throws Exception {
        when(userDetailRepository.deleteByUserId(any())).thenReturn(0);

        Map<String, Object> result = userManagementService.deleteUserAccount("betterpoul@gmail.com");
        Assert.assertEquals(new HashMap<String, Object>() {{
            put("String", null);
        }}, result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme