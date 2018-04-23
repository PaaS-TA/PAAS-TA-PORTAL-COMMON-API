package org.openpaas.paasta.portal.common.api.domain.org;

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
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

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
 * Created by SEJI on 2018-03-07.
 */
@EnableAsync
@Service
public class OrgService {

    private final Logger LOGGER = getLogger(this.getClass());

    @Autowired
    private OrgService userService;

    @Autowired
    UserDetailRepository userDetailRepository;

    @Autowired
    OrgCcRepository orgCcRepository;

    @Autowired
    InviteOrgSpaceRepository inviteOrgSpaceRepository;

    @Autowired
    PortalConfig portalConfig;

    @Autowired
    CcConfig ccConfig;

    @Autowired
    JinqSource jinqSource;

    /*운영자 포털에서 조직목록을 요청했을때, 모든 조직목록을 응답한다.*/
    public List<Object> getOrgsForAdmin() throws Exception {
        EntityManager portalEm = ccConfig.ccEntityManager().getNativeEntityManagerFactory().createEntityManager();

        CriteriaBuilder cb = portalEm.getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = cb.createTupleQuery();
        Root<OrganizationsCc> from = cq.from(OrganizationsCc.class);

        //SQL:Select
        cq.multiselect(from.get("id").alias("id")
                , from.get("name").alias("name")
                , from.get("guid").alias("guid")
                , from.get("createdAt").alias("createdAt")
                , from.get("updatedAt").alias("updatedAt")
                , from.get("billingEnabled").alias("billingEnabled")
                , from.get("status").alias("status"));

        TypedQuery<Tuple> tq = portalEm.createQuery(cq);
        List<Tuple> resultList = tq.getResultList();

        List<Object> OrgsForAdmintList = resultList.stream().map(x -> new HashMap<String, Object>(){{
            put("orgId", x.get("id"));
            put("orgName", x.get("name"));
            put("orgGuid", x.get("guid"));
            put("created", x.get("createdAt"));
            put("lastModified", x.get("updatedAt"));
            put("billingEnabled", x.get("billingEnabled"));
            put("status", x.get("status"));
        }}).collect(Collectors.toList());

        return OrgsForAdmintList;
    }


    public List<Object> getOrg(String guid) {

        String orgs = OrgCcRepository.findByGuid(guid);

        EntityManager portalEm = ccConfig.ccEntityManager().getNativeEntityManagerFactory().createEntityManager();

        CriteriaBuilder cb = portalEm.getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = cb.createTupleQuery();
        Root<OrganizationsCc> from = cq.from(OrganizationsCc.class);

        Predicate predicate = cb.conjunction();
        predicate = cb.and(predicate, cb.equal(from.get("guid"), orgs.toString()));
        cq.where(predicate);

        cq.multiselect(from.get("id").alias("id")
                , from.get("name").alias("name")
                , from.get("guid").alias("guid")
                , from.get("createdAt").alias("createdAt")
                , from.get("updatedAt").alias("updatedAt")
                , from.get("billingEnabled").alias("billingEnabled")
                , from.get("status").alias("status"));

        TypedQuery<Tuple> tq = portalEm.createQuery(cq);
        List<Tuple> resultList = tq.getResultList();

        List<Object> OrgsList = resultList.stream().map(x -> new HashMap<String, Object>(){{
            put("orgId", x.get("id"));
            put("orgName", x.get("name"));
            put("orgGuid", x.get("guid"));
            put("created", x.get("createdAt"));
            put("lastModified", x.get("updatedAt"));
            put("billingEnabled", x.get("billingEnabled"));
            put("status", x.get("status"));
        }}).collect(Collectors.toList());

        return OrgsList;
    }


    /*공간에 초대한 이메일의 token을 가진 초대 정보를 가져온다.*/
    public List selectInviteInfo(String code){

        EntityManager portalEm = portalConfig.portalEntityManager().getNativeEntityManagerFactory().createEntityManager();
        CriteriaBuilder cb = portalEm.getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = cb.createTupleQuery();
        Root<InviteOrgSpace> from = cq.from(InviteOrgSpace.class);

        //SQL:Select
        cq.multiselect(from.get("id").alias("id")
                , from.get("token").alias("token")
                , from.get("gubun").alias("gubun")
                , from.get("inviteId").alias("inviteId")
                , from.get("roleName").alias("roleName")
                , from.get("inviteUserId").alias("inviteUserId")
                , from.get("userId").alias("userId")
                , from.get("createTime").alias("createTime")
                , from.get("accessCnt").alias("accessCnt")
                , from.get("inviteName").alias("inviteName")
                , from.get("setyn").alias("setyn"));

        TypedQuery<Tuple> tq = portalEm.createQuery(cq);
        List<Tuple> resultList = tq.getResultList();

        List<Map<String, Object>> selectInviteInfo = resultList.stream().map(x -> new HashMap<String, Object>(){{
            put("id", x.get("id"));
            put("token", x.get("token"));
            put("gubun", x.get("gubun"));
            put("inviteId", x.get("inviteId"));
            put("roleName", x.get("roleName"));
            put("inviteUserId", x.get("inviteUserId"));
            put("userId", x.get("userId"));
            put("createTime", x.get("createTime"));
            put("accessCnt", x.get("accessCnt"));
            put("inviteName", x.get("inviteName"));
            put("setyn", x.get("setyn"));
        }}).collect(Collectors.toList());
        return selectInviteInfo;
    }

}