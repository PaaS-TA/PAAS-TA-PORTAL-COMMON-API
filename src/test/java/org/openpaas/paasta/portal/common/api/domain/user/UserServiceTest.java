package org.openpaas.paasta.portal.common.api.domain.user;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.openpaas.paasta.portal.common.api.config.dataSource.PortalConfig;
import org.openpaas.paasta.portal.common.api.config.dataSource.UaaConfig;
import org.openpaas.paasta.portal.common.api.domain.common.CommonService;
import org.openpaas.paasta.portal.common.api.domain.email.EmailService;
import org.openpaas.paasta.portal.common.api.domain.email.EmailServiceV3;
import org.openpaas.paasta.portal.common.api.entity.portal.UserDetail;
import org.openpaas.paasta.portal.common.api.entity.uaa.Users;
import org.openpaas.paasta.portal.common.api.repository.portal.UserDetailRepository;
import org.openpaas.paasta.portal.common.api.repository.uaa.UsersRepository;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.persistence.EntityManager;
import javax.xml.crypto.Data;
import java.util.*;

import static org.mockito.Mockito.*;


@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Mock
    UaaConfig uaaConfig;

    @Mock
    CommonService commonService;

    @Mock
    UsersRepository usersRepository;

    @Mock
    EmailService emailService;

    @Mock
    EmailServiceV3 emailServiceV3;

    @Mock
    UserDetailRepository userDetailRepository;

    @InjectMocks
    UserService userService;

    UserDetail userDetail;
    Users users;
    Map thenReturnMap;
    List<Map> thenReturnMapList;
    private MockMvc mockMvc;

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


        users = new Users();
        users.setId("");
        users.setIdentityZoneId("");
        users.setVersion(1);
        users.setGivenName("");
        users.setFamilyName("");
        users.setExternalId("");
        users.setEmail("");
        users.setCreated(new Date());
        users.setAuthorities("");
        users.setActive("Y");

        thenReturnMapList = Arrays.<Map<String, Object>>asList(thenReturnMap);


    }

    @Test
    public void testGetUser() throws Exception {
        when(userDetailRepository.findByUserId(any())).thenReturn(userDetail);
        UserDetail result = userService.getUser("userId");
        Assert.assertEquals(userDetail, result);
    }

    @Test
    public void testUpdateUser() throws Exception {
        when(userDetailRepository.countByUserId(any())).thenReturn(1);

        int result = userService.updateUser("userId", new UserDetail());
        Assert.assertEquals(1, result);
    }

    @Test
    public void testCreateUser() throws Exception {
        int result = userService.createUser(new UserDetail());
        Assert.assertEquals(1, result);
    }

    @Test
    public void testCreateRequestUser() throws Exception {
        when(emailService.createEmail(any(), any())).thenReturn(thenReturnMap);
        thenReturnMap = userService.createRequestUser(new HashMap());
        Assert.assertEquals(thenReturnMap, thenReturnMap);
    }


    @Test
    public void testResetRequestUser() throws Exception {
        when(emailService.resetEmail(any(), any())).thenReturn(thenReturnMap);
        when(userService.getUser(anyString())).thenReturn(userDetail);

        Map result = userService.resetRequestUser(new HashMap() {{
            put("userid", "String");
        }});
        Assert.assertEquals(thenReturnMap, result);
    }


    @Test
    public void testDeleteUser() throws Exception {
        Map result = userService.deleteUser("userId");
        Assert.assertEquals(thenReturnMap, result);
    }

    @Test
    public void testDeleteUserInfra() throws Exception {

        when(commonService.procCfApiRestTemplate(any(),any(),any(),any())).thenReturn(thenReturnMap);
        when(usersRepository.findById(any())).thenReturn(new Users());
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
        thrown.expect(NullPointerException.class);

        EntityManager portalEm = uaaConfig.uaaEntityManager().getNativeEntityManagerFactory().createEntityManager();
        when(uaaConfig.uaaEntityManager().getNativeEntityManagerFactory().createEntityManager()).thenReturn(portalEm);

        List<Map<String, Object>> result = userService.getUserInfo();
        Assert.assertEquals(null, result);
    }
}

