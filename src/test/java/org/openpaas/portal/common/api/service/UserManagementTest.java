package org.openpaas.portal.common.api.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.jinq.orm.stream.JinqStream;
import org.junit.FixMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.openpaas.paasta.portal.common.api.config.Constants;
import org.openpaas.paasta.portal.common.api.config.JinqSource;
import org.openpaas.paasta.portal.common.api.config.dataSource.PortalConfig;
import org.openpaas.paasta.portal.common.api.entity.portal.UserDetail;
import org.openpaas.paasta.portal.common.api.entity.uaa.Users;
import org.openpaas.paasta.portal.common.api.repository.portal.UserDetailRepository;
import org.openpaas.paasta.portal.common.api.repository.uaa.UsersRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by swmoon on 2018-06-25
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserManagementTest {

}

