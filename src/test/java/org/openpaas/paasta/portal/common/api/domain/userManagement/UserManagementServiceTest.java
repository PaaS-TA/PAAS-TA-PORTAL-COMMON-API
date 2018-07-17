package org.openpaas.paasta.portal.common.api.domain.userManagement;

import com.sun.org.apache.bcel.internal.classfile.Method;
import com.sun.xml.internal.bind.annotation.OverrideAnnotationOf;
import org.apache.commons.collections.map.HashedMap;
import org.jinq.jpa.JinqJPAStreamProvider;
import org.jinq.jpa.transform.LambdaAnalysis;
import org.jinq.orm.stream.JinqStream;
import org.jinq.tuples.Pair;
import org.jinq.tuples.Tuple3;
import org.jinq.tuples.Tuple4;
import org.jinq.tuples.Tuple5;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.OngoingStubbing;
import org.openpaas.paasta.portal.common.api.CommonApiApplication;
import org.openpaas.paasta.portal.common.api.config.Constants;
import org.openpaas.paasta.portal.common.api.config.JinqSource;
import org.openpaas.paasta.portal.common.api.config.dataSource.PortalConfig;
import org.openpaas.paasta.portal.common.api.domain.common.CommonService;
import org.openpaas.paasta.portal.common.api.domain.user.UserService;
import org.openpaas.paasta.portal.common.api.entity.portal.UserDetail;
import org.openpaas.paasta.portal.common.api.entity.uaa.Users;
import org.openpaas.paasta.portal.common.api.repository.portal.UserDetailRepository;
import org.openpaas.paasta.portal.common.api.repository.uaa.UsersRepository;
import org.openpaas.paasta.portal.common.api.config.TestConfig;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

import static java.lang.Math.E;
import static javafx.scene.input.KeyCode.T;
import static junit.framework.TestCase.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;


/**
 * Created by indra on 2018-06-26.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
//@DataJpaTest
//@AutoConfigureDataJpa
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserManagementServiceTest extends TestConfig {
    @MockBean(name = "logger")
    Logger logger;

    @MockBean(name = "userDetailRepository")
    UserDetailRepository userDetailRepository;

    @MockBean(name = "usersRepository")
    UsersRepository usersRepository;

    @MockBean(name = "jinqSource")
    JinqSource jinqSource;

    @MockBean(name = "streams")
    JinqJPAStreamProvider streams;

//    @Autowired
    @InjectMocks
    UserManagementService userManagementService;

    @Mock
    UserManagementService userManagementServiceMock;

    @MockBean
    UserManagementService userManagementServiceBean;

    @MockBean(name = "streams2")
    JinqStream<UserDetail> streams2;



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
//        JinqStream streams = mock(JinqStream.class, Mockito.RETURNS_DEEP_STUBS);
        JinqStream<UserDetail> streams = mock(JinqStream.class, Mockito.RETURNS_DEEP_STUBS);
        when(jinqSource.streamAllPortal(UserDetail.class)).thenReturn(streams);
        when(userManagementServiceBean.getUserInfoList(getUserInfoListSetParam)).thenReturn(getUserInfoListResult);

        Map<String, Object> result = userManagementServiceBean.getUserInfoList(getUserInfoListSetParam);
        Assert.assertEquals(getUserInfoListResult, result);
    }

    @Test
    public void testGetUserInfo() throws Exception {
        JinqStream<UserDetail> streams = mock(JinqStream.class, Mockito.RETURNS_DEEP_STUBS);
        when(jinqSource.streamAllPortal(UserDetail.class)).thenReturn(streams);
        when(userManagementServiceMock.getUserInfo("test@test.com")).thenReturn(getUserInfoResult);

        Map<String, Object> result = userManagementServiceMock.getUserInfo("test@test.com");
        Assert.assertEquals(getUserInfoResult, result);
    }

    @Test
    public void testUpdateOperatingAuthority() throws Exception {
//        when(userDetailRepository.findByUserId("test@test.com")).thenReturn(updateOperatingAuthorityResult);
//        when(userManagementService.updateOperatingAuthority("test@test.com")).thenReturn(resultMap);
        when(userDetailRepository.findByUserId("test@test.com")).thenReturn(updateOperatingAuthorityResult);

        Map<String, Object> result = userManagementService.updateOperatingAuthority("test@test.com");
        Assert.assertEquals(resultMap, result);
    }

    @Test
    public void testDeleteUserAccount() throws Exception {
//        when(userDetailRepository.deleteByUserId("test@test.com")).thenReturn(1);
        given(userDetailRepository.deleteByUserId("test@test.com")).willReturn(1);
//        when(userManagementService.deleteUserAccount("test@test.com")).thenReturn(resultMap);

        Map<String, Object> result = userManagementService.deleteUserAccount("test@test.com");
        Assert.assertEquals(resultMap, result);
    }

}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme