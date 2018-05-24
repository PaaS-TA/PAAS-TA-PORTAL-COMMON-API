package org.openpaas.paasta.portal.common.api.domain.user;


import org.apache.commons.lang.RandomStringUtils;
import org.openpaas.paasta.portal.common.api.config.Constants;
import org.openpaas.paasta.portal.common.api.config.JinqSource;
import org.openpaas.paasta.portal.common.api.config.dataSource.PortalConfig;
import org.openpaas.paasta.portal.common.api.config.dataSource.UaaConfig;
import org.openpaas.paasta.portal.common.api.domain.email.EmailService;
import org.openpaas.paasta.portal.common.api.entity.portal.UserDetail;
import org.openpaas.paasta.portal.common.api.repository.portal.UserDetailRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
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
        List<UserDetail> userDetails = userDetailRepository.findAll();
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

    /**
     * 이메일 인증된 사용자의 추가 정보를 저장한다.
     *
     * @param map (이름, 비밀번호)
     * @return boolean
     */
    public boolean authAddUser(HashMap map) {
        Boolean bRtn = false;
        String resultStr = Constants.RESULT_STATUS_SUCCESS;

        EntityManager portalEm = portalConfig.portalEntityManager().getNativeEntityManagerFactory().createEntityManager();
        Date d = new Date();

        try {
            UserDetail updateUser = userDetailRepository.findByUserId(map.get("searchUserId").toString());
            String status = updateUser.getStatus();

            CriteriaBuilder cb = portalEm.getCriteriaBuilder();
            CriteriaUpdate<UserDetail> update = cb.createCriteriaUpdate(UserDetail.class);
            Root e = update.from(UserDetail.class);

            update.set("updatedAt", d);

            if(null != map.get("userId") && !("").equals(map.get("userId").toString())) {
                update.set("userId", map.get("userId"));
            }
            if(null != map.get("username") && !("").equals(map.get("username").toString())) {
                update.set("username", map.get("username"));
            }
            if(null != map.get("tellPhone") && !("").equals(map.get("tellPhone").toString())) {
                update.set("tellPhone", map.get("tellPhone"));
            }
            if(null != map.get("zipCode") && !("").equals(map.get("zipCode").toString())) {
                update.set("zipCode", map.get("zipCode"));
            }
            if(null != map.get("address") && !("").equals(map.get("address").toString())) {
                update.set("address", map.get("address"));

            } if(null != map.get("addressDetail") && !("").equals(map.get("addressDetail").toString())) {
                update.set("addressDetail", map.get("addressDetail"));

            } if(null != map.get("adminYn") && !("").equals(map.get("adminYn").toString())) {
                update.set("adminYn", map.get("adminYn"));

            } if(null != map.get("refreshToken") && !("").equals(map.get("refreshToken").toString())) {
                update.set("refreshToken", map.get("refreshToken"));

            } if(null != map.get("authAccessTime") && !("").equals(map.get("authAccessTime").toString())) {
                update.set("authAccessTime", map.get("authAccessTime"));

            } if(null != map.get("authAccessCnt") && !("").equals(map.get("authAccessCnt").toString())) {
                update.set("authAccessCnt", map.get("authAccessCnt"));
            }

            Predicate predicate = cb.conjunction();

            //SQL:WHERE
            if(null != map.get("searchUserId") && !("").equals(map.get("searchUserId").toString())) {
                predicate = cb.and(predicate, cb.equal(e.get("searchUserId"), map.get("searchUserId").toString()));
            }
            update.where(predicate);

            portalEm.getTransaction().begin();
            int rtn = portalEm.createQuery(update).executeUpdate();
            portalEm.getTransaction().commit();

            if (rtn < 1) {
                bRtn = true;
            } throw new NullPointerException();

        } catch (NullPointerException nex) {
            logger.error("Exception :: NullPointerException :: {}", nex.getCause().getMessage());
            resultStr = Constants.RESULT_STATUS_FAIL;
        } finally {
            portalEm.close();
        }
        return bRtn;

    }

    /**
     * 사용자 자동생성
     *
     * @param userDetail the user detail
     * @return int int
     */
    public int createUser(UserDetail userDetail) {
        int createResult = 1;
        if(createResult > 0){
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
     * @throws IOException        the io exception
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
            logger.info("save UserInfo ::::::::::::::::::::::::::");
            createUser(userDetail);
            logger.info("send Email :::::::::::::::::::::::::::: ");
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
     * @throws IOException        the io exception

     */

    public Map resetRequestUser(Map body) {
        Map map = new HashMap();
        try {
            String randomId = RandomStringUtils.randomAlphanumeric(17).toUpperCase() + RandomStringUtils.randomAlphanumeric(2).toUpperCase();
            UserDetail userDetail =  getUser(body.get("userid").toString());
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, 1);
            userDetail.setAuthAccessTime(cal.getTime());
            userDetail.setAuthAccessCnt(0);
            userDetail.setStatus("1");
            userDetail.setRefreshToken(randomId);
            /*
             * 여기서 에러나면 Exception으로 빠져버림
             */
            logger.info("reset save UserInfo ::::::::::::::::::::::::::");
            createUser(userDetail);
            logger.info("reset send Email :::::::::::::::::::::::::::: ");
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
    public int deleteUser(String userId) {
        int deleteResult = userDetailRepository.deleteByUserId(userId);
        return deleteResult;
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

}



