package org.openpaas.paasta.portal.common.api.domain.user;

import org.openpaas.paasta.portal.common.api.config.dataSource.PortalConfig;
import org.openpaas.paasta.portal.common.api.entity.portal.UserDetail;
import org.openpaas.paasta.portal.common.api.repository.portal.UserDetailRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
 * Created by SEJI on 2018-02-20.
 */
@Service
public class UserService {

    private final Logger LOGGER = getLogger(this.getClass());

    @Autowired
    PortalConfig portalConfig;

    @Autowired
    UserDetailRepository userDetailRepository;

    @Autowired
    private UserService userService;


    /**
     * portal db에 등록된 UserDetail 수
     *
     * @return int user count
     */
    public int getUserCount() {
        //JpaRepository를 상속하는 인터페이스를 생성하는것 만으로 count기능을 바로 사용
        int userCnt = (int) userDetailRepository.count();
        System.out.println(userCnt);
        return userCnt;
    }

    /**
     * 사용자 상세 정보
     *
     * @param userId the user id
     * @return UserDetail user
     */
    public UserDetail getUser(String userId) {
        UserDetail userDetail = userDetailRepository.findByUserId(userId);
        System.out.println(userDetail);
        return userDetail;
    }

    /**
     * 사용자의 상세정보를 조회한다.
     *
     * @param map the map
     * @return List<UserDetail> user detail info
     * @query문 :<select id="getUserDetailInfo" resultType="org.openpaas.paasta.portal.api.model.UserDetail" parameterType="map">
     */
    public List<Map<String, Object>> getUserDetailInfo(HashMap map) {

        EntityManager portalEm = portalConfig.portalEntityManager().getNativeEntityManagerFactory().createEntityManager();

        //CriteriaBuilder 인스턴스를 작성한다.
        CriteriaBuilder cb = portalEm.getCriteriaBuilder();
        //CriteriaQuery 인스턴스를 생성한다.
        CriteriaQuery<Tuple> cq = cb.createTupleQuery();
        //Root는 영속적 엔티티를 표시하는 쿼리 표현식이다. SQL의 FROM 과 유사함
        Root<UserDetail> from = cq.from(UserDetail.class);

        //SQL:Select
        //alias 튜플 요소에 할당 된 별칭
        cq.multiselect(from.get("userId").alias("userId")
                , from.get("status").alias("status")
                , from.get("tellPhone").alias("tellPhone")
                , from.get("zipCode").alias("zipCode")
                , from.get("address").alias("address")
                , from.get("addressDetail").alias("addressDetail")
                , from.get("userName").alias("userName")
                , from.get("adminYn").alias("adminYn")
                , from.get("refreshToken").alias("refreshToken")
                , from.get("authAccessTime").alias("authAccessTime")
                , from.get("authAccessCnt").alias("authAccessCnt"));

        Predicate predicate = cb.conjunction();

        //SQL:WHERE
        if(null != map.get("userId") && !("").equals(map.get("userId").toString())) {
            predicate = cb.and(predicate, cb.equal(from.get("userId"), map.get("userId").toString()));
        }
        if(null != map.get("status") && !("").equals(map.get("status").toString())) {
            predicate = cb.and(predicate, cb.equal(from.get("status"), map.get("status").toString()));
        }
        if(null != map.get("tellPhone") && !("").equals(map.get("tellPhone").toString())) {
            predicate = cb.and(predicate, cb.equal(from.get("tellPhone"), map.get("tellPhone").toString()));
        }
        if(null != map.get("zipCode") && !("").equals(map.get("zipCode").toString())) {
            predicate = cb.and(predicate, cb.equal(from.get("zipCode"), map.get("zipCode").toString()));
        }
        if(null != map.get("address") && !("").equals(map.get("address").toString())) {
            predicate = cb.and(predicate, cb.equal(from.get("address"), map.get("address").toString()));
        }
        if(null != map.get("addressDetail") && !("").equals(map.get("addressDetail").toString())) {
            predicate = cb.and(predicate, cb.equal(from.get("addressDetail"), map.get("addressDetail").toString()));
        }
        if(null != map.get("userName") && !("").equals(map.get("userName").toString())) {
            predicate = cb.and(predicate, cb.equal(from.get("userName"), map.get("userName").toString()));
        }
        if(null != map.get("adminYn") && !("").equals(map.get("adminYn").toString())) {
            predicate = cb.and(predicate, cb.equal(from.get("adminYn"), map.get("adminYn").toString()));
        }
        if(null != map.get("refreshToken") && !("").equals(map.get("refreshToken").toString())) {
            predicate = cb.and(predicate, cb.equal(from.get("refreshToken"), map.get("refreshToken").toString()));
        }

        cq.where(predicate);

        TypedQuery<Tuple> tq = portalEm.createQuery(cq);
        List<Tuple> resultList = tq.getResultList();

        List<Map<String, Object>> resultList2 = resultList.stream().map(x -> new HashMap<String, Object>(){{
            put("userId", x.get("userId"));
            put("status", x.get("status"));
            put("tellPhone", x.get("tellPhone"));
            put("zipCode", x.get("zipCode"));
            put("address", x.get("address"));
            put("userName", x.get("userName"));
            put("adminYn", x.get("adminYn"));
            put("refreshToken", x.get("refreshToken"));
        }}).collect(Collectors.toList());

        return resultList2;
    }

    /**
     * 사용자 상세화면에서
     * 사용자 정보 수정
     *
     * @param userId     the user id
     * @param userDetail the user detail
     * @return Int updateCount
     */
    public int updateUser(String userId, UserDetail userDetail) {

        int resultCnt = userDetailRepository.countByUserId(userId);
            if(resultCnt > 0) {
                userDetailRepository.save(userDetail);
            }

        return resultCnt;
    }

}
