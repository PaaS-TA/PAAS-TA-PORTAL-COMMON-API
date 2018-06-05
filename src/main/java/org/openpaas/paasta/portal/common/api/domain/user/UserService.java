package org.openpaas.paasta.portal.common.api.domain.user;


import org.apache.commons.lang.RandomStringUtils;
import org.openpaas.paasta.portal.common.api.config.Constants;
import org.openpaas.paasta.portal.common.api.config.JinqSource;
import org.openpaas.paasta.portal.common.api.config.dataSource.PortalConfig;
import org.openpaas.paasta.portal.common.api.config.dataSource.UaaConfig;
import org.openpaas.paasta.portal.common.api.domain.common.CommonService;
import org.openpaas.paasta.portal.common.api.domain.email.EmailService;
import org.openpaas.paasta.portal.common.api.entity.portal.UserDetail;
import org.openpaas.paasta.portal.common.api.entity.uaa.Users;
import org.openpaas.paasta.portal.common.api.repository.portal.UserDetailRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.OrderBy;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by SEJI on 2018-02-20.
 */
@Service
public class UserService {

    private final Logger logger = getLogger(this.getClass());

    @Autowired
    PortalConfig portalConfig;

    @Autowired
    UaaConfig uaaConfig;

    @Autowired
    UserDetailRepository userDetailRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    JinqSource jinqSource;

    @Autowired
    CommonService commonService;

    /**
     * portal db에 등록된 UserDetail 수
     *
     * @return int user count
     */
    public int getUserCount() {
        //JpaRepository를 상속하는 인터페이스를 생성하는것 만으로 count기능을 바로 사용
        int userCnt = (int) userDetailRepository.count();
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
        return userDetail;
    }


    /**
     * 사용자 상세 정보
     *
     * @return UserDetail user
     */
    public List<UserDetail> getUsers() {
        List<UserDetail> userDetails = userDetailRepository.findAllByOrderByUserIdAsc();
        return userDetails;
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
        cq.multiselect(from.get("userId").alias("userId"), from.get("status").alias("status"), from.get("tellPhone").alias("tellPhone"), from.get("zipCode").alias("zipCode"), from.get("address").alias("address"), from.get("addressDetail").alias("addressDetail"), from.get("userName").alias("userName"), from.get("adminYn").alias("adminYn"), from.get("refreshToken").alias("refreshToken"), from.get("authAccessTime").alias("authAccessTime"), from.get("authAccessCnt").alias("authAccessCnt"));


        Predicate predicate = cb.conjunction();

        //SQL:WHERE
        if (null != map.get("userId") && !("").equals(map.get("userId").toString())) {
            predicate = cb.and(predicate, cb.equal(from.get("userId"), map.get("userId").toString()));
        }
        if (null != map.get("status") && !("").equals(map.get("status").toString())) {
            predicate = cb.and(predicate, cb.equal(from.get("status"), map.get("status").toString()));
        }
        if (null != map.get("tellPhone") && !("").equals(map.get("tellPhone").toString())) {
            predicate = cb.and(predicate, cb.equal(from.get("tellPhone"), map.get("tellPhone").toString()));
        }
        if (null != map.get("zipCode") && !("").equals(map.get("zipCode").toString())) {
            predicate = cb.and(predicate, cb.equal(from.get("zipCode"), map.get("zipCode").toString()));
        }
        if (null != map.get("address") && !("").equals(map.get("address").toString())) {
            predicate = cb.and(predicate, cb.equal(from.get("address"), map.get("address").toString()));
        }
        if (null != map.get("addressDetail") && !("").equals(map.get("addressDetail").toString())) {
            predicate = cb.and(predicate, cb.equal(from.get("addressDetail"), map.get("addressDetail").toString()));
        }
        if (null != map.get("userName") && !("").equals(map.get("userName").toString())) {
            predicate = cb.and(predicate, cb.equal(from.get("userName"), map.get("userName").toString()));
        }
        if (null != map.get("adminYn") && !("").equals(map.get("adminYn").toString())) {
            predicate = cb.and(predicate, cb.equal(from.get("adminYn"), map.get("adminYn").toString()));
        }
        if (null != map.get("refreshToken") && !("").equals(map.get("refreshToken").toString())) {
            predicate = cb.and(predicate, cb.equal(from.get("refreshToken"), map.get("refreshToken").toString()));
        }

        cq.where(predicate);
        cq.orderBy(cb.asc(from.get("userId")));

        TypedQuery<Tuple> tq = portalEm.createQuery(cq);
        List<Tuple> resultList = tq.getResultList();


        return resultList.stream().map(x -> new HashMap<String, Object>() {{
            put("userId", x.get("userId"));
            put("status", x.get("status"));
            put("tellPhone", x.get("tellPhone"));
            put("zipCode", x.get("zipCode"));
            put("address", x.get("address"));
            put("userName", x.get("userName"));
            put("adminYn", x.get("adminYn"));
            put("refreshToken", x.get("refreshToken"));
        }}).collect(Collectors.toList());
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
        if (resultCnt > 0) {
            userDetailRepository.save(userDetail);
        }
        return resultCnt;
    }


    /**
     * 사용자 자동생성
     *
     * @param userDetail the user detail
     * @return int int
     */
    public int createUser(UserDetail userDetail) {
        int createResult = 1;
        if (createResult > 0) {
            userDetailRepository.save(userDetail);
        }
        return createResult;
    }


    /**
     * 사용자 정보 인증
     * potalDB에 사용자 정보를 등록한후 이메일을 보낸다.
     *
     * @param body the body
     * @return boolean
     * @throws IOException the io exception
     */

    public Map createRequestUser(Map body) {
        Map map = new HashMap();
        try {
            String randomId = RandomStringUtils.randomAlphanumeric(17).toUpperCase() + RandomStringUtils.randomAlphanumeric(2).toUpperCase();
            UserDetail userDetail = new UserDetail();
            userDetail.setUserId(body.get("userid").toString());
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, 1);
            userDetail.setAuthAccessTime(cal.getTime());
            userDetail.setAuthAccessCnt(0);
            userDetail.setAdminYn("N");
            userDetail.setStatus("1");
            userDetail.setRefreshToken(randomId);
            /*
             * 여기서 에러나면 Exception으로 빠져버림
             */
            createUser(userDetail);
            map = emailService.createEmail(userDetail.getUserId(), randomId);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("result", false);
            map.put("msg", e.getMessage());
        }
        return map;

    }

    /**
     * 사용자 정보 인증
     * potalDB에 사용자 정보를 등록한후 이메일을 보낸다.
     *
     * @return boolean
     * @throws IOException the io exception
     */

    public Map resetRequestUser(Map body) {
        Map map = new HashMap();
        try {
            String randomId = RandomStringUtils.randomAlphanumeric(17).toUpperCase() + RandomStringUtils.randomAlphanumeric(2).toUpperCase();
            UserDetail userDetail = getUser(body.get("userid").toString());
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, 1);
            userDetail.setAuthAccessTime(cal.getTime());
            userDetail.setAuthAccessCnt(0);
            userDetail.setStatus("1");
            userDetail.setRefreshToken(randomId);
            /*
             * 여기서 에러나면 Exception으로 빠져버림
             */
            createUser(userDetail);
            map = emailService.resetEmail(userDetail.getUserId(), randomId);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("result", false);
            map.put("msg", e.getMessage());
        }
        return map;

    }


    /**
     * DB에서 사용자 삭제
     *
     * @param userId the user id
     * @return 삭제 정보
     */
    public Map deleteUser(String userId) {
        Map map = new HashMap();
        try {
            int deleteResult = userDetailRepository.deleteByUserId(userId);
            map.put("result", true);
            map.put("msg", "You have successfully completed the task.");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("result", false);
            map.put("msg", e.getMessage());
        }
        return map;
    }

    /**
     * DB에서 사용자 삭제 후 CF인프라에서도 사용자 삭제
     *
     * @return 삭제 정보
     */
    public Map deleteUserInfra(String guid, UserDetail userDetail, String token) {

        logger.info("userId ::::: " +userDetail.getUserId());
        Map map = new HashMap();
        try {
            Map result;
            result = commonService.procCfApiRestTemplate("/users/" + guid, HttpMethod.GET, null, token);
            Map entity = (Map) result.get("entity");

            if (entity.get("username").equals(userDetail.getUserId())) {
                result = commonService.procCfApiRestTemplate("/users/" + guid, HttpMethod.DELETE, null, token);
                logger.info("result " + result.toString());
                if (result.get("result").toString().equals("true")) {
                    userDetailRepository.deleteByUserId(userDetail.getUserId());
                    map.put("result", true);
                    map.put("msg", "You have successfully completed the task.");
                } else {
                    map.put("result", false);
                    map.put("msg", "");
                }
            }else{
                map.put("result", false);
                map.put("msg","Invalid parameter.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("result", false);
            map.put("msg", e.getMessage());
        }
        return map;


    }


    /**
     * 전체 UAA 유저의 userName과 userGuid를 가져온다.
     *
     * @return userInfo list
     */
    public UserDetail getRefreshTokenUser(UserDetail userDetail) {
        logger.info("getRefreshTokenUser :: " + userDetail.toString());
        UserDetail data = userDetailRepository.findByUserIdAndRefreshToken(userDetail.getUserId(), userDetail.getRefreshToken());
        return data;
    }

    /**
     * 전체 UAA 유저의 userName과 userGuid를 가져온다.
     *
     * @return All Users List( GUID, userName )
     */
    public List<Map<String, Object>> getUserInfo() {

        EntityManager portalEm = uaaConfig.uaaEntityManager().getNativeEntityManagerFactory().createEntityManager();

        CriteriaBuilder cb = portalEm.getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = cb.createTupleQuery();
        Root<Users> from = cq.from(Users.class);

        //SQL:Select
        cq.multiselect(from.get("userName").alias("userName"), from.get("id").alias("id"));

        TypedQuery<Tuple> tq = portalEm.createQuery(cq);
        List<Tuple> resultList = tq.getResultList();


        List<Map<String, Object>> userInfo = resultList.stream().map(x -> new HashMap<String, Object>() {{
            put("userName", x.get("userName"));
            put("userGuid", x.get("id"));
        }}).collect(Collectors.toList());

        return userInfo;
    }
}



