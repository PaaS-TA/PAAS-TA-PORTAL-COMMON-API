package org.openpaas.portal.common.api.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.junit.FixMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.openpaas.paasta.portal.common.api.config.JinqSource;
import org.openpaas.paasta.portal.common.api.config.dataSource.CcConfig;
import org.openpaas.paasta.portal.common.api.config.dataSource.PortalConfig;
import org.openpaas.paasta.portal.common.api.entity.cc.OrganizationsCc;
import org.openpaas.paasta.portal.common.api.entity.portal.InviteOrgSpace;
import org.openpaas.paasta.portal.common.api.repository.cc.OrgCcRepository;
import org.openpaas.paasta.portal.common.api.repository.portal.InviteOrgSpaceRepository;
import org.openpaas.paasta.portal.common.api.repository.portal.UserDetailRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by swmoon on 2018-06-25
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OrgTest {


}