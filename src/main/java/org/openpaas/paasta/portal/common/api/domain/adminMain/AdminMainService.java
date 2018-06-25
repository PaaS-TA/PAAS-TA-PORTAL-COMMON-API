package org.openpaas.paasta.portal.common.api.domain.adminMain;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
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

    @HystrixCommand(commandKey = "getTotalCountList")
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
    @HystrixCommand(commandKey = "getTotalOrganizationList")
    public Map<String, Object>getTotalOrganizationList() {  //Map<String, Object> reqParam
        JinqStream<OrganizationsTolCc> streams = jinqSource.streamAllCc(OrganizationsTolCc.class);
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

    @HystrixCommand(commandKey = "getTotalSpaceList")
    public Map<String, Object>getTotalSpaceList(String organizationId) {
        EntityManager ccEm = ccConfig.ccEntityManager().getNativeEntityManagerFactory().createEntityManager();
        Query q = null;
        if(null != organizationId && !("").equals(organizationId)) {
            q = ccEm.createNamedQuery("SpacesCc.allByOrganizationIdList");
            q.setParameter(1, Integer.parseInt(organizationId));
            //q.setParameter(2, Integer.parseInt(organizationId));
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
