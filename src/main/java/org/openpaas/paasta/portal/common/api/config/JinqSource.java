package org.openpaas.paasta.portal.common.api.config;

import org.jinq.jpa.JinqJPAStreamProvider;
import org.jinq.orm.stream.JinqStream;
import org.openpaas.paasta.portal.common.api.config.dataSource.CcConfig;
import org.openpaas.paasta.portal.common.api.config.dataSource.PortalConfig;
import org.openpaas.paasta.portal.common.api.config.dataSource.UaaConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

/**
 * Created by indra on 2018-02-23.
 */
@Component
public class JinqSource {
    private JinqJPAStreamProvider streams;

    @Autowired
    PortalConfig portalConfig;

    @Autowired
    CcConfig ccConfig;

    @Autowired
    UaaConfig uaaConfig;


    public <U> JinqStream<U> streamAll(EntityManager em, Class<U> entity) {
        return streams.streamAll(em, entity);
    }

    public <U> JinqStream<U> streamAllPortal(Class<U> entity) {
        streams = new JinqJPAStreamProvider(portalConfig.portalEntityManager().getNativeEntityManagerFactory());
        EntityManager portalEm = portalConfig.portalEntityManager().getNativeEntityManagerFactory().createEntityManager();
        return streams.streamAll(portalEm, entity);
    }

    public <U> JinqStream<U> streamAllCc(Class<U> entity) {
        streams = new JinqJPAStreamProvider(ccConfig.ccEntityManager().getNativeEntityManagerFactory());
        EntityManager ccEm = ccConfig.ccEntityManager().getNativeEntityManagerFactory().createEntityManager();
        return streams.streamAll(ccEm, entity);
    }

    public <U> JinqStream<U> streamAllUAA(Class<U> entity) {
        streams = new JinqJPAStreamProvider(uaaConfig.uaaEntityManager().getNativeEntityManagerFactory());
        EntityManager uaaEm = uaaConfig.uaaEntityManager().getNativeEntityManagerFactory().createEntityManager();
        return streams.streamAll(uaaEm, entity);
    }

    public JinqJPAStreamProvider streamCc() {
        streams = new JinqJPAStreamProvider(ccConfig.ccEntityManager().getNativeEntityManagerFactory());
        return streams;
    }
}
