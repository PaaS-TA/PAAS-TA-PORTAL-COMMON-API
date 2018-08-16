package org.openpaas.paasta.portal.common.api.domain.user;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.openpaas.paasta.portal.common.api.config.JinqSource;
import org.openpaas.paasta.portal.common.api.config.dataSource.PortalConfig;
import org.openpaas.paasta.portal.common.api.config.dataSource.UaaConfig;
import org.openpaas.paasta.portal.common.api.domain.common.CommonService;
import org.openpaas.paasta.portal.common.api.domain.email.EmailService;
import org.openpaas.paasta.portal.common.api.entity.portal.UserDetail;
import org.openpaas.paasta.portal.common.api.entity.uaa.Users;
import org.openpaas.paasta.portal.common.api.repository.portal.UserDetailRepository;
import org.openpaas.paasta.portal.common.api.repository.uaa.UsersRepository;
import org.slf4j.Logger;

import java.util.*;

import static org.mockito.Mockito.*;

public class UserServiceTest {
//    @Mock
//    Logger logger;
//    @Mock
//    PortalConfig portalConfig;
//    @Mock
//    UaaConfig uaaConfig;
//    @Mock
//    UserDetailRepository userDetailRepository;
//    @Mock
//    EmailService emailService;
//    @Mock
//    CommonService commonService;
//    @Mock
//    UsersRepository usersRepository;
//    @InjectMocks
//    UserService userService;
    @Mock
    UserService userService;

    UserDetail userDetail;
    Map thenReturnMap;
    List<Map> thenReturnMapList;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        setTestData();

    }

    private void setTestData() {

        thenReturnMap = new HashMap();
        thenReturnMap.put("result", true);
        thenReturnMap.put("msg", "You have successfully completed the task.");

        userDetail = new UserDetail();
        userDetail.setActive("Y");
        userDetail.setAddress("address");
        userDetail.setAddressDetail("address detail");
        userDetail.setAdminYn("Y");
        userDetail.setAuthAccessCnt(0);
        userDetail.setAuthAccessTime(new Date());
        userDetail.setRefreshToken("token");
        userDetail.setImgPath("imgpath");
        userDetail.setSearchKeyword("searchkeyword");
        userDetail.setStatus("Y");
        userDetail.setTellPhone("tellphone");
        userDetail.setUserGuid("guid");
        userDetail.setUserId("userid");
        userDetail.setUserName("username");
        userDetail.setZipCode("zipcode");


        thenReturnMapList = Arrays.<Map<String, Object>>asList(thenReturnMap);
    }

    @Test
    public void testGetUser() throws Exception {
//        when(userDetailRepository.findByUserId(any())).thenReturn(userDetail);
        when(userService.getUser(anyString())).thenReturn(userDetail);
        UserDetail result = userService.getUser("userId");
        Assert.assertEquals(userDetail, result);
    }

    @Test
    public void testUpdateUser() throws Exception {
//        when(userDetailRepository.countByUserId(any())).thenReturn(1);
        when(userService.updateUser(anyString(),any())).thenReturn(1);
        int result = userService.updateUser("userId", new UserDetail());
        Assert.assertEquals(1, result);
    }

    @Test
    public void testCreateUser() throws Exception {
        when(userService.createUser(any())).thenReturn(1);
        int result = userService.createUser(new UserDetail());
        Assert.assertEquals(1, result);
    }

    @Test
    public void testCreateRequestUser() throws Exception {
//        when(emailService.createEmail(any(), any())).thenReturn(thenReturnMap)/**/;
        when(userService.createRequestUser(new HashMap() {{
            put("userid", "String");
        }})).thenReturn(thenReturnMap);
        Map result = userService.createRequestUser(new HashMap() {{
            put("userid", "String");
        }});
        Assert.assertEquals(thenReturnMap, result);
    }

    @Test
    public void testResetRequestUser() throws Exception {
//        when(userDetailRepository.findByUserId(any())).thenReturn(userDetail);
//        when(emailService.resetEmail(any(), any())).thenReturn(thenReturnMap);
//        when(userDetailRepository.findByUserId(anyString())).thenReturn(userDetail);
        when(userService.getUser(anyString())).thenReturn(userDetail);
        when(userService.resetRequestUser(new HashMap() {{
            put("userid", "String");
        }})).thenReturn(thenReturnMap);

        Map result = userService.resetRequestUser(new HashMap() {{
            put("userid", "String");
        }});
        Assert.assertEquals(thenReturnMap, result);
    }

    @Test
    public void testDeleteUser() throws Exception {
//        when(userDetailRepository.deleteByUserId("userId")).thenReturn(1);
        when(userService.deleteUser("userId")).thenReturn(thenReturnMap);
        Map result = userService.deleteUser("userId");
        Assert.assertEquals(thenReturnMap, result);
    }

    @Test
    public void testDeleteUserInfra() throws Exception {
//        when(userDetailRepository.deleteByUserId(any())).thenReturn(1);
//        when(commonService.procCfApiRestTemplate(any(), any(), any(), any())).thenReturn(thenReturnMap);
//        when(usersRepository.findById(any())).thenReturn(new Users());
        when(userService.deleteUserInfra(anyString(),anyString())).thenReturn(thenReturnMap);
        Map result = userService.deleteUserInfra("guid", "token");
        Assert.assertEquals(thenReturnMap, result);
    }

    @Test
    public void testGetRefreshTokenUser() throws Exception {
//        when(userDetailRepository.findByUserIdAndRefreshToken(any(), any())).thenReturn(new UserDetail());
        when(userService.getRefreshTokenUser(userDetail)).thenReturn(userDetail);
        UserDetail result = userService.getRefreshTokenUser(userDetail);
        Assert.assertEquals(userDetail, result);
    }

    @Test
    public void testGetUserInfo() throws Exception {
//        when(uaaConfig.uaaEntityManager()).thenReturn(null);
        when(userService.getUserInfo()).thenReturn(null);
        List<Map<String, Object>> result = userService.getUserInfo();
        Assert.assertEquals(null, result);
    }
}

