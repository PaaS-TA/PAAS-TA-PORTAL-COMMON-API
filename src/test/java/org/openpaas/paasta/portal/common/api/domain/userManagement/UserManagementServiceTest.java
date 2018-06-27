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
import org.openpaas.paasta.portal.common.api.config.TestConfig;
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

    @Mock
    JinqJPAStreamProvider streams;

    UserDetail getUserInfoListSetParam;
    UserDetail updateOperatingAuthorityResult;
    UserDetail userDetail;

    List<UserDetail> userDetailList;

    Map<String, Object> getUserInfoListResult;
    Map<String, Object> getUserInfoResult;

    Map resultMap;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        Date d = new Date();

        //testGetUserInfoList
        getUserInfoListSetParam = new UserDetail();
        getUserInfoListSetParam.setSearchKeyword("test");

        getUserInfoListResult = new HashMap<String, Object>();
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
        userDetail = new UserDetail();
        userDetail.setUserId("test2@test.com");
        userDetail.setStatus("1");
        userDetail.setTellPhone("02-3333-4444");
        userDetail.setZipCode("123456");
        userDetail.setAddress("서울 마포구");
        userDetail.setAddressDetail("빌딩");
        userDetail.setUserName("테스터2");
        userDetail.setAdminYn("Y");
        userDetail.setRefreshToken("E5TIG6AJCLX7EFPZVRW");
        userDetail.setAuthAccessCnt(0);
        userDetail.setAuthAccessTime(d);
        userDetail.setImgPath("");
        userDetailList.add(userDetail);
        getUserInfoListResult.put("list", userDetailList);

        //testGetUserInfo
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

        //testUpdateOperatingAuthority
        resultMap = new HashedMap();
        resultMap.put("RESULT", Constants.RESULT_STATUS_SUCCESS);
        updateOperatingAuthorityResult = new UserDetail();
        updateOperatingAuthorityResult.setUserId("test@test.com");
        updateOperatingAuthorityResult.setStatus("1");
        updateOperatingAuthorityResult.setTellPhone("02-1111-2222");
        updateOperatingAuthorityResult.setZipCode("123456");
        updateOperatingAuthorityResult.setAddress("서울 마포구");
        updateOperatingAuthorityResult.setAddressDetail("빌딩");
        updateOperatingAuthorityResult.setUserName("테스터");
        updateOperatingAuthorityResult.setAdminYn("Y");
        updateOperatingAuthorityResult.setRefreshToken("UDMQHWNALKF69YTCHL0");
        updateOperatingAuthorityResult.setAuthAccessCnt(0);
        updateOperatingAuthorityResult.setAuthAccessTime(d);
        updateOperatingAuthorityResult.setImgPath("");
    }

    @Test
    public void testGetUserInfoList() throws Exception {
        when(userManagementService.getUserInfoList(getUserInfoListSetParam)).thenReturn(getUserInfoListResult);

        Map<String, Object> result = userManagementService.getUserInfoList(getUserInfoListSetParam);
        Assert.assertEquals(getUserInfoListResult, result);
    }

    @Test
    public void testGetUserInfo() throws Exception {
        when(userManagementService.getUserInfo("test@test.com")).thenReturn(getUserInfoResult);

        Map<String, Object> result = userManagementService.getUserInfo("test@test.com");
        Assert.assertEquals(getUserInfoResult, result);
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
        when(userDetailRepository.findByUserId("test@test.com")).thenReturn(updateOperatingAuthorityResult);
        when(userManagementService.updateOperatingAuthority("test@test.com")).thenReturn(resultMap);

        Map<String, Object> result = userManagementService.updateOperatingAuthority("test@test.com");
        Assert.assertEquals(resultMap, result);
    }

    @Test
    public void testDeleteUserAccount() throws Exception {
        when(userDetailRepository.deleteByUserId("test@test.com")).thenReturn(1);
        when(userManagementService.deleteUserAccount("test@test.com")).thenReturn(resultMap);

        Map<String, Object> result = userManagementService.deleteUserAccount("test@test.com");
        Assert.assertEquals(resultMap, result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme