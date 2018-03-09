package org.openpaas.paasta.portal.common.api.domain.userManagement;

import org.openpaas.paasta.portal.common.api.config.Constants;
import org.openpaas.paasta.portal.common.api.config.dataSource.PortalConfig;
import org.openpaas.paasta.portal.common.api.entity.portal.UserDetail;
import org.openpaas.paasta.portal.common.api.repository.portal.UserDetailRepository;
import org.openpaas.paasta.portal.common.api.repository.uaa.UsersRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.HashMap;
import java.util.Map;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by SEJI on 2018-03-08.
 */
@Transactional
@Service
public class UserManagementService {
    private final Logger logger = getLogger(this.getClass());

    @Autowired
    PortalConfig portalConfig;

    @Autowired
    UserDetailRepository userDetailRepository;

    @Autowired
    UsersRepository usersRepository;

    /**
     * 사용자 정보 목록을 조회한다.
     *
     * @param param UserManagement(모델클래스)
     * @return Map(자바클래스)
     */
    public Map<String, Object> getUserInfoList(HashMap param) {
        int pageNo = Constants.PAGE_NO;
        float pageSize = Constants.PAGE_SIZE;

        HashMap<String, Object> resultMap = new HashMap<>();
        return resultMap;

    }

    /**사용자에게 운영자 권한을 부여한다.*/
    public String updateOperatingAuthority(HashMap param) {

        String resultStr = Constants.RESULT_STATUS_SUCCESS;

        EntityManager portalEm = portalConfig.portalEntityManager().getNativeEntityManagerFactory().createEntityManager();

        try{
            CriteriaBuilder cb = portalEm.getCriteriaBuilder();
            CriteriaUpdate<UserDetail> update = cb.createCriteriaUpdate(UserDetail.class);
            Root e = update.from(UserDetail.class);

            if(null != param.get("status") && !("").equals(param.get("status").toString())) {
                update.set("status", param.get("status"));
            }
            if(null != param.get("tellPhone") && !("").equals(param.get("tellPhone").toString())) {
                update.set("tellPhone", param.get("tellPhone"));
            }
            if(null != param.get("zipCode") && !("").equals(param.get("zipCode").toString())) {
                update.set("zipCode", param.get("zipCode"));
            }
            if(null != param.get("address") && !("").equals(param.get("address").toString())) {
                update.set("address", param.get("address"));
            }
            if(null != param.get("addressDetail") && !("").equals(param.get("addressDetail").toString())) {
                update.set("addressDetail", param.get("addressDetail"));
            }
            if(null != param.get("userName") && !("").equals(param.get("userName").toString())) {
                update.set("userName", param.get("userName"));
            }
            if(null != param.get("adminYn") && !("").equals(param.get("adminYn").toString())) {
                update.set("adminYn", param.get("adminYn"));
            }

            Predicate predicate = cb.conjunction();

            //SQL:WHERE
            if(null != param.get("userId") && !("").equals(param.get("userId").toString())) {
                predicate = cb.and(predicate, cb.equal(e.get("userId"), param.get("userId").toString()));
            }
            update.where(predicate);
            portalEm.getTransaction().begin();
            portalEm.getTransaction().commit();

        }catch (NullPointerException nex) {
            logger.error("Exception :: NullPointerException :: {}", nex.getCause().getMessage());
            resultStr = Constants.RESULT_STATUS_FAIL;
        }finally {
            portalEm.close();
        }
        return  resultStr;
    }

    /**사용자를 삭제한다.*/
    public Map<String, Object> deleteUserAccount(HashMap map)  {

        String userId = new String();
        String resultUserGuid = new String();

        map.put("userId", userId);
        map.put("resultUserGuid", resultUserGuid);

        userDetailRepository.deleteByUserId(userId);

        return new HashMap<String, Object>() {{
            put("RESULT", Constants.RESULT_STATUS_SUCCESS);
        }};
    }

}

