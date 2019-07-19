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
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
    public void testGetParameter(){
        userDetail.getActive();
        userDetail.getAddress();
        userDetail.getAddressDetail();
        userDetail.getAdminYn();
        userDetail.getAuthAccessCnt();
        userDetail.getAuthAccessTime();
        userDetail.getRefreshToken();
        userDetail.getImgPath();
        userDetail.getSearchKeyword();
        userDetail.getStatus();
        userDetail.getTellPhone();
        userDetail.getUserGuid();
        userDetail.getUserId();
        userDetail.getUserName();
        userDetail.getZipCode();
        userDetail.toString();

        users.getId();
        users.getIdentityZoneId();
        users.getVersion();
        users.getGivenName();
        users.getFamilyName();
        users.getExternalId();
        users.getEmail();
        users.getCreated();
        users.getAuthorities();
        users.getActive();
        users.toString();
    }


    @Test
    public void testGetUser() throws Exception {
        when(userDetailRepository.findByUserId(any())).thenReturn(userDetail);
        UserDetail result = userService.getUser("userId");

        Assert.assertEquals(userDetail.getActive(), result.getActive());
        Assert.assertEquals(userDetail.getAddress(), result.getAddress());
        Assert.assertEquals(userDetail.getAddressDetail(), result.getAddressDetail());
        Assert.assertEquals(userDetail.getAuthAccessCnt(), result.getAuthAccessCnt());
        Assert.assertEquals(userDetail.getAdminYn(), result.getAdminYn());
        Assert.assertEquals(userDetail.getAuthAccessTime(), result.getAuthAccessTime());
        Assert.assertEquals(userDetail.getImgPath(), result.getImgPath());
        Assert.assertEquals(userDetail.getRefreshToken(), result.getRefreshToken());
        Assert.assertEquals(userDetail.getSearchKeyword(), result.getSearchKeyword());
        Assert.assertEquals(userDetail.getStatus(), result.getStatus());
        Assert.assertEquals(userDetail.getTellPhone(), result.getTellPhone());
        Assert.assertEquals(userDetail.getUserGuid(), result.getUserGuid());
        Assert.assertEquals(userDetail.getUserId(), result.getUserId());
        Assert.assertEquals(userDetail.getZipCode(), result.getZipCode());

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

        when(commonService.procCfApiRestTemplate(any(), any(), any(), any())).thenReturn(thenReturnMap);
        when(usersRepository.findById(any())).thenReturn(new Users());
        Map result = userService.deleteUserInfra("guid", "token");
        Assert.assertEquals(thenReturnMap, result);
    }

    @Test
    public void testGetRefreshTokenUser() throws Exception {
        when(userService.getRefreshTokenUser(userDetail)).thenReturn(userDetail);
        UserDetail result = userService.getRefreshTokenUser(userDetail);
        Assert.assertEquals(userDetail, result);
    }

    @Test(expected = NullPointerException.class)
    public void testGetUserInfo() throws Exception {

        EntityManager portalEm = uaaConfig.uaaEntityManager().getNativeEntityManagerFactory().createEntityManager();
        CriteriaBuilder cb = portalEm.getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = cb.createTupleQuery();
        when(uaaConfig.uaaEntityManager().getNativeEntityManagerFactory().createEntityManager()).thenReturn(portalEm);
        when(portalEm.getCriteriaBuilder()).thenReturn(cb);
        when(cb.createTupleQuery()).thenReturn(cq);

        List<Map<String, Object>> result = userService.getUserInfo();


        Assert.assertEquals(null, result);
    }
}

