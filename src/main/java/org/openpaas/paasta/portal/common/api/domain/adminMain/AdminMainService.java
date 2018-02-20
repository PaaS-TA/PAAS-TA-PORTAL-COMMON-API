package org.openpaas.paasta.portal.common.api.domain.adminMain;

import org.openpaas.paasta.portal.common.api.config.dataSource.CcConfig;
import org.openpaas.paasta.portal.common.api.entity.cc.OrganizationsCc;
import org.openpaas.paasta.portal.common.api.entity.cc.SpacesCc;
import org.openpaas.paasta.portal.common.api.entity.cc.SpaceunionViewCc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
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


    public Map<String, Object>getTotalCountList() {
        List<Map<String, Object>> resultList = new ArrayList<>();
        Map<String, Object> resultMap = new HashMap<String, Object>();

        EntityManager ccEm = ccConfig.ccEntityManager().getNativeEntityManagerFactory().createEntityManager();

        CriteriaBuilder cb = ccEm.getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = cb.createTupleQuery();
        Root<OrganizationsCc> from = cq.from(OrganizationsCc.class);

        cq.multiselect(from.get("organizationCount").alias("organizationCount")
                , from.get("spaceCount").alias("spaceCount")
                , from.get("applicationCount").alias("applicationCount")
                , from.get("userCount").alias("userCount"));

        TypedQuery<Tuple> tq = ccEm.createQuery(cq);
        Tuple result = tq.getSingleResult();

        int organizationCount   = result.get("organizationCount", Integer.class);
        int spaceCount          = result.get("spaceCount", Integer.class);
        int applicationCount    = result.get("applicationCount", Integer.class);
        int userCount           = result.get("userCount", Integer.class);

        resultMap.put("organizationCount", organizationCount);
        resultMap.put("spaceCount", spaceCount);
        resultMap.put("applicationCount", applicationCount);
        resultMap.put("userCount", userCount);

        //TODO 꼭 list로 넘겨야 하나??
        resultList.add(resultMap);
//        List<Tuple> resultList = tq.getResultList();
//        for (Tuple tuple : resultList) {
//            System.out.print(tuple.get("organizationCount", Integer.class));
//            System.out.print(",");
//            System.out.print(tuple.get("spaceCount", Integer.class));
//            System.out.print(",");
//            System.out.print(tuple.get("applicationCount", Integer.class));
//            System.out.print(",");
//            System.out.print(tuple.get("userCount", Integer.class));
//        }

        return new HashMap<String, Object>() {{
            put("list", resultList);
        }};
//        return resultMap;
    }

    public Map<String, Object>getTotalOrganizationList(Map<String, Object> reqParam) {
        EntityManager ccEm = ccConfig.ccEntityManager().getNativeEntityManagerFactory().createEntityManager();

        CriteriaBuilder cb = ccEm.getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = cb.createTupleQuery();
        Root<OrganizationsCc> from = cq.from(OrganizationsCc.class);

        cq.multiselect(from.get("id").alias("organizationId")
                , from.get("name").alias("organizationName")
                , from.get("spaceCountTOL").alias("spaceCount")
                , from.get("applicationCountTOL").alias("applicationCount")
                , from.get("userCountTOL").alias("userCount"));

        Predicate predicate = cb.conjunction();

        if(null != reqParam.get("organizationId") && !("").equals(reqParam.get("organizationId").toString())) {
            predicate = cb.and(predicate, cb.equal(from.get("id"), reqParam.get("organizationId").toString()));
        }

        cq.where(predicate);
        cq.orderBy(cb.asc(from.get("id")));

        TypedQuery<Tuple> tq = ccEm.createQuery(cq);
        List<Tuple> resultList = tq.getResultList();

        List<Map<String, Object>> resultList2 = resultList.stream().map(x -> new HashMap<String, Object>(){{
            put("organizationId", x.get("organizationId"));
            put("organizationName", x.get("organizationName"));
            put("spaceCount", x.get("spaceCount"));
            put("applicationCount", x.get("applicationCount"));
            put("userCount", x.get("userCount"));
        }}).collect(Collectors.toList());

        return new HashMap<String, Object>() {{
            put("list", resultList2);
        }};
    }

    public Map<String, Object>getTotalSpaceList(Map<String, Object> reqParam) {
        EntityManager ccEm = ccConfig.ccEntityManager().getNativeEntityManagerFactory().createEntityManager();

        CriteriaBuilder cb = ccEm.getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = cb.createTupleQuery();
        Root<SpacesCc> from = cq.from(SpacesCc.class);

        cq.multiselect(from.get("id").alias("spaceId")
                , from.get("name").alias("spaceName"));

        Predicate predicate = cb.conjunction();

        if(null != reqParam.get("organizationId") && !("").equals(reqParam.get("organizationId").toString())) {
            predicate = cb.and(predicate, cb.equal(from.get("organizationId"), reqParam.get("organizationId").toString()));
        }

        cq.where(predicate);


        TypedQuery<Tuple> tq = ccEm.createQuery(cq);
        List<Tuple> resultList = tq.getResultList();

//        for (Tuple tuple : resultList) {
//            System.out.print(tuple.get("spaceId", Integer.class));
//            System.out.print(",");
//            System.out.print(tuple.get("spaceName", String.class));
//            System.out.print("\n");
//        }

        CriteriaQuery<Tuple> cq2 = cb.createTupleQuery();
        Root<SpaceunionViewCc> from2 = cq2.from(SpaceunionViewCc.class);
        cq2.multiselect(from2.get("spaceId").alias("spaceId")
                , from2.get("applicationCount").alias("applicationCount")
                , from2.get("spaceName").alias("spaceName")).distinct(true);

        if(null != reqParam.get("organizationId") && !("").equals(reqParam.get("organizationId").toString())) {
            Subquery<SpacesCc> subQuery = cq2.subquery(SpacesCc.class);
            Root<SpaceunionViewCc> subM = subQuery.correlate(from2);
            Root<SpacesCc> subFrom = subQuery.from(SpacesCc.class);

            subQuery.select(subFrom.get("id"));

            Predicate predicate2 = cb.conjunction();
            predicate2 = cb.and(predicate2, cb.equal(subFrom.get("id"), subM.get("spaceId")));
//            predicate2 = cb.and(predicate2, cb.equal(subFrom.get("id"), from2.get("spaceId")));
            predicate2 = cb.and(predicate2, cb.equal(subFrom.get("organizationId"), reqParam.get("organizationId").toString()));

            subQuery.where(predicate2);

            cq2.where(cb.exists(subQuery));
            cq2.groupBy(from2.get("spaceId"));
        }

        TypedQuery<Tuple> tq2 = ccEm.createQuery(cq2);
        List<Tuple> resultList2 = tq2.getResultList();

        List<Map<String, Object>> resultList1f = resultList.stream().map(x -> new HashMap<String, Object>(){{
            put("spaceId", x.get("spaceId"));
            put("spaceName", x.get("spaceName"));
        }}).collect(Collectors.toList());

        List<Map<String, Object>> resultList2f = resultList2.stream().map(x -> new HashMap<String, Object>(){{
            put("spaceId", x.get("spaceId"));
            put("applicationCount", x.get("applicationCount"));
            put("spaceName", x.get("spaceName"));
        }}).collect(Collectors.toList());

        for(int i=0; i<resultList1f.size(); i++) {
            for(int j=0; j<resultList2f.size(); j++) {
                if(resultList2f.get(j).get("spaceId") == resultList1f.get(i).get("spaceId")) {
                    resultList1f.get(i).put("applicationCount", resultList2f.get(j).get("applicationCount"));
                    break;
                } else {
                    resultList1f.get(i).put("applicationCount", 0);
                }
            }
//            System.out.println(resultList1f.get(i).get("spaceId") + ", "+ resultList1f.get(i).get("applicationCount") + ", "+ resultList1f.get(i).get("spaceName"));
        }

        return new HashMap<String, Object>() {{
            put("list", resultList1f);
        }};
    }
}
