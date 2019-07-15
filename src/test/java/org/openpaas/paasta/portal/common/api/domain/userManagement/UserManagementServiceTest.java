package org.openpaas.paasta.portal.common.api.domain.userManagement;

import org.jinq.jpa.JinqJPAStreamProvider;
import org.jinq.orm.stream.JinqStream;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.openpaas.paasta.portal.common.api.config.JinqSource;
import org.openpaas.paasta.portal.common.api.config.dataSource.PortalConfig;
import org.openpaas.paasta.portal.common.api.config.dataSource.UaaConfig;
import org.openpaas.paasta.portal.common.api.entity.portal.UserDetail;
import org.openpaas.paasta.portal.common.api.entity.uaa.Users;
import org.openpaas.paasta.portal.common.api.repository.portal.UserDetailRepository;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManager;
import java.util.*;

import static org.mockito.Mockito.*;


@RunWith(SpringJUnit4ClassRunner.class)
public class UserManagementServiceTest {

    @Mock
    UaaConfig uaaConfig;

    @Mock
    PortalConfig portalConfig;

    @Mock
    JinqSource jinqSource;

    @Mock
    UserDetailRepository userDetailRepository;

    @InjectMocks
    UserManagementService userManagementService;


    @Rule
    public ExpectedException thrown = ExpectedException.none();


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        setTestData();
    }

    private JinqJPAStreamProvider streams;

    Map thenReturnMap;
    Map<String, Object> testResult;
    List<Map> thenReturnMapList;


    UserDetail userDetail;
    List<UserDetail> userDetails;

    private void setTestData() {
        thenReturnMap = new HashMap();
        thenReturnMap.put("RESULT", "SUCCESS");
        thenReturnMapList = Arrays.<Map<String, Object>>asList(thenReturnMap);

        userDetail = new UserDetail();

        userDetail.setActive("Y");
        userDetail.setAddress("address");
        userDetail.setAddressDetail("address detail");
        userDetail.setAdminYn("N");
        userDetail.setAuthAccessCnt(0);
        userDetail.setAuthAccessTime(new Date());
        userDetail.setImgPath("img");
        userDetail.setRefreshToken("refresh token");
        userDetail.setSearchKeyword("search");
        userDetail.setTellPhone("tellphone");
        userDetail.setUserGuid("guid");
        userDetail.setStatus("status");
        userDetail.setUserName("username");
        userDetail.setZipCode("zipcode");
        userDetail.setUserId("userid");


        userDetail.toString();
        setUserDetails();
    }


    private void setUserDetails() {

        userDetails = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            UserDetail detail = new UserDetail();
            detail.setActive("Y");
            detail.setAddress("address");
            detail.setAddressDetail("address detail");
            detail.setAdminYn("N");
            detail.setAuthAccessCnt(0);
            detail.setAuthAccessTime(new Date());
            detail.setImgPath("img");
            detail.setRefreshToken("refresh token");
            detail.setSearchKeyword("search");
            detail.setTellPhone("tellphone");
            detail.setUserGuid("guid");
            detail.setStatus("status");
            detail.setUserName("username");
            detail.setZipCode("zipcode");
            detail.setUserId("userid");


            detail.getActive();
            detail.getAddress();
            detail.getAddressDetail();
            detail.getAdminYn();
            detail.getAuthAccessCnt();
            detail.getAuthAccessTime();
            detail.getImgPath();
            detail.getRefreshToken();
            detail.getSearchKeyword();
            detail.getStatus();
            detail.getTellPhone();
            detail.getUserGuid();
            detail.getUserId();
            detail.getUserName();
            detail.getZipCode();



            userDetails.add(detail);
        }

    }


    @Test
    public void testGetUserInfoList() throws Exception {
        thrown.expect(NullPointerException.class);

        streams = new JinqJPAStreamProvider(uaaConfig.uaaEntityManager().getNativeEntityManagerFactory());
        EntityManager uaaEm = uaaConfig.uaaEntityManager().getNativeEntityManagerFactory().createEntityManager();

        JinqStream<Users> userStream = jinqSource.streamAllUAA(Users.class);
        JinqStream<UserDetail> streams = jinqSource.streamAllPortal(UserDetail.class);
        when(jinqSource.streamAllUAA(Users.class)).thenReturn(userStream);
        when(jinqSource.streamAllPortal(UserDetail.class)).thenReturn(streams);

        Map<String, Object> result = userManagementService.getUserInfoList("filter", new UserDetail());
        Assert.assertEquals(thenReturnMap, result);
    }

    @Test
    public void testGetUserInfo() throws Exception {
        thrown.expect(NullPointerException.class);

        JinqStream<UserDetail> streams = jinqSource.streamAllPortal(UserDetail.class);
        when(streams.where(d -> d.getUserId().equals(anyString()))).thenReturn(streams);

        Map<String, Object> result = userManagementService.getUserInfo("userid");
        Assert.assertEquals(thenReturnMap, result);
    }

    @Test
    public void testUpdateOperatingAuthority() throws Exception {
        when(userDetailRepository.findByUserId(anyString())).thenReturn(userDetail);
        Map<String, Object> result = userManagementService.updateOperatingAuthority("userId");
        Assert.assertEquals(thenReturnMap, result);
    }

    @Test
    public void testDeleteUserAccount() throws Exception {
        when(userDetailRepository.deleteByUserId(any())).thenReturn(1);
        Map<String, Object> result = userManagementService.deleteUserAccount("userId");
        Assert.assertEquals(thenReturnMap, result);
    }

    @Test
    public void testUpdateUserActive() throws Exception {

        when(userDetailRepository.findByUserId(anyString())).thenReturn(userDetail);
        when(userDetailRepository.save(userDetail)).thenReturn(userDetail);
        Map<String, Object> result = userManagementService.UpdateUserActive("userId", new UserDetail());

        Assert.assertEquals(thenReturnMap, result);
    }
}

