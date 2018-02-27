package org.openpaas.paasta.portal.common.api.domain.webIdeUser;

import org.openpaas.paasta.portal.common.api.config.Constants;
import org.openpaas.paasta.portal.common.api.config.dataSource.PortalConfig;
import org.openpaas.paasta.portal.common.api.entity.portal.WebIdeUser;
import org.openpaas.paasta.portal.common.api.repository.portal.WebIdeUserRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.Date;
import java.util.HashMap;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by indra on 2018-02-21.
 */
@Service
public class WebIdeUserService {

    private static final Logger logger = getLogger(WebIdeUserService.class);

    @Autowired
    WebIdeUserRepository webIdeUserRepository;

    @Autowired
    PortalConfig portalConfig;


    /**
     * WEB IDE 사용자 정보를 조회한다.
     *
     * @param webIdeUser the webIdeUser
     * @return ModelAndView model
     */
    public WebIdeUser getUser(WebIdeUser webIdeUser) {
        return webIdeUserRepository.findAllByOrgName(webIdeUser.getOrgName());
    }

    /**
     * WEB IDE 사용자 리스트를 조회한다.
     *
     * @param webIdeUser the webIdeUser
     * @return ModelAndView model
     */
    public HashMap<String, Object> getList(WebIdeUser webIdeUser) {
        HashMap<String, Object> resultMap = new HashMap<>();

        resultMap.put("list", webIdeUserRepository.findAllByOrderByUseYnDescUrlAsc());
        return resultMap;

//        EntityManager portalEm = portalConfig.portalEntityManager().getNativeEntityManagerFactory().createEntityManager();
//
//        CriteriaBuilder cb = portalEm.getCriteriaBuilder();
//        CriteriaQuery<Tuple> cq = cb.createTupleQuery();
//        Root<WebIdeUser> from = cq.from(WebIdeUser.class);
//
//        cq.multiselect(from.get("userId").alias("userId")
//                , from.get("orgName").alias("orgName")
//                , from.get("url").alias("url")
//                , from.get("useYn").alias("useYn")
//                , from.get("createdAt").alias("createdAt")
//                , from.get("updatedAt").alias("updatedAt"));
//
//
//        cq.orderBy(cb.desc(from.get("useYn")), cb.asc(from.get("url")));
//
//        TypedQuery<Tuple> tq = portalEm.createQuery(cq);
//        List<Tuple> resultList = tq.getResultList();
//
//        List<Map<String, Object>> resultList2 = resultList.stream().map(x -> new HashMap<String, Object>(){{
//            put("userId", x.get("userId"));
//            put("orgName", x.get("orgName"));
//            put("url", x.get("url"));
//            put("useYn", x.get("useYn"));
//            put("createdAt", x.get("createdAt"));
//            put("updatedAt", x.get("updatedAt"));
//        }}).collect(Collectors.toList());
//
//        return new HashMap<String, Object>() {{
//            put("list", resultList2);
//        }};
    }

    /**
     * WEB IDE 사용자를 저장한다.
     *
     * @param webIdeUser the webIdeUser
     * @return ModelAndView model
     */
    public String insertUser(WebIdeUser webIdeUser) {
        String resultStr = Constants.RESULT_STATUS_SUCCESS;

        try {
            if(webIdeUserRepository.countByOrgName(webIdeUser.getOrgName()) == 0) {
                webIdeUser.setUrl(null);
                webIdeUser.setUseYn("N");
                webIdeUser.setUpdatedAt(null);

                WebIdeUser result = webIdeUserRepository.save(webIdeUser);
            } else {
                resultStr = Constants.RESULT_STATUS_FAIL_DUPLICATED;
            }
        } catch (DataAccessException ex) {
            logger.error("Exception :: DataAccessException :: {}", ex.getCause().getMessage());
            resultStr = Constants.RESULT_STATUS_FAIL;
        }

        return resultStr;
    }

    /**
     * WEB IDE 사용자를 수정한다.
     *
     * @param webIdeUser the webIdeUser
     * @return ModelAndView model
     */
    public String updateUser(WebIdeUser webIdeUser) {
        String resultStr = Constants.RESULT_STATUS_SUCCESS;

        EntityManager portalEm = portalConfig.portalEntityManager().getNativeEntityManagerFactory().createEntityManager();

        Date d = new Date();

        try {
            if(null == webIdeUser.getUserId() || "".equals(webIdeUser.getUserId()))
                throw new NullPointerException();

            if(null == webIdeUser.getOrgName() || "".equals(webIdeUser.getOrgName()))
                throw new NullPointerException();

            CriteriaBuilder cb = portalEm.getCriteriaBuilder();
            CriteriaUpdate<WebIdeUser> update = cb.createCriteriaUpdate(WebIdeUser.class);
            Root e = update.from(WebIdeUser.class);

            update.set("updatedAt", d);

            if(null != webIdeUser.getUrl() && !"".equals(webIdeUser.getUrl()))
                update.set("url", webIdeUser.getUrl());

            if(null != webIdeUser.getUseYn() && !"".equals(webIdeUser.getUseYn()))
                update.set("useYn", webIdeUser.getUseYn());

            Predicate predicate = cb.conjunction();

            predicate = cb.and(predicate, cb.equal(e.get("userId"), webIdeUser.getUserId()));
            predicate = cb.and(predicate, cb.equal(e.get("orgName"), webIdeUser.getOrgName()));

            update.where(predicate);

            portalEm.getTransaction().begin();
            int updateCnt = portalEm.createQuery(update).executeUpdate();
            portalEm.getTransaction().commit();

            if(updateCnt == 0)
                throw new NullPointerException();

        } catch (DataAccessException ex) {
        logger.error("Exception :: DataAccessException :: {}", ex.getCause().getMessage());
        resultStr = Constants.RESULT_STATUS_FAIL;
    } catch (NullPointerException nex) {
        logger.error("Exception :: NullPointerException :: {}", nex.getCause().getMessage());
        resultStr = Constants.RESULT_STATUS_FAIL;
    } finally {
        portalEm.close();
    }

        return resultStr;
    }

    /**
     * WEB IDE 사용자를 삭제한다.
     *
     * @param webIdeUser the webIdeUser
     * @return ModelAndView model
     */
    public String deleteUser(WebIdeUser webIdeUser) {
        String resultStr = Constants.RESULT_STATUS_SUCCESS;

        EntityManager portalEm = portalConfig.portalEntityManager().getNativeEntityManagerFactory().createEntityManager();

        try {
            if(null == webIdeUser.getOrgName() || "".equals(webIdeUser.getOrgName()))
                throw new NullPointerException();

            CriteriaBuilder cb = portalEm.getCriteriaBuilder();
            CriteriaDelete<WebIdeUser> delete = cb.createCriteriaDelete(WebIdeUser.class);
            Root e = delete.from(WebIdeUser.class);

            delete.where(cb.greaterThanOrEqualTo(e.get("orgName"), (String) webIdeUser.getOrgName()));

            portalEm.getTransaction().begin();
            int deleteCnt = portalEm.createQuery(delete).executeUpdate();
            portalEm.getTransaction().commit();

            if(deleteCnt == 0)
                throw new NullPointerException();

        } catch (DataAccessException ex) {
            logger.error("Exception :: DataAccessException :: {}", ex.getCause().getMessage());
            resultStr = Constants.RESULT_STATUS_FAIL;
        } catch (NullPointerException nex) {
            logger.error("Exception :: NullPointerException :: {}", nex.getCause().getMessage());
            resultStr = Constants.RESULT_STATUS_FAIL;
        } finally {
            portalEm.close();
        }

        return resultStr;
    }
}
