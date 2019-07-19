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
import org.openpaas.paasta.portal.common.api.domain.email.EmailServiceV3;
import org.openpaas.paasta.portal.common.api.entity.cc.UsersCc;
import org.openpaas.paasta.portal.common.api.entity.portal.UserDetail;
import org.openpaas.paasta.portal.common.api.entity.uaa.Users;
import org.openpaas.paasta.portal.common.api.repository.portal.UserDetailRepository;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigInteger;
import java.util.*;

import static org.mockito.Mockito.*;


@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceV3Test {

    @Rule
    public ExpectedException thrown = ExpectedException.none();


    @Mock
    UserDetailRepository userDetailRepository;

    @Mock
    EmailServiceV3 emailServiceV3;

    @InjectMocks
    UserServiceV3 userServiceV3;

    UserDetail userDetail;
    Users users;
    Map thenReturnMap;
    List<Map> thenReturnMapList;
    UsersCc usersCc;

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
        users.setId("Id");
        users.setIdentityZoneId("IdentityZoneId");
        users.setVersion(1);
        users.setGivenName("GivenName");
        users.setFamilyName("FamilyName");
        users.setExternalId("ExternalId");
        users.setEmail("Email");
        users.setCreated(new Date());
        users.setAuthorities("Authorities");
        users.setActive("Y");
        users.setLastLogonSuccessTime(BigInteger.ONE);
        users.setLastmodified(new Date());
        users.setLegacyVerification_behavior("Verification_behavior");
        users.setOrigin("orgin");
        users.setPasswdChange_required("PasswdChange_required");
        users.setPasswdLastmodified(new Date());
        users.setPassword("password");
        users.setPhoneNumber("phonenumber");
        users.setPreviousLogonSuccessTime(BigInteger.ONE);
        users.setSalt("salt");
        users.setVerified("verified");


        usersCc = new UsersCc();
        usersCc.setActive(true);
        usersCc.setAdmin(true);
        usersCc.setCreatedAt(new Date());
        usersCc.setId(1);
        usersCc.setGuid("guid");
        usersCc.setSpaceid("spaceid");
        usersCc.setUpdatedAt(new Date());

        thenReturnMapList = Arrays.<Map<String, Object>>asList(thenReturnMap);

    }

    public void testGetUser() {

        when(userDetailRepository.findByUserId(anyString())).thenReturn(userDetail);

        UserDetail result = userServiceV3.getUser("userId");


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
    public void testCreateUser() throws Exception {
        int result = userServiceV3.createUser(new UserDetail());
        Assert.assertEquals(1, result);
    }

    @Test
    public void testCreateRequestUser() throws Exception {
        when(emailServiceV3.createEmail(any(), any(), any())).thenReturn(thenReturnMap);
        Map result = userServiceV3.createRequestUser(new HashMap() {{
            put("username","username");
            put("userid","userid");
            put("seq","1");
        }});
        Assert.assertEquals(thenReturnMap, result);
    }

    @Test
    public void testCreateRequestUser_fail() throws Exception {
        when(emailServiceV3.createEmail(any(), any(), any())).thenThrow(Exception.class);
        Map result = userServiceV3.createRequestUser(new HashMap() {{
            put("username","username");
            put("userid","userid");
            put("seq","1");
        }});
        Map map = new HashMap();
        map.put("result", false);
        map.put("msg", null);

        Assert.assertEquals(map, result);
    }

    @Test
    public void testResetRequestUser() throws Exception {
        when(emailServiceV3.resetEmail(any(), any(), anyString())).thenReturn(thenReturnMap);
        when(userServiceV3.getUser(anyString())).thenReturn(userDetail);

        Map result = userServiceV3.resetRequestUser(new HashMap() {{
            put("userid", "String");
            put("seq", "seq");
        }});
        Assert.assertEquals(thenReturnMap, result);
    }

    @Test
    public void testResetRequestUser_fail() throws Exception {
        when(emailServiceV3.resetEmail(any(), any(), anyString())).thenThrow(Exception.class);
        when(userServiceV3.getUser(anyString())).thenReturn(userDetail);

        Map result = userServiceV3.resetRequestUser(new HashMap() {{
            put("userid", "String");
            put("seq", "seq");
        }});
        Map map = new HashMap();
        map.put("result", false);
        map.put("msg", null);

        Assert.assertEquals(map, result);
    }

}

