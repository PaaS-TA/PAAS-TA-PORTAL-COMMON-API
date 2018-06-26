package org.openpaas.portal.common.api.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.commons.collections.map.HashedMap;
import org.jinq.orm.stream.JinqStream;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.openpaas.paasta.portal.common.api.CommonApiApplication;
import org.openpaas.paasta.portal.common.api.config.Constants;
import org.openpaas.paasta.portal.common.api.config.JinqSource;
import org.openpaas.paasta.portal.common.api.config.dataSource.PortalConfig;
import org.openpaas.paasta.portal.common.api.domain.app.AppService;
import org.openpaas.paasta.portal.common.api.domain.catalog.CatalogService;
import org.openpaas.paasta.portal.common.api.entity.cc.BuildpackLifecyleDataCc;
import org.openpaas.paasta.portal.common.api.entity.cc.CatalogCc;
import org.openpaas.paasta.portal.common.api.entity.portal.*;
import org.openpaas.paasta.portal.common.api.repository.cc.BuildpackLifecyleDataCcRepository;
import org.openpaas.paasta.portal.common.api.repository.cc.CatalogCcRepository;
import org.openpaas.paasta.portal.common.api.repository.portal.*;
import org.openpaas.portal.common.api.config.TestConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by swmoon on 2018-06-25
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = CommonApiApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CatalogTest extends TestConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(AppTest.class);


    @InjectMocks
    CatalogService catalogService;


    @Mock
    ServicepackCategoryRepository servicepackCategoryRepository;


    @Mock
    JinqSource jinqSource;

    @Mock
    JinqStream<ServicepackCategory> servicepackCategory_streams;

    ServicepackCategory servicepackCategory;

    int thenReturn = 1;


    Map result;


    List<ServicepackCategory> testServicepackCategories;

    /**
     * Sets up.
     *
     * @throws Exception the exception
     */
    @Before
    public void setUp() throws Exception {

        LOGGER.info("##############################################################");
        LOGGER.info("##############################################################");
        LOGGER.info("CommonApi AppTest Start");
        LOGGER.info("##############################################################");
        LOGGER.info("##############################################################");


        servicepackCategory_streams = jinqSource.streamAllPortal(ServicepackCategory.class);

        servicepackCategory = new ServicepackCategory();
        servicepackCategory.setNo(1);
        servicepackCategory.setName("test-catalog");

        result = new HashedMap();
        result.put("RESULT", Constants.RESULT_STATUS_SUCCESS);
        testServicepackCategories = getServicepackCategories();

    }

    private List<ServicepackCategory> getServicepackCategories() {
        List<ServicepackCategory> servicepackCategories = new ArrayList<>();
        for (int i = 0; i <= 10; i++) {
            ServicepackCategory servicepackCategory = new ServicepackCategory();
            servicepackCategory.setNo(i);
            servicepackCategory.setName("ServicepackCategory" + i);
            servicepackCategories.add(servicepackCategory);
        }

        return servicepackCategories;
    }


    @Test
    public void insertServicePackCatalog_Test() {
        when(servicepackCategoryRepository.save(servicepackCategory)).thenReturn(servicepackCategory);
        when(catalogService.insertServicePackCatalog(servicepackCategory)).thenReturn(result);
        Map rs = catalogService.insertServicePackCatalog(servicepackCategory);
        assertEquals(Constants.RESULT_STATUS_SUCCESS, rs.get("RESULT"));
    }


    @Test
    public void getServicePackCatalogCount_Test() {
        String name = "";
        when(jinqSource.streamAllPortal(ServicepackCategory.class)).thenReturn(servicepackCategory_streams);
        when(servicepackCategory_streams.where(c -> c.getName().equals(name))).thenReturn(servicepackCategory_streams);
        when(servicepackCategory_streams.sortedDescendingBy(c -> c.getNo())).thenReturn(servicepackCategory_streams);
        when(servicepackCategory_streams.toList()).thenReturn(testServicepackCategories);
        when(catalogService.getServicePackCatalogCount(servicepackCategory)).thenReturn(1);
        int rs = catalogService.getServicePackCatalogCount(servicepackCategory);
        assertEquals(rs, 1);
    }

}
