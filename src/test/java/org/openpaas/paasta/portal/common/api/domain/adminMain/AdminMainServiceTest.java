package org.openpaas.paasta.portal.common.api.domain.adminMain;

import org.apache.commons.collections.map.HashedMap;
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
import org.openpaas.paasta.portal.common.api.config.JinqSource;
import org.openpaas.paasta.portal.common.api.config.TestConfig;
import org.openpaas.paasta.portal.common.api.config.dataSource.CcConfig;
import org.openpaas.paasta.portal.common.api.entity.cc.OrganizationsCc;
import org.openpaas.paasta.portal.common.api.entity.cc.SpacesCc;
import org.openpaas.paasta.portal.common.api.repository.cc.SpacesCcRepository;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.mockito.Mockito.*;

/**
 * Created by indra on 2018-06-27.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AdminMainServiceTest extends TestConfig {
    @Mock
    CcConfig ccConfig;
    @Mock
    SpacesCcRepository spacesCcRepository;
    @Mock
    JinqSource jinqSource;
    @MockBean
    AdminMainService adminMainService;

    OrganizationsCc organizationsCc;

    SpacesCc spacesCc;

    List<OrganizationsCc> organizationsCcList;

    List<SpacesCc> spacesCcList;

    Map resultMap;
    Map getTotalSpaceListResultMap;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        Date d = new Date();

        resultMap = new HashedMap();

        //testGetTotalCountList
        organizationsCcList = new ArrayList<OrganizationsCc>();
        organizationsCc = new OrganizationsCc();
        organizationsCc.setId(1);
        organizationsCc.setGuid("4fa133ee-d49f-4a8f-b56c-c691f8bd8674");
        organizationsCc.setCreatedAt(d);
        organizationsCc.setUpdatedAt(d);
        organizationsCc.setName("test-org");
        organizationsCc.setBillingEnabled(false);
        organizationsCc.setQuotaDefinitionId("1");
        organizationsCc.setStatus("active");
        organizationsCc.setDefaultIsolationSegmentGuid(null);
        organizationsCcList.add(organizationsCc);
        resultMap.put("list", organizationsCcList);

        //testGetTotalSpaceList
        getTotalSpaceListResultMap = new HashedMap();
        spacesCcList = new ArrayList<SpacesCc>();
        spacesCc = new SpacesCc();
        spacesCcList.add(spacesCc);
        getTotalSpaceListResultMap.put("list", spacesCcList);

    }

    @Test
    public void testGetTotalCountList() throws Exception {
        when(adminMainService.getTotalCountList()).thenReturn(resultMap);

        Map<String, Object> result = adminMainService.getTotalCountList();
        Assert.assertEquals(resultMap, result);
    }

    @Test
    public void testGetTotalOrganizationList() throws Exception {
        when(adminMainService.getTotalOrganizationList()).thenReturn(resultMap);

        Map<String, Object> result = adminMainService.getTotalOrganizationList();
        Assert.assertEquals(resultMap, result);
    }

    @Test
    public void testGetTotalSpaceList() throws Exception {
        when(adminMainService.getTotalSpaceList("1")).thenReturn(getTotalSpaceListResultMap);

        Map<String, Object> result = adminMainService.getTotalSpaceList("1");
        Assert.assertEquals(getTotalSpaceListResultMap, result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme