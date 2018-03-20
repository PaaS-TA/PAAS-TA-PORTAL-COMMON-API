package org.openpaas.paasta.portal.common.api.domain.service;

import org.openpaas.paasta.portal.common.api.config.dataSource.PortalConfig;
import org.openpaas.paasta.portal.common.api.entity.portal.ServicepackCategory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by indra on 2018-02-22.
 */
@Service
public class ServiceService {

    private static final Logger logger = getLogger(ServiceService.class);

    @Autowired
    PortalConfig portalConfig;


    /**
     * 서비스 이미지를 가져온다.
     *
     * @param servicepackCategory the serviceName
     * @return the boolean
     * @throws Exception the exception
     */
    public String getServiceImageUrl(ServicepackCategory servicepackCategory) {
        String resultStr = "";

        EntityManager portalEm = portalConfig.portalEntityManager().getNativeEntityManagerFactory().createEntityManager();

        CriteriaBuilder cb = portalEm.getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = cb.createTupleQuery();
        Root<ServicepackCategory> from = cq.from(ServicepackCategory.class);

        Predicate predicate = cb.conjunction();
        predicate = cb.and(predicate, cb.equal(from.get("serviceName"), servicepackCategory.getServiceName().toString()));

        Expression maxThumbImgPath = cb.max(from.get("thumbImgPath"));

        cq.multiselect(maxThumbImgPath.alias("thumbImgPath"));
        cq.where(predicate);

        TypedQuery<Tuple> tq = portalEm.createQuery(cq);
        Tuple result = tq.getSingleResult();

        resultStr = result.get("thumbImgPath", String.class);

        return resultStr;
    }
}
