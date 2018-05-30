package org.openpaas.paasta.portal.common.api.domain.adminMain;

import org.jinq.orm.stream.JinqStream;
import org.openpaas.paasta.portal.common.api.config.JinqSource;
import org.openpaas.paasta.portal.common.api.config.dataSource.CcConfig;
import org.openpaas.paasta.portal.common.api.entity.cc.OrganizationsCc;
import org.openpaas.paasta.portal.common.api.entity.cc.OrganizationsTolCc;
import org.openpaas.paasta.portal.common.api.entity.cc.SpacesCc;
import org.openpaas.paasta.portal.common.api.repository.cc.SpacesCcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by indra on 2018-02-12.
 */
@Service
public class AdminMainService {

    @Autowired
    CcConfig ccConfig;

    @Autowired
    SpacesCcRepository spacesCcRepository;

    @Autowired
    JinqSource jinqSource;


    public Map<String, Object>getTotalCountList() {
        JinqStream<OrganizationsCc> streams = jinqSource.streamAllCc(OrganizationsCc.class);

        List<Map<String, Object>> resultList = streams.map(x -> new HashMap<String, Object>(){{
            put("organizationCount", x.getOrganizationCount());
            put("spaceCount", x.getSpaceCount());
            put("applicationCount", x.getApplicationCount());
            put("userCount", x.getUserCount());
        }}).collect(Collectors.toList());

        return new HashMap<String, Object>() {{
            put("list", resultList);
        }};
    }

    public Map<String, Object>getTotalOrganizationList() {  //Map<String, Object> reqParam
        JinqStream<OrganizationsTolCc> streams = jinqSource.streamAllCc(OrganizationsTolCc.class);

//        if(null != reqParam.get("organizationId")) {
//            int id = Integer.parseInt(reqParam.get("organizationId").toString());
//            streams = streams.where(c -> c.getId() == id);
//        }

        streams = streams.sortedBy(c -> c.getId());

        List<Map<String, Object>> resultList = streams.map(x -> new HashMap<String, Object>(){{
            put("organizationId", x.getId());
            put("organizationName", x.getName());
            put("spaceCount", x.getSpaceCount());
            put("applicationCount", x.getApplicationCount());
            put("userCount", x.getUserCount());
        }}).collect(Collectors.toList());

        return new HashMap<String, Object>() {{
            put("list", resultList);
        }};
    }

    public Map<String, Object>getTotalSpaceList(String organizationId) {
        EntityManager ccEm = ccConfig.ccEntityManager().getNativeEntityManagerFactory().createEntityManager();

//        CriteriaBuilder cb = ccEm.getCriteriaBuilder();
//        CriteriaQuery<Tuple> cq = cb.createTupleQuery();
//        Root<SpacesCc> from = cq.from(SpacesCc.class);
//
//        cq.multiselect(from.get("id").alias("spaceId")
//                , from.get("name").alias("spaceName"));
//
//        Predicate predicate = cb.conjunction();
//
//        if(null != reqParam.get("organizationId") && !("").equals(reqParam.get("organizationId").toString())) {
//            predicate = cb.and(predicate, cb.equal(from.get("organizationId"), reqParam.get("organizationId").toString()));
//        }
//
//        cq.where(predicate);
//
//
//        TypedQuery<Tuple> tq = ccEm.createQuery(cq);
//        List<Tuple> resultList = tq.getResultList();
//
//        CriteriaQuery<Tuple> cq2 = cb.createTupleQuery();
//        Root<SpaceunionViewCc> from2 = cq2.from(SpaceunionViewCc.class);
//        cq2.multiselect(from2.get("spaceId").alias("spaceId")
//                , from2.get("applicationCount").alias("applicationCount")
//                , from2.get("spaceName").alias("spaceName")).distinct(true);
//
//        if(null != reqParam.get("organizationId") && !("").equals(reqParam.get("organizationId").toString())) {
//            Subquery<SpacesCc> subQuery = cq2.subquery(SpacesCc.class);
//            Root<SpaceunionViewCc> subM = subQuery.correlate(from2);
//            Root<SpacesCc> subFrom = subQuery.from(SpacesCc.class);
//
//            subQuery.select(subFrom.get("id"));
//
//            Predicate predicate2 = cb.conjunction();
//            predicate2 = cb.and(predicate2, cb.equal(subFrom.get("id"), subM.get("spaceId")));
//            predicate2 = cb.and(predicate2, cb.equal(subFrom.get("organizationId"), reqParam.get("organizationId").toString()));
//
//            subQuery.where(predicate2);
//
//            cq2.where(cb.exists(subQuery));
//            cq2.groupBy(from2.get("spaceId"));
//        }
//
//        TypedQuery<Tuple> tq2 = ccEm.createQuery(cq2);
//        List<Tuple> resultList2 = tq2.getResultList();
//
//        List<Map<String, Object>> resultList1f = resultList.stream().map(x -> new HashMap<String, Object>(){{
//            put("spaceId", x.get("spaceId"));
//            put("spaceName", x.get("spaceName"));
//        }}).collect(Collectors.toList());
//
//        List<Map<String, Object>> resultList2f = resultList2.stream().map(x -> new HashMap<String, Object>(){{
//            put("spaceId", x.get("spaceId"));
//            put("applicationCount", x.get("applicationCount"));
//            put("spaceName", x.get("spaceName"));
//        }}).collect(Collectors.toList());
//
//        for(int i=0; i<resultList1f.size(); i++) {
//            for(int j=0; j<resultList2f.size(); j++) {
//                if(resultList2f.get(j).get("spaceId") == resultList1f.get(i).get("spaceId")) {
//                    resultList1f.get(i).put("applicationCount", resultList2f.get(j).get("applicationCount"));
//                    break;
//                } else {
//                    resultList1f.get(i).put("applicationCount", 0);
//                }
//            }
//        }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        Query q = null;
        if(null != organizationId && !("").equals(organizationId)) {
            q = ccEm.createNamedQuery("SpacesCc.allByOrganizationIdList");
            q.setParameter(1, Integer.parseInt(organizationId));
            q.setParameter(2, Integer.parseInt(organizationId));
        } else {
            q = ccEm.createNamedQuery("SpacesCc.allList");
        }

        List<SpacesCc> results = q.getResultList();

        List<Map<String, Object>> resultList = results.stream().map(x -> new HashMap<String, Object>(){{
            put("spaceId", x.getId());
            put("applicationCount", x.getApplicationCount());
            put("spaceName", x.getName());
        }}).collect(Collectors.toList());

        return new HashMap<String, Object>() {{
            put("list", resultList);
        }};
    }
}
