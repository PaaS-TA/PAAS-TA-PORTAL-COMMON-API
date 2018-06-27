package org.openpaas.portal.common.api.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.commons.collections.map.HashedMap;
import org.jinq.orm.stream.JinqStream;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.openpaas.paasta.portal.common.api.CommonApiApplication;
import org.openpaas.paasta.portal.common.api.config.Constants;
import org.openpaas.paasta.portal.common.api.config.JinqSource;
import org.openpaas.paasta.portal.common.api.config.dataSource.PortalConfig;
import org.openpaas.paasta.portal.common.api.domain.user.UserService;
import org.openpaas.paasta.portal.common.api.domain.userManagement.UserManagementService;
import org.openpaas.paasta.portal.common.api.entity.portal.UserDetail;
import org.openpaas.paasta.portal.common.api.entity.uaa.Users;
import org.openpaas.paasta.portal.common.api.repository.portal.UserDetailRepository;
import org.openpaas.paasta.portal.common.api.repository.uaa.UsersRepository;
import org.openpaas.portal.common.api.config.TestConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by swmoon on 2018-06-25
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = CommonApiApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserManagementTest extends TestConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserManagementTest.class);

    @Mock
    JinqSource jinqSource;

    @Mock
    UserDetailRepository userDetailRepository;
    
    UserManagementService userManagementService;

    UsersRepository usersRepository;

    UserService userService;

    UserDetail userDetail;

    Map result;

    List<UserDetail> userDetailList;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        userManagementService = new UserManagementService(userService);

        result = new HashedMap();
        result.put("RESULT", Constants.RESULT_STATUS_SUCCESS);

    }

    @Test
    public void getUserInfoTest() {
//        when(userManagementService.getUserInfo("betterpoul@gmail.com")).thenReturn(result);
//        Map<String, Object> map = userManagementService.getUserInfo("betterpoul@gmail.com");
    }

}

