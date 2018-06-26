package org.openpaas.portal.common.api.service;


import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.openpaas.paasta.portal.common.api.CommonApiApplication;
import org.openpaas.paasta.portal.common.api.config.dataSource.PortalConfig;
import org.openpaas.paasta.portal.common.api.domain.app.AppService;
import org.openpaas.paasta.portal.common.api.entity.cc.BuildpackLifecyleDataCc;
import org.openpaas.paasta.portal.common.api.repository.cc.BuildpackLifecyleDataCcRepository;
import org.openpaas.portal.common.api.config.TestConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.metamodel.Metamodel;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Created by swmoon on 2018-06-25
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = CommonApiApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AppTest extends TestConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppTest.class);


    @InjectMocks
    AppService appService;

    @Mock
    BuildpackLifecyleDataCcRepository buildpackLifecyleDataCcRepository;

    @Mock
    PortalConfig portalConfig;

    @Mock
    EntityManager em;

    @Mock
    EntityManagerFactory emf;

    @Mock
    CriteriaBuilder cb;

    @Mock
    CriteriaQuery<Tuple> cq;

    @Mock
    LocalContainerEntityManagerFactoryBean lemf;

    BuildpackLifecyleDataCc buildpackLifecyleDataCc;





    String IMG_PATH = "paasta.png";

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

        buildpackLifecyleDataCc = new BuildpackLifecyleDataCc();
        buildpackLifecyleDataCc.setId("Id(");
        buildpackLifecyleDataCc.setGuid("Guid");
        buildpackLifecyleDataCc.setAdminBuildpackName("AdminBuildpackName");
        buildpackLifecyleDataCc.setAppGuid("AppGuid");
        buildpackLifecyleDataCc.setBuildGuid("BuildGuid");
        buildpackLifecyleDataCc.setDropletGuid("DropletGuid");
        buildpackLifecyleDataCc.setEncryptedBuildpackUrl("EncryptedBuildpackUrl");
        buildpackLifecyleDataCc.setEncryptedBuildpackUrlSalt("EncryptedBuildpackUrlSalt");
        buildpackLifecyleDataCc.setStack("Stack");
        buildpackLifecyleDataCc.setCreatedAt(new Date());
        buildpackLifecyleDataCc.setUpdatedAt(new Date());

        reflectionTestUtils();
    }


    @Test
    public void getAppImageUrl_Test() {
        when(portalConfig.portalEntityManager()).thenReturn(lemf);
        when(portalConfig.portalEntityManager().getNativeEntityManagerFactory()).thenReturn(emf);
        when(portalConfig.portalEntityManager().getNativeEntityManagerFactory().createEntityManager()).thenReturn(em);
        when(em.getCriteriaBuilder()).thenReturn(cb);
        when(cb.createTupleQuery()).thenReturn(cq);

        when(buildpackLifecyleDataCcRepository.findDistinctTopByAppGuid(GUID)).thenReturn(buildpackLifecyleDataCc);
        when(appService.getAppImageUrl(GUID)).thenReturn(IMG_PATH);
        String img = appService.getAppImageUrl(GUID);
        assertEquals(img, IMG_PATH);

    }


}
